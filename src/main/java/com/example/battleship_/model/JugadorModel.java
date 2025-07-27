package com.example.battleship_.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa a un jugador en el juego UNO.
 * Gestiona las cartas en mano, el estado del turno y
 * la condición de haber gritado "UNO".
 *
 * @Author Jhojan Moreno
 * @version 1.0.2
 */
public class JugadorModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Lista de Barcos que el jugador tiene en su tablero
     */
    public ArrayList<Barco> Barcos;


    /**
     * Tablero logico del jugador
     */
    public Tablero tablero;

    /**
     * Nombre del jugador
     */
    private String nombre;

    /**
     * Indica si es el turno del jugador
     */
    private boolean turno;

    /**
     * Indica si el jugador ha disparado
     */
    private boolean Disparo = false;

    /**
     * Constructor que inicializa los barcos del jugador como lista vacía, incluyendo su tablero.
     */
    public JugadorModel() {
        this.Barcos = new ArrayList<>();
        this.tablero = new Tablero();
    }

    /**
     * Reinicia el tablero del jugador, junto con sus barcos
     */
    public void reset() {
        this.Barcos.clear();
        this.tablero.limpiarTablero();
        this.turno = false;
    }


    // Métodos de acceso (getters)
    /**
     * Obtiene el nombre del jugador
     * @return Nombre del jugador
     */
    public String getNombre() { return nombre; }

    /**
     * Verifica si es el turno del jugador
     * @return true si es su turno, false si no
     */
    public boolean isTurno() { return turno; }

    /**
     * Obtiene los Barcos en flota del jugador
     * @return ArrayList de Barcos del Jugador
     */
    public ArrayList<Barco> getBarcos() { return Barcos; }

    /**
     * Verifica si el jugador ha Disparado
     * @return true si Disparo, false si no
     */
    public boolean getDisparo() { return Disparo; }



    // Métodos de modificación (setters)

    /**
     * Establece el nombre del jugador
     * @param nombre Nuevo nombre del jugador
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Establece el estado del turno del jugador
     * @param turno true para asignar turno, false para quitarlo
     */
    public void setTurno(boolean turno) { this.turno = turno; }

    /**
     * Establece si el jugador ha Disparado
     * @param Disparo true si Disparo, false si no
     */
    public void setDisparo(boolean Disparo) { this.Disparo = Disparo; }



    // Métodos de funcionalidad

    /**
     * Obtiene una posicion aleatoria
     *
     * @return int decision, Número de casilla a la que ataca
     */
    public int decision(){
        Random Randnumber = new Random();
        return Randnumber.nextInt(99 + 1); //si es menor a 10, que empiece con 0 para ubicar celdas
    }


    /**
     * Valida si el nombre del jugador es válido.
     * Un nombre válido no es nulo y no está vacío o solo contiene espacios.
     *
     * @return true si el nombre es válido, false en caso contrario
     */
    public boolean isValid() {
        return nombre != null && !nombre.trim().isEmpty();
    }
}
