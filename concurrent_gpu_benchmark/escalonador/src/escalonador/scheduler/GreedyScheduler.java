package escalonador.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import escalonador.util.FileWriter;

public class GreedyScheduler {

	private List<List<Kernel>> kernelRounds;

	private int numberOfStream;

	public GreedyScheduler(GlobalKernel kernelGlobal, List<Kernel> kernelList, int SPCount, int regUnit, int smUnit, int warpUnit, int threadUnit) throws IOException {
		List<Kernel> kernels = new ArrayList<>(kernelList);
		List<List<Kernel>> rounds = new ArrayList<>();
		try (FileWriter file = new FileWriter("greedy-schedule-rounds.txt")) {
			while (!kernels.isEmpty()) {
				file.println("Round: " + (rounds.size() + 1));
				file.println("Kernels Disponíveis:");
				for (Kernel kernel : kernels) {
					file.println("\t" + kernel.toString());
				}
				int regUsing = 0, regCapacity = kernelGlobal.register;
				int smUsing = 0, smCapacity = kernelGlobal.sharedMemory;
				int threadUsing = 0, threadCapacity = kernelGlobal.threads;
				List<Kernel> round = new ArrayList<Kernel>();
				for (int i = 0; i < kernels.size(); i++) {
					Kernel kernel = kernels.get(i);
					if (kernel.register <= regCapacity && kernel.sharedMemory <= smCapacity && kernel.threads <= threadCapacity) {
						round.add(kernel);
						kernels.remove(i--);
						regCapacity -= kernel.register;
						regUsing += kernel.register;
						smCapacity -= kernel.sharedMemory;
						smUsing += kernel.sharedMemory;
						threadCapacity -= kernel.threads;
						threadUsing += kernel.threads;
					}
				}
				file.println("Recursos Usados: " + new GlobalKernel(SPCount, regUsing, smUsing, threadUsing));
				file.println("Recursos Disponíveis: " + new GlobalKernel(SPCount, regCapacity, smCapacity, threadCapacity));
				file.println("Kernels Selecionados:");
				for (Kernel kernel : round.stream().sorted().collect(Collectors.toList())) {
					file.println("\t" + kernel.toString());
				}
				rounds.add(round);
				file.println();
			}
		}
		try (FileWriter file = new FileWriter("greedy-schedule-groups.txt")) {
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
