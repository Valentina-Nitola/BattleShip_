package com.example.battleship_.controller;

import com.example.battleship_.model.Board;
import com.example.battleship_.model.MusicModel;
import com.example.battleship_.model.Shape;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;

/**
 * Controlador para la vista del menú principal del juego PirateWar.
 * Maneja las interacciones del usuario en la pantalla de menú,
 * incluyendo la configuración inicial, control de música y navegación
 * entre vistas.
 *
 * @author Valentina Nitola
 * @version 1.0.2
 */
public class JuegoController {
    @FXML
    private AnchorPane tableroPlayer, tableroCPU;

    @FXML
    private StackPane agua, tocado, hundido;

    @FXML
    private Button btnSonido;

    private Board playerBoard;
    private Board cpuBoard;

    @FXML
    public void initialize() {
        playerBoard = new Board(10);
        cpuBoard = new Board(10);
        tableroPlayer.getChildren().add(playerBoard);
        tableroCPU.getChildren().add(cpuBoard);

        // Agua
        Node aguaShape = new Shape.Agua().getShape(false, false, false);
        aguaShape.setScaleX(2);
        aguaShape.setScaleY(2);
        agua.getChildren().clear();
        agua.getChildren().add(aguaShape);

        // Tocado
        Node bombaShape = new Shape.Bomba().getShape(false, false, false);
        bombaShape.setScaleX(2);
        bombaShape.setScaleY(2);
        tocado.getChildren().clear();
        tocado.getChildren().add(bombaShape);

        // Hundido
        Node fuegoShape = new Shape.Fuego().getShape(false, false, false);
        fuegoShape.setScaleX(2);
        fuegoShape.setScaleY(2);
        hundido.getChildren().clear();
        hundido.getChildren().add(fuegoShape);
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
        MusicModel.getInstance().toggleMusic(); // Cambia el estado de la música
        actualizarBotonMusica(); // Actualiza la imagen del botón
    }

    /**
     * Actualiza la imagen del botón de música según su estado actual.
     * Muestra un ícono diferente para música encendida/apagada.
     */

    private void actualizarBotonMusica() {
        boolean isMusicOn = MusicModel.getInstance().isMusicOn();

        // Selecciona la ruta de la imagen según el estado
        String imgPath = isMusicOn
                ? "/com/example/battleship_/img/on.png"
                : "/com/example/battleship_/img/off.png";

        // Carga y configura la imagen
        URL imgURL = getClass().getResource(imgPath);
        if (imgURL != null) {
            Image img = new Image(imgURL.toString());
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(70);
            imageView.setFitHeight(70);
            btnSonido.setGraphic(imageView);
        } else {
            System.out.println("No se encontró la imagen: " + imgPath);
        }
    }
}
