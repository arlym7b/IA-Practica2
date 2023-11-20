package trocho;

import agentesolitario.AgenteSolitario;
import agentesolitario.ArbolGenerico;
import agentesolitario.NodoBasico;
import mundoadversario.EstadoJuegoAprox;
import mundosolitario.OverrideHashCode;

import java.util.*;

public class Trocho extends OverrideHashCode implements EstadoJuegoAprox<Trocho> {

    /*
     *
     * VARIABLES
     *
     * */

    private boolean[][] tablero;
    private Punto posicion_j1;
    private Punto posicion_j2;

    private boolean turno1;        //true si es el turno del primer jugador
    private int tam;

    /*
     *
     * CONSTRUCTORES
     *
     * */

    // Constructur inicial
    public Trocho(int n){
        this.tablero = new boolean[n][n];

        this.posicion_j1 = new Punto(0, 0);
        this.posicion_j2 = new Punto(n-1, n-1);
        // Las posiciones iniciales quedan automáticamente bloqueadas
        this.tablero[0][0] = true;
        this.tablero[n-1][n-1] = true;

        this.turno1 = true;
        this.tam = n;
    }

    // Constructor posiciones intermedias
    public Trocho(int tam, int fb, int cb, int fn, int cn, boolean turno1, boolean[][] ocupadas){
        this.tablero = ocupadas;

        this.posicion_j1 = new Punto(fb, cb);
        this.posicion_j2 = new Punto(fn, cn);

        this.turno1 = turno1;
        this.tam = tam;
    }

    /*
     *
     *  METODOS HEREDADOS
     *
     * */

    @Override
    public boolean ganaActual() {
        return !this.turno1 && contar_accesibles(this.posicion_j2) == 0;
    }

    @Override
    public boolean ganaOtro() {
        return this.turno1 && contar_accesibles(this.posicion_j1) == 0;
    }

    @Override
    public boolean agotado() {
        return calculaSucesores().isEmpty();
    }

    @Override
    public void ver() {
        System.out.println("Turno de " + (this.turno1 ? "Jugador 1" : "Jugador 2"));

        String separador_linea = "-----------------------";
        System.out.println(separador_linea);
        for (int i = 0; i < tam; i++){
            for (int j = 0; j < tam; j++){
                if (j == 0){
                    System.out.print("|");
                }

                // Posicion del jugador 1
                if (this.tablero[i][j] && posicion_j1.getX() == i && posicion_j1.getY() == j){
                    System.out.print("1|");
                // Posicion del jugador 2
                } else if (this.tablero[i][j] && posicion_j2.getX() == i && posicion_j2.getY() == j) {
                    System.out.print("2|");
                // Posicion bloqueda
                } else if (this.tablero[i][j]) {
                    System.out.print("X|");
                // Posicion libre
                } else {
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
        System.out.println(separador_linea);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trocho trocho = (Trocho) o;
        return turno1 == trocho.turno1 && tam == trocho.tam && Arrays.deepEquals(tablero, trocho.tablero) &&
                posicion_j1.equals(trocho.posicion_j1) && posicion_j2.equals(trocho.posicion_j2);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(posicion_j1, posicion_j2, turno1, tam);
        result = 31 * result + Arrays.deepHashCode(tablero);
        return result;
    }

    @Override
    public int[] codifica() {
        int[] caracteristicas = new int[3];

        caracteristicas[0] = 1;
        //caracteristicas[0] = this.turno1 ? 1 : -1;
        caracteristicas[1] = contar_accesibles(this.posicion_j1);
        caracteristicas[2] = contar_accesibles(this.posicion_j2);

        return caracteristicas;
    }

    @Override
    public List<Trocho> calculaSucesores() {
        List<Trocho> lista_sucesores = new ArrayList<>();
        Punto punto = new Punto(0, 0);

        // Turno jugador 1
        if (this.turno1) {
            Punto posicion_actual = this.posicion_j1;

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY());
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY());
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX(), posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX(), posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }
        }

        // Turno jugador 2
        else {
            Punto posicion_actual = this.posicion_j2;

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY());
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY());
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX(), posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX(), posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY())){
                Trocho sucesor = new Trocho(this.tam, punto.getX(), punto.getY(), this.posicion_j2.getX(),
                        this.posicion_j2.getY(), !this.turno1, this.tablero);
                sucesor.tablero[punto.getX()][punto.getY()] = true;
                lista_sucesores.add(sucesor);
            }
        }

        return lista_sucesores;
    }

    /*
     *
     *  METODOS PROPIOS
     *
     * */

    private boolean punto_en_mapa(Punto punto) {
        return punto.getX() >= 0 && punto.getX() < this.tam &&
                punto.getY() >= 0 && punto.getY() < this.tam;
    }

    // Return: nº casillas accesibles por el jugador
    private int contar_accesibles(Punto posicion_jugador){
        int contador;

        if (posicion_jugador == this.posicion_j1){
            contador = contador_en_amplitud(new Trocho(this.tam, posicion_jugador.getX(),
                    posicion_jugador.getY(), this.posicion_j2.getX(),
                    this.posicion_j2.getY(), this.turno1, this.tablero));
        }
        else {
            contador = contador_en_amplitud(new Trocho(this.tam, this.posicion_j1.getX(),
                    this.posicion_j1.getY(), posicion_jugador.getX(),
                    posicion_jugador.getY(), this.turno1, this.tablero));
        }

        return contador;
    }

    // Version modificada de búsqueda en amplitud para devolver el numero de nodos visitados
    // Al no cambiar el turno, podemos mover solo un jugador hasta dejarlo sin movimientos
    private int contador_en_amplitud (Trocho e){
        ArbolGenerico<Trocho, NodoBasico<Trocho>> a = new ArbolGenerico<>();
        a.put(e, new NodoBasico<Trocho>(null));

        LinkedList<Trocho> abiertos = new LinkedList<Trocho>();
        int contador = 0;

        while (e!=null && !this.agotado()) {
            for (Trocho e2 : (List<Trocho>)e.calculaSucesores()){
                if (!a.contains(e2)) {
                    abiertos.offer(e2);
                    a.put(e2, new NodoBasico<Trocho>(e));
                }//if
            }//for
            e = abiertos.poll();
            contador++;
        }//while

        return contador;
    }

    /*
    *
    * GETTERS PROPIOS
    *
    * */

    public boolean[][] getTablero() {
        return tablero;
    }

    public Punto getPosicion_j1() {
        return posicion_j1;
    }

    public Punto getPosicion_j2() {
        return posicion_j2;
    }

    public boolean isTurno1() {
        return turno1;
    }

    public int getTam() {
        return tam;
    }

    /*
     *
     * GETTERS SOLICITADOS
     *
     * */

    public int fb(){
        return this.posicion_j1.getX();
    }

    public int cb(){
        return this.posicion_j1.getY();
    }

    public int fn(){
        return this.posicion_j2.getX();
    }

    public int cn(){
        return this.posicion_j2.getY();
    }

    public boolean ocupada(int f, int c){
        return this.tablero[f][c];
    }

    public boolean turno1(){
        return this.turno1;
    }

}
