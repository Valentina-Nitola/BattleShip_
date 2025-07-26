
package com.example.battleship_.model;

import javafx.scene.Node;

/**
 * Interfaz que define el contrato para las figuras 2D de los barcos y efectos visuales en el juego Batalla Naval.
 * Cualquier clase que implemente esta interfaz debe proveer un método para obtener la figura a dibujar
 * y el tamaño del elemento representado en casillas del tablero.
 *
 * @author Valentina
 * @version 1.0.1
 */
public interface IShape {
    /**
     * CAMBIO: Retorna la figura 2D (Node) para una parte específica del barco.
     *
     * @param partIndex el índice de la pieza (0 para proa, 1 para medio, etc.).
     * @param vertical indica si la figura debe representarse en posición vertical.
     * @param sunk     indica si la figura corresponde a un barco hundido.
     * @param hit      indica si la figura corresponde a un barco tocado.
     * @return un Node de JavaFX con la figura gráfica de esa pieza.
     */
    Node getShapePart(int partIndex, boolean vertical, boolean sunk, boolean hit);

    /**
     * Retorna el tamaño del elemento (número de casillas que ocupa en el tablero).
     *
     * @return cantidad de casillas que ocupa.
     */
    int getSize();
}
