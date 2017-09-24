package escalonador.kernel;

public class KernelCode extends AbstractCode {

	/**
	 * @param name
	 * @param sharedMemory
	 */
	public KernelCode(String name, int sharedMemory) {
		println(0, "__global__ void %s (int repeat, int *sm) {", name);
		println(1, "__shared__ unsigned char s[%d];", sharedMemory);
		println(1, "int i = threadIdx.x;");
		println(1, "s[i] = 0;");
		println(1, "for (int n = 0; n < 45; n++) {");
		println(2, "for (int n = 0; n < repeat; n++) s[i]++;");
		println(2, "for (int n = 0; n < repeat; n++) s[i]--;");
		println(2, "for (int n = 0; n < repeat; n++) s[i]++;");
		println(1, "}");
		println(1, "if ( i == 0 ) sm[blockIdx.x] = get_smid();");
		println(0, "}");
	}
}
