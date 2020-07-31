import static org.junit.Assert.assertEquals;

import org.junit.Test;

import estruturas.Lista;
import utils.Data;
import utils.Str;

public class Teste {

	public static void main(String[] args) {
		Lista<Integer> lista = new Lista<Integer>();
		lista.add(0);
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		for (Integer i : lista)
			System.out.print(i + "-" + lista.getIndiceIteracao() + " ");
		System.out.println("\nInvertendo...");
		lista.inverteProximoIterador();
		for (Integer i : lista)
			System.out.print(i + "-" + lista.getIndiceIteracao() + " ");
		System.out.println("\nRetirando um item");
		for (Integer i : lista) {
			System.out.print(i + "-" + lista.getIndiceIteracao());
			if (i == 3) {
				lista.remove();
				System.out.print("-" + lista.getIndiceIteracao());
			}
			System.out.print(" ");
		}
		System.out.println("\nRetirando um item reverso");
		lista.inverteProximoIterador();
		for (Integer i : lista) {
			System.out.print(i + "-" + lista.getIndiceIteracao());
			if (i == 2) {
				lista.remove();
				System.out.print("-" + lista.getIndiceIteracao());
			}
			System.out.print(" ");
		}
		System.out.println("\nResultado");
		for (Integer i : lista)
			System.out.print(i + "-" + lista.getIndiceIteracao() + " ");
		
	}
	
	@Test
	public void testeSubstrings() {
		Str frutas = new Str("abacaxi, banana, maçã, abacaxi, abacate, uva, abacaxi, morango, laranja");
		confere("abacaxi, abacate, uva, abacaxi, morango, laranja", frutas.desdeEnesimo("abacaxi", 2));
		confere("abacaxi, abacate, uva, ", frutas.desdeEnesimo("abacaxi", 2).ateEnesimo("abacaxi", 2));
		confere("abacate, uva, abacaxi, morango, laranja", frutas.desdeEnesimo(" ", 4, false));
		confere("abacaxi, banana, maçã, abacaxi", frutas.ateEnesimo("abacaxi", 2, true));
	}
	
	@Test
	public void testeDatas() {
		Data data1 = new Data("2020-07-31 09:24:09,767", "yyyy-MM-dd HH:mm:ss,SSS");
		Data data2 = new Data("12:30:00", Data.HORA_HH_mm_ss);
		Data data3 = new Data(Long.parseLong("1596216131918"));
		assertEquals(9, data1.getHours());
		assertEquals(12, data2.getHours());
		assertEquals(14, data3.getHours());
	}
	
	private void confere(String esperado, Str resultado) {
		assertEquals(new Str(esperado), resultado);
	}

}
