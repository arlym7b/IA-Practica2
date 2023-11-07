package trocho;

import mundosolitario.OverrideHashCode;

import java.util.Arrays;
import java.util.Objects;

public class Trocho extends OverrideHashCode {
    private boolean[][] tablero;
    private Punto posicion_j1;
    private Punto posicion_j2;

    private boolean turno1;        //true si es el turno del primer jugador
    private int tam;

    public Trocho(boolean[][] tablero, boolean turno1) {
        this.tablero = tablero;
        this.turno1 = turno1;
    }

    public Trocho(int n){
        this.tablero = new boolean[n][n];

        this.posicion_j1 = new Punto(0, 0);
        this.posicion_j2 = new Punto(n-1, n-1);

        this.turno1 = true;
        this.tam = n;
    }

    public Trocho(int tam, int fb, int cb, int fn, int cn, boolean turno1, boolean[][] ocupadas){
        this.tablero = ocupadas;

        this.posicion_j1 = new Punto(fb, cb);
        this.posicion_j2 = new Punto(fn, cn);

        this.turno1 = turno1;
        this.tam = tam;
    }

    /* GETTERS PROPIOS */

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

    /* GETTERS SOLICITADOS */

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trocho trocho = (Trocho) o;
        return turno1 == trocho.turno1 && tam == trocho.tam && Arrays.equals(tablero, trocho.tablero) && posicion_j1.equals(trocho.posicion_j1) && posicion_j2.equals(trocho.posicion_j2);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(posicion_j1, posicion_j2, turno1, tam);
        result = 31 * result + Arrays.hashCode(tablero);
        return result;
    }
}
