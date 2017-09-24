package escalonador.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import escalonador.util.FileWriter;

public class RandomScheduler {

	private List<List<Kernel>> kernelRounds;

	private int numberOfStream;

	public RandomScheduler(Resource kernelGlobal, List<Kernel> kernels, Random random) throws IOException {
		List<Kernel> kernelList = new ArrayList<>(kernels);
		Collections.shuffle(kernelList, random);
		kernelRounds = new ArrayList<>();
		numberOfStream = Math.min(32, kernelList.size());
		while (!kernelList.isEmpty()) {
			List<Kernel> round = new ArrayList<>();
			for (int i = 0; i < numberOfStream && !kernelList.isEmpty(); i++) {
				round.add(kernelList.remove(0));
			}
			kernelRounds.add(round);
		}
		try (FileWriter file = new FileWriter("random-schedule-groups.txt")) {
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
