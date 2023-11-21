package trochojunit;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;

import mundoadversario.EstadoJuego;



public class TestSucesoresTrocho extends TestTBaseTrocho {
	
	//CAMBIAR PARA CADA JUEGO:
	public static String nomPaquete = "trocho";
	
	
	//Archivo que contiene los casos de prueba
	public static String archivoCasos = "./src/" + nomPaquete + "junit/salida.txt";

	@Test
	public <E extends EstadoJuego<E>> void probarSucesores () {
		
		System.out.println("Procesando archivo para el test de sucesores: " + archivoCasos);
		
		List<Object[][][][]> casos = leerCasos(archivoCasos);
				
		
		System.out.println("Archivo leído. Comenzando prueba de casos.");
		int cont = 0;
		//veamos los casos
		E e;
		List<E> sucesores;
		for (Object[][][][] obj: casos) {
			System.out.println("Procesando caso: " + cont);
			e = (E) creaEstado(obj[0]);
			e.ver();
			sucesores = new LinkedList<>();
			for(int i = 1; i < obj.length; i++) {
				sucesores.add((E) creaEstado(obj[i]));
			}//for i
			
			Assert.assertTrue("No coinciden los sucesores en el caso " + cont,
					probarCaso(e, sucesores));

			cont++;
		}
	}

	/**
	 * @param e  - estado del caso
	 * @param sucesores - sucesores (reales o esperados) del estado e proporcionados por el caso
	 * @return true si los sucesores de e calculados por calculaSucesores coinciden conlos proporcionados
	 * por el caso.
	 */

	public <E extends EstadoJuego<E>>boolean probarCaso(E e, List<E> sucesores){
		
		List<E> suc2 = e.calculaSucesores();

		// comprobamos que el número de sucesores coincide
		if (sucesores.size() != suc2.size()){
			
			System.out.println(" No coincide el número de Sucesores calculados. Hay " + suc2.size() + " en lugar de " + sucesores.size());
			System.out.println("*******************************************");
			System.out.println("ESTADO PADRE:");
			e.ver();
			System.out.println("-------------------------------------------");
			System.out.println("SUCESORES DEL TEST:");
			for (E s : sucesores) {
				s.ver();
			}
			System.out.println("-------------------------------------------");
			System.out.println("SUCESORES CALCULADOS POR EL PROGRAMA:");
			for (E s : suc2) {
				s.ver();
			}
			return false; //el número de sucesores no coincide
		}

		//comprobamos que los sucesores calculados están entre los reales
		for(int i = 0; i < suc2.size(); i++){
			if(!sucesores.contains(suc2.get(i))){  //nota: contains utiliza equals para la comparación
				System.out.println("  - El sucesor " + i + " no debería ser un sucesor válido");
				System.out.println("***********************************");
				System.out.println("Estado padre:");
				e.ver();
				System.out.println("-------------------------------");
				System.out.println("Sucesor en conflicto:");
				suc2.get(i).ver();

				System.out.println("-------------------------");
				System.out.println("Sucesores de Reales:");
				System.out.println("-------------------------");
				for(EstadoJuego p : sucesores) {
					p.ver();
				}
				return false; //hay un sucesor que no está en la lista de los esperados
			}//if !
		}

		//comprobamos que los sucesores reales están entre los calculados
		for(int i = 0; i < sucesores.size(); i++){
			if(!suc2.contains(sucesores.get(i))){  //nota: contains utiliza equals para la comparación
				System.out.println("  - El sucesor " + i + " no aparece (equals) entre los calculados por calculaSucesores");
				sucesores.get(i).ver();
				
				System.out.println("***********************************");
				System.out.println("Estado padre:");
				e.ver();
				System.out.println("-------------------------------");
				System.out.println("Sucesor en conflicto:");
				sucesores.get(i).ver();
				return false; //hay un sucesor que no está en la lista de los calculados
			}//if !
		}


		//si las longitudes coinciden y los conjuntos también, entonces todo fue bien
		System.out.println("  OK");


		return true;
	}

	
}
	