package com.example.battleship_.model;

import java.io.Serializable;

/**
 * Contenedor para el estado completo de una partida, diseñado para ser serializado.
 * Almacena los modelos del jugador y de la CPU.
 */
public class PartidaGuardada implements Serializable {
    private static final long serialVersionUID = 1L;

    private JugadorModel jugador;
    private JugadorModel cpu;

    public PartidaGuardada(JugadorModel jugador, JugadorModel cpu) {
        this.jugador = jugador;
        this.cpu = cpu;
    }

    public JugadorModel getJugador() {
        return jugador;
    }

    public JugadorModel getCpu() {
        return cpu;
    }
}