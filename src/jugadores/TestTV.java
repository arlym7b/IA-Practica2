package jugadores;

import conectak.ConectaK;
import conectak.JugadorHumanoCK;
import mundoadversario.Juego;

public class TestTV {
    public static void main(String[] args) {
        int entrena = 2000;
        entrenaTV(entrena);
    }

    private static void entrenaTV(int entrena) {
        ConectaK e = new ConectaK(3,3,3);

        JugadorEvaluarTV j1 = new JugadorEvaluarTV();
        Jugador j2 = new JugadorAleatorio();

        double epsilon = 0.1;

        System.out.println("Entrenando");
        for (int i = 0; i < entrena; i++){
            j1.aprendeTurno2(j2, e, epsilon);
        }

        Jugador j3 = new JugadorHumanoCK();
        Juego jj = new Juego(j1, j3, e);

        System.out.println("A jugar");
        for (int i = 0; i < 10; i++){
            System.out.println("Nueva partida:");
            jj.jugarPartida(true);
        }
    }
}
