package trochojunit;

import org.junit.Assert;
import org.junit.Test;

// CAMBIAR PARA CADA JUEGO
import trocho.Trocho;



/**
 * Clase para probar la interfaz necesaria para realizar los tests sobre
 * el problema del Trocho (constructor y selectores).
 * 
 * 
 * @author Lawrence Mandow
 * @version 2.0
 */

public class TestOperacionesTrocho extends TestTBaseTrocho {

	@Test
	public void testDatos(){
		
		probarDatos(datos, "Probando test operaciones: ");
	}	
	
	/**
	 * Recibe un array Object[][][][] con los datos de un conjunto
	 * de estados.
	 * Para cada estado obtiene los datos, lo constuye, y
	 * comprueba que los métodos de consulta especificados en el
	 * enunciado de la práctica devuelven los valores correctos.
	 */
	
	// CAMBIAR PARA CADA JUEGO:
	private void probarDatos (Object[][][][] estados, String str){
		
		System.out.println(str);
		
		for (int i = 0; i < estados.length; i++){
			
			Object[][][] e = estados[i];
			//Obtenemos los datos del estado a crear
			int tam = tam(e);
			int fb = fb(e);
			int cb = cb(e);
			int fn = fn(e);
			int cn = cn(e);
			boolean turno1 = turno1(e); 
			
			boolean[][] ocupadas = ocupadas(e);
						
			//Creamos el estado
			Trocho a = new Trocho(tam, fb, cb, fn, cn, turno1, ocupadas);
			
			System.out.println("Estado " + i);
			System.out.println("*************************");
			a.ver();
			compruebaTrocho(a, tam, fb, cb, fn, cn, turno1, ocupadas);
		}//for i
			
	}
	
	
	
	/**
	 * Comprueba que el estado e se corresponde con las partes recibidas 
	 */
	// CAMBIAR PARA CADA JUEGO
	public static void compruebaTrocho(Trocho a, int tam, int fb, int cb, int fn, int cn,
			boolean turno1, boolean[][] ocupadas){
		//Comprobamos
		int fil2 = ocupadas.length;
		int col2 = ocupadas[0].length;
		
		Assert.assertEquals("Error en num. filas ", tam, fil2);
		Assert.assertEquals("Error en num. columnas ", tam, col2);
		
		for (int f = 0; f < tam; f++){
			for (int c = 0; c < tam; c++) {
				Assert.assertTrue("Ocupadas: Posición " + "(" + f + "," + c + ") debería ser " + ocupadas[f][c],
						ocupadas[f][c] == a.ocupada(f, c));				
			}
		}

		Assert.assertEquals("Error en turno1 ", turno1, 	a.turno1());

	}
	

static Object[][][][] datos = 

{
{{{5}}, {{1}}, {{0}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{4}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{3}}, {{2}}, {{true}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{3}}, {{2}}, {{false}}, {{true, false, false, false, false}, {true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{2}}, {{3}}, {{true}}, {{true, false, false, false, false}, {true, true, false, false, false}, {false, false, false, false, false}, {false, false, true, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{2}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, true, true, false, false}, {false, false, false, false, false}, {false, false, true, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{3}}, {{4}}, {{true}}, {{true, false, false, false, false}, {true, true, true, false, false}, {false, false, false, true, false}, {false, false, true, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{3}}, {{4}}, {{false}}, {{true, false, false, false, false}, {true, true, true, false, false}, {false, true, false, true, false}, {false, false, true, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{3}}, {{3}}, {{true}}, {{true, false, false, false, false}, {true, true, true, false, false}, {false, true, false, true, false}, {false, false, true, false, true}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{4}}, {{0}}, {{3}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, true, true, false, false}, {false, true, false, true, false}, {false, true, true, false, true}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{4}}, {{0}}, {{4}}, {{2}}, {{true}}, {{true, false, false, false, false}, {true, true, true, false, false}, {false, true, false, true, false}, {false, true, true, true, true}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{4}}, {{1}}, {{4}}, {{2}}, {{false}}, {{true, false, false, false, false}, {true, true, true, false, false}, {false, true, false, true, false}, {false, true, true, true, true}, {true, false, false, true, true}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{4}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{4}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{3}}, {{3}}, {{true}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{3}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{2}}, {{4}}, {{true}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, true, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{2}}, {{0}}, {{2}}, {{4}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, true, false, false, false}, {false, true, false, true, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{2}}, {{0}}, {{1}}, {{4}}, {{true}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, true, false, false, true}, {false, true, false, true, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{0}}, {{1}}, {{4}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, false}, {true, true, false, false, true}, {false, true, false, true, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{0}}, {{0}}, {{3}}, {{true}}, {{true, false, false, false, false}, {true, false, false, false, true}, {true, true, false, false, true}, {false, true, false, true, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{4}}, {{1}}, {{0}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, true}, {true, true, false, false, true}, {true, true, false, true, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{4}}, {{1}}, {{0}}, {{2}}, {{true}}, {{true, false, false, true, false}, {true, false, false, false, true}, {true, true, false, false, true}, {true, true, false, true, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{2}}, {{0}}, {{2}}, {{false}}, {{true, false, false, true, false}, {true, false, false, false, true}, {true, true, false, false, true}, {true, true, false, true, false}, {false, true, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{2}}, {{1}}, {{1}}, {{true}}, {{true, false, true, true, false}, {true, false, false, false, true}, {true, true, false, false, true}, {true, true, false, true, false}, {false, true, false, true, true}}, null, null, null},
{{{5}}, {{4}}, {{2}}, {{1}}, {{1}}, {{false}}, {{true, false, true, true, false}, {true, false, false, false, true}, {true, true, false, false, true}, {true, true, true, true, false}, {false, true, false, true, true}}, null, null, null},
{{{5}}, {{4}}, {{2}}, {{2}}, {{2}}, {{true}}, {{true, false, true, true, false}, {true, true, false, false, true}, {true, true, false, false, true}, {true, true, true, true, false}, {false, true, false, true, true}}, null, null, null},
{{{5}}, {{0}}, {{1}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{0}}, {{1}}, {{3}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{3}}, {{3}}, {{false}}, {{true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{2}}, {{3}}, {{true}}, {{true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{0}}, {{2}}, {{3}}, {{false}}, {{true, true, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{0}}, {{1}}, {{3}}, {{true}}, {{true, true, false, false, false}, {false, true, false, false, false}, {false, false, false, true, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{1}}, {{3}}, {{false}}, {{true, true, false, false, false}, {false, true, false, false, false}, {true, false, false, true, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{2}}, {{4}}, {{true}}, {{true, true, false, false, false}, {false, true, false, true, false}, {true, false, false, true, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{2}}, {{4}}, {{false}}, {{true, true, false, false, false}, {true, true, false, true, false}, {true, false, false, true, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{1}}, {{4}}, {{true}}, {{true, true, false, false, false}, {true, true, false, true, false}, {true, false, false, true, true}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{2}}, {{1}}, {{4}}, {{false}}, {{true, true, false, false, false}, {true, true, false, true, false}, {true, true, false, true, true}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{2}}, {{0}}, {{4}}, {{true}}, {{true, true, false, false, false}, {true, true, false, true, true}, {true, true, false, true, true}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{3}}, {{2}}, {{0}}, {{4}}, {{false}}, {{true, true, false, false, false}, {true, true, false, true, true}, {true, true, true, true, true}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{3}}, {{2}}, {{0}}, {{3}}, {{true}}, {{true, true, false, false, true}, {true, true, false, true, true}, {true, true, true, true, true}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{4}}, {{1}}, {{0}}, {{3}}, {{false}}, {{true, true, false, false, true}, {true, true, false, true, true}, {true, true, true, true, true}, {false, false, true, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{4}}, {{1}}, {{1}}, {{2}}, {{true}}, {{true, true, false, true, true}, {true, true, false, true, true}, {true, true, true, true, true}, {false, false, true, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{1}}, {{2}}, {{false}}, {{true, true, false, true, true}, {true, true, false, true, true}, {true, true, true, true, true}, {false, false, true, true, false}, {false, true, false, false, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{0}}, {{2}}, {{true}}, {{true, true, false, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {false, false, true, true, false}, {false, true, false, false, true}}, null, null, null},
{{{5}}, {{4}}, {{0}}, {{0}}, {{2}}, {{false}}, {{true, true, false, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {false, true, true, true, false}, {false, true, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{1}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{0}}, {{1}}, {{3}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{3}}, {{3}}, {{false}}, {{true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{2}}, {{3}}, {{true}}, {{true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{2}}, {{3}}, {{false}}, {{true, true, false, false, false}, {false, false, true, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{2}}, {{4}}, {{true}}, {{true, true, false, false, false}, {false, false, true, false, false}, {false, false, false, true, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{2}}, {{4}}, {{false}}, {{true, true, true, false, false}, {false, false, true, false, false}, {false, false, false, true, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{3}}, {{4}}, {{true}}, {{true, true, true, false, false}, {false, false, true, false, false}, {false, false, false, true, true}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{3}}, {{4}}, {{false}}, {{true, true, true, false, false}, {false, true, true, false, false}, {false, false, false, true, true}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{4}}, {{3}}, {{true}}, {{true, true, true, false, false}, {false, true, true, false, false}, {false, false, false, true, true}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{4}}, {{3}}, {{false}}, {{true, true, true, false, false}, {false, true, true, false, false}, {false, true, false, true, true}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{4}}, {{2}}, {{true}}, {{true, true, true, false, false}, {false, true, true, false, false}, {false, true, false, true, true}, {false, false, false, true, true}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{2}}, {{2}}, {{4}}, {{2}}, {{false}}, {{true, true, true, false, false}, {false, true, true, false, false}, {false, true, false, true, true}, {false, true, false, true, true}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{2}}, {{2}}, {{3}}, {{2}}, {{true}}, {{true, true, true, false, false}, {false, true, true, false, false}, {false, true, false, true, true}, {false, true, false, true, true}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{3}}, {{2}}, {{false}}, {{true, true, true, false, false}, {false, true, true, false, false}, {false, true, true, true, true}, {false, true, false, true, true}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{4}}, {{1}}, {{true}}, {{true, true, true, false, false}, {false, true, true, false, false}, {false, true, true, true, true}, {false, true, true, true, true}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{4}}, {{4}}, {{1}}, {{false}}, {{true, true, true, false, false}, {false, true, true, true, false}, {false, true, true, true, true}, {false, true, true, true, true}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{4}}, {{3}}, {{0}}, {{true}}, {{true, true, true, false, false}, {false, true, true, true, false}, {false, true, true, true, true}, {false, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{0}}, {{3}}, {{3}}, {{0}}, {{false}}, {{true, true, true, false, false}, {false, true, true, true, true}, {false, true, true, true, true}, {false, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{0}}, {{3}}, {{4}}, {{0}}, {{true}}, {{true, true, true, false, false}, {false, true, true, true, true}, {false, true, true, true, true}, {true, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{0}}, {{4}}, {{4}}, {{0}}, {{false}}, {{true, true, true, true, false}, {false, true, true, true, true}, {false, true, true, true, true}, {true, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{3}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{3}}, {{3}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{3}}, {{2}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{3}}, {{2}}, {{false}}, {{true, false, false, false, false}, {false, true, true, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{4}}, {{2}}, {{true}}, {{true, false, false, false, false}, {false, true, true, false, false}, {false, false, false, false, false}, {false, false, true, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{4}}, {{2}}, {{false}}, {{true, false, false, false, false}, {false, true, true, false, false}, {false, true, false, false, false}, {false, false, true, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{4}}, {{1}}, {{true}}, {{true, false, false, false, false}, {false, true, true, false, false}, {false, true, false, false, false}, {false, false, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{4}}, {{0}}, {{4}}, {{1}}, {{false}}, {{true, false, false, false, false}, {false, true, true, false, false}, {false, true, false, false, false}, {false, true, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{4}}, {{0}}, {{3}}, {{0}}, {{true}}, {{true, false, false, false, false}, {false, true, true, false, false}, {false, true, false, false, false}, {false, true, true, true, false}, {false, true, true, false, true}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{3}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{1}}, {{3}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{1}}, {{4}}, {{2}}, {{true}}, {{true, false, false, false, false}, {true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{2}}, {{false}}, {{true, true, false, false, false}, {true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{3}}, {{2}}, {{true}}, {{true, true, false, false, false}, {true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{3}}, {{2}}, {{false}}, {{true, true, false, false, false}, {true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{3}}, {{1}}, {{true}}, {{true, true, false, false, false}, {true, true, false, false, false}, {false, false, false, false, false}, {false, false, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{3}}, {{1}}, {{false}}, {{true, true, true, false, false}, {true, true, false, false, false}, {false, false, false, false, false}, {false, false, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{2}}, {{0}}, {{true}}, {{true, true, true, false, false}, {true, true, false, false, false}, {false, false, false, false, false}, {false, true, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{0}}, {{3}}, {{2}}, {{0}}, {{false}}, {{true, true, true, false, false}, {true, true, true, false, false}, {false, false, false, false, false}, {false, true, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{0}}, {{3}}, {{3}}, {{0}}, {{true}}, {{true, true, true, false, false}, {true, true, true, false, false}, {true, false, false, false, false}, {false, true, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{1}}, {{4}}, {{3}}, {{0}}, {{false}}, {{true, true, true, true, false}, {true, true, true, false, false}, {true, false, false, false, false}, {false, true, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{1}}, {{4}}, {{4}}, {{0}}, {{true}}, {{true, true, true, true, false}, {true, true, true, false, false}, {true, false, false, false, false}, {true, true, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{4}}, {{0}}, {{false}}, {{true, true, true, true, false}, {true, true, true, false, true}, {true, false, false, false, false}, {true, true, true, true, false}, {false, false, true, false, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{4}}, {{1}}, {{true}}, {{true, true, true, true, false}, {true, true, true, false, true}, {true, false, false, false, false}, {true, true, true, true, false}, {true, false, true, false, true}}, null, null, null},
{{{5}}, {{2}}, {{3}}, {{4}}, {{1}}, {{false}}, {{true, true, true, true, false}, {true, true, true, true, true}, {true, false, false, false, false}, {true, true, true, true, false}, {true, false, true, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{4}}, {{3}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{4}}, {{2}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{0}}, {{4}}, {{2}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{3}}, {{0}}, {{3}}, {{2}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{3}}, {{2}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, false, false, false}, {true, false, false, false, false}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{3}}, {{1}}, {{2}}, {{2}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, false, false, false}, {true, false, true, false, false}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{4}}, {{0}}, {{2}}, {{2}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, false, false, false}, {true, true, true, false, false}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{4}}, {{0}}, {{2}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, true, false, false}, {true, true, true, false, false}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{4}}, {{1}}, {{2}}, {{3}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, true, false, false}, {true, true, true, false, false}, {true, false, true, true, true}}, null, null, null},
{{{5}}, {{4}}, {{1}}, {{1}}, {{4}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, true, true, false}, {true, true, true, false, false}, {true, false, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{3}}, {{4}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{3}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{3}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{3}}, {{3}}, {{false}}, {{true, false, true, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{2}}, {{3}}, {{true}}, {{true, false, true, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{2}}, {{3}}, {{false}}, {{true, false, true, false, false}, {false, true, true, false, false}, {false, false, false, false, false}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{2}}, {{2}}, {{true}}, {{true, false, true, false, false}, {false, true, true, false, false}, {false, false, false, true, false}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{4}}, {{2}}, {{2}}, {{false}}, {{true, false, true, false, false}, {false, true, true, true, false}, {false, false, false, true, false}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{4}}, {{3}}, {{2}}, {{true}}, {{true, false, true, false, false}, {false, true, true, true, false}, {false, false, true, true, false}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{3}}, {{3}}, {{2}}, {{false}}, {{true, false, true, false, true}, {false, true, true, true, false}, {false, false, true, true, false}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{3}}, {{3}}, {{1}}, {{true}}, {{true, false, true, false, true}, {false, true, true, true, false}, {false, false, true, true, false}, {false, false, true, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{4}}, {{3}}, {{1}}, {{false}}, {{true, false, true, true, true}, {false, true, true, true, false}, {false, false, true, true, false}, {false, false, true, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{4}}, {{3}}, {{0}}, {{true}}, {{true, false, true, true, true}, {false, true, true, true, false}, {false, false, true, true, false}, {false, true, true, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{4}}, {{3}}, {{0}}, {{false}}, {{true, false, true, true, true}, {false, true, true, true, true}, {false, false, true, true, false}, {false, true, true, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{4}}, {{4}}, {{1}}, {{true}}, {{true, false, true, true, true}, {false, true, true, true, true}, {false, false, true, true, false}, {true, true, true, true, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{3}}, {{4}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{3}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{0}}, {{2}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{2}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{1}}, {{1}}, {{4}}, {{true}}, {{true, false, false, false, false}, {true, true, false, false, false}, {false, false, false, true, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{2}}, {{1}}, {{4}}, {{false}}, {{true, false, false, false, false}, {true, true, false, false, false}, {false, true, false, true, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{2}}, {{2}}, {{0}}, {{3}}, {{true}}, {{true, false, false, false, false}, {true, true, false, false, true}, {false, true, false, true, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{0}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, true, false, false, true}, {false, true, true, true, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{0}}, {{4}}, {{true}}, {{true, false, false, true, false}, {true, true, false, false, true}, {false, true, true, true, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{0}}, {{4}}, {{false}}, {{true, false, false, true, false}, {true, true, false, true, true}, {false, true, true, true, false}, {false, false, false, false, true}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{0}}, {{1}}, {{4}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}}, null, null, null},
{{{5}}, {{0}}, {{1}}, {{4}}, {{3}}, {{true}}, {{true, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{3}}, {{false}}, {{true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, true}}, null, null, null},
{{{5}}, {{1}}, {{1}}, {{4}}, {{2}}, {{true}}, {{true, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{4}}, {{2}}, {{false}}, {{true, true, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, false, true, true}}, null, null, null},
{{{5}}, {{0}}, {{2}}, {{4}}, {{1}}, {{true}}, {{true, true, false, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{4}}, {{1}}, {{false}}, {{true, true, true, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, false, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{3}}, {{3}}, {{1}}, {{true}}, {{true, true, true, false, false}, {false, true, false, false, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{2}}, {{2}}, {{3}}, {{1}}, {{false}}, {{true, true, true, false, false}, {false, true, false, true, false}, {false, false, false, false, false}, {false, false, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{2}}, {{2}}, {{2}}, {{1}}, {{true}}, {{true, true, true, false, false}, {false, true, false, true, false}, {false, false, false, false, false}, {false, true, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{2}}, {{3}}, {{2}}, {{1}}, {{false}}, {{true, true, true, false, false}, {false, true, false, true, false}, {false, false, true, false, false}, {false, true, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{2}}, {{3}}, {{3}}, {{0}}, {{true}}, {{true, true, true, false, false}, {false, true, false, true, false}, {false, true, true, false, false}, {false, true, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{3}}, {{0}}, {{false}}, {{true, true, true, false, false}, {false, true, false, true, false}, {false, true, true, true, false}, {false, true, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{2}}, {{2}}, {{0}}, {{true}}, {{true, true, true, false, false}, {false, true, false, true, false}, {false, true, true, true, false}, {true, true, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{0}}, {{3}}, {{2}}, {{0}}, {{false}}, {{true, true, true, false, false}, {false, true, true, true, false}, {false, true, true, true, false}, {true, true, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{0}}, {{3}}, {{1}}, {{0}}, {{true}}, {{true, true, true, false, false}, {false, true, true, true, false}, {true, true, true, true, false}, {true, true, false, false, false}, {false, true, true, true, true}}, null, null, null},
{{{5}}, {{1}}, {{4}}, {{1}}, {{0}}, {{false}}, {{true, true, true, true, false}, {false, true, true, true, false}, {true, true, true, true, false}, {true, true, false, false, false}, {false, true, true, true, true}}, null, null, null}
}

;

	

}