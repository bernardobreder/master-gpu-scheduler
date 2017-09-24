package escalonador.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import escalonador.Main;

public class SerialRandomScheduler2 extends SerialDynamicScheduler2 {

	public SerialRandomScheduler2(Resource kernelLimit, Resource kernelUnit, List<Kernel> kernelList, int spCount, int streamLimit) throws IOException {
		super(kernelLimit, kernelUnit, kernelList, spCount, streamLimit);
	}

	public List<Kernel> processRound(Resource kernelGlobal, Resource kernelUnit, List<Kernel> kernels) {
		if (kernels.isEmpty()) {
			return new ArrayList<>();
		}
		Random random = Main.random;
		int kernelCount = Math.min(kernels.size(), random.nextInt(2) + 1);
		List<Kernel> result = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		while (set.size() != kernelCount) {
			set.add(random.nextInt(kernels.size()));
		}
		for (int i : set) {
			result.add(kernels.get(i));
		}
		return result;
	}
}
