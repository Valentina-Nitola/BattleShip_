package com.example.battleship_.controller;

import com.example.battleship_.model.Board;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Controlador para la vista de verificación del tablero de la CPU en PirateWar.
 * Esta vista se utiliza antes de iniciar una partida para que el usuario (o docente)
 * pueda revisar visualmente que la CPU tiene un tablero válido, con los barcos correctamente ubicados.
 *
 * @author Valentina
 * @version 1.0
 */
public class TbroCPUController {

    /** Panel donde se mostrará el tablero visual de la CPU. */
    @FXML
    private AnchorPane tableroCPU;

    /** Tablero visual de la CPU. */
    private Board board;

    /**
     * Inicializa la vista, creando el tablero de la CPU y agregándolo al panel.
     * El tablero permite verificar la disposición de los barcos de la máquina antes de comenzar el juego.
     */
    @FXML
    public void initialize() {
        board = new Board(10);
        tableroCPU.getChildren().add(board);
    }
}

