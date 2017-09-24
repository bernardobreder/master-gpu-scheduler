package escalonador.algorithm.knapsack;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import escalonador.Log;

public class IODynamicKnapsack extends MemoryDynamicKnapsack {

	protected final int bufferSize;

	private int rowIndex;

	private final float[] empty;

	public IODynamicKnapsack(KnapsackResource[] weights, KnapsackResource capacity, float[] values, int bufferSize) {
		super(weights, capacity, values);
		this.bufferSize = bufferSize;
		this.rows = new float[bufferSize][];
		this.empty = new float[bufferSize];
	}

	protected int index(int index) {
		return index / bufferSize;
	}

	@Override
	protected void storeStarted() {
		super.storeStarted();
		Log.println("Number of Store: " + (int) (Math.ceil((float) size / bufferSize)));
		int count = (int) (Math.ceil((float) size / bufferSize));
		for (int i = 0; i < count; i++) {
			File file = file(i);
			file.getParentFile().mkdirs();
			file.delete();
		}
	}

	@Override
	protected void store(int index, float[] source) {
		int i = index(index);
		if (i != rowIndex) {
			storeCurrentRow();
			rowIndex = i;
		}
		super.store(index - i * bufferSize, source);
	}

	@Override
	protected void load(int index, float[] target) {
		int i = index(index);
		if (i != rowIndex) {
			rowIndex = i;
			loadIntoCurrentRow();
		}
		super.load(index - i * bufferSize, target);
	}

	@Override
	protected void loadFinished() {
		int count = (int) (Math.ceil((float) size / bufferSize));
		for (int i = 0; i < count; i++) {
			File file = file(i);
			File parentFile = file.getParentFile();
			file.delete();
			if (parentFile != null && parentFile.list() != null && parentFile.list().length == 0) {
				parentFile.delete();
			}
		}
	}

	protected void storeCurrentRow() {
		Log.println("Store started: " + rowIndex);
		long time = System.currentTimeMillis();
		File file = file(rowIndex);
		file.deleteOnExit();
		try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(file))))) {
			for (int j = 0; j < bufferSize; j++) {
				float[] rowItem = rows[j];
				if (rowItem == null) {
					rowItem = empty;
				}
				for (int k = 1; k < rowItem.length; k++) {
					out.writeFloat(rowItem[k]);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		time = System.currentTimeMillis() - time;
		Log.println("Store finished in " + time + " milisegs");
	}

	protected void loadIntoCurrentRow() {
		Log.println("Load started: " + rowIndex);
		long time = System.currentTimeMillis();
		int capacitySize = capacity.size();
		File file = file(rowIndex);
		try (DataInputStream in = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))))) {
			for (int j = 0; j < bufferSize; j++) {
				float[] r = new float[capacitySize + 1];
				for (int k = 1; k < capacitySize; k++) {
					r[k] = in.readFloat();
				}
				rows[j] = r;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		time = System.currentTimeMillis() - time;
		Log.println("Load finished in " + time + " milisegs");
	}

	protected File file(int rowIndex) {
		return new File("tmp", getClass().getSimpleName() + "_" + Math.abs(hashCode()) + "_" + rowIndex + ".tmp");
	}

}
