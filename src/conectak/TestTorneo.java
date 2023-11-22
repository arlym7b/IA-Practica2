package conectak;

import jugadores.JugadorAleatorio;
import jugadores.JugadorAlfaBeta;
import jugadores.JugadorEvaluar;
import mundoadversario.Juego;

public class TestTorneo {
    public static int N_PARTIDAS = 1000;

    public static void main(String[] args) {
        System.out.println("                              Gana 1   Empate   Gana 2");
        System.out.println("------------------------------------------------------");

        torneo_aleatorio_aleatorio();
        torneo_aleatorio_evaluar();
        torneo_aleatorio_alfabeta(2);
        torneo_aleatorio_alfabeta(3);
        torneo_aleatorio_alfabeta(4);
        torneo_aleatorio_alfabeta(5);
        torneo_aleatorio_alfabeta(6);
    }

    private static void torneo_aleatorio_alfabeta(int profundidad) {
        ConectaK e = new ConectaK(3,3,3);
        // long semilla_j1, semilla_j2
        JugadorAleatorio j1 = new JugadorAleatorio<>();
        JugadorEvaluar j2 = new JugadorAlfaBeta(new EvaluadorCK(), profundidad);
        Juego juego = new Juego<>(j1, j2, e);

        System.out.print("Aleatorio vs AlfaBeta (p=" + profundidad + ")   ");
        ver_torneo(juego);
        System.out.println();
    }

    private static void torneo_aleatorio_evaluar() {
        ConectaK e = new ConectaK(3,3,3);
        // long semilla_j1, semilla_j2
        JugadorAleatorio j1 = new JugadorAleatorio<>();
        JugadorEvaluar j2 = new JugadorEvaluar<>(new EvaluadorCK());
        Juego juego = new Juego<>(j1, j2, e);

        System.out.print("Aleatorio vs Evaluar          ");
        ver_torneo(juego);
        System.out.println();
    }

    private static void torneo_aleatorio_aleatorio() {
        ConectaK e = new ConectaK(3,3,3);
        // long semilla_j1, semilla_j2
        JugadorAleatorio j1 = new JugadorAleatorio<>();
        JugadorAleatorio j2 = new JugadorAleatorio<>();
        Juego juego = new Juego<>(j1, j2, e);

        System.out.print("Aleatorio vs Aleatorio        ");
        ver_torneo(juego);
        System.out.println();
    }

    private static void ver_torneo(Juego juego) {
        juego.jugar_torneo(N_PARTIDAS, true);
    }
}
