package escalonador.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import escalonador.Log;

public class GpuCompileProcess extends GpuProcess {

	private String targetFile;

	public GpuCompileProcess(String sourceFile, int capability) throws IOException {
		try {
			targetFile = sourceFile.substring(0, sourceFile.length() - ".cu".length()) + EXTENSTION;
			List<String> commandArguments = new ArrayList<>();
			commandArguments.add(NVCC.getAbsolutePath());
			commandArguments.add(sourceFile);
			commandArguments.add("-O3");
			commandArguments.add("-gencode");
			commandArguments.add("arch=compute_" + capability + ",code=sm_" + capability);
			commandArguments.add("-o");
			commandArguments.add(targetFile);
			Log.println(String.format("Compiling %s", targetFile));
			try {
				Process process = new ProcessBuilder(commandArguments).start();
				if (process.waitFor() != 0) {
					throw new IOException(new String(readProcess(process).getByteArray(), "utf-8"));
				}
			} finally {
				Log.println(String.format("Compiled %s", targetFile));
			}
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public String getTargetFile() {
		return targetFile;
	}

}
