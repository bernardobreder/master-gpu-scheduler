package escalonador.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import escalonador.Log;
import escalonador.kernel.Kernel;
import escalonador.profile.KernelExecution;
import escalonador.util.ByteArrayOutputStream;

public class GpuProfileProcess extends GpuProcess {

	protected List<KernelExecution> kernelProfiles = new ArrayList<>();

	public GpuProfileProcess(List<Kernel> kernels, String execFilename) throws IOException {
		ByteArrayOutputStream dump = dump(execFilename);
		List<List<String>> dataRows = new ArrayList<>();
		try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(dump.toInputStream()))) {
			String line = reader.readLine();
			while (line != null && !line.trim().toLowerCase().endsWith("profiling result:")) {
				line = reader.readLine();
			}
			if (line != null) {
				line = reader.readLine();
				while (line != null && line.trim().length() > 0) {
					dataRows.add(csvSplit(line.trim(), ','));
					line = reader.readLine();
				}
			}
		}
		Map<String, Kernel> kernelMap = new HashMap<>();
		for (Kernel kernel : kernels) {
			kernelMap.put(kernel.getName(), kernel);
		}
		List<KernelExecution> kernelExecutionList = new ArrayList<KernelExecution>();
		for (int j = 2; j < dataRows.size(); j++) {
			List<String> row = dataRows.get(j);
			double start = Double.valueOf(row.get(0));
			double duration = Double.valueOf(row.get(1));
			String name = row.get(16);
			name = name.substring(0, name.indexOf('('));
			Kernel kernel = kernelMap.get(name);
			if (kernel == null) {
				throw new IllegalStateException(name);
			}
			kernelExecutionList.add(new KernelExecution(kernel, start, duration));
		}
		kernelProfiles = prepareKernelExecution(kernelExecutionList);
	}

	protected static synchronized ByteArrayOutputStream dump(String execFilename) throws IOException {
		try {
			if (!NVPROF.exists()) {
				throw new FileNotFoundException(NVPROF.toString());
			}
			File execFile = new File(execFilename);
			Log.println(String.format("Profiling %s", execFile));
			try {
				Process process = new ProcessBuilder(NVPROF.getAbsolutePath(), "--print-gpu-trace", "--normalized-time-unit", "ms", "--csv", execFile.getAbsolutePath()).start();
				if (process.waitFor() != 0) {
					throw new IOException(new String(readProcess(process).getByteArray(), "utf-8"));
				} else {
					return readProcess(process);
				}
			} finally {
				Log.println(String.format("Profiled %s", execFile));
			}
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	protected static List<String> csvSplit(String line, char separator) {
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		boolean string = false;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == '\"') {
				string = !string;
			} else if (c == separator && !string) {
				list.add(sb.toString());
				sb.delete(0, sb.length());
			} else {
				sb.append(c);
			}
		}
		list.add(sb.toString());
		return list;
	}

	public List<KernelExecution> prepareKernelExecution(List<KernelExecution> kernelProfiles) {
		double startTime = Double.MAX_VALUE;
		for (KernelExecution kernel : kernelProfiles) {
			if (kernel.getStart() < startTime) {
				startTime = kernel.getStart();
			}
		}
		for (KernelExecution kernel : kernelProfiles) {
			kernel.setStart(kernel.getStart() - startTime);
		}
		return kernelProfiles;
	}

	public List<KernelExecution> getKernelProfiles() {
		return kernelProfiles;
	}

}
