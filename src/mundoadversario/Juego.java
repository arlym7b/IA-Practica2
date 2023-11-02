package mundoadversario;

import jugadores.Jugador;

import java.text.DecimalFormat;

/**
 * @version 2017-12-21
 * @author Lorenzo Mandow
 * 
 * Entorno formado por el estado de un juego y dos jugadores cuyos
 * movimientos se alternan por turnos.
 */
public class Juego<E extends EstadoJuego<E>, J1 extends Jugador<E>, J2 extends Jugador<E>> {
    public J1 jug1;
    public J2 jug2;
    E eIni;
    
    /**
     * Constructor. Recibe los dos jugadores y el estado inicial del juego.
     * 
     * @param j1  Jugador 1 (empieza el juego).
     * @param j2  Jugador 2.
     * @param eIni  Estado inicial de un juego.
     * 
     */
    
    public Juego (J1 j1, J2 j2, E eIni){
        this.jug1 = j1;
        this.jug2 = j2;
        this.eIni = eIni;
    }
    
    /**
     * Controla el desarrollo de la partida hasta que haya terminado.
     * 
     * @param eco   Permite opcionalmente mostrar por pantalla el desarrollo del juego.
     * @return      1, 0, -1, según gane el primer jugador, haya empate, o gane el segundo jugador, respectivamente.
     */
    public int jugarPartida (boolean eco) {
        
    	E e = this.eIni;
        Jugador<E> jug;     
        
        if(eco){e.ver();}
        
        //Desarrollo de la partida
        while(!(e.ganaActual() || e.ganaOtro() || e.agotado())){
        	 jug = e.turno1() ? this.jug1 : this.jug2;     // Le toca a jug.
             e = jug.mueve(e);
             if (eco) {e.ver();}
        }//while
        
        //Mostramos el último tablero
        if (eco) {
        	System.out.println("\nPOSICIÓN FINAL DEL JUEGO:\n");
        	e.ver();
        }
        
        //Resultado
        if(e.ganaActual()){
        	if(eco){System.out.println("GANA EL JUGADOR ACTUAL");}
        	return e.turno1() ? 1 : -1;
        } else if (e.ganaOtro()){
        	if(eco){System.out.println("GANA EL OTRO");}
        	return e.turno1() ? -1 : 1;
        } else { //agotado
        	return 0;
        }
    }

    public void jugar_torneo(int n_partidas){
        int resultado;
        int victorias_j1 = 0;
        int victorias_j2 = 0;

        for (int i = 0; i < n_partidas; i++){
            resultado = this.jugarPartida(false);

            // Gana el primer jugador
            if (resultado == 1){
                victorias_j1++;
            // Gana el segundo jugador
            } else if (resultado == -1) {
                victorias_j2++;
            }
        }

        int empates = n_partidas - (victorias_j1 + victorias_j2);

        DecimalFormat numberFormat = new DecimalFormat("#.00%");

        System.out.println("Porcentaje Victorias Jugador1: " + numberFormat.format(
                ((double)victorias_j1/(double)n_partidas)));
        System.out.println("Porcentaje Victorias Jugador2: " + numberFormat.format(
                ((double)victorias_j2/(double)n_partidas)));
        System.out.println("Porcentaje Empates: " + numberFormat.format(
                ((double)empates/(double)n_partidas)));
    }
    
}