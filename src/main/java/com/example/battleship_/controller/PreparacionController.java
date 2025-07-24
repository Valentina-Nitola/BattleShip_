package com.example.battleship_.controller;

import com.example.battleship_.model.*;
import com.example.battleship_.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    /**
     * Maneja el evento de clic en el botón de música.
     * Alterna el estado de la música entre encendido/apagado y actualiza
     * la imagen del botón.
     *
     * @param event Evento de acción generado por el clic
     */

    @FXML
    private void musica(ActionEvent event) {
        MusicModel.getInstance().toggleMusic(); // Cambia el estado de la música
        actualizarBotonMusica(); // Actualiza la imagen del botón
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
         //Cuando se cree la logica para almacenar el nombre se usa lo siguiente:
        JugadorModel player = new JugadorModel();
        player.setNombre(nickname);

        if (!player.isValid()) {
            //mostrarErrorNombre();
            return;
        }

        System.out.println("Iniciando Partida");
        System.out.println("Tu nombre sera");
        System.out.println(nickname);
        System.out.println(player.getNombre());



        JuegoView juegoView = JuegoView.getInstance();
        PreparacionView.getInstance().close();
        juegoView.show();
        //lblTurno.setText("Este texto no se muestra en los labeles");
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
