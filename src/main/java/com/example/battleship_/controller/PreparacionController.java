package com.example.battleship_.controller;

import com.example.battleship_.model.GameStateManager;
import com.example.battleship_.model.*;
import com.example.battleship_.view.JuegoView;
import com.example.battleship_.view.PreparacionView;
import com.example.battleship_.view.TbroCPUView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


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
            btnSonido,
            btnReset;
    @FXML private TextField txtNombre;
    //@FXML private Label lblTurno;

    private Button botonSeleccionado;
    private Board board;
    private PreparacionManager manager;
    private JugadorModel jugador;
    private Map<Button, String> barcoTypes;
    private Map<String, Integer> barcosColocados;

    @FXML
    public void initialize() {
        board = new Board(10);
        tableroContainer.getChildren().add(board);

        manager = new PreparacionManager();

        jugador = new JugadorModel();

        initializeBarcoMaps();

        asignarGraficosBarcos();
        configurarDragAndDrop();
        configurarTableroParaDrop();
        actualizarBotonMusica();
        actualizarImagenGirar();
    }


    /**
     * Inicializa los mapas para el tracking de tipos de barcos
     */
    private void initializeBarcoMaps() {
        barcoTypes = new HashMap<>();
        barcoTypes.put(btnPortaaviones1, "PORTAAVIONES");
        barcoTypes.put(btnSubmarino1, "SUBMARINO");
        barcoTypes.put(btnSubmarino2, "SUBMARINO");
        barcoTypes.put(btnDestructor1, "DESTRUCTOR");
        barcoTypes.put(btnDestructor2, "DESTRUCTOR");
        barcoTypes.put(btnDestructor3, "DESTRUCTOR");
        barcoTypes.put(btnFragata1, "FRAGATA");
        barcoTypes.put(btnFragata2, "FRAGATA");
        barcoTypes.put(btnFragata3, "FRAGATA");
        barcoTypes.put(btnFragata4, "FRAGATA");

        barcosColocados = new HashMap<>();
        barcosColocados.put("PORTAAVIONES", 0);
        barcosColocados.put("SUBMARINO", 0);
        barcosColocados.put("DESTRUCTOR", 0);
        barcosColocados.put("FRAGATA", 0);
    }

    /**
     * Configura el drag and drop para todos los botones de barcos
     */
    private void configurarDragAndDrop() {
        Button[] botones = {btnPortaaviones1, btnSubmarino1, btnSubmarino2,
                btnDestructor1, btnDestructor2, btnDestructor3,
                btnFragata1, btnFragata2, btnFragata3, btnFragata4};

        for (Button boton : botones) {

            boton.setOnDragDetected(event -> {
                if (boton.isDisabled()) return;

                botonSeleccionado = boton;
                Dragboard dragboard = boton.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(barcoTypes.get(boton));
                dragboard.setContent(content);

                event.consume();
            });

            boton.setOnDragDone(event -> {
                boton.setStyle("-fx-background-color: #fff9d0;");
                if (event.getTransferMode() == TransferMode.MOVE) {
                    boton.setDisable(true);
                    boton.setOpacity(0.5);
                }
                botonSeleccionado = null;
                event.consume();
            });
        }
    }

    /**
     * Configura el tablero para recibir los drops
     */
    private void configurarTableroParaDrop() {
        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                StackPane cell = board.getCell(row, col);
                final int finalRow = row;
                final int finalCol = col;

                cell.setOnDragOver(event -> {
                    if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                        String tipoBarco = event.getDragboard().getString();
                        if (puedeColocarBarco(tipoBarco, finalRow, finalCol)) {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
                    }
                    event.consume();
                });

                cell.setOnDragDropped(event -> {
                    Dragboard dragboard = event.getDragboard();
                    boolean success = false;

                    if (dragboard.hasString()) {
                        String tipoBarco = dragboard.getString();
                        success = colocarBarcoEnTablero(tipoBarco, finalRow, finalCol);
                    }

                    event.setDropCompleted(success);
                    event.consume();
                });
            }
        }
    }

    /**
     * Verifica si se puede colocar un barco en la posición especificada
     */
    private boolean puedeColocarBarco(String tipoBarco, int row, int col) {
        Barco.TipoBarco tipo = Barco.TipoBarco.valueOf(tipoBarco);
        Barco barcoTemp = new Barco(tipo);
        Barco.Orientacion orientacion = manager.isVertical() ?
                Barco.Orientacion.VERTICAL : Barco.Orientacion.HORIZONTAL;

        return jugador.tablero.posicionamientoValido(barcoTemp, row, col, orientacion);
    }

    /**
     * Coloca el barco en el tablero lógico y visual
     */
    private boolean colocarBarcoEnTablero(String tipoBarco, int row, int col) {
        try {
            Barco.TipoBarco tipo = Barco.TipoBarco.valueOf(tipoBarco);
            Barco barco = new Barco(tipo);
            Barco.Orientacion orientacion = manager.isVertical() ?
                    Barco.Orientacion.VERTICAL : Barco.Orientacion.HORIZONTAL;


            if (jugador.tablero.colocarBarco(barco, row, col, orientacion)) {

                jugador.Barcos.add(barco);
                sincronizarTableroVisual();

                String tipoStr = tipo.name();
                barcosColocados.put(tipoStr, barcosColocados.get(tipoStr) + 1);

                return true;
            }
        } catch (Exception e) {
            System.err.println("Error al colocar barco: " + e.getMessage());
        }
        return false;
    }


    /**
     * Sincroniza el tablero visual con el lógico
     */
    private void sincronizarTableroVisual() {
        board.clearBoard();
        Tablero tableroLogico = jugador.tablero;

        for (int row = 0; row < tableroLogico.getTAMANIO(); row++) {
            for (int col = 0; col < tableroLogico.getTAMANIO(); col++) {
                Casilla estado = tableroLogico.getCasilla(row, col);

                if (estado == Casilla.BARCO) {
                    var barcoOpt = tableroLogico.encontrarBarcoEn(row, col);
                    if (barcoOpt.isPresent()) {
                        Barco barco = barcoOpt.get();

                        int partIndex;
                        boolean esVertical = barco.getOrientacion() == Barco.Orientacion.VERTICAL;
                        if (esVertical) {
                            partIndex = row - barco.getFilaInicio();
                        } else {
                            partIndex = col - barco.getColumnaInicio();
                        }

                        IShape shape = barco.getTipo().getShape();
                        Node contentToDraw = shape.getShapePart(partIndex, esVertical, false, false);
                        board.setCellContent(row, col, contentToDraw);
                    }
                }
            }
        }
    }

    /**
     * Maneja la rotación de barcos
     */
    @FXML
    private void girar(ActionEvent event) {
        manager.toggleOrientacion();
        asignarGraficosBarcos();
        actualizarImagenGirar();
    }

    /**
     * Resetea todos los barcos colocados
     */
    @FXML
    private void resetearBarcos(ActionEvent event) {

        jugador.tablero.limpiarTablero();
        jugador.Barcos.clear();
        board.clearBoard();


        Button[] botones = {btnPortaaviones1, btnSubmarino1, btnSubmarino2,
                btnDestructor1, btnDestructor2, btnDestructor3,
                btnFragata1, btnFragata2, btnFragata3, btnFragata4};

        for (Button boton : botones) {
            boton.setDisable(false);
            boton.setOpacity(1.0);
        }

        for (String tipo : barcosColocados.keySet()) {
            barcosColocados.put(tipo, 0);
        }
    }

    /**
     * Actualiza la imagen del botón girar según la orientación
     */
    private void actualizarImagenGirar() {
        String imagePath = manager.isVertical() ?
                "/com/example/battleship_/img/vertical.png" :
                "/com/example/battleship_/img/horizontal.png";

        URL imgURL = getClass().getResource(imagePath);
        if (imgURL != null) {
            Image img = new Image(imgURL.toString());
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            imageView.setPreserveRatio(true);
            girar.setGraphic(imageView);
        }
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

        if (nickname == null || nickname.trim().isEmpty()) {
            System.out.println("Debe ingresar un nombre");
            return;
        }

        if (jugador.Barcos.size() != 10) {
            System.out.println("Debe colocar todos los barcos antes de comenzar");
            return;
        }

        jugador.setNombre(nickname);
        System.out.println("Iniciando Partida con nombre: " + jugador.getNombre());
        System.out.println("Barcos preparados: " + jugador.Barcos.size());

        GameStateManager.getInstance().setJugador(jugador);

        JuegoView.resetInstance();

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