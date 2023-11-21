package trocho;

import jugadores.Evaluador;

public class EvaluadorTrocho extends Evaluador<Trocho> {
    @Override
    protected double evaluacion(Trocho estado, boolean miTurno) {
        int[] codificacion = estado.codifica();

        // codificacion[1] contiene el numero de casillas posibles para jugador 1
        // codificacion[2] contiene el numero de casillas posibles para jugador 2

        return miTurno ? codificacion[1] - codificacion[2] : codificacion[2] - codificacion[1];
    }
}
