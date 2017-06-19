package utils;

public abstract class Objeto {
	
	public boolean em(Object... objetos) {
		for (Object o : objetos)
			if (this.equals(o))
				return true;
		return false;
	}
	
	public boolean nenhum(Object... objetos) {
		return !em(objetos);
	}

}
