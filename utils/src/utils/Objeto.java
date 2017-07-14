package utils;

public interface Objeto {
	
	default boolean em(Object... objetos) {
		for (Object o : objetos)
			if (this.equals(o))
				return true;
		return false;
	}
	
	default boolean nenhum(Object... objetos) {
		return !em(objetos);
	}
	
	default <T> T to(Class<T> classe) {
		return classe.cast(this);
	}
}
