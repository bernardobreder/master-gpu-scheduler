package escalonador.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SchedulerProperties extends Properties {

	public SchedulerProperties() {
		try {
			load(getClass().getResourceAsStream("/escalonador/config.properties"));
		} catch (IOException e) {
		} catch (Exception e) {
		}
		try {
			load(new FileInputStream("config.properties"));
		} catch (IOException e) {
		}
	}

	public int getGpuStreamMultiProcessorCount() {
		return Integer.valueOf(getProperty("gpu-sm-count", "24"));
	}

	public int getRepeatCount() {
		return Integer.valueOf(getProperty("repeat-time", "1"));
	}

	public int getRepeatKernelCount() {
		return Integer.valueOf(getProperty("repeat-kernel-time", "3"));
	}

	public int getKernelSharedMemoryMax() {
		return Integer.valueOf(getProperty("kernel-shared-memory-max", "48"));
	}

	public int getKernelTime() {
		return Integer.valueOf(getProperty("kernel-time", "500"));
	}

	public int getDeviceId() {
		return Integer.valueOf(getProperty("device-id", "0"));
	}

	public int getCapability() {
		return Integer.valueOf(getProperty("gpu-capability", "52"));
	}

	public int getGpuSharedMemoryMax() {
		return Integer.valueOf(getProperty("gpu-shared-memory-max", "96"));
	}
	
	public boolean getTableEnabled() {
		return Boolean.valueOf(getProperty("table-enabled", "true"));
	}

	public boolean getGainGraphEnabled() {
		return Boolean.valueOf(getProperty("gain-graphic-enabled", "true"));
	}

	public boolean getSMDebug() {
		return Boolean.valueOf(getProperty("sm-debug", "false"));
	}
}
