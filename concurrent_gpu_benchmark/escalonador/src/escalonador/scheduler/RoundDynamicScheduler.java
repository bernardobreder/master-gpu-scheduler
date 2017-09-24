package escalonador.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import escalonador.util.FileWriter;
import escalonador.util.Matrix4D;

public class RoundDynamicScheduler {

	private List<List<Kernel>> kernelRounds;

	private int numberOfStream;

	public RoundDynamicScheduler(GlobalKernel kernelGlobal, List<Kernel> kernelList, int SPCount, int regUnit, int smUnit, int warpUnit, int threadUnit) throws IOException {
		List<Kernel> kernels = new ArrayList<>(kernelList);
		List<List<Kernel>> rounds = new ArrayList<>();
		try (FileWriter file = new FileWriter("dynamic-schedule-rounds.txt")) {
			while (!kernels.isEmpty()) {
				file.println("Round: " + (rounds.size() + 1));
				file.println("Kernels Disponíveis:");
				for (Kernel kernel : kernels.stream().sorted((a, b) -> Double.compare(b.value, a.value)).collect(Collectors.toList())) {
					file.println("\t" + kernel.toString());
				}
				int regGlobal = kernelGlobal.register;
				int smGlobal = kernelGlobal.sharedMemory;
				int threadGlobal = kernelGlobal.threads;
				Matrix4D map = new Matrix4D(kernels.size() + 1, regGlobal + 1, smGlobal / smUnit + 1, threadGlobal / threadUnit + 1);
				for (int i = 1; i <= kernels.size(); i++) {
					Kernel kernel = kernels.get(i - 1);
					for (int wReg = regUnit; wReg <= regGlobal; wReg += regUnit) {
						for (int wSM = smUnit; wSM <= smGlobal; wSM += smUnit) {
							for (int wTh = threadUnit; wTh <= threadGlobal; wTh += threadUnit) {
								int registerDelta = wReg - kernel.register;
								int sharedMemoryDelta = wSM - kernel.sharedMemory;
								int threadDelta = wTh - kernel.threads;
								if (registerDelta < 0 || sharedMemoryDelta < 0 || threadDelta < 0) {
									map.set(i, wReg, wSM / smUnit, wTh / threadUnit, map.get(i - 1, wReg, wSM / smUnit, wTh / threadUnit));
								} else {
									map.set(i, wReg, wSM / smUnit, wTh / threadUnit, Math.max(map.get(i - 1, wReg, wSM / smUnit, wTh / threadUnit), map.get(i - 1, registerDelta, sharedMemoryDelta / smUnit, threadDelta / threadUnit) + kernel.value));
								}
							}
						}
					}
				}
				List<Kernel> round = new ArrayList<Kernel>();
				{
					int regUsing = 0, regCapacity = kernelGlobal.register;
					int smUsing = 0, smCapacity = kernelGlobal.sharedMemory;
					int threadUsing = 0, threadCapacity = kernelGlobal.threads;
					int time = Integer.MAX_VALUE;
					for (int i = kernels.size(); i >= 1; i--) {
						double left = map.get(i, regCapacity, smCapacity / smUnit, threadCapacity / threadUnit);
						double right = map.get(i - 1, regCapacity, smCapacity / smUnit, threadCapacity / threadUnit);
						if (left != right) {
							Kernel kernel = kernels.get(i - 1);
							round.add(kernel);
							regCapacity -= kernel.register;
							regUsing += kernel.register;
							smCapacity -= kernel.sharedMemory;
							smUsing += kernel.sharedMemory;
							threadCapacity -= kernel.threads;
							threadUsing += kernel.threads;
							time = Math.min(time, kernel.time);
						}
					}
					file.println("Recursos Usados: " + new GlobalKernel(SPCount, regUsing, smUsing, threadUsing));
					file.println("Recursos Disponíveis: " + new GlobalKernel(SPCount, regCapacity, smCapacity, threadCapacity));
				}
				file.println("Kernels Selecionados:");
				for (Kernel kernel : round.stream().sorted().collect(Collectors.toList())) {
					file.println("\t" + kernel.toString());
				}
				rounds.add(round);
				kernels.removeAll(round);
				file.println();
			}
		}
		try (FileWriter file = new FileWriter("dynamic-schedule-groups.txt")) {
			kernelRounds = new ArrayList<>();
			numberOfStream = 0;
			for (int i = 0; i < rounds.size(); i += SPCount) {
				List<List<Kernel>> spListList = new ArrayList<>();
				for (int j = i; j < i + SPCount && j < rounds.size(); j++) {
					List<Kernel> list = rounds.get(j);
					list.sort((a, b) -> b.sharedMemory - a.sharedMemory);
					spListList.add(list);
				}
				List<Kernel> spList = new ArrayList<>();
				boolean found;
				do {
					found = false;
					for (int j = 0; j < spListList.size(); j++) {
						List<Kernel> spListItem = spListList.get(j);
						if (!spListItem.isEmpty()) {
							found = true;
							spList.add(spListItem.remove(0));
						}
					}
				} while (found);
				numberOfStream = Math.max(numberOfStream, spList.size());
				kernelRounds.add(spList);
			}
			for (int i = 0; i < kernelRounds.size(); i++) {
				List<Kernel> kernelGroups = kernelRounds.get(i);
				file.println("Round Group " + (i + 1) + ":");
				for (Kernel kernel : kernelGroups) {
					file.println("\t" + kernel.toString());
				}
				file.println();
			}
		}
	}

	/**
	 * Retorna
	 * 
	 * @return kernelRounds
	 */
	public List<List<Kernel>> getKernelRounds() {
		return kernelRounds;
	}

	/**
	 * Retorna
	 * 
	 * @return numberOfStream
	 */
	public int getNumberOfStream() {
		return numberOfStream;
	}
}
