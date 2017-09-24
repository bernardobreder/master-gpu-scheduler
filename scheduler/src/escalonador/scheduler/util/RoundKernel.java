package escalonador.scheduler.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RoundKernel implements Iterable<StreamKernel> {

	protected final List<StreamKernel> elements = new ArrayList<>();

	public boolean add(StreamKernel kernel) {
		return elements.add(kernel);
	}

	public boolean addAll(Collection<? extends StreamKernel> kernels) {
		return elements.addAll(kernels);
	}

	public void clear() {
		elements.clear();
	}

	public StreamKernel get(int index) {
		return elements.get(index);
	}

	public int size() {
		return elements.size();
	}

	@Override
	public Iterator<StreamKernel> iterator() {
		return elements.iterator();
	}

	public StreamKernel[] toArray() {
		return elements.toArray(new StreamKernel[elements.size()]);
	}

}
