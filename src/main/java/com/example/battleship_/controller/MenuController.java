package com.example.battleship_.controller;

import com.example.battleship_.model.GameStateManager;
import com.example.battleship_.model.MusicModel;
import com.example.battleship_.view.ComoJugarView;
import com.example.battleship_.view.JuegoView;
import com.example.battleship_.view.MenuView;
import com.example.battleship_.view.PreparacionView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Controlador para la vista del menú principal del juego PirateWar.
 * Maneja las interacciones del usuario en la pantalla de menú,
 * incluyendo la configuración inicial, control de música y navegación
 * entre vistas.
 *
 * @author Valentina Nitola
 * @version 1.1.3
 */
public class MenuController {
    /**
     * Botón para controlar el estado de la música del juego
     */
    @FXML
    private Button btnSonido;
    /**
     * Método de inicialización que se ejecuta al cargar la vista.
     * Configura la música del juego y actualiza el estado visual del botón de música.
     */
    @FXML
    public void initialize() {
        MusicModel.getInstance(); // Inicializa el reproductor de música
        actualizarBotonMusica(); // Configura la imagen inicial del botón
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

    /**
     * Maneja el inicio de una nueva partida.
     * Valida el nombre del jugador y abre la vista del juego principal.
     *
     * @param event Evento de acción generado por el clic
     * @throws IOException Si ocurre un error al cargar la vista del juego
     */

    @FXML
    private void nuevaPartida(ActionEvent event) throws IOException {
        System.out.println("Iniciando nueva partida");


        GameStateManager.getInstance().reset();
        PreparacionView.resetInstance();
        JuegoView.resetInstance();

        PreparacionView preparacionView = PreparacionView.getInstance();
        MenuView.getInstance().close();
        preparacionView.show();
    }

    @FXML
    private void cargarPartida(ActionEvent event) throws IOException {
        System.out.println("Iniciando partida guardada");

        GameStateManager.getInstance().reset();
        JuegoView.resetInstance();
        PreparacionView.resetInstance();

        JuegoView juegoView = JuegoView.getInstance();
        MenuView.getInstance().close();
        juegoView.show();
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
}
