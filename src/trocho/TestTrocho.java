package trocho;

import conectak.EvaluadorCK;
import jugadores.JugadorAleatorio;
import jugadores.JugadorAlfaBeta;
import jugadores.JugadorEvaluar;
import mundoadversario.Juego;

public class TestTrocho {
    public static int N_PARTIDAS = 1000;

    public static void main(String[] args) {
        //test_simple();
        test_torneo();
    }

    private static void test_torneo() {
        System.out.println("                             Gana 1   Gana 2");
        System.out.println("---------------------------------------------");

        torneo_aleatorio_aleatorio();
        torneo_aleatorio_evaluar();
        torneo_aleatorio_alfabeta(2);
        torneo_aleatorio_alfabeta(3);
        torneo_aleatorio_alfabeta(4);
        torneo_aleatorio_alfabeta(5);
        torneo_aleatorio_alfabeta(6);
    }

    private static void torneo_aleatorio_alfabeta(int profundidad) {
        Trocho e = new Trocho(5);
        // long semilla_j1, semilla_j2
        JugadorAleatorio j1 = new JugadorAleatorio<>();
        JugadorEvaluar j2 = new JugadorAlfaBeta(new EvaluadorTrocho(), profundidad);
        Juego juego = new Juego<>(j1, j2, e);

        System.out.print("Aleatorio vs AlfaBeta (p=" + profundidad + ")   ");
        ver_torneo(juego);
        System.out.println();
    }

    private static void torneo_aleatorio_evaluar() {
        Trocho e = new Trocho(5);
        // long semilla_j1, semilla_j2
        JugadorAleatorio j1 = new JugadorAleatorio<>();
        JugadorEvaluar j2 = new JugadorEvaluar(new EvaluadorTrocho());
        Juego juego = new Juego<>(j1, j2, e);

        System.out.print("Aleatorio vs Evaluar          ");
        ver_torneo(juego);
        System.out.println();

    }

    private static void torneo_aleatorio_aleatorio() {
        Trocho e = new Trocho(5);
        // long semilla_j1, semilla_j2
        JugadorAleatorio j1 = new JugadorAleatorio<>();
        JugadorAleatorio j2 = new JugadorAleatorio<>();
        Juego juego = new Juego<>(j1, j2, e);

        System.out.print("Aleatorio vs Aleatorio        ");
        ver_torneo(juego);
        System.out.println();
    }

    private static void test_simple() {
        JugadorAleatorio<Trocho> j1 = new JugadorAleatorio<>();
        JugadorAleatorio<Trocho> j2 = new JugadorAleatorio<>();

        Trocho e = new Trocho(5);
        Juego juego = new Juego<>(j1, j2, e);

        juego.jugarPartida(true);
    }

    private static void ver_torneo(Juego juego) {juego.jugar_torneo(N_PARTIDAS, false);
    }
}
