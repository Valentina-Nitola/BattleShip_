package com.example.battleship_.view;

import com.example.battleship_.HelloApplication;
import com.example.battleship_.controller.TbroCPUController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa la vista del menú principal del juego uno
 * @author Valentina Nitola
 * @version 1.0.0
 */

public class TbroCPUView extends Stage {

    private TbroCPUController controller;
    /**
     * Constructor que inicia la vista del menu
     */
    public TbroCPUView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("/com/example/battleship_/ui/tbrocpuView.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());
        this.controller = fxmlLoader.getController();
        this.setResizable(false);
        this.setTitle("Pirate War --> TABLERO DEL CPU");
        this.setScene(scene);
    }
    /**
     * Obtiene el controlador de la vista del menu
     * @return el controlador de la vista del menu
     */
    public TbroCPUController getController() {
        return controller;
    }
    /**
     *
     */
    public static TbroCPUView getInstance() throws IOException {
        if (TbroCPUView.TbroCPUViewHolder.INSTANCE == null) {
            TbroCPUView.TbroCPUViewHolder.INSTANCE = new TbroCPUView();
        }
        return TbroCPUView.TbroCPUViewHolder.INSTANCE;
    }

    /**

     */
    private static class TbroCPUViewHolder {
        /**

         */
        private static TbroCPUView INSTANCE;
    }
}
