package system;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import org.apache.commons.io.IOUtils;

import utils.Str;

public class Sistema {
	
	private static final String NEWLINE = System.getProperty("line.separator");

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
	
    /**
     * Executa o comando passado e retorna uma String com outputs de resultado e/ou erro.
     * @return a saida do comando
     */
    public static String executa(String command)
    {
    	Process process;
		try {
			process = Runtime.getRuntime().exec("bash -i -c " + command);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
        StringBuilder result = new StringBuilder();
        
        Thread output = new Thread() {
        	@Override
        	public void run() {
        		try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream())))
                {
                    while (true)
                    {
                        String line = in.readLine();
                        if (line == null)
                            break;
                        result.append("OUTPUT: " + line).append(NEWLINE);
                    }
                } catch (IOException e) {
					result.append("OUTPUT: " + e.getMessage()).append(NEWLINE);
					e.printStackTrace();
				}
        	}
        };
        
        Thread error = new Thread() {
        	@Override
        	public void run() {
        		try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream())))
                {
                    while (true)
                    {
                        String line = in.readLine();
                        if (line == null)
                            break;
                        result.append("ERROR: " + line).append(NEWLINE);
                    }
                } catch (IOException e) {
					result.append("ERROR: " + e.getMessage()).append(NEWLINE);
					e.printStackTrace();
				}
        	}
        };
        
        output.start();
        error.start();
        try {
			output.join();
			error.join();
        }
        catch (InterruptedException e) {
        	System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
        
        String retorno = result.toString();
        if (!retorno.isBlank())
        	System.out.println(retorno);
        
        return retorno;
    }
    
    /**
     * Executa sequencia de comandos separados por espaco.
     * Os argumentos tambem devem ser separados por espaco, exatamente como escrito direto no terminal.
     * @return string com toda a saida da execucao, incluindo possiveis erros.
     */
   public static Str executaComandos(String comandos) {
 	   String[] comandosArray = comandos.split(" ");
 	   ProcessBuilder builder = new ProcessBuilder(comandosArray);
 	   builder.redirectErrorStream(true);
 	   try {
 		   String output = IOUtils.toString(builder.start().getInputStream(), "UTF-8");
 		   return new Str(output);
 	   } catch (IOException e) {
 		   throw new IllegalStateException(e);
 	   }
   }

   /**
    * Executa sequencia de comandos separados por espaco.
    * Os argumentos tambem devem ser separados por espaco, exatamente como escrito direto no terminal.
    * @return BufferedReader com as saidas dos comandos que deve ser lido continuamente.
    */
   public static BufferedReader iniciaComandos(String comandos) {
	   String[] comandosArray = comandos.split(" ");
	   ProcessBuilder builder = new ProcessBuilder(comandosArray);
	   builder.redirectErrorStream(true);
	   try {
		   return new BufferedReader(new InputStreamReader(builder.start().getInputStream()));
	   } catch (IOException e) {
		   throw new IllegalStateException(e);
	   }
   }
   
   public static boolean ehWindows() {
	   return System.getProperty("os.name").contains("win");
   }
    
}
