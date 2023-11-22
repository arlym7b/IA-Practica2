package trocho;

import jugadores.JugadorAleatorio;
import mundoadversario.Juego;

public class TestTrocho {
    public static void main(String[] args) {
        JugadorAleatorio<Trocho> j1 = new JugadorAleatorio<>();
        JugadorAleatorio<Trocho> j2 = new JugadorAleatorio<>();

        Trocho e = new Trocho(5);
        Juego juego = new Juego<>(j1, j2, e);

        juego.jugarPartida(true);
    }
}
