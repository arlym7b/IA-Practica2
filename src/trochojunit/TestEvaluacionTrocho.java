package trochojunit;


import java.util.List;

import org.junit.Test;
import org.junit.Assert;


import mundoadversario.EstadoJuegoAprox;

//CAMBIAR PARA CADA JUEGO
import trocho.Trocho;
import trocho.EvaluadorTrocho;


public class TestEvaluacionTrocho extends TestTBaseTrocho {
	
	//CAMBIAR PARA CADA JUEGO
	public static String NOM_CLASE = "trocho";
	
	
	//Archivo que contiene los casos de prueba
	public static String archivoCasos = "./src/" + NOM_CLASE + "junit/evalua.txt";
	
	

	@Test
	public <E extends EstadoJuegoAprox<E>> void probarEvaluacion () {
		
		System.out.println("Procesando archivo para el test de evaluacion: " + archivoCasos);
		
		List<Object[][][][]> casos = leerCasos(archivoCasos);
				
		System.out.println("Archivo leído. Comenzando prueba de casos.");
		int cont = 0;

		//CAMBIAR PARA CADA JUEGO:
		Trocho e;
		EvaluadorTrocho ev; 	//Evaluador que utilizaremos para las pruebas
		
		for (Object[][][][] obj: casos) {
			System.out.println("Procesando caso: " + cont);
			e = creaEstado(obj[0]);
			double valorReal = valor(obj[0]);
			boolean miTurno = maxPrimero(obj[0]);
			
			//CAMBIAR PARA CADA JUEGO
			//int tam = e.tam();
			//ev = new EvaluadorTrocho(tam);
			ev = new EvaluadorTrocho();

			double valorCalculado = ev.evalua(e, miTurno);
			
			if (valorReal != valorCalculado) {
				System.out.println("------ Estado conflictivo: ----------");
				e.ver();
				System.out.println("MAX es el primer jugador : " + miTurno);
				System.out.println("Valor esperado: " + valorReal + "\nValor calculado: " + valorCalculado + "\n");				
//			} else {
//				e.ver();
//				System.out.println("MAX es el primer jugador : " + miTurno);
//				System.out.println("Valor esperado: " + valorReal);
//				System.out.println("-----------------------------");
			}
			
			Assert.assertTrue("No coincide el valor calculado " + valorCalculado +
					" con el esperado " + valorReal,
					valorReal == valorCalculado);

			cont++;
		}
	}

		
}
	