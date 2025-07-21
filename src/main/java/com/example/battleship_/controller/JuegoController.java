package com.example.battleship_.controller;

import com.example.battleship_.model.Board;
import com.example.battleship_.model.MusicModel;
import com.example.battleship_.model.Shape;
import com.example.battleship_.view.ComoJugarView;
import com.example.battleship_.view.JuegoView;
import com.example.battleship_.view.MenuView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;

/**
 * Controlador para la vista principal del juego PirateWar.
 * Maneja las interacciones del usuario en la pantalla de juego,
 * incluyendo la inicialización de los tableros, el control de la música,
 * y la visualización de las leyendas de efectos (agua, tocado y hundido).
 *
 * @author Valentina Nitola
 * @version 1.5.2
 */
public class JuegoController {

    /** Panel donde se muestra el tablero del jugador humano. */
    @FXML
    private AnchorPane tableroPlayer;

    /** Panel donde se muestra el tablero del CPU (máquina). */
    @FXML
    private AnchorPane tableroCPU;

    /** Leyenda visual: dibujo para el efecto "agua". */
    @FXML
    private StackPane agua;

    /** Leyenda visual: dibujo para el efecto "tocado". */
    @FXML
    private StackPane tocado;

    /** Leyenda visual: dibujo para el efecto "hundido". */
    @FXML
    private StackPane hundido;

    /** Botón para controlar el estado de la música del juego. */
    @FXML
    private Button btnSonido;

    /** Tablero visual del jugador. */
    private Board playerBoard;

    /** Tablero visual de la CPU (máquina). */
    private Board cpuBoard;

    /**
     * Inicializa la vista del juego, configurando los tableros y las leyendas visuales
     * de los efectos especiales (agua, tocado, hundido) en el layout.
     */
    @FXML
    public void initialize() {
        playerBoard = new Board(10);
        cpuBoard = new Board(10);
        tableroPlayer.getChildren().add(playerBoard);
        tableroCPU.getChildren().add(cpuBoard);

        // Leyenda: Agua
        Node aguaShape = new Shape.Agua().getShape(false, false, false);
        aguaShape.setScaleX(2);
        aguaShape.setScaleY(2);
        agua.getChildren().clear();
        agua.getChildren().add(aguaShape);

        // Leyenda: Tocado
        Node bombaShape = new Shape.Bomba().getShape(false, false, false);
        bombaShape.setScaleX(2);
        bombaShape.setScaleY(2);
        tocado.getChildren().clear();
        tocado.getChildren().add(bombaShape);

        // Leyenda: Hundido
        Node fuegoShape = new Shape.Fuego().getShape(false, false, false);
        fuegoShape.setScaleX(2);
        fuegoShape.setScaleY(2);
        hundido.getChildren().clear();
        hundido.getChildren().add(fuegoShape);
    }

    /**
     * Maneja la apertura de la vista del tutorial.
     *
     * @param event Evento de acción generado por el clic
     */

    @FXML
    private void comojugar(ActionEvent event) throws IOException {
        try {
            ComoJugarView comoJugarView = ComoJugarView.getInstance();
            comoJugarView.show(); // Muestra la vista del tutorial
        } catch (IOException e) {
            System.err.println("Error al abrir el tutorial: " + e.getMessage());
        }
    }

    /**
     * Maneja el evento de clic en el botón de música.
     * Alterna el estado de la música entre encendido/apagado y actualiza
     * la imagen del botón de sonido.
     *
     * @param event Evento de acción generado por el clic del usuario.
     */
    @FXML
    private void musica(ActionEvent event) {
        MusicModel.getInstance().toggleMusic(); // Cambia el estado de la música
        actualizarBotonMusica(); // Actualiza la imagen del botón
    }

    @FXML
    private void salir(ActionEvent event) throws IOException {
        System.out.println("Saliendo de la Partida");

        MenuView menuView = MenuView.getInstance();
        JuegoView.getInstance().close();
        menuView.show();
    }

    /**
     * Actualiza la imagen del botón de música según su estado actual.
     * Muestra un ícono diferente para música encendida o apagada.
     */
    private void actualizarBotonMusica() {
        boolean isMusicOn = MusicModel.getInstance().isMusicOn();

        // Selecciona la ruta de la imagen según el estado de la música
        String imgPath = isMusicOn
                ? "/com/example/battleship_/img/on.png"
                : "/com/example/battleship_/img/off.png";

        // Carga y configura la imagen en el botón
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
