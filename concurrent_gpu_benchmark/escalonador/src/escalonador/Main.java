package escalonador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import escalonador.kernel.SerialUnitSchedulerCode;
import escalonador.scheduler.AbstractSerialScheduler;
import escalonador.scheduler.Resource;
import escalonador.scheduler.Kernel;
import escalonador.scheduler.ResourceSet;
import escalonador.scheduler.SerialDynamicScheduler2;
import escalonador.scheduler.SerialDynamicScheduler3;
import escalonador.scheduler.SerialLinearScheduler;
import escalonador.scheduler.SerialRandomScheduler2;
import escalonador.util.FileWriter;

public class Main {

	public static final Random random = new Random(0);

	public static void main(String[] args) throws IOException {
		int regUnit = 1;
		int sharedMemoryUnit = 1024;
		int threadUnit = 32;
		int sharedMemoryLimit = 48 * 1024;
		int timeUnit = 1024;
		int timeMin = 256 / 8;
		int timeLimit = 256 * 7 / 8;
		int streamLimit = 32;
		Resource kernelUnit = new Resource(regUnit, sharedMemoryUnit, threadUnit);
		if (false) {
			for (int sm : new int[] { 24 }) {
				System.out.println("Stream: " + sm);
				FileWriter.prefix = String.format("%d MultiProcessors/", sm);
				for (int k = 64; k <= 128; k *= 2) {
					FileWriter.suffix = String.format("%04d Kernels", k);
					int kernelCount = k;
					Resource kernelLimit = new Resource(1, 64 * 1024, 128);
					List<Kernel> kernels = new ArrayList<Kernel>();
					for (int i = 0; i < kernelCount; i++) {
						int register = 0;
						int sharedMemory = Math.max(1, ((int) (random.nextInt(sharedMemoryLimit) + 1) / sharedMemoryUnit) * sharedMemoryUnit);
						int blocks = 1;
						int threads = blocks * 32;
						int time = (timeMin + random.nextInt(timeLimit)) * timeUnit;
						kernels.add(new Kernel(kernelLimit, i + 1, register, sharedMemory, blocks, threads, time));
					}
					try (FileWriter file = new FileWriter("kernels.txt")) {
						file.println("Recursos de GPU: " + kernelLimit);
						file.println();
						for (Kernel kernel : kernels) {
							file.println(kernel.toString());
						}
					}
					{
						AbstractSerialScheduler scheduler = new SerialDynamicScheduler2(kernelLimit, kernelUnit, kernels, sm, streamLimit);
						try (FileWriter file = new FileWriter("dynamic-serial-code-2.cu")) {
							file.println(new SerialUnitSchedulerCode(kernels, scheduler.getInitialKernels(), scheduler.getRoundsStep(), scheduler.getNumberOfStream()).toString());
						}
					}
					{
						AbstractSerialScheduler scheduler = new SerialRandomScheduler2(kernelLimit, kernelUnit, kernels, sm, streamLimit);
						try (FileWriter file = new FileWriter("random-serial-code-2.cu")) {
							file.println(new SerialUnitSchedulerCode(kernels, scheduler.getInitialKernels(), scheduler.getRoundsStep(), scheduler.getNumberOfStream()).toString());
						}
					}
				}
			}
		}
		for (int sm : new int[] { 24 }) {
			System.out.println("Stream MultiProcessors: " + sm);
			FileWriter.prefix = String.format("out/%d MultiProcessors/", sm);
			for (int k = 64; k <= 64; k *= 2) {
				FileWriter.suffix = String.format("%04d Kernels", k);
				int kernelCount = k;
				Resource kernelLimit = new Resource(1, 96 * 1024, 2 * 1024);
				ResourceSet resourceSet = new ResourceSet(sm, kernelLimit);
				List<Kernel> kernels = new ArrayList<Kernel>();
				for (int i = 0; i < kernelCount; i++) {
					int register = 0;
					int sharedMemory = Math.max(1, ((int) (random.nextInt(sharedMemoryLimit ) + 1) / sharedMemoryUnit) * sharedMemoryUnit);
					int blocks = random.nextInt(32) + 1;
					int threads = blocks * 32;
					int time = (timeMin + random.nextInt(timeLimit)) * timeUnit;
					kernels.add(new Kernel(kernelLimit, i + 1, register, sharedMemory, blocks, threads, time));
				}
				try (FileWriter file = new FileWriter("dynamic-sm-serial-kernels.txt")) {
					file.println("Recursos de GPU: " + kernelLimit);
					file.println();
					for (Kernel kernel : kernels) {
						file.println(kernel.toString());
					}
				}
				{
					AbstractSerialScheduler scheduler = new SerialDynamicScheduler3(resourceSet, kernelUnit, kernels, sm, streamLimit);
					try (FileWriter file = new FileWriter("dynamic-sm.cu")) {
						file.println(new SerialUnitSchedulerCode(kernels, scheduler.getInitialKernels(), scheduler.getRoundsStep(), scheduler.getNumberOfStream()).toString());
					}
				}
				{
					AbstractSerialScheduler scheduler = new SerialLinearScheduler(resourceSet, kernelUnit, kernels, sm, streamLimit);
					try (FileWriter file = new FileWriter("linear-sm.cu")) {
						file.println(new SerialUnitSchedulerCode(kernels, scheduler.getInitialKernels(), scheduler.getRoundsStep(), scheduler.getNumberOfStream()).toString());
					}
				}
			}
		}
	}

	// RandomScheduler randomScheduler = new
	// RandomScheduler(kernelLimit, kernels, random);
	// try (FileWriter file = new
	// FileWriter("random-serial-code.cu")) {
	// file.println(new
	// RoundUnitSchedulerCode(randomScheduler.getKernelRounds(),
	// randomScheduler.getNumberOfStream()).toString());
	// }
	// RandomScheduler2 randomScheduler2 = new
	// RandomScheduler2(kernelLimit, kernels, random);
	// try (FileWriter file = new
	// FileWriter("random-serial-code-2.cu")) {
	// file.println(new
	// RoundUnitSchedulerCode(randomScheduler2.getKernelRounds(),
	// randomScheduler2.getNumberOfStream()).toString());
	// }
	// GreedyScheduler greedyScheduler = new
	// GreedyScheduler(kernelLimit, kernels, mp, regUnit,
	// sharedMemoryUnit, warpUnit, threadUnit);
	// try (FileWriter file = new
	// FileWriter("greedy-round-code.cu")) {
	// file.println(new
	// RoundUnitSchedulerCode(greedyScheduler.getKernelRounds(),
	// greedyScheduler.getNumberOfStream()).toString());
	// }
	// if (false) {
	// RoundDynamicScheduler roundDynamicScheduler = new
	// RoundDynamicScheduler(kernelLimit, kernels, mp, regUnit,
	// sharedMemoryUnit, warpUnit, threadUnit);
	// try (FileWriter file = new
	// FileWriter("dynamic-round-schedule-code.cu")) {
	// file.println(new
	// RoundUnitSchedulerCode(roundDynamicScheduler.getKernelRounds(),
	// roundDynamicScheduler.getNumberOfStream()).toString());
	// }
	// }
	// SerialDynamicScheduler serialDynamicScheduler = new
	// SerialDynamicScheduler(kernelLimit, kernelUnit, kernels, mp,
	// streamLimit);
	// try (FileWriter file = new
	// FileWriter("dynamic-serial-code.cu")) {
	// file.println(new SerialUnitSchedulerCode(kernels,
	// serialDynamicScheduler.getInitialKernels(),
	// serialDynamicScheduler.getRoundsStep(),
	// serialDynamicScheduler.getNumberOfStream()).toString());
	// }

}
