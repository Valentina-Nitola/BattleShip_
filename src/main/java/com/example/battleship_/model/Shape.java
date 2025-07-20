package com.example.battleship_.model;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.Group;

/**
 * Clase que agrupa todas las figuras de barcos y efectos del juego PirateWar
 *
 * @author Valentina
 * @version 2.0.1
 */
public class Shape {

    /**
     * Portaaviones con cañones y vela pirata.
     */
    public static class Portaaviones implements IShape {
        @Override
        public Node getShape(boolean vertical, boolean sunk, boolean hit) {
            Group group = new Group();
            Polygon barco;
            if (vertical) {
                barco = new Polygon(0, 0, 20, 0, 20, 80, 0, 80);
            } else {
                barco = new Polygon(0, 0, 80, 0, 80, 20, 0, 20);
            }
            barco.setFill(Color.SADDLEBROWN); // Madera oscura
            barco.setStroke(Color.GOLDENROD); // Detalle dorado
            barco.setStrokeWidth(2);

            // Cañones (círculos pequeños en los extremos)
            Circle cannon1, cannon2;
            if (vertical) {
                cannon1 = new Circle(10, 10, 3, Color.DARKGRAY);
                cannon2 = new Circle(10, 70, 3, Color.DARKGRAY);
            } else {
                cannon1 = new Circle(10, 10, 3, Color.DARKGRAY);
                cannon2 = new Circle(70, 10, 3, Color.DARKGRAY);
            }

            // Vela pirata (blanca)
            Rectangle vela = vertical ?
                    new Rectangle(5, 25, 10, 30) : new Rectangle(30, 5, 20, 10);
            vela.setFill(Color.WHITESMOKE);
            vela.setStroke(Color.DIMGRAY);

            group.getChildren().addAll(barco, cannon1, cannon2, vela);

            // Estados especiales
            if (sunk) {
                group.getChildren().add(new Fuego().getShape(vertical, true, false));
            } else if (hit) {
                group.getChildren().add(new Bomba().getShape(vertical, false, true));
            }
            return group;
        }

        @Override
        public int getSize() { return 4; }
    }

    /**
     * Submarino con cañones y vela pequeña.
     */
    public static class Submarino implements IShape {
        @Override
        public Node getShape(boolean vertical, boolean sunk, boolean hit) {
            Group group = new Group();
            Polygon barco;
            if (vertical) {
                barco = new Polygon(0, 0, 20, 0, 20, 60, 0, 60);
            } else {
                barco = new Polygon(0, 0, 60, 0, 60, 20, 0, 20);
            }
            barco.setFill(Color.DARKSLATEGRAY);
            barco.setStroke(Color.GOLDENROD);
            barco.setStrokeWidth(2);

            // Cañones
            Circle cannon1, cannon2;
            if (vertical) {
                cannon1 = new Circle(10, 10, 3, Color.BLACK);
                cannon2 = new Circle(10, 50, 3, Color.BLACK);
            } else {
                cannon1 = new Circle(10, 10, 3, Color.BLACK);
                cannon2 = new Circle(50, 10, 3, Color.BLACK);
            }

            // Vela pequeña
            Rectangle vela = vertical ?
                    new Rectangle(7, 20, 6, 18) : new Rectangle(22, 7, 18, 6);
            vela.setFill(Color.LIGHTYELLOW);
            vela.setStroke(Color.DARKGRAY);

            group.getChildren().addAll(barco, cannon1, cannon2, vela);

            if (sunk) {
                group.getChildren().add(new Fuego().getShape(vertical, true, false));
            } else if (hit) {
                group.getChildren().add(new Bomba().getShape(vertical, false, true));
            }
            return group;
        }

        @Override
        public int getSize() { return 3; }
    }

    /**
     * Destructor tipo bote cañonero.
     */
    public static class Destructor implements IShape {
        @Override
        public Node getShape(boolean vertical, boolean sunk, boolean hit) {
            Group group = new Group();
            Polygon barco;
            if (vertical) {
                barco = new Polygon(0, 0, 20, 0, 20, 40, 0, 40);
            } else {
                barco = new Polygon(0, 0, 40, 0, 40, 20, 0, 20);
            }
            barco.setFill(Color.PERU); // Madera clara
            barco.setStroke(Color.GOLDENROD);
            barco.setStrokeWidth(2);

            // Cañón central
            Circle cannon = vertical ? new Circle(10, 20, 3, Color.GRAY) : new Circle(20, 10, 3, Color.GRAY);

            group.getChildren().addAll(barco, cannon);

            if (sunk) {
                group.getChildren().add(new Fuego().getShape(vertical, true, false));
            } else if (hit) {
                group.getChildren().add(new Bomba().getShape(vertical, false, true));
            }
            return group;
        }

        @Override
        public int getSize() { return 2; }
    }

    /**
     * Fragata ligera con mástil pequeño.
     */
    public static class Fragata implements IShape {
        @Override
        public Node getShape(boolean vertical, boolean sunk, boolean hit) {
            Group group = new Group();
            Rectangle rect = new Rectangle(0, 0, 20, 20);
            rect.setFill(Color.SIENNA); // Madera rojiza
            rect.setStroke(Color.DARKGOLDENROD);
            rect.setStrokeWidth(2);

            // Mástil
            Line mastil = vertical ? new Line(10, 2, 10, 18) : new Line(2, 10, 18, 10);
            mastil.setStroke(Color.WHITESMOKE);
            mastil.setStrokeWidth(2);

            group.getChildren().addAll(rect, mastil);

            if (sunk) {
                group.getChildren().add(new Fuego().getShape(vertical, true, false));
            } else if (hit) {
                group.getChildren().add(new Bomba().getShape(vertical, false, true));
            }
            return group;
        }

        @Override
        public int getSize() { return 1; }
    }

    /**
     * Efecto de agua: equis roja rústica estilo mapa pirata.
     */
    public static class Agua implements IShape {
        @Override
        public Node getShape(boolean vertical, boolean sunk, boolean hit) {
            Group group = new Group();
            Line line1 = new Line(3, 3, 17, 17);
            Line line2 = new Line(17, 3, 3, 17);
            line1.setStroke(Color.DARKRED.deriveColor(0,1,1,0.8));
            line2.setStroke(Color.DARKRED.deriveColor(0,1,1,0.8));
            line1.setStrokeWidth(4);
            line2.setStrokeWidth(4);
            group.getChildren().addAll(line1, line2);
            return group;
        }

        @Override
        public int getSize() { return 1; }
    }

    /**
     * Efecto de bomba estilo bola de cañón pirata con chispa dorada.
     */
    public static class Bomba implements IShape {
        @Override
        public Node getShape(boolean vertical, boolean sunk, boolean hit) {
            Group group = new Group();
            Circle body = new Circle(10, 10, 7);
            body.setFill(Color.BLACK);
            body.setStroke(Color.GOLD);
            body.setStrokeWidth(1.5);

            // Pabilo de la bomba
            Line fuse = new Line(10, 2, 10, -3);
            fuse.setStroke(Color.GOLDENROD);
            fuse.setStrokeWidth(2);

            // Chispa/destello
            Line spark1 = new Line(10, -3, 8, -6);
            Line spark2 = new Line(10, -3, 12, -6);
            spark1.setStroke(Color.YELLOW);
            spark2.setStroke(Color.ORANGE);
            spark1.setStrokeWidth(2);
            spark2.setStrokeWidth(2);

            group.getChildren().addAll(body, fuse, spark1, spark2);
            return group;
        }

        @Override
        public int getSize() { return 1; }
    }

    /**
     * Efecto de fuego, con varias llamas y un poco de humo gris.
     */
    public static class Fuego implements IShape {
        @Override
        public Node getShape(boolean vertical, boolean sunk, boolean hit) {
            Group group = new Group();
            // Base fuego
            Circle base = new Circle(10, 12, 8);
            base.setFill(Color.RED);

            // Llamas
            Polygon flame1 = new Polygon(10.0, 3.0, 14.0, 12.0, 10.0, 8.0, 6.0, 12.0);
            flame1.setFill(Color.ORANGE);

            Polygon flame2 = new Polygon(10.0, 6.0, 12.0, 10.0, 10.0, 8.0, 8.0, 10.0);
            flame2.setFill(Color.YELLOW);

            // Humo (círculo gris claro)
            Circle smoke = new Circle(10, 0, 4);
            smoke.setFill(Color.LIGHTGRAY.deriveColor(0,1,1,0.5));

            group.getChildren().addAll(base, flame1, flame2, smoke);
            return group;
        }

        @Override
        public int getSize() { return 1; }
    }
}

