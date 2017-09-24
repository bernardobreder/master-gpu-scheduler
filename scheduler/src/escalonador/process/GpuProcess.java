package escalonador.process;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import escalonador.util.ByteArrayOutputStream;

public class GpuProcess {

	protected static final String EXTENSTION = System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "";

	protected static final File CUDA_PATH = System.getenv("CUDA_PATH") != null ? new File(System.getenv("CUDA_PATH")) : new File("/usr/local/cuda");

	protected static final File CUDA_BIN = new File(CUDA_PATH, "bin");

	protected static final File NVCC = new File(CUDA_BIN, "nvcc" + EXTENSTION);

	protected static final File NVPROF = new File(CUDA_BIN, "nvprof" + EXTENSTION);

	protected static ByteArrayOutputStream readProcess(Process process) throws IOException {
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			byte[] b = new byte[1024];
			try (InputStream in = process.getInputStream()) {
				for (int n; (n = in.read(b)) != -1;) {
					out.write(b, 0, n);
				}
			}
			try (InputStream in = process.getErrorStream()) {
				for (int n; (n = in.read(b)) != -1;) {
					out.write(b, 0, n);
				}
			}
			return out;
		}
	}

}
