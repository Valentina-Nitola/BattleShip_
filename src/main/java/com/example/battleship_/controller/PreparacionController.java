package com.example.battleship_.controller;

import com.example.battleship_.model.*;
import com.example.battleship_.view.JuegoView;
import com.example.battleship_.view.PreparacionView;
import com.example.battleship_.view.TbroCPUView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node; // Asegúrate de importar Node
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox; // Importante para ensamblar barcos horizontales
import javafx.scene.layout.VBox; // Importante para ensamblar barcos verticales

import java.io.IOException;
import java.net.URL;

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
            girar,
            btnSonido;
    @FXML private TextField txtNombre;
    @FXML private Label lblTurno;

    private Button botonSeleccionado;
    private Board board;
    private PreparacionManager manager;

    @FXML
    public void initialize() {
        board = new Board(10);
        tableroContainer.getChildren().add(board);

        manager = new PreparacionManager();
        asignarGraficosBarcos();
    }

    /**
     * Esta función sirve para ensamblar las piezas
     * y crear una vista previa completa del barco.
     */
    private void asignarGraficosBarcos() {
        boolean vertical = manager != null && manager.isVertical();

        btnPortaaviones1.setGraphic(ensamblarBarco(new Shape.Portaaviones(), vertical));
        btnSubmarino1.setGraphic(ensamblarBarco(new Shape.Submarino(), vertical));
        btnSubmarino2.setGraphic(ensamblarBarco(new Shape.Submarino(), vertical));
        btnDestructor1.setGraphic(ensamblarBarco(new Shape.Destructor(), vertical));
        btnDestructor2.setGraphic(ensamblarBarco(new Shape.Destructor(), vertical));
        btnDestructor3.setGraphic(ensamblarBarco(new Shape.Destructor(), vertical));
        btnFragata1.setGraphic(ensamblarBarco(new Shape.Fragata(), vertical));
        btnFragata2.setGraphic(ensamblarBarco(new Shape.Fragata(), vertical));
        btnFragata3.setGraphic(ensamblarBarco(new Shape.Fragata(), vertical));
        btnFragata4.setGraphic(ensamblarBarco(new Shape.Fragata(), vertical));
    }

    /**
     * Crea una vista previa de un barco completo
     * uniendo todas sus piezas en un VBox (vertical) o HBox (horizontal).
     *
     * @param shape El tipo de barco (Portaaviones, Submarino, etc.).
     * @param vertical La orientación deseada para la vista previa.
     * @return Un nodo (VBox o HBox) que contiene todas las piezas del barco ensambladas.
     */
    private Node ensamblarBarco(IShape shape, boolean vertical) {
        if (vertical) {
            VBox contenedorVertical = new VBox();
            contenedorVertical.setSpacing(-1); // Ajusta el espaciado para que las piezas se toquen

            for (int i = 0; i < shape.getSize(); i++) {
                Node pieza = shape.getShapePart(i, true, false, false);
                pieza.setScaleX(0.5);
                pieza.setScaleY(0.5);
                contenedorVertical.getChildren().add(pieza);
            }
            return contenedorVertical;

        } else { // Si es horizontal
            HBox contenedorHorizontal = new HBox();
            contenedorHorizontal.setSpacing(-1); // Ajusta el espaciado

            for (int i = 0; i < shape.getSize(); i++) {
                Node pieza = shape.getShapePart(i, false, false, false);
                pieza.setScaleX(0.5);
                pieza.setScaleY(0.5);
                contenedorHorizontal.getChildren().add(pieza);
            }
            return contenedorHorizontal;
        }
    }

    /**
     * Maneja el evento de clic en el botón de música.
     * Alterna el estado de la música entre encendido/apagado y actualiza
     * la imagen del botón.
     *
     * @param event Evento de acción generado por el clic
     */
    @FXML
    private void musica(ActionEvent event) {
        MusicModel.getInstance().toggleMusic();
        actualizarBotonMusica();
    }

    /**
     * Maneja el inicio de la partida.
     * Valida el nombre del jugador y abre la vista del juego principal.
     *
     * @param event Evento de acción generado por el clic
     * @throws IOException Si ocurre un error al cargar la vista del juego
     */
    @FXML
    private void comenzar(ActionEvent event) throws IOException {
        String nickname = txtNombre.getText();
        JugadorModel player = new JugadorModel();
        player.setNombre(nickname);

        if (!player.isValid()) {
            // mostrarErrorNombre();
            return;
        }
        System.out.println("Iniciando Partida con nombre: " + player.getNombre());

        JuegoView juegoView = JuegoView.getInstance();
        PreparacionView.getInstance().close();
        juegoView.show();
    }


    /**
     * Maneja la apertura de la vista del tutorial.
     *
     * @param event Evento de acción generado por el clic
     */
    @FXML
    private void vertablerocpu(ActionEvent event) throws IOException {
        TbroCPUView tbroCPUView = TbroCPUView.getInstance();
        tbroCPUView.show();
    }

    /**
     * Actualiza la imagen del botón de música según su estado actual.
     * Muestra un ícono diferente para música encendida/apagada.
     */
    private void actualizarBotonMusica() {
        boolean isMusicOn = MusicModel.getInstance().isMusicOn();
        String imgPath = isMusicOn
                ? "/com/example/battleship_/img/on.png"
                : "/com/example/battleship_/img/off.png";

        URL imgURL = getClass().getResource(imgPath);
        if (imgURL != null) {
            Image img = new Image(imgURL.toString());
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);
            btnSonido.setGraphic(imageView);
        }
    }
}