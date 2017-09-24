package escalonador.scheduler;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import escalonador.util.FileWriter;
import escalonador.util.Matrix4D;

public class SerialDynamicScheduler {

	private final List<Entry<Integer, List<Entry<Integer, Kernel>>>> roundsStep;

	private final List<Entry<Integer, Kernel>> initialKernels;

	private final LinkedList<Integer> streams = new LinkedList<>();

	public SerialDynamicScheduler(GlobalKernel kernelLimit, GlobalKernel kernelUnit, List<Kernel> kernelList, int spCount, int streamLimit) throws IOException {
		//
		List<List<Entry<Integer, Kernel>>> rounds = processRounds(kernelLimit, kernelUnit, kernelList, spCount);
		List<GlobalKernel> resources = processResources(kernelLimit, rounds);
		//
		List<Kernel> kernelsNotExecuted = new ArrayList<>(kernelList);
		List<Kernel> kernels = rounds.stream().flatMap(e -> e.stream()).map(e -> e.getValue()).collect(Collectors.toList());
		kernelsNotExecuted.removeAll(kernels);
		//
		for (int n = 0; n < kernelList.size(); n++) {
			streams.addLast(n);
		}
		List<Entry<Integer, Kernel>> initialKernels = new ArrayList<>(kernels.stream().map(e -> new SimpleEntry<>(streams.removeFirst(), e)).collect(Collectors.toList()));
		List<Entry<Integer, List<Entry<Integer, Kernel>>>> roundsStep = new ArrayList<>();
		while (!isEmpty(rounds)) {
			Entry<Integer, Integer> entrySpPosition = findFast(rounds);
			Entry<Integer, Kernel> entryStreamKernel = rounds.get(entrySpPosition.getKey()).get(entrySpPosition.getValue());
			Kernel kernel = entryStreamKernel.getValue();
			rounds.get(entrySpPosition.getKey()).remove(entrySpPosition.getValue().intValue());
			streams.addLast(entryStreamKernel.getKey());
			GlobalKernel resource = resources.get(entrySpPosition.getKey());
			resource.register += kernel.register;
			resource.sharedMemory += kernel.sharedMemory;
			resource.threads += kernel.threads;
			List<Kernel> newKernels = processRound(resource, kernelUnit, kernelsNotExecuted);
			kernelsNotExecuted.removeAll(newKernels);
			List<Entry<Integer, Kernel>> entrys = new ArrayList<>();
			for (int i = 0; i < newKernels.size(); i++) {
				Kernel newKernel = newKernels.get(i);
				resource.register -= newKernel.register;
				resource.sharedMemory -= newKernel.sharedMemory;
				resource.threads -= newKernel.threads;
				Entry<Integer, Kernel> entry = new SimpleEntry<>(streams.removeFirst(), newKernel);
				rounds.get(entrySpPosition.getKey()).add(entry);
				entrys.add(entry);
			}
			if (!entrys.isEmpty()) {
				roundsStep.add(new SimpleEntry<>(entryStreamKernel.getKey(), entrys));
			}
		}
		for (int stream : streams) {
			roundsStep.add(new SimpleEntry<>(stream, new ArrayList<>()));
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
				if (time < 0 || kernel.time < time) {
					streamProcessorIndex = i;
					kernelIndex = j;
					time = kernel.time;
				}
			}
		}
		return new SimpleEntry<>(streamProcessorIndex, kernelIndex);
	}

	public List<GlobalKernel> processResources(GlobalKernel kernelLimit, List<List<Entry<Integer, Kernel>>> rounds) {
		List<GlobalKernel> resources = new ArrayList<>();
		for (int i = 0; i < rounds.size(); i++) {
			GlobalKernel resource = new GlobalKernel(1, kernelLimit.register, kernelLimit.sharedMemory, kernelLimit.threads);
			for (Entry<Integer, Kernel> entry : rounds.get(i)) {
				Kernel kernel = entry.getValue();
				resource.register -= kernel.register;
				resource.sharedMemory -= kernel.sharedMemory;
				resource.threads = kernel.threads;
			}
			resources.add(resource);
		}
		return resources;
	}

	public List<List<Entry<Integer, Kernel>>> processRounds(GlobalKernel kernelLimit, GlobalKernel kernelUnit, List<Kernel> kernelList, int spCount) throws IOException {
		List<List<Entry<Integer, Kernel>>> rounds = new ArrayList<>();
		List<Kernel> kernels = new ArrayList<>(kernelList);
		try (FileWriter file = new FileWriter("dynamic-serial-schedule-rounds.txt")) {
			AtomicInteger count = new AtomicInteger(0);
			for (int n = 0; n < spCount && !kernels.isEmpty(); n++) {
				file.println("Round: " + (rounds.size() + 1));
				file.println("Kernels Disponíveis:");
				for (Kernel kernel : kernels.stream().sorted((a, b) -> Double.compare(b.value, a.value)).collect(Collectors.toList())) {
					file.println("\t" + kernel.toString());
				}
				List<Kernel> round = processRound(kernelLimit, kernelUnit, kernels);
				rounds.add(round.stream().map(e -> new SimpleEntry<>(count.getAndIncrement(), e)).collect(Collectors.toList()));
				kernels.removeAll(round);
				int regUsing = 0, regHas = kernelLimit.register;
				int smUsing = 0, smHas = kernelLimit.sharedMemory;
				int threadUsing = 0, threadHas = kernelLimit.threads;
				for (Kernel kernel : round) {
					regHas -= kernel.register;
					regUsing += kernel.register;
					smHas -= kernel.sharedMemory;
					smUsing += kernel.sharedMemory;
					threadHas -= kernel.threads;
					threadUsing += kernel.threads;
				}
				file.println("Recursos Usados: " + new GlobalKernel(1, regUsing, smUsing, threadUsing));
				file.println("Recursos Disponíveis: " + new GlobalKernel(1, regHas, smHas, threadHas));
				file.println("Kernels Selecionados:");
				for (Kernel kernel : round.stream().sorted().collect(Collectors.toList())) {
					file.println("\t" + kernel.toString());
				}
				file.println();
			}
		}
		return rounds;
	}

	public List<Kernel> processRound(GlobalKernel kernelGlobal, GlobalKernel kernelUnit, List<Kernel> kernels) {
		Matrix4D map = new Matrix4D(kernels.size() + 1, kernelGlobal.register + 1, kernelGlobal.sharedMemory / kernelUnit.sharedMemory + 1, kernelGlobal.threads / kernelUnit.threads + 1);
		for (int k = 1; k <= kernels.size(); k++) {
			Kernel kernel = kernels.get(k - 1);
			for (int reg = kernelUnit.register; reg <= kernelGlobal.register; reg += kernelUnit.register) {
				for (int sm = kernelUnit.sharedMemory; sm <= kernelGlobal.sharedMemory; sm += kernelUnit.sharedMemory) {
					for (int th = kernelUnit.threads; th <= kernelGlobal.threads; th += kernelUnit.threads) {
						int regDelta = reg - kernel.register;
						int smDelta = sm - kernel.sharedMemory;
						int thDelta = th - kernel.threads;
						double withoutValue = map.get(k - 1, reg / kernelUnit.register, sm / kernelUnit.sharedMemory, th / kernelUnit.threads);
						if (regDelta < 0 || smDelta < 0 || thDelta < 0) {
							map.set(k, reg / kernelUnit.register, sm / kernelUnit.sharedMemory, th / kernelUnit.threads, withoutValue);
						} else {
							double withValue = map.get(k - 1, regDelta / kernelUnit.register, smDelta / kernelUnit.sharedMemory, thDelta / kernelUnit.threads);
							map.set(k, reg / kernelUnit.register, sm / kernelUnit.sharedMemory, th / kernelUnit.threads, Math.max(withoutValue, withValue + kernel.value));
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
					regHas -= kernel.register;
					smHas -= kernel.sharedMemory;
					threadHas -= kernel.threads;
					round.add(kernel);
				}
			}
		}
		return round;
	}

	/**
	 * Retorna
	 * 
	 * @return numberOfStream
	 */
	public int getNumberOfStream() {
		return streams.size();
	}

	/**
	 * Retorna
	 * 
	 * @return roundsStep
	 */
	public List<Entry<Integer, List<Entry<Integer, Kernel>>>> getRoundsStep() {
		return roundsStep;
	}

	/**
	 * Retorna
	 * 
	 * @return initialKernels
	 */
	public List<Entry<Integer, Kernel>> getInitialKernels() {
		return initialKernels;
	}
}
