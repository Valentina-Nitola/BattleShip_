package com.example.battleship_.model;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Tablero visual para PirateWar
 * Este tablero puede ser usado en cualquier view (preparación, jugador, CPU, etc.).
 *
 * @author Valentina
 * @version 2.0.0
 */
public class Board extends GridPane {
    private int size;
    private StackPane[][] cells;

    /**
     * Crea un tablero de tamaño size x size.
     *
     * @param size Número de filas y columnas (usualmente 10).
     */
    public Board(int size) {
        this.size = size;
        this.cells = new StackPane[size][size];

        // Fondo de tablero: estilo mapa antiguo/pirata
        this.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #e8d8b0 50%, #c2b280 100%);" +  // pergamino
                        "-fx-border-color: goldenrod; -fx-border-width: 6px; -fx-border-radius: 8px;"
        );
        this.setAlignment(Pos.CENTER);

        // Agrega coordenadas (letras arriba, números a la izquierda)
        addLabels();

        // Celdas del tablero
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(36, 36); // Puedes ajustar
                cell.setStyle(
                        "-fx-background-color: #2d4c6a;" +    // Azul marino pirata
                                "-fx-border-color: #c2b280;" +        // Oro viejo
                                "-fx-border-width: 2px;" +
                                "-fx-border-radius: 4px;"
                );
                int r = row;
                int c = col;
                cell.setOnMouseEntered(e -> cell.setStyle(
                        "-fx-background-color: #45688d; -fx-border-color: #ffd700; -fx-border-width: 2px; -fx-border-radius: 4px;"));
                cell.setOnMouseExited(e -> cell.setStyle(
                        "-fx-background-color: #2d4c6a; -fx-border-color: #c2b280; -fx-border-width: 2px; -fx-border-radius: 4px;"));
                // Puedes añadir aquí listeners para clicks si lo deseas

                this.cells[row][col] = cell;
                this.add(cell, col + 1, row + 1); // +1 para dejar espacio a las coordenadas
            }
        }
    }

    /**
     * Añade las etiquetas de letras y números en los bordes del tablero.
     */
    private void addLabels() {
        // Letras arriba
        for (int col = 0; col < size; col++) {
            Label letra = new Label(String.valueOf((char) ('A' + col)));
            letra.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18; -fx-text-fill: saddlebrown; -fx-font-weight: bold;");
            letra.setMinSize(36, 36);
            letra.setAlignment(Pos.CENTER);
            this.add(letra, col + 1, 0); // Col +1 para dejar la esquina
        }
        // Números a la izquierda
        for (int row = 0; row < size; row++) {
            Label num = new Label(String.valueOf(row + 1));
            num.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18; -fx-text-fill: saddlebrown; -fx-font-weight: bold;");
            num.setMinSize(36, 36);
            num.setAlignment(Pos.CENTER);
            this.add(num, 0, row + 1); // Row +1 para dejar la esquina
        }
    }

    /**
     * Dibuja una figura en una celda específica.
     * @param row Fila (0-indexado)
     * @param col Columna (0-indexado)
     * @param shape Figura IShape a dibujar
     * @param vertical Orientación
     * @param sunk Si está hundido
     * @param hit Si está tocado
     */
    public void setCellShape(int row, int col, IShape shape, boolean vertical, boolean sunk, boolean hit) {
        StackPane cell = cells[row][col];
        cell.getChildren().clear();
        Node figura = shape.getShape(vertical, sunk, hit);
        cell.getChildren().add(figura);
    }

    /**
     * Limpia el contenido de una celda.
     * @param row Fila
     * @param col Columna
     */
    public void clearCell(int row, int col) {
        cells[row][col].getChildren().clear();
    }

    /**
     * Limpia todo el tablero (solo el contenido, no las coordenadas).
     */
    public void clearBoard() {
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                clearCell(row, col);
    }

    /**
     * Devuelve el StackPane de una celda.
     * @param row Fila
     * @param col Columna
     * @return StackPane correspondiente
     */
    public StackPane getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * Devuelve el tamaño del tablero.
     * @return tamaño
     */
    public int getBoardSize() { return size; }
}
