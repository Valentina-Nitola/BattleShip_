package com.example.battleship_.view;

import com.example.battleship_.HelloApplication;
import com.example.battleship_.controller.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que representa la vista del menú principal del juego uno
 * @author Valentina Nitola
 * @version 1.0.4
 */
public class MenuView extends Stage {

    private MenuController controller;
    /**
     * Constructor que inicia la vista del menu
     */
    public MenuView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("/com/example/battleship_/ui/menuView.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());
        this.controller = fxmlLoader.getController();
        this.setResizable(false);
        this.setTitle("Pirate War --> MENU");
        this.setScene(scene);
    }
    /**
     * Obtiene el controlador de la vista del menu
     * @return el controlador de la vista del menu
     */
    public MenuController getController() {
        return controller;
    }
    /**
     *
     */
    public static MenuView getInstance() throws IOException {
        if (MenuViewHolder.INSTANCE == null) {
            MenuViewHolder.INSTANCE = new MenuView();
        }
        return MenuViewHolder.INSTANCE;
    }

    /**

     */
    private static class MenuViewHolder {
        /**

         */
        private static MenuView INSTANCE;
    }
}