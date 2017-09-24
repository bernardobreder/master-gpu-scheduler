package escalonador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import escalonador.kernel.Kernel;
import escalonador.kernel.SimpleSchedulerCode;
import escalonador.process.GpuCompileProcess;
import escalonador.process.GpuProfileProcess;
import escalonador.profile.KernelExecution;
import escalonador.scheduler.AbstractScheduler;
import escalonador.scheduler.SequencialScheduler;
import escalonador.scheduler.StandardScheduler;
import escalonador.scheduler.dynamic.ResourceDynamicScheduler;
import escalonador.scheduler.dynamic.TimeResourceDynamicScheduler;
import escalonador.scheduler.util.Resource;
import escalonador.scheduler.util.ResourceSet;
import escalonador.util.FileWriter;
import escalonador.util.SchedulerProperties;

public class Main {

	private static final String OUT_DIR = "out/";

	public static final Random RANDOM = new Random(0);

	public static final SchedulerProperties PROPERTIES = new SchedulerProperties();

	public static final int[] KERNELS = new int[] { 32, 64, 128, 256 };

	public static final int[] TABLE_BLOCKS = new int[] { 32, 64, 128, 256, 512 };

	public static final int[] GRAPH_GAIN_BLOCKS = new int[] { 32, 64, 128, 256, 512 };

	public static final String DOUBLE_FORMAT = "%.3f";

	public static final int QUEUE_LIMIT = 32;

	public static final Resource GPU_LIMIT = new Resource(1, PROPERTIES.getGpuSharedMemoryMax() * 1024, 2 * 1024);

	public static final int BLOCK_MINIMUM = 4;

	public static final Resource KERNEL_UNIT = new Resource(1, 1024, 32);

	public static void main(String[] args) throws IOException {
		if (PROPERTIES.getGainGraphEnabled()) {
			FileWriter.prefix = OUT_DIR;
			executeGainGraphStep(16, 16, 0);
			try (FileWriter logWriter = new FileWriter("graph_gain.txt")) {
				try (FileWriter timeDataWriter = new FileWriter("graph_gain_time.txt")) {
					try (FileWriter resDataWriter = new FileWriter("graph_gain_res.txt")) {
						for (int numberOfBlocks : GRAPH_GAIN_BLOCKS) {
							logWriter.println(String.format("%3d to %d Blocks" + "\tAverage Waiting Time" + "\tAverage Waiting Resource" + "\tGain with Time" + "\tGain with Resource", BLOCK_MINIMUM, numberOfBlocks));
							for (int numberOfKernels : KERNELS) {
								FileWriter.prefix = OUT_DIR + blockDir(numberOfBlocks) + kernelSetDir(numberOfKernels);
								logWriter.print(String.format("%3d Kernels", numberOfKernels));
								double[] gainGraph = executeGainGraph(numberOfKernels, numberOfBlocks);
								for (double averageTime : gainGraph) {
									logWriter.printf("\t" + DOUBLE_FORMAT, averageTime);
									Log.println(String.format(DOUBLE_FORMAT, averageTime));
								}
								timeDataWriter.printf("(%d," + DOUBLE_FORMAT + ")", numberOfKernels, gainGraph[0]);
								resDataWriter.printf("(%d," + DOUBLE_FORMAT + ")", numberOfKernels, gainGraph[1]);
								logWriter.println();
								timeDataWriter.println();
								resDataWriter.println();
							}
							logWriter.println();
							timeDataWriter.println();
							resDataWriter.println();
						}
					}
				}
			}
			FileWriter.prefix = OUT_DIR;
		}
		if (PROPERTIES.getTableEnabled()) {
			FileWriter.prefix = OUT_DIR;
			executeTableStep(128, 16, 0);
			try (FileWriter logWriter = new FileWriter("result.txt")) {
				for (int numberOfKernels : KERNELS) {
					logWriter.println(String.format("%3d Kernels" + "\tRegular AWT" + "\tReorder AWT" + "\tRegular AT" + "\tReorder AWT" + "\tAverage Gain" + "\tOverhead" + "\tRegular ANTT" + "\tReorder ANTT" + "\tRegular STP" + "\tReorder STP", numberOfKernels));
					for (int numberOfBlocks : TABLE_BLOCKS) {
						FileWriter.prefix = OUT_DIR + blockDir(numberOfBlocks) + kernelSetDir(numberOfKernels);
						logWriter.print(String.format("%3d to %d Blocks", BLOCK_MINIMUM, numberOfBlocks));
						for (double averageTime : executeTable(numberOfKernels, numberOfBlocks)) {
							logWriter.print(String.format("\t" + DOUBLE_FORMAT, averageTime));
							Log.println(String.format(DOUBLE_FORMAT, averageTime));
						}
						logWriter.println();
					}
					logWriter.println();
				}
			}
			FileWriter.prefix = OUT_DIR;
		}
	}

	protected static double[] executeGainGraph(int numberOfKernels, int numberOfBlocks) throws IOException {
		double[] results = new double[4];
		for (int step = 0, j = 0; step < PROPERTIES.getRepeatCount(); step++, j = 0) {
			ResultGraph resultGraph = executeGainGraphStep(numberOfKernels, numberOfBlocks, step);
			results[j++] += resultGraph.timeValue;
			results[j++] += resultGraph.resourceValue;
			results[j++] += resultGraph.gainTimeValue;
			results[j++] += resultGraph.gainResourceValue;
		}
		for (int i = 0; i < results.length; i++) {
			results[i] /= PROPERTIES.getRepeatCount();
		}
		return results;
	}

	protected static ResultGraph executeGainGraphStep(int numberOfKernels, int numberOfBlocks, int step) throws IOException {
		ResourceSet resourceSet = new ResourceSet(PROPERTIES.getGpuStreamMultiProcessorCount(), GPU_LIMIT);
		List<Kernel> kernels = createKernels(numberOfKernels, numberOfBlocks);
		ResultTime standardResult = executeStandard(kernels, resourceSet);
		ResultTime timeResourceDynamicResult = executeTimeResourceDynamic(numberOfKernels, numberOfBlocks, kernels, resourceSet);
		ResultTime resourceDynamicResult = executeResourceDynamic(numberOfKernels, numberOfBlocks, kernels, resourceSet);
		double timeValue = timeResourceDynamicResult.averageStartTime;
		double resourceValue = resourceDynamicResult.averageStartTime;
		double gainTimeValue = standardResult.averageStartTime - timeValue;
		double gainResourceValue = standardResult.averageStartTime - resourceValue;
		ResultGraph resultGraph = new ResultGraph(timeValue, resourceValue, gainTimeValue, gainResourceValue);
		return resultGraph;
	}

	protected static double[] executeTable(int numberOfKernels, int numberOfBlocks) throws IOException {
		double[] results = new double[10];
		for (int step = 0, j = 0; step < PROPERTIES.getRepeatCount(); step++, j = 0) {
			ResultTable table = executeTableStep(numberOfKernels, numberOfBlocks, step);
			results[j++] += table.standardAWT;
			results[j++] += table.dynamicAWT;
			results[j++] += table.standardAT;
			results[j++] += table.dynamicAT;
			results[j++] += table.gain;
			results[j++] += table.overhead;
			results[j++] += table.standardANTT;
			results[j++] += table.dynamicANTT;
			results[j++] += table.standardSTP;
			results[j++] += table.dynamicSTP;
		}
		for (int i = 0; i < results.length; i++) {
			results[i] /= PROPERTIES.getRepeatCount();
		}
		return results;
	}

	protected static ResultTable executeTableStep(int numberOfKernels, int numberOfBlocks, int step) throws IOException {
		ResourceSet resourceSet = new ResourceSet(PROPERTIES.getGpuStreamMultiProcessorCount(), GPU_LIMIT);
		List<Kernel> kernels = createKernels(numberOfKernels, numberOfBlocks);
		ResultTime standardResult = executeStandard(kernels, resourceSet);
		ResultTime dynamicResult = executeTimeResourceDynamic(numberOfKernels, numberOfBlocks, kernels, resourceSet);
		ResultTime standardSequencialResult = executeStandardSequencial(toKernels(standardResult), resourceSet);
		ResultTime dynamicSequencialResult = executeDynamicSequencial(toKernels(dynamicResult), resourceSet);
		double standardANTT = executeAntt(standardSequencialResult, standardResult);
		double dynamicANTT = executeAntt(dynamicSequencialResult, dynamicResult);
		double standardSTP = executeStp(standardSequencialResult, standardResult);
		double dynamicSTP = executeStp(dynamicSequencialResult, dynamicResult);

		try (FileWriter logWriter = new FileWriter(String.format("table-antt-stp-%3dKernels-%3dBlocks-%2dStep.txt", numberOfKernels, numberOfBlocks, step))) {
			Map<Kernel, Double> sequencialStandardMap = mapEndTime(standardSequencialResult);
			Map<Kernel, Double> standardMap = mapEndTime(standardResult);
			Map<Kernel, Double> dynamicSequencialMap = mapEndTime(dynamicSequencialResult);
			Map<Kernel, Double> dynamicMap = mapEndTime(dynamicResult);
			TreeSet<Kernel> keys = new TreeSet<>(sequencialStandardMap.keySet());
			logWriter.println("Kernel\tRegular Time\tReorder Time\tRegular Sequencial\tReorder Sequencial");
			for (Kernel kernel : keys) {
				logWriter.printfln("Kernel %03d\t%.3f\t%.3f\t%.3f\t%.3f", kernel.getId(), standardMap.get(kernel).doubleValue(), dynamicMap.get(kernel).doubleValue(), sequencialStandardMap.get(kernel).doubleValue(), dynamicSequencialMap.get(kernel).doubleValue());
			}
			logWriter.println("");
		}

		double gain = standardResult.averageEndTime - dynamicResult.averageEndTime;
		double overhead = dynamicResult.processTime / (standardResult.averageStartTime - dynamicResult.averageStartTime) * 100;
		ResultTable table = new ResultTable(standardResult.averageStartTime, dynamicResult.averageStartTime, standardResult.averageEndTime, dynamicResult.averageEndTime, gain, overhead, standardANTT, dynamicANTT, standardSTP, dynamicSTP);
		return table;
	}

	protected static List<Kernel> toKernels(ResultTime genericResult) {
		List<Kernel> list = new ArrayList<>();
		for (KernelExecution execution : genericResult.kernelProfiles) {
			list.add(execution.getKernel());
		}
		return list;
	}

	protected static void executeKernel(int numberOfStreamMultiProcessor) throws IOException {
		try (FileWriter writer = new FileWriter("kernels.txt")) {
			writer.println("Number of Kernel\tNumber of Blocks\tAverage Duration");
			for (int numberOfKernels : KERNELS) {
				for (int numberOfBlocks : TABLE_BLOCKS) {
					double result = 0.0d;
					int repeat = PROPERTIES.getRepeatKernelCount();
					for (int step = 0; step < repeat; step++) {
						result += executeKernelStep(numberOfStreamMultiProcessor, numberOfKernels, numberOfBlocks);
					}
					result /= repeat;
					Log.println("%03d Kernel with %03d Blocks has Real Average Duration: " + DOUBLE_FORMAT, numberOfKernels, numberOfBlocks, result);
					writer.println(String.format("%03d\t%03d\t" + DOUBLE_FORMAT, numberOfKernels, numberOfBlocks, result));
				}
			}
		}
	}

	protected static double executeKernelStep(int numberOfStreamMultiProcessor, int numberOfKernels, int numberOfBlocks) throws IOException {
		ResourceSet resourceSet = new ResourceSet(numberOfStreamMultiProcessor, GPU_LIMIT);
		List<Kernel> kernels = createKernels(numberOfKernels, numberOfBlocks);
		ResultTime sequencialResult = executeStandardSequencial(kernels, resourceSet);
		double durationAverageTime = 0.0d;
		for (KernelExecution kernelExecution : sequencialResult.kernelProfiles) {
			durationAverageTime += kernelExecution.getDuration();
		}
		durationAverageTime /= kernels.size();
		return durationAverageTime;
	}

	protected static double executeAntt(ResultTime sequencialResult, ResultTime genericResult) {
		Map<Kernel, Double> sequencialMap = mapEndTime(sequencialResult);
		Map<Kernel, Double> genericMap = mapEndTime(genericResult);
		if (!sequencialMap.keySet().equals(genericMap.keySet())) {
			throw new IllegalStateException();
		}
		Set<Kernel> kernels = sequencialMap.keySet();
		double sum = 0.0d;
		for (Kernel kernel : kernels) {
			sum += genericMap.get(kernel) / sequencialMap.get(kernel);
		}
		return sum / kernels.size();
	}

	protected static double executeStp(ResultTime sequencialResult, ResultTime dynamicResult) {
		Map<Kernel, Double> sequencialMap = mapEndTime(sequencialResult);
		Map<Kernel, Double> dynamicMap = mapEndTime(dynamicResult);
		if (!sequencialMap.keySet().equals(dynamicMap.keySet())) {
			throw new IllegalStateException();
		}
		Set<Kernel> kernels = sequencialMap.keySet();
		double sum = 0.0d;
		for (Kernel kernel : kernels) {
			sum += sequencialMap.get(kernel) / dynamicMap.get(kernel);
		}
		return sum;
	}

	protected static ResultTime executeStandardSequencial(List<Kernel> kernels, ResourceSet resourceSet) throws IOException {
		String execName = "sequencial-standard";
		String filename = execName + ".cu";
		AbstractScheduler scheduler = new SequencialScheduler(resourceSet, KERNEL_UNIT, kernels, PROPERTIES.getGpuStreamMultiProcessorCount(), QUEUE_LIMIT);
		return executeAlgorithm(scheduler, kernels, filename);
	}

	protected static ResultTime executeDynamicSequencial(List<Kernel> kernels, ResourceSet resourceSet) throws IOException {
		String execName = "sequencial-dynamic";
		String filename = execName + ".cu";
		AbstractScheduler scheduler = new SequencialScheduler(resourceSet, KERNEL_UNIT, kernels, PROPERTIES.getGpuStreamMultiProcessorCount(), QUEUE_LIMIT);
		return executeAlgorithm(scheduler, kernels, filename);
	}

	protected static ResultTime executeStandard(List<Kernel> kernels, ResourceSet resourceSet) throws IOException {
		String execName = "standard";
		String filename = execName + ".cu";
		AbstractScheduler scheduler = new StandardScheduler(resourceSet, KERNEL_UNIT, kernels, PROPERTIES.getGpuStreamMultiProcessorCount(), QUEUE_LIMIT);
		return executeAlgorithm(scheduler, kernels, filename);
	}

	protected static ResultTime executeTimeResourceDynamic(int numberOfKernels, int numberOfBlocks, List<Kernel> kernels, ResourceSet resourceSet) throws IOException {
		String execName = "time-res-dynamic";
		String filename = execName + ".cu";
		AbstractScheduler scheduler = new TimeResourceDynamicScheduler(resourceSet, kernels, KERNEL_UNIT, PROPERTIES.getGpuStreamMultiProcessorCount(), QUEUE_LIMIT, numberOfBlocks, numberOfKernels);
		return executeAlgorithm(scheduler, kernels, filename);
	}

	protected static ResultTime executeResourceDynamic(int numberOfKernels, int numberOfBlocks, List<Kernel> kernels, ResourceSet resourceSet) throws IOException {
		String execName = "res-dynamic";
		String filename = execName + ".cu";
		AbstractScheduler scheduler = new ResourceDynamicScheduler(resourceSet, kernels, KERNEL_UNIT, PROPERTIES.getGpuStreamMultiProcessorCount(), QUEUE_LIMIT, numberOfBlocks, numberOfKernels);
		return executeAlgorithm(scheduler, kernels, filename);
	}

	protected static ResultTime executeAlgorithm(AbstractScheduler scheduler, List<Kernel> kernels, String sourceFile) throws IOException {
		long processTime = System.currentTimeMillis();
		scheduler.execute();
		processTime = System.currentTimeMillis() - processTime;
		try (FileWriter file = new FileWriter(sourceFile)) {
			file.println(new SimpleSchedulerCode(kernels, scheduler.getInitialKernels(), scheduler.getRoundsStep(), scheduler.getNumberOfStream(), PROPERTIES.getSMDebug()).toString());
		}
		String execFilename = new GpuCompileProcess(FileWriter.file(sourceFile).toPath().toString(), PROPERTIES.getCapability()).getTargetFile();
		List<KernelExecution> kernelProfiles = new GpuProfileProcess(kernels, execFilename).getKernelProfiles();
		double sumStartTime = 0;
		double sumDurationTime = 0;
		double sumEndTime = 0;
		for (KernelExecution kernel : kernelProfiles) {
			sumStartTime += kernel.getStart();
			sumDurationTime += kernel.getDuration();
			sumEndTime += kernel.getStart() + kernel.getDuration();
		}
		int kernelCount = kernelProfiles.size();
		double averageStartTime = sumStartTime / kernelCount;
		double averageDurationTime = sumDurationTime / kernelCount;
		double averageEndTime = sumEndTime / kernelCount;
		return new ResultTime(kernelProfiles, processTime, averageStartTime, averageDurationTime, averageEndTime);
	}

	protected static Map<Kernel, Double> mapEndTime(ResultTime resultTime) {
		Map<Kernel, Double> sequencialMap = new HashMap<>();
		for (KernelExecution execution : resultTime.kernelProfiles) {
			sequencialMap.put(execution.getKernel(), execution.getStart() + execution.getDuration());
		}
		return sequencialMap;
	}

	protected static List<Kernel> createKernels(int numberOfKernels, int numberOfBlocks) {
		List<Kernel> kernels = new ArrayList<Kernel>();
		for (int i = 0; i < numberOfKernels; i++) {
			int register = 0;
			int sharedMemory = random(1, PROPERTIES.getKernelSharedMemoryMax()) * 1024;
			int blocks = random(Math.min(numberOfBlocks, BLOCK_MINIMUM), numberOfBlocks);
			int threads = blocks * 32;
			int time = random(1, PROPERTIES.getKernelTime()) * 1000;
			kernels.add(new Kernel(GPU_LIMIT, i + 1, register, sharedMemory, blocks, threads, time));
		}
		return kernels;
	}

	protected static int random(int min, int max) {
		return RANDOM.nextInt(max - (min - 1)) + min;
	}

	protected static String kernelSetDir(int kernel) {
		return String.format("%04dKernels/", kernel);
	}

	protected static String mpDir(int sm) {
		return String.format("%02dMultiProcessors/", sm);
	}

	protected static String blockDir(int b) {
		return String.format("%03dBlocks/", b);
	}

	public static class ResultTime {

		public final List<KernelExecution> kernelProfiles;

		public final double processTime;

		public final double averageStartTime;

		public final double averageDurationTime;

		public final double averageEndTime;

		public ResultTime(List<KernelExecution> kernelProfiles, double processTime, double averageStartTime, double averageDurationTime, double averageEndTime) {
			super();
			this.kernelProfiles = kernelProfiles;
			this.processTime = processTime;
			this.averageStartTime = averageStartTime;
			this.averageDurationTime = averageDurationTime;
			this.averageEndTime = averageEndTime;
		}

	}

	public static class ResultTable {

		public double standardAWT;

		public double dynamicAWT;

		public double standardAT;

		public double dynamicAT;

		public double gain;

		public double overhead;

		public double standardANTT;

		public double dynamicANTT;

		public double standardSTP;

		public double dynamicSTP;

		public ResultTable(double standardAWT, double dynamicAWT, double standardAT, double dynamicAT, double gain, double overhead, double standardANTT, double dynamicANTT, double standardSTP, double dynamicSTP) {
			this.standardAWT = standardAWT;
			this.dynamicAWT = dynamicAWT;
			this.standardAT = standardAT;
			this.dynamicAT = dynamicAT;
			this.gain = gain;
			this.overhead = overhead;
			this.standardANTT = standardANTT;
			this.dynamicANTT = dynamicANTT;
			this.standardSTP = standardSTP;
			this.dynamicSTP = dynamicSTP;
		}

	}

	public static class ResultGraph {

		public double timeValue;

		public double resourceValue;

		public double gainTimeValue;

		public double gainResourceValue;

		public ResultGraph(double timeValue, double resourceValue, double gainTimeValue, double gainResourceValue) {
			this.timeValue = timeValue;
			this.resourceValue = resourceValue;
			this.gainTimeValue = gainTimeValue;
			this.gainResourceValue = gainResourceValue;
		}

	}

}
