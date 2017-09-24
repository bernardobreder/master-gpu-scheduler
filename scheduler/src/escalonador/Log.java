package escalonador;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	public static boolean ENABLED = true;

	public static void println(Object format, Object... objects) {
		if (ENABLED) {
			if (objects != null && objects.length != 0) {
				format = String.format(format.toString(), objects);
			}
			format = "[" + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()) + "]: " + format;
			System.out.println(format);
		}
	}

}
