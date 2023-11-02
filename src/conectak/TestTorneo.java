package conectak;

import jugadores.JugadorAleatorio;
import jugadores.JugadorEvaluar;
import mundoadversario.Juego;

public class TestTorneo {
    public static int N_PARTIDAS = 1000;

    public static void main(String[] args) {
        torneo_aleatorio_aleatorio();
        torneo_aleatorio_evaluar();
    }

    private static void torneo_aleatorio_evaluar() {
        ConectaK e = new ConectaK(3,3,3);
        // long semilla_j1, semilla_j2
        JugadorAleatorio j1 = new JugadorAleatorio<>();
        JugadorEvaluar j2 = new JugadorEvaluar<>(new EvaluadorCK());
        Juego juego = new Juego<>(j1, j2, e);

        System.out.println("Resultados del torneo Aleatorio vs Evaluar");
        ver_torneo(juego);
    }

    private static void torneo_aleatorio_aleatorio() {
        ConectaK e = new ConectaK(3,3,3);
        // long semilla_j1, semilla_j2
        JugadorAleatorio j1 = new JugadorAleatorio<>();
        JugadorAleatorio j2 = new JugadorAleatorio<>();
        Juego juego = new Juego<>(j1, j2, e);

        System.out.println("Resultados del torneo Aleatorio vs Aleatorio");
        ver_torneo(juego);
    }

    private static void ver_torneo(Juego juego) {
        juego.jugar_torneo(N_PARTIDAS);
    }
}
