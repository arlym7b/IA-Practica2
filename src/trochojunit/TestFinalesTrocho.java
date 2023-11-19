package trochojunit;

import org.junit.Assert;
import org.junit.Test;

import mundoadversario.EstadoJuego;



/**
 * Test para comprobar los métodos ganaActual, ganaOtro y agotado.
 * 
 * @author Lawrence
 * @version 2.0
 *
 */

public class TestFinalesTrocho extends TestTBaseTrocho{

	
	
	@Test
	public <E extends EstadoJuego<E>>void pruebaGanaOtro() {
		System.out.println("\n*******************************\nTest ganaOtro\n");
		for(int i = 0; i < ganaOtro.length; i++){
			System.out.println("Caso : " + i);
			E e = (E) creaEstado(ganaOtro[i]);

			Assert.assertEquals("ganaOtro: Estado " + i + " agotado: " + e.agotado() + " ganaActual: " + e.ganaActual(),
				e.ganaOtro(), true);
		}
	}
	
	/**
	 * Método auxiliar para ver el estado de una prueba
	 * @param i - índice de la prueba
	 */
	public void verPrueba(int i) {
		creaEstado(ganaOtro[i]).ver();
	}

	static Object[][][][] ganaOtro = 

		{
		{{{5}}, {{4}}, {{0}}, {{0}}, {{3}}, {{true}}, {{true, true, false, false, false}, {true, true, true, false, true}, {true, true, true, true, true}, {true, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{0}}, {{2}}, {{2}}, {{true}}, {{true, true, true, true, false}, {true, true, true, true, true}, {true, true, false, true, false}, {true, true, true, true, true}, {false, true, false, true, true}}, null, null, null},
		{{{5}}, {{1}}, {{2}}, {{1}}, {{3}}, {{false}}, {{true, true, true, true, true}, {true, true, false, false, true}, {false, true, true, true, true}, {false, true, false, false, true}, {false, false, false, false, true}}, null, null, null},
		{{{5}}, {{1}}, {{2}}, {{3}}, {{0}}, {{true}}, {{true, true, true, true, true}, {true, true, false, true, true}, {false, true, true, true, true}, {false, true, true, true, true}, {false, false, true, true, true}}, null, null, null},
		{{{5}}, {{2}}, {{3}}, {{2}}, {{1}}, {{true}}, {{true, true, true, false, false}, {false, false, true, true, true}, {false, false, true, false, true}, {false, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
		{{{5}}, {{0}}, {{4}}, {{4}}, {{2}}, {{true}}, {{true, true, true, true, false}, {false, false, true, true, true}, {false, false, false, false, false}, {true, true, true, true, false}, {true, true, false, false, true}}, null, null, null},
		{{{5}}, {{2}}, {{4}}, {{3}}, {{4}}, {{false}}, {{true, true, false, true, false}, {false, false, true, true, false}, {false, false, true, true, false}, {false, false, true, true, false}, {false, false, false, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{3}}, {{1}}, {{3}}, {{true}}, {{true, true, true, true, false}, {true, true, true, false, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, false, true}}, null, null, null},
		{{{5}}, {{1}}, {{3}}, {{2}}, {{0}}, {{false}}, {{true, true, false, false, false}, {true, true, true, false, false}, {false, true, true, true, false}, {true, true, true, true, false}, {true, true, false, false, true}}, null, null, null},
		{{{5}}, {{4}}, {{1}}, {{1}}, {{4}}, {{false}}, {{true, true, true, true, true}, {true, true, true, true, false}, {true, true, true, true, true}, {false, true, true, true, true}, {false, false, true, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{0}}, {{0}}, {{4}}, {{false}}, {{true, true, true, true, false}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
		{{{5}}, {{3}}, {{0}}, {{0}}, {{4}}, {{false}}, {{true, true, true, true, false}, {true, true, false, true, true}, {true, false, true, true, true}, {false, true, true, true, false}, {true, true, true, false, true}}, null, null, null},
		{{{5}}, {{4}}, {{2}}, {{4}}, {{0}}, {{true}}, {{true, true, false, false, false}, {true, false, false, false, false}, {false, true, true, false, false}, {false, true, true, true, true}, {false, true, false, true, true}}, null, null, null},
		{{{5}}, {{2}}, {{4}}, {{3}}, {{2}}, {{true}}, {{true, true, true, true, true}, {false, false, false, true, true}, {false, false, true, true, false}, {false, false, false, true, true}, {false, false, true, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{2}}, {{2}}, {{4}}, {{false}}, {{true, false, false, false, false}, {false, true, false, true, true}, {false, true, true, true, false}, {true, true, false, true, true}, {true, true, false, true, true}}, null, null, null},
		{{{5}}, {{0}}, {{1}}, {{2}}, {{2}}, {{true}}, {{true, false, true, false, false}, {true, true, true, false, false}, {false, false, false, true, true}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
		{{{5}}, {{1}}, {{2}}, {{0}}, {{4}}, {{false}}, {{true, true, true, true, false}, {true, true, false, true, true}, {true, true, false, true, true}, {true, false, false, true, false}, {false, false, false, false, true}}, null, null, null},
		{{{5}}, {{4}}, {{0}}, {{4}}, {{2}}, {{false}}, {{true, false, false, false, false}, {false, true, false, false, false}, {true, true, true, true, false}, {true, true, true, true, false}, {false, true, false, true, true}}, null, null, null},
		{{{5}}, {{3}}, {{0}}, {{1}}, {{4}}, {{true}}, {{true, true, true, true, true}, {true, true, true, true, false}, {true, true, true, false, false}, {false, true, true, true, false}, {true, true, false, false, true}}, null, null, null},
		{{{5}}, {{0}}, {{4}}, {{4}}, {{1}}, {{true}}, {{true, true, true, true, false}, {true, true, true, true, true}, {true, true, true, false, true}, {false, true, false, true, true}, {true, false, false, false, true}}, null, null, null},
		{{{5}}, {{1}}, {{0}}, {{2}}, {{3}}, {{true}}, {{true, true, false, false, false}, {false, true, true, false, false}, {true, true, false, false, false}, {false, false, true, true, true}, {false, false, true, true, true}}, null, null, null},
		{{{5}}, {{2}}, {{1}}, {{4}}, {{0}}, {{false}}, {{true, false, true, true, true}, {true, true, true, true, true}, {false, false, false, false, true}, {true, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{0}}, {{4}}, {{2}}, {{false}}, {{true, true, false, true, false}, {true, true, true, true, false}, {true, true, true, true, false}, {true, true, true, true, true}, {false, true, false, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{0}}, {{0}}, {{1}}, {{true}}, {{true, false, false, false, false}, {true, true, false, false, false}, {true, true, false, false, false}, {true, true, true, false, false}, {false, true, true, true, true}}, null, null, null},
		{{{5}}, {{2}}, {{4}}, {{4}}, {{3}}, {{true}}, {{true, false, false, true, true}, {false, true, false, true, true}, {false, true, true, true, false}, {false, true, true, true, true}, {false, true, true, false, true}}, null, null, null},
		{{{5}}, {{2}}, {{3}}, {{4}}, {{1}}, {{false}}, {{true, true, false, false, false}, {true, true, false, false, false}, {true, true, true, false, false}, {true, true, true, true, false}, {true, false, true, true, true}}, null, null, null},
		{{{5}}, {{2}}, {{4}}, {{2}}, {{0}}, {{false}}, {{true, true, true, true, true}, {true, true, true, true, true}, {false, true, false, false, false}, {true, true, true, true, true}, {true, true, false, false, true}}, null, null, null},
		{{{5}}, {{2}}, {{4}}, {{0}}, {{1}}, {{false}}, {{true, false, true, false, false}, {true, true, true, true, false}, {true, false, true, true, false}, {false, true, true, true, true}, {false, true, true, true, true}}, null, null, null},
		{{{5}}, {{3}}, {{2}}, {{1}}, {{4}}, {{false}}, {{true, true, false, true, true}, {false, true, true, true, false}, {false, true, true, true, true}, {false, false, false, true, true}, {false, false, false, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{1}}, {{0}}, {{4}}, {{false}}, {{true, true, true, true, false}, {true, true, true, true, true}, {true, true, false, true, true}, {true, true, false, true, true}, {true, false, false, false, true}}, null, null, null},
		{{{5}}, {{4}}, {{0}}, {{0}}, {{2}}, {{true}}, {{true, false, false, false, false}, {true, true, true, false, false}, {true, true, true, false, false}, {true, true, true, true, false}, {false, true, true, false, true}}, null, null, null},
		{{{5}}, {{1}}, {{1}}, {{0}}, {{4}}, {{false}}, {{true, true, false, true, false}, {false, false, true, true, true}, {false, true, true, true, true}, {false, false, true, false, true}, {false, false, false, false, true}}, null, null, null},
		{{{5}}, {{2}}, {{4}}, {{3}}, {{4}}, {{true}}, {{true, true, true, true, true}, {true, true, true, true, true}, {false, false, true, true, false}, {false, true, true, true, false}, {true, true, true, true, true}}, null, null, null},
		{{{5}}, {{0}}, {{4}}, {{2}}, {{1}}, {{true}}, {{true, true, true, true, false}, {true, true, true, true, true}, {false, false, true, true, false}, {false, true, true, true, true}, {false, false, true, true, true}}, null, null, null},
		{{{5}}, {{0}}, {{1}}, {{0}}, {{3}}, {{true}}, {{true, false, true, false, true}, {true, true, true, true, false}, {true, true, true, true, false}, {true, true, true, true, true}, {false, false, false, true, true}}, null, null, null},
		{{{5}}, {{3}}, {{3}}, {{3}}, {{2}}, {{false}}, {{true, true, true, true, false}, {true, true, true, true, false}, {true, true, true, true, true}, {true, true, false, false, true}, {true, true, true, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{1}}, {{3}}, {{4}}, {{false}}, {{true, false, true, true, false}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, false}, {true, false, true, true, true}}, null, null, null},
		{{{5}}, {{2}}, {{2}}, {{4}}, {{3}}, {{false}}, {{true, true, false, false, false}, {true, true, false, false, false}, {true, true, false, false, true}, {true, true, true, true, true}, {false, true, true, false, true}}, null, null, null},
		{{{5}}, {{4}}, {{1}}, {{4}}, {{2}}, {{true}}, {{true, false, false, false, false}, {false, true, false, false, false}, {false, true, true, false, false}, {true, true, true, true, false}, {true, false, false, false, true}}, null, null, null},
		{{{5}}, {{4}}, {{3}}, {{4}}, {{2}}, {{false}}, {{true, false, false, false, false}, {false, true, true, false, false}, {false, false, false, true, true}, {false, true, true, true, true}, {false, true, false, false, true}}, null, null, null},
		{{{5}}, {{1}}, {{2}}, {{4}}, {{3}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, false}, {true, true, false, true, true}, {true, true, true, true, true}, {true, true, true, false, true}}, null, null, null},
		{{{5}}, {{2}}, {{1}}, {{0}}, {{3}}, {{true}}, {{true, true, true, false, false}, {true, true, true, true, false}, {true, false, true, false, false}, {true, true, true, true, false}, {false, true, false, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{2}}, {{2}}, {{0}}, {{true}}, {{true, true, false, false, false}, {false, true, false, false, false}, {false, false, true, true, true}, {true, true, true, true, false}, {true, true, false, true, true}}, null, null, null},
		{{{5}}, {{0}}, {{3}}, {{0}}, {{4}}, {{true}}, {{true, true, true, false, false}, {false, false, true, true, true}, {false, false, false, false, true}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
		{{{5}}, {{2}}, {{2}}, {{4}}, {{0}}, {{false}}, {{true, false, false, false, false}, {true, false, false, false, false}, {true, true, false, false, false}, {true, true, true, false, false}, {false, true, true, true, true}}, null, null, null},
		{{{5}}, {{1}}, {{0}}, {{3}}, {{1}}, {{true}}, {{true, true, true, true, false}, {false, true, true, true, true}, {true, true, true, true, true}, {false, false, false, true, true}, {false, false, false, false, true}}, null, null, null},
		{{{5}}, {{1}}, {{3}}, {{2}}, {{0}}, {{false}}, {{true, true, true, true, true}, {true, true, true, false, true}, {false, true, true, true, false}, {true, true, true, false, true}, {true, true, false, false, true}}, null, null, null},
		{{{5}}, {{0}}, {{2}}, {{4}}, {{3}}, {{false}}, {{true, false, false, true, false}, {true, true, true, true, false}, {true, true, true, false, false}, {false, true, true, true, true}, {false, false, true, false, true}}, null, null, null},
		{{{5}}, {{4}}, {{2}}, {{1}}, {{4}}, {{true}}, {{true, true, true, false, false}, {true, true, true, true, false}, {true, true, false, true, true}, {true, true, true, true, true}, {true, true, false, true, true}}, null, null, null},
		{{{5}}, {{4}}, {{1}}, {{2}}, {{2}}, {{false}}, {{true, true, true, true, true}, {true, true, true, true, true}, {true, true, false, true, true}, {true, true, true, true, false}, {false, false, false, false, true}}, null, null, null}
		}

		;

	
}
