package trocho;

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
        return !this.turno1 && contador_disponibles(this.posicion_j2, copiar_tablero(this.tablero)) == 0;
    }

    @Override
    public boolean ganaOtro() {
        return this.turno1 && contador_disponibles(this.posicion_j1, copiar_tablero(this.tablero)) == 0;
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
                if (posicion_j1.getX() == i && posicion_j1.getY() == j){
                    System.out.print("1|");
                // Posicion del jugador 2
                } else if (posicion_j2.getX() == i && posicion_j2.getY() == j) {
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
        return turno1 == trocho.turno1 && tam == trocho.tam && Arrays.deepEquals(tablero, trocho.tablero) && Objects.equals(posicion_j1, trocho.posicion_j1) && Objects.equals(posicion_j2, trocho.posicion_j2);
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
        caracteristicas[1] = contador_disponibles(this.posicion_j1, copiar_tablero(this.tablero));
        caracteristicas[2] = contador_disponibles(this.posicion_j2, copiar_tablero(this.tablero));

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
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), punto.getX(), punto.getY(), this.getPosicion_j2().getX(),
                        this.getPosicion_j2().getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY());
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), punto.getX(), punto.getY(), this.getPosicion_j2().getX(),
                        this.getPosicion_j2().getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX(), posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), punto.getX(), punto.getY(), this.getPosicion_j2().getX(),
                        this.getPosicion_j2().getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX(), posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), punto.getX(), punto.getY(), this.getPosicion_j2().getX(),
                        this.getPosicion_j2().getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), punto.getX(), punto.getY(), this.getPosicion_j2().getX(),
                        this.getPosicion_j2().getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), punto.getX(), punto.getY(), this.getPosicion_j2().getX(),
                        this.getPosicion_j2().getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), punto.getX(), punto.getY(), this.getPosicion_j2().getX(),
                        this.getPosicion_j2().getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), punto.getX(), punto.getY(), this.getPosicion_j2().getX(),
                        this.getPosicion_j2().getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }
        }

        // Turno jugador 2
        else {
            Punto posicion_actual = this.posicion_j2;

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY());
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), this.getPosicion_j1().getX(), this.getPosicion_j1().getY(),
                        punto.getX(), punto.getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY());
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), this.getPosicion_j1().getX(), this.getPosicion_j1().getY(),
                        punto.getX(), punto.getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX(), posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), this.getPosicion_j1().getX(), this.getPosicion_j1().getY(),
                        punto.getX(), punto.getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX(), posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), this.getPosicion_j1().getX(), this.getPosicion_j1().getY(),
                        punto.getX(), punto.getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), this.getPosicion_j1().getX(), this.getPosicion_j1().getY(),
                        punto.getX(), punto.getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() + 1, posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), this.getPosicion_j1().getX(), this.getPosicion_j1().getY(),
                        punto.getX(), punto.getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY() + 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), this.getPosicion_j1().getX(), this.getPosicion_j1().getY(),
                        punto.getX(), punto.getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
                lista_sucesores.add(sucesor);
            }

            punto.setPunto(posicion_actual.getX() - 1, posicion_actual.getY() - 1);
            if (punto_en_mapa(punto) && !ocupada(punto.getX(), punto.getY()) && !colision(punto)){
                Trocho sucesor = new Trocho(this.getTam(), this.getPosicion_j1().getX(), this.getPosicion_j1().getY(),
                        punto.getX(), punto.getY(), !this.isTurno1(), copiar_tablero(this.getTablero()));
                sucesor.tablero[posicion_actual.getX()][posicion_actual.getY()] = true;
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
    private int contador_disponibles(Punto posicion, boolean[][] tablero){
        int contador = 0;
        LinkedList<Punto> abiertos = new LinkedList<>();
        Punto punto = new Punto(0, 0);

        abiertos.add(posicion);

        // Cuando nos quedemos sin puntos en abiertos, no quedan posiciones disponibles por añadir
        while (!abiertos.isEmpty()){
            // Cogemos el siguiente punto de abiertos y calculamos sus sucesores
            punto = abiertos.poll();
            List<Punto> nuevos_sucesores = puntos_sucesores(punto, tablero);
            // Añadimos los nuevos sucesores a abiertos y aumentamos el contador para cada uno
            for (Punto sucesor : nuevos_sucesores) {
                abiertos.add(sucesor);
                contador++;
            }
            // Marcamos el nodo actual como ya visitado
            tablero[punto.getX()][punto.getY()] = true;
        }

        return contador;
    }

    private List<Punto> puntos_sucesores(Punto punto, boolean[][] tablero) {
        List<Punto> sucesores = new ArrayList<>();
        Punto nuevo_punto = new Punto(0, 0);

        nuevo_punto.setPunto(punto.getX() + 1, punto.getY());
        if (punto_en_mapa(nuevo_punto) && !tablero[nuevo_punto.getX()][nuevo_punto.getY()] && !colision(nuevo_punto)){
            tablero[nuevo_punto.getX()][nuevo_punto.getY()] = true;
            sucesores.add(new Punto(nuevo_punto.getX(), nuevo_punto.getY()));
        }

        nuevo_punto.setPunto(punto.getX() - 1, punto.getY());
        if (punto_en_mapa(nuevo_punto) && !tablero[nuevo_punto.getX()][nuevo_punto.getY()] && !colision(nuevo_punto)){
            tablero[nuevo_punto.getX()][nuevo_punto.getY()] = true;
            sucesores.add(new Punto(nuevo_punto.getX(), nuevo_punto.getY()));
        }

        nuevo_punto.setPunto(punto.getX(), punto.getY() + 1);
        if (punto_en_mapa(nuevo_punto) && !tablero[nuevo_punto.getX()][nuevo_punto.getY()] && !colision(nuevo_punto)){
            tablero[nuevo_punto.getX()][nuevo_punto.getY()] = true;
            sucesores.add(new Punto(nuevo_punto.getX(), nuevo_punto.getY()));
        }

        nuevo_punto.setPunto(punto.getX(), punto.getY() - 1);
        if (punto_en_mapa(nuevo_punto) && !tablero[nuevo_punto.getX()][nuevo_punto.getY()] && !colision(nuevo_punto)){
            tablero[nuevo_punto.getX()][nuevo_punto.getY()] = true;
            sucesores.add(new Punto(nuevo_punto.getX(), nuevo_punto.getY()));
        }

        nuevo_punto.setPunto(punto.getX() + 1, punto.getY() + 1);
        if (punto_en_mapa(nuevo_punto) && !tablero[nuevo_punto.getX()][nuevo_punto.getY()] && !colision(nuevo_punto)){
            tablero[nuevo_punto.getX()][nuevo_punto.getY()] = true;
            sucesores.add(new Punto(nuevo_punto.getX(), nuevo_punto.getY()));
        }

        nuevo_punto.setPunto(punto.getX() + 1, punto.getY() - 1);
        if (punto_en_mapa(nuevo_punto) && !tablero[nuevo_punto.getX()][nuevo_punto.getY()] && !colision(nuevo_punto)){
            tablero[nuevo_punto.getX()][nuevo_punto.getY()] = true;
            sucesores.add(new Punto(nuevo_punto.getX(), nuevo_punto.getY()));
        }

        nuevo_punto.setPunto(punto.getX() - 1, punto.getY() + 1);
        if (punto_en_mapa(nuevo_punto) && !tablero[nuevo_punto.getX()][nuevo_punto.getY()] && !colision(nuevo_punto)){
            tablero[nuevo_punto.getX()][nuevo_punto.getY()] = true;
            sucesores.add(new Punto(nuevo_punto.getX(), nuevo_punto.getY()));
        }

        nuevo_punto.setPunto(punto.getX() - 1, punto.getY() - 1);
        if (punto_en_mapa(nuevo_punto) && !tablero[nuevo_punto.getX()][nuevo_punto.getY()] && !colision(nuevo_punto)){
            tablero[nuevo_punto.getX()][nuevo_punto.getY()] = true;
            sucesores.add(new Punto(nuevo_punto.getX(), nuevo_punto.getY()));
        }

        return sucesores;
    }

    private boolean[][] copiar_tablero(boolean[][] original){
        boolean[][] copia = new boolean[original.length][original[0].length];

        for (int i = 0; i < original.length; i++){
            for (int j = 0; j < original[i].length; j++){
                copia[i][j] = original[i][j];
            }
        }

        return copia;
    }

    private boolean colision(Punto punto) {
        return this.posicion_j1.equals(punto) || this.posicion_j2.equals(punto);
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
