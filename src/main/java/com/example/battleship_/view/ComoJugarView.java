package com.example.battleship_.view;

import com.example.battleship_.controller.ComoJugarController;
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
public class ComoJugarView extends Stage {
    private ComoJugarController controller;
    /**
     * Constructor que inicia la vista de preparacion
     */
    public ComoJugarView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/battleship_/ui/comojugarView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        this.controller = fxmlLoader.getController();
        this.setResizable(false);
        this.setTitle("PirateWar --> COMO JUGAR");
        this.setScene(scene);
    }
    /**
     * Obtiene el controlador de la vista del tutorial
     * @return el controlador de la vista del tutorial
     */
    public ComoJugarController getController() {
        return controller;
    }
    /**
     *
     */
    public static ComoJugarView getInstance() throws IOException {
        if (ComoJugarView.ComoJugarViewHolder.INSTANCE == null) {
            ComoJugarView.ComoJugarViewHolder.INSTANCE = new ComoJugarView();
        }
        return ComoJugarView.ComoJugarViewHolder.INSTANCE;
    }

    /**

     */
    private static class ComoJugarViewHolder {
        /**

         */
        private static ComoJugarView INSTANCE;
    }
}
