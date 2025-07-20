package com.example.battleship_.controller;

import com.example.battleship_.model.Board;
import com.example.battleship_.model.IShape;
import com.example.battleship_.model.Shape;
import com.example.battleship_.model.PreparacionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class PreparacionController {

    @FXML private AnchorPane tableroContainer;
    @FXML private Button
            btnPortaaviones1,
            btnSubmarino1,
            btnSubmarino2,
            btnDestructor1,
            btnDestructor2,
            btnDestructor3,
            btnFragata1,
            btnFragata2,
            btnFragata3,
            btnFragata4,
            girar;

    private Button botonSeleccionado;
    private Board board;
    private PreparacionManager manager;

    @FXML
    public void initialize() {
        board = new Board(10);
        tableroContainer.getChildren().add(board);

        manager = new PreparacionManager();
        asignarGraficosBarcos();
        /**
        setBotonSeleccionBarco(btnPortaaviones1, new Shape.Portaaviones());
        setBotonSeleccionBarco(btnSubmarino1, new Shape.Submarino());
        setBotonSeleccionBarco(btnSubmarino2, new Shape.Submarino());
        setBotonSeleccionBarco(btnDestructor1, new Shape.Destructor());
        setBotonSeleccionBarco(btnDestructor2, new Shape.Destructor());
        setBotonSeleccionBarco(btnDestructor3, new Shape.Destructor());
        setBotonSeleccionBarco(btnFragata1, new Shape.Fragata());
        setBotonSeleccionBarco(btnFragata2, new Shape.Fragata());
        setBotonSeleccionBarco(btnFragata3, new Shape.Fragata());
        setBotonSeleccionBarco(btnFragata4, new Shape.Fragata());
        */
    }

    private void asignarGraficosBarcos() {
        boolean vertical = manager != null && manager.isVertical();
        btnPortaaviones1.setGraphic(new Shape.Portaaviones().getShape(vertical, false, false));
        btnSubmarino1.setGraphic(new Shape.Submarino().getShape(vertical, false, false));
        btnSubmarino2.setGraphic(new Shape.Submarino().getShape(vertical, false, false));
        btnDestructor1.setGraphic(new Shape.Destructor().getShape(vertical, false, false));
        btnDestructor2.setGraphic(new Shape.Destructor().getShape(vertical, false, false));
        btnDestructor3.setGraphic(new Shape.Destructor().getShape(vertical, false, false));
        btnFragata1.setGraphic(new Shape.Fragata().getShape(vertical, false, false));
        btnFragata2.setGraphic(new Shape.Fragata().getShape(vertical, false, false));
        btnFragata3.setGraphic(new Shape.Fragata().getShape(vertical, false, false));
        btnFragata4.setGraphic(new Shape.Fragata().getShape(vertical, false, false));
    }
    /**
    private void setBotonSeleccionBarco(Button boton, IShape shape) {
        boton.setOnAction(e -> {
            manager.setBarcoSeleccionado(shape);
            if (botonSeleccionado != null) {
                botonSeleccionado.setStyle("");
                // También puedes restaurar el gráfico en horizontal al deseleccionar si quieres
                botonSeleccionado.setGraphic(shape.getShape(false, false, false));
            }
            boton.setStyle("-fx-border-color: #ffd700; -fx-border-width: 3px; -fx-background-color: #f5e7b7;");
            // Muestra el gráfico del barco en la orientación actual (vertical/horizontal)
            boton.setGraphic(shape.getShape(manager.isVertical(), false, false));
            botonSeleccionado = boton;
        });
    }*/
}
