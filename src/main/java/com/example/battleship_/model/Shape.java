package com.example.battleship_.model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * Clase que agrupa todas las figuras de barcos y efectos del juego PirateWar
 *
 * @author Valentina
 * @version 2.0.2
 */
public class Shape {

    /**
     * Portaaviones con cañones y vela pirata.
     */
    public static class Portaaviones implements IShape {
        @Override
        public Node getShapePart(int partIndex, boolean vertical, boolean sunk, boolean hit) {
            // Un portaaviones tiene 4 partes: 0 (proa), 1 y 2 (medio), 3 (popa)
            Rectangle part = new Rectangle(0, 0, 34, 34); // Un poco más pequeño que la celda (36x36)
            part.setArcWidth(10);
            part.setArcHeight(10);
            part.setFill(Color.SADDLEBROWN);
            part.setStroke(Color.GOLDENROD);
            part.setStrokeWidth(1.5);

            // Añadir detalles para distinguir las partes
            if (partIndex == 0) { // Proa (parte de adelante)
                Circle detail = new Circle(17, 17, 4, Color.DARKGRAY);
                return new Group(part, detail);
            }
            if (partIndex == 3) { // Popa (parte de atrás)
                Rectangle detail = new Rectangle(13, 13, 8, 8);
                detail.setFill(Color.DARKGRAY);
                return new Group(part, detail);
            }
            // Partes del medio
            return part;
        }
        @Override
        public int getSize() { return 4; }
    }

    /**
     * Submarino con cañones y vela pequeña.
     */
    public static class Submarino implements IShape {
        @Override
        public Node getShapePart(int partIndex, boolean vertical, boolean sunk, boolean hit) {
            // Submarino de 3 partes: 0 (proa), 1 (medio/torreta), 2 (popa)
            Rectangle part = new Rectangle(0, 0, 34, 34);
            part.setArcWidth(15);
            part.setArcHeight(15);
            part.setFill(Color.DARKSLATEGRAY);
            part.setStroke(Color.SILVER);

            if (partIndex == 1) { // Parte del medio con torreta
                Circle tower = new Circle(17, 17, 6, Color.BLACK);
                return new Group(part, tower);
            }
            return part; // Proa y popa son iguales
        }
        @Override
        public int getSize() { return 3; }
    }

    /**
     * Destructor tipo bote cañonero.
     */
    public static class Destructor implements IShape {
        @Override
        public Node getShapePart(int partIndex, boolean vertical, boolean sunk, boolean hit) {
            // Destructor de 2 partes: 0 y 1
            Rectangle part = new Rectangle(0, 0, 34, 34);
            part.setArcWidth(8);
            part.setArcHeight(8);
            part.setFill(Color.PERU);
            part.setStroke(Color.DARKGOLDENROD);

            // Añadir cañón para distinguir
            Circle cannon = new Circle(17, 17, 3, Color.GRAY);
            return new Group(part, cannon);
        }
        @Override
        public int getSize() { return 2; }
    }

    /**
     * Fragata ligera con mástil pequeño.
     */
    public static class Fragata implements IShape {
        @Override
        public Node getShapePart(int partIndex, boolean vertical, boolean sunk, boolean hit) {
            // Fragata es 1 sola parte
            Rectangle part = new Rectangle(0, 0, 34, 34);
            part.setFill(Color.SIENNA);
            part.setStroke(Color.DARKGOLDENROD);
            return part;
        }
        @Override
        public int getSize() { return 1; }
    }


    /**
     * Efecto de agua: equis roja rústica estilo mapa pirata.
     */
    public static class Agua implements IShape {
        @Override
        public Node getShapePart(int partIndex, boolean vertical, boolean sunk, boolean hit) {
            Group group = new Group();
            Line line1 = new Line(5, 5, 29, 29);
            Line line2 = new Line(29, 5, 5, 29);
            line1.setStroke(Color.DARKRED.deriveColor(0,1,1,0.8));
            line2.setStroke(Color.DARKRED.deriveColor(0,1,1,0.8));
            line1.setStrokeWidth(4);
            line2.setStrokeWidth(4);
            group.getChildren().addAll(line1, line2);
            return group;
        }
        @Override public int getSize() { return 1; }
    }

    /**
     * Efecto de bomba estilo bola de cañón pirata con chispa dorada.
     */
    public static class Bomba implements IShape {
        @Override
        public Node getShapePart(int partIndex, boolean vertical, boolean sunk, boolean hit) {
            Circle body = new Circle(17, 17, 10, Color.BLACK);
            body.setStroke(Color.ORANGERED);
            body.setStrokeWidth(2);
            return body;
        }
        @Override public int getSize() { return 1; }
    }

    /**
     * Efecto de fuego, con varias llamas y un poco de humo gris.
     */
    public static class Fuego implements IShape {
        @Override
        public Node getShapePart(int partIndex, boolean vertical, boolean sunk, boolean hit) {
            // ... (código de fuego similar al anterior, ajustado al centro de una celda 36x36)
            Group group = new Group();
            Circle base = new Circle(17, 20, 12, Color.RED);
            Polygon flame1 = new Polygon(17.0, 5.0, 23.0, 20.0, 17.0, 15.0, 11.0, 20.0);
            flame1.setFill(Color.ORANGE);
            Polygon flame2 = new Polygon(17.0, 10.0, 20.0, 18.0, 17.0, 15.0, 14.0, 18.0);
            flame2.setFill(Color.YELLOW);
            group.getChildren().addAll(base, flame1, flame2);
            return group;
        }
        @Override public int getSize() { return 1; }
    }
}

