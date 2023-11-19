package trochojunit;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

import trocho.Trocho;


public class TestTBaseTrocho {
	
	
	public static int tam (Object[][][] e) {
		return (int)e[0][0][0];
	}
	
	public static int fb (Object[][][] e) {
		return (int)e[1][0][0];
	}

	public static int cb (Object[][][] e) {
		return (int)e[2][0][0];
	}
	
	public static int fn (Object[][][] e) {
		return (int)e[3][0][0];
	}
	
	public static int cn (Object[][][] e) {
		return (int)e[4][0][0];
	}
	
	public static boolean turno1 (Object[][][] e) {
		return (boolean)e[5][0][0];
	}
	
	public static boolean ocupada (Object[][][] e, int f, int c) {
		return (boolean) e[6][f][c];
	}

	
	public static boolean[][] ocupadas(Object[][][] e){
		int tam = tam(e);
		
		boolean[][] ocupadas = new boolean[tam][tam];
		for (int f = 0; f < tam; f++){
			for(int c = 0; c < tam; c++) {
				ocupadas[f][c] = ocupada(e,f,c); //(boolean) e[3][f][c];
			}
		}
		return ocupadas;
	}
	
	public static Trocho creaEstado(Object[][][] e){
		return new Trocho(tam(e), fb(e), cb(e), fn(e), cn(e), turno1(e), ocupadas(e));
	}

	
	// Para las pruebas de la función de evaluación, incluimos en cada estado
	// su evaluación y quién era MAX
	
	public static double valor (Object[][][] e) {
		return (double) e[7][0][0];
	}
	
	public static boolean maxPrimero (Object[][][] e) {
		return (boolean) e[8][0][0];
	}

	
	// Para las pruebas de la función codifica(), incluimos en cada estado
	// su número de características y el array con los valores de las mismas
	
	public static int numCaracteristicas (Object[][][] e) {
		return (int) e[7][0][0];
	}
	
	public static int[] caracteristicas (Object[][][] e) {
		int tam = numCaracteristicas(e);
		
		int[] codigo = new int[tam];
		for (int i = 0; i < tam; i++){
			codigo[i] = caract(e, i); //(int) e[7][f][c];
		}
		return codigo;
	}
	
	public static int caract (Object[][][] e, int i) {
		return (int) e[8][0][i];
	}
	
	/**
	 * Lee casos de prueba de un fichero externo (para sucesores y evaluación)
	 */
	public List<Object[][][][]> leerCasos(String archivoEntrada){
		List<Object[][][][]> casos = new LinkedList<>();
		//leémos los casos del fichero
		try {
		     FileInputStream streamIn = new FileInputStream(archivoEntrada);
		     ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
		     
		     int cont = 0;
		     Object[][][][] readCase;
		     while(true) {
		    	 try {
		    		 readCase = (Object[][][][]) objectinputstream.readObject();
				     System.out.println("Caso " + cont + "  longitud : " + readCase.length);
				     cont++;
				     casos.add(readCase);

		    	 } catch (EOFException e) {
		    		 System.out.println("EOF");
		    		 objectinputstream.close();
		    		 break;
		    	 }
		     }
		 } catch (Exception e) {
		     e.printStackTrace();
		 }

		return casos;
	}

	
}
