package trochojunit;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import mundoadversario.EstadoJuegoAprox;



public class TestCodificaTrocho extends TestTBaseTrocho {
	
	//CAMBIAR PARA CADA JUEGO:
	public static String nomClase = "trocho";
	
	//Archivo que contiene los casos de prueba
	public static String archivoCasos = "./src/" + nomClase + "junit/codifica.txt";
	
	

	@Test
	public <E extends EstadoJuegoAprox<E>>  void probarEvaluacion () {
		
		System.out.println("Procesando archivo para el test de codifica: " + archivoCasos);
		
		List<Object[][][][]> casos = leerCasos(archivoCasos);
				
		System.out.println("Archivo leído. Comenzando prueba de casos.");
		int cont = 0;

		//veamos los casos
		E e;
		
		for (Object[][][][] obj: casos) {
			System.out.print("Procesando caso: " + cont);
			e = (E) creaEstado(obj[0]);
			int num = numCaracteristicas(obj[0]);
			int[] codigo = caracteristicas(obj[0]);

			int[] valorCalculado = e.codifica();  
			
			if (!Arrays.equals(codigo, valorCalculado)) {
				System.out.println("------ Estado conflictivo: ----------");
				e.ver();
				System.out.println("Valor esperado: " + Arrays.toString(codigo) 
					+ "\nValor calculado: " + Arrays.toString(valorCalculado) + "\n");				
			} else {
				System.out.println(" : OK");
//				e.ver();
//				System.out.println(Arrays.toString(codigo));
			}
			
			Assert.assertTrue("No coincide el valor calculado " + valorCalculado +
					" con el esperado " + codigo,
					Arrays.equals(codigo, valorCalculado));

			cont++;
		}
	}

		
}
	