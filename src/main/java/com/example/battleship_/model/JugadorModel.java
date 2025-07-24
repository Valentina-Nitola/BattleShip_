package com.example.battleship_.model;
import java.util.ArrayList;

/**
 * Clase que representa a un jugador en el juego UNO.
 * Gestiona las cartas en mano, el estado del turno y
 * la condición de haber gritado "UNO".
 *
 * @Author Jhojan Moreno
 * @version 1.0.0
 */
public class JugadorModel {

    /**
     * Lista de Barcos que el jugador tiene en su tablero
     */
    public ArrayList<Barco> Barcos;

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
     * Constructor que inicializa los barcos del jugador como lista vacía.
     */
    public JugadorModel() {this.Barcos = new ArrayList<>();}



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
     * @param Uno true si Disparo, false si no
     */
    public void setDisparo(boolean Disparo) { this.Disparo = Disparo; }




    // Métodos de funcionalidad

    /**
     * Reparte las cartas iniciales al jugador (5 cartas).
     * Toma cartas del mazo principal y las añade a la mano del jugador.
     *
     * @param mazo Mazo de cartas del que se reparten
     */
    /*
    public void GenerarBarcosIniciales(ArrayList<Barco> Flota) {
        for (int i = 0; i < 5; i++) {
            if (!mazo.isEmpty()) {
                JuegoModel.Carta carta = mazo.remove(0); // Toma la primera carta
                carta.setEstado(JuegoModel.Estado.MANO); // Cambia estado a "en mano"
                this.Mano.add(carta); // Añade a la mano del jugador
            }
        }
    }
*/
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
