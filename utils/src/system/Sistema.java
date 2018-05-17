package system;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

public class Sistema {
	
	public static String getSystemPath() {
		try {
			return URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(".").getPath(), "UTF-8");
		} catch (Throwable t) {
			throw new IllegalStateException("Nao foi possivel recuperar o diretorio do sistema", t);
		}
	}
	
	public static void executaFile(CharSequence filePath) {
		try {
			java.awt.Desktop.getDesktop().open(new File(filePath.toString()));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
