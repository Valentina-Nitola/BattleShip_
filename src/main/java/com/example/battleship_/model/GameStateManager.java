package com.example.battleship_.model;

/**
 * Singleton que mantiene el estado del juego entre diferentes vistas.
 * Permite compartir los datos del jugador entre PreparacionView y JuegoView.
 *
 * @author Braulio Robledo Delgado
 * @version 1.0.0
 */
public class GameStateManager {
    private static GameStateManager instance;
    private JugadorModel jugador;

    private GameStateManager() {
    }

    /**
     * Obtiene la instancia única del GameStateManager
     * @return instancia del GameStateManager
     */
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    /**
     * Establece el jugador actual
     * @param jugador el jugador con sus barcos colocados
     */
    public void setJugador(JugadorModel jugador) {
        this.jugador = jugador;
    }

    /**
     * Obtiene el jugador actual
     * @return el jugador con sus datos
     */
    public JugadorModel getJugador() {
        return jugador;
    }

    /**
     * Verifica si hay un jugador configurado
     * @return true si hay un jugador, false en caso contrario
     */
    public boolean hasJugador() {
        return jugador != null;
    }

    /**
     * Limpia el estado del juego
     */
    public void reset() {
        jugador = null;
    }
}