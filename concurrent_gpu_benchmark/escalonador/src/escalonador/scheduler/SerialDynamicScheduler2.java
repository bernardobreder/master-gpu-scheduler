package escalonador.scheduler;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import escalonador.util.FileWriter;
import escalonador.util.Array4D;

public class SerialDynamicScheduler2 extends AbstractSerialScheduler {

	public SerialDynamicScheduler2(Resource kernelLimit, Resource kernelUnit, List<Kernel> kernelList, int spCount, int streamLimit) throws IOException {
		//
		List<List<Entry<Integer, Kernel>>> rounds = processRounds(kernelLimit, kernelUnit, kernelList, spCount);
		List<Resource> resources = processResources(kernelLimit, rounds);
		//
		List<Kernel> kernels = new ArrayList<>();
		List<Kernel> kernelsNotExecuted = new ArrayList<>(kernelList);
		for (List<Entry<Integer, Kernel>> entryList : rounds) {
			for (Entry<Integer, Kernel> entry : entryList) {
				kernels.add(entry.getValue());
			}
		}
		kernelsNotExecuted.removeAll(kernels);
		//
		for (int n = 0; n < Math.max(kernels.size(), spCount); n++) {
			streams.addLast(numberOfStream++);
		}
		List<Entry<Integer, Kernel>> initialKernels = new ArrayList<>();
		for (Kernel kernel : kernels) {
			initialKernels.add(new SimpleEntry<>(streams.removeFirst(), kernel));
		}
		List<List<Entry<Integer, Kernel>>> roundsStep = new ArrayList<>();
		while (!isEmpty(rounds)) {
			Entry<Integer, Integer> entrySpPosition = findFast(rounds);
			Entry<Integer, Kernel> entryStreamKernel = rounds.get(entrySpPosition.getKey()).get(entrySpPosition.getValue());
			Kernel kernel = entryStreamKernel.getValue();
			rounds.get(entrySpPosition.getKey()).remove(entrySpPosition.getValue().intValue());
			streams.addLast(entryStreamKernel.getKey());
			Resource resource = resources.get(entrySpPosition.getKey());
			resource.register += kernel.getRegister();
			resource.sharedMemory += kernel.getSharedMemory();
			resource.threads += kernel.getThreads();
			List<Kernel> newKernels = processRound(resource, kernelUnit, kernelsNotExecuted);
			kernelsNotExecuted.removeAll(newKernels);
			List<Entry<Integer, Kernel>> entrys = new ArrayList<>();
			for (int i = 0; i < newKernels.size(); i++) {
				Kernel newKernel = newKernels.get(i);
				resource.register -= newKernel.getRegister();
				resource.sharedMemory -= newKernel.getSharedMemory();
				resource.threads -= newKernel.getThreads();
				if (streams.isEmpty()) {
					streams.addLast(numberOfStream++);
				}
				Entry<Integer, Kernel> entry = new SimpleEntry<>(streams.removeFirst(), newKernel);
				rounds.get(entrySpPosition.getKey()).add(entry);
				entrys.add(entry);
			}
			if (!entrys.isEmpty()) {
				roundsStep.add(entrys);
			}
		}
		this.initialKernels = initialKernels;
		this.roundsStep = roundsStep;
	}

	protected boolean isEmpty(List<List<Entry<Integer, Kernel>>> rounds) {
		for (List<Entry<Integer, Kernel>> kernelList : rounds) {
			if (!kernelList.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	protected Entry<Integer, Integer> findFast(List<List<Entry<Integer, Kernel>>> rounds) {
		int time = -1;
		int streamProcessorIndex = 0;
		int kernelIndex = 0;
		for (int i = 0; i < rounds.size(); i++) {
			List<Entry<Integer, Kernel>> kernelList = rounds.get(i);
			for (int j = 0; j < kernelList.size(); j++) {
				Kernel kernel = kernelList.get(j).getValue();
				if (time < 0 || kernel.getTime() < time) {
					streamProcessorIndex = i;
					kernelIndex = j;
					time = kernel.getTime();
				}
			}
		}
		return new SimpleEntry<>(streamProcessorIndex, kernelIndex);
	}

	public List<Resource> processResources(Resource kernelLimit, List<List<Entry<Integer, Kernel>>> rounds) {
		List<Resource> resources = new ArrayList<>();
		for (int i = 0; i < rounds.size(); i++) {
			Resource resource = new Resource(kernelLimit.register, kernelLimit.sharedMemory, kernelLimit.threads);
			for (Entry<Integer, Kernel> entry : rounds.get(i)) {
				Kernel kernel = entry.getValue();
				resource.register -= kernel.getRegister();
				resource.sharedMemory -= kernel.getSharedMemory();
				resource.threads = kernel.getThreads();
			}
			resources.add(resource);
		}
		return resources;
	}

	public List<List<Entry<Integer, Kernel>>> processRounds(Resource kernelLimit, Resource kernelUnit, List<Kernel> kernelList, int spCount) throws IOException {
		List<List<Entry<Integer, Kernel>>> rounds = new ArrayList<>();
		List<Kernel> kernels = new ArrayList<>(kernelList);
		try (FileWriter file = new FileWriter("dynamic-serial-schedule-rounds-2.txt")) {
			AtomicInteger count = new AtomicInteger(0);
			for (int n = 0; n < spCount && !kernels.isEmpty(); n++) {
				file.println("Round: " + (rounds.size() + 1));
				List<Kernel> round = processRound(kernelLimit, kernelUnit, kernels);
				List<Entry<Integer, Kernel>> entryList = new ArrayList<>();
				file.println("StreamProcessor: " + n);
				file.println("Kernels Selecionados:");
				for (Kernel kernel : round) {
					int sIndex = count.getAndIncrement();
					file.println("\t" + kernel.toString());
					entryList.add(new SimpleEntry<>(sIndex, kernel));
				}
				rounds.add(entryList);
				kernels.removeAll(round);
				int regUsing = 0, regHas = kernelLimit.register;
				int smUsing = 0, smHas = kernelLimit.sharedMemory;
				int threadUsing = 0, threadHas = kernelLimit.threads;
				for (Kernel kernel : round) {
					regHas -= kernel.getRegister();
					regUsing += kernel.getRegister();
					smHas -= kernel.getSharedMemory();
					smUsing += kernel.getSharedMemory();
					threadHas -= kernel.getThreads();
					threadUsing += kernel.getThreads();
				}
				file.println("Recursos Usados: " + new Resource(regUsing, smUsing, threadUsing));
				file.println("Recursos Disponíveis: " + new Resource(regHas, smHas, threadHas));
				file.println("Kernels que sobraram:");
				for (Kernel kernel : kernels) {
					file.println("\t" + kernel.toString());
				}
				file.println("---------------------------------------------");
			}
		}
		return rounds;
	}

	public List<Kernel> processRound(Resource kernelGlobal, Resource kernelUnit, List<Kernel> kernels) {
		Array4D map = new Array4D(kernels.size() + 1, kernelGlobal.register + 1, kernelGlobal.sharedMemory / kernelUnit.sharedMemory + 1, kernelGlobal.threads / kernelUnit.threads + 1);
		for (int k = 1; k <= kernels.size(); k++) {
			Kernel kernel = kernels.get(k - 1);
			for (int reg = kernelUnit.register; reg <= kernelGlobal.register; reg += kernelUnit.register) {
				for (int sm = kernelUnit.sharedMemory; sm <= kernelGlobal.sharedMemory; sm += kernelUnit.sharedMemory) {
					for (int th = kernelUnit.threads; th <= kernelGlobal.threads; th += kernelUnit.threads) {
						int regDelta = reg - kernel.getRegister();
						int smDelta = sm - kernel.getSharedMemory();
						int thDelta = th - kernel.getThreads();
						double withoutValue = map.get(k - 1, reg / kernelUnit.register, sm / kernelUnit.sharedMemory, th / kernelUnit.threads);
						if (regDelta < 0 || smDelta < 0 || thDelta < 0) {
							map.set(k, reg / kernelUnit.register, sm / kernelUnit.sharedMemory, th / kernelUnit.threads, withoutValue);
						} else {
							double withValue = map.get(k - 1, regDelta / kernelUnit.register, smDelta / kernelUnit.sharedMemory, thDelta / kernelUnit.threads);
							map.set(k, reg / kernelUnit.register, sm / kernelUnit.sharedMemory, th / kernelUnit.threads, Math.max(withoutValue, withValue + kernel.getValue()));
						}
					}
				}
			}
		}
		List<Kernel> round = new ArrayList<Kernel>();
		{
			int regHas = kernelGlobal.register;
			int smHas = kernelGlobal.sharedMemory;
			int threadHas = kernelGlobal.threads;
			for (int i = kernels.size(); i >= 1; i--) {
				double left = map.get(i, regHas / kernelUnit.register, smHas / kernelUnit.sharedMemory, threadHas / kernelUnit.threads);
				double right = map.get(i - 1, regHas / kernelUnit.register, smHas / kernelUnit.sharedMemory, threadHas / kernelUnit.threads);
				if (left != right) {
					Kernel kernel = kernels.get(i - 1);
					regHas -= kernel.getRegister();
					smHas -= kernel.getSharedMemory();
					threadHas -= kernel.getThreads();
					round.add(kernel);
				}
			}
		}
		return round;
	}

}
