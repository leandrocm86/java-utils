package test;

import java.text.SimpleDateFormat;

import utils.Benchmark;
import utils.Data;
import utils.Str;

public class Tests {
	
	private static Data hoje = new Data("05/05/2021", Data.DATA_dd_MM_yyyy);
	public static void main(String[] args) {
		Str horaStr = new Str("12:30:30,555");
		
		int total = 100000;
		while(total-- > 0) {
			toData(horaStr);
			toData2(horaStr);
		}
		
//		System.out.println("toData: " + data1 + " (" + data1.getTime() + ") = " + data1.toStr(Data.DATA_dd_MM_yyyy_HH_mm_ss_SSS));
//		System.out.println("toData2: " + data2 + " (" + data2.getTime() + ") = " + data2.toStr(Data.DATA_dd_MM_yyyy_HH_mm_ss_SSS));
		
		System.out.println(Benchmark.getResultado());
	}
	
	private static Data toData(Str horaStr) {
		Benchmark.start("toData1");
		Data retorno = new Data(horaStr, Data.HORA_HH_mm_ss_SSS); 
		retorno = new Data(hoje.getTime() + retorno.getTime());
		Benchmark.stop("toData1");
		Benchmark.stop("teste");
		return retorno;
	}
	
	private static Data toData2(Str horaStr) {
		Benchmark.start("toData2");
		int hora = horaStr.sub(0,2).toInt() * Data.HORA + horaStr.sub(3, 5).toInt() * Data.MINUTO + horaStr.sub(6, 8).toInt() * Data.SEGUNDO + horaStr.sub(9, 11).toInt();
		Data retorno = new Data(hora);
		retorno = new Data(hoje.getTime() + retorno.getTime());
		Benchmark.stop("toData2");
		return retorno;
	}
	
}
