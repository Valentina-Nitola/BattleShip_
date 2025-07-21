package com.example.battleship_.model;
/**
 * Interfaz que define el contrato para las figuras 2D de los barcos y efectos visuales en el juego Batalla Naval.
 * Cualquier clase que implemente esta interfaz debe proveer un método para obtener la figura a dibujar
 * y el tamaño del elemento representado en casillas del tablero.
 *
 * @author Valentina
 * @version 1.0.0
 */
public interface IShape {
    /**
     * Retorna la figura 2D ({@link Node}) que representa la casilla del tablero.
     *
     * @param vertical indica si la figura debe representarse en posición vertical
     * @param sunk     indica si la figura corresponde a un barco hundido
     * @param hit      indica si la figura corresponde a un barco tocado pero no hundido
     * @return un {@link Node} de JavaFX con la figura gráfica que representa el estado
     */
    javafx.scene.Node getShape(boolean vertical, boolean sunk, boolean hit);
    /**
     * Retorna el tamaño del elemento (número de casillas que ocupa en el tablero).
     * Por ejemplo: 4 para portaaviones, 1 para fragata.
     *
     * @return cantidad de casillas que ocupa
     */
    int getSize();
}
