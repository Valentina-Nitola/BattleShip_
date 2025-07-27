package com.example.battleship_.controller;

import com.example.battleship_.model.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    /**
     * Este método recibe el modelo de la CPU y dibuja sus barcos en el tablero.
     * @param cpu El modelo del jugador CPU con sus barcos ya posicionados.
     */
    public void mostrarTableroCPU(JugadorModel cpu) {
        board.clearBoard();
        Tablero tableroLogico = cpu.tablero;

        for (int row = 0; row < tableroLogico.getTAMANIO(); row++) {
            for (int col = 0; col < tableroLogico.getTAMANIO(); col++) {
                if (tableroLogico.getCasilla(row, col) == Casilla.BARCO) {
                    Barco barco = tableroLogico.encontrarBarcoEn(row, col).orElse(null);
                    if (barco != null) {
                        int partIndex;
                        boolean esVertical = barco.getOrientacion() == Barco.Orientacion.VERTICAL;
                        if (esVertical) {
                            partIndex = row - barco.getFilaInicio();
                        } else {
                            partIndex = col - barco.getColumnaInicio();
                        }

                        IShape shape = barco.getTipo().getShape();
                        Node contentToDraw = shape.getShapePart(partIndex, esVertical, false, false);
                        board.setCellContent(row, col, contentToDraw);
                    }
                }
            }
        }
    }
}

