package com.example.battleship_.view;

import com.example.battleship_.controller.JuegoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JuegoView extends Stage {
    private JuegoController controller;

    private JuegoView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/battleship_/ui/juegoView.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());
        this.controller = fxmlLoader.getController();
        this.setResizable(false);
        this.setTitle("PirateWar --> GAME");
        this.setScene(scene);
    }

    public JuegoController getController() {
        return controller;
    }

    public static JuegoView getInstance() throws IOException {
        return JuegoViewHolder.INSTANCE == null ?
                (JuegoViewHolder.INSTANCE = new JuegoView()) :
                JuegoViewHolder.INSTANCE;
    }

    private static class JuegoViewHolder {
        private static JuegoView INSTANCE;
    }
}