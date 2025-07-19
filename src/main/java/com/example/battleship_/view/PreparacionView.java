package com.example.battleship_.view;

import com.example.battleship_.controller.PreparacionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa la vista de la preparacion del juego PirateWar
 * @author Valentina Nitola
 * @version 1.0.4
 */
public class PreparacionView extends Stage {

    private PreparacionController controller;
    /**
     * Constructor que inicia la vista de preparacion
     */
    public PreparacionView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/battleship_/ui/preparacionView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        this.controller = fxmlLoader.getController();
        this.setResizable(false);
        this.setTitle("Pirate War --> PREPARATE");
        this.setScene(scene);
    }
    /**
     * Obtiene el controlador de la vista de preparacion
     * @return el controlador de la vista de preparacion
     */
    public PreparacionController getController() {
        return controller;
    }
    /**
     *
     */
    public static PreparacionView getInstance() throws IOException {
        if (PreparacionView.PreparacionViewHolder.INSTANCE == null) {
            PreparacionView.PreparacionViewHolder.INSTANCE = new PreparacionView();
        }
        return PreparacionView.PreparacionViewHolder.INSTANCE;
    }

    /**

     */
    private static class PreparacionViewHolder {
        /**

         */
        private static PreparacionView INSTANCE;
    }
}
