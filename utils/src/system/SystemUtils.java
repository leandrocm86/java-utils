package system;

import java.net.URLDecoder;

public class SystemUtils {
	
	public static String getSystemPath() {
		try {
			return URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(".").getPath(), "UTF-8");
		} catch (Throwable t) {
			throw new IllegalStateException("Nao foi possivel recuperar o diretorio do sistema", t);
		}
	}

}
