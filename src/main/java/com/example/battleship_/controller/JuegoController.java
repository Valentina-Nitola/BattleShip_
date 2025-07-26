package com.example.battleship_.controller;

import com.example.battleship_.model.*;
import com.example.battleship_.view.ComoJugarView;
import com.example.battleship_.view.JuegoView;
import com.example.battleship_.view.MenuView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controlador para la vista principal del juego PirateWar.
 * Maneja las interacciones del usuario en la pantalla de juego,
 * incluyendo la inicialización de los tableros, el control de la música,
 * y la visualización de las leyendas de efectos (agua, tocado y hundido).
 *
 * @author Valentina Nitolam
 * @author Jhojan Moreno
 * @author Jhon Steven Angulo Nieves
 * @version 1.5.3
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

    /** Cuadro de texto de los turnos*/
    @FXML
    private Label IndicadorCentral;

    /** Tablero visual del jugador. */
    private Board playerBoard;

    /** Tablero visual de la CPU (máquina). */
    private Board cpuBoard;

    /** Instancia del jugador humano. */
    JugadorModel jugador;

    /** Instancia del jugador CPU. */
    JugadorModel cpu;

    private Random random = new Random();
    private boolean juegoTerminado = false; // Para evitar acciones después del final


    /**
     * Inicializa la vista del juego.
     */
    @FXML
    public void initialize() throws InterruptedException {

        jugador = new JugadorModel();
        cpu = new JugadorModel();

        playerBoard = new Board(10);
        cpuBoard = new Board(10);

        tableroPlayer.getChildren().add(playerBoard);
        tableroCPU.getChildren().add(cpuBoard);

        setupLegends();
        iniciarPartida();
    }

    /**
     *  Leyendas visuales de los efectos especiales (agua, tocado, hundido) en el layout
     */
    private void setupLegends() {

        // Leyenda: Agua
        Node aguaShape = new Shape.Agua().getShapePart(0, false, false, false);
        aguaShape.setScaleX(2);
        aguaShape.setScaleY(2);
        agua.getChildren().clear();
        agua.getChildren().add(aguaShape);

        // Leyenda: Tocado
        Node bombaShape = new Shape.Bomba().getShapePart(0, false, false, true);
        bombaShape.setScaleX(2);
        bombaShape.setScaleY(2);
        tocado.getChildren().clear();
        tocado.getChildren().add(bombaShape);

        // Leyenda: Hundido
        Node fuegoShape = new Shape.Fuego().getShapePart(0, false, true, false);
        fuegoShape.setScaleX(2);
        fuegoShape.setScaleY(2);
        hundido.getChildren().clear();
        hundido.getChildren().add(fuegoShape);
    }

    /**
     * Inicia una nueva partida de BattleShip
     */
    public void iniciarPartida() {
        juegoTerminado = false;
        jugador. reset();
        cpu.reset();

        crearFlotaPara(jugador);
        crearFlotaPara(cpu);

        posicionarBarcosAleatoriamente(jugador); // Crear otra funcion para hacerl manualmente
        posicionarBarcosAleatoriamente(cpu);

        sincronizarTableroVisual(jugador, playerBoard, true);
        sincronizarTableroVisual(cpu, cpuBoard, true);

        configurarTableroParaDisparos();

        jugador.setTurno(true);
        actualizarIndicadorTurno();
    }

    /**
     * Crea los barcos para el jugador (user, cpu)
     *
     * @param jugadorModel El jugador que recibe los barcos
     */
    private void crearFlotaPara(JugadorModel jugadorModel) {
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.PORTAAVIONES));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.SUBMARINO));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.SUBMARINO));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.DESTRUCTOR));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.DESTRUCTOR));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.DESTRUCTOR));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.FRAGATA));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.FRAGATA));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.FRAGATA));
        jugadorModel.Barcos.add(new Barco(Barco.TipoBarco.FRAGATA));
    }


    /**
     * Posiciona los barcos de manera aleatoria, dentro del Tablero Logico
     *
     * @param jugadorModel El jugador con sus respectivos barcos
     */
    private void posicionarBarcosAleatoriamente(JugadorModel jugadorModel) {
        Tablero tableroLogico = jugadorModel.tablero;
        for (Barco barco : jugadorModel.Barcos) {
            boolean posicionado = false;
            while (!posicionado) {
                int fila = random.nextInt(tableroLogico.getTAMANIO());
                int col = random.nextInt(tableroLogico.getTAMANIO());
                Barco.Orientacion orientacion = random.nextBoolean() ? Barco.Orientacion.HORIZONTAL : Barco.Orientacion.VERTICAL;
                posicionado = tableroLogico.colocarBarco(barco, fila, col, orientacion);
            }
        }
    }

    /**
     * Conecta el "Tablero" junto con el "Board", para poder usarse.
     *
     * @param jugadorModel El jugador con sus barcos
     * @param boardVisual El visual del tablero
     * @param mostrarBarcos Valida si se pueden poner o no los barcos
     */

    private void sincronizarTableroVisual(JugadorModel jugadorModel, Board boardVisual, boolean mostrarBarcos) {
        boardVisual.clearBoard();
        Tablero tableroLogico = jugadorModel.tablero;

        for (int row = 0; row < tableroLogico.getTAMANIO(); row++) {
            for (int col = 0; col < tableroLogico.getTAMANIO(); col++) {
                Casilla estado = tableroLogico.getCasilla(row, col);
                Node contentToDraw = null;

                switch (estado) {
                    case BARCO:
                        if (mostrarBarcos) {
                            Barco barco = tableroLogico.encontrarBarcoEn(row, col).orElse(null);
                            if (barco != null) {
                                // --- LÓGICA CLAVE PARA OBTENER LA PIEZA CORRECTA ---
                                int partIndex;
                                boolean esVertical = barco.getOrientacion() == Barco.Orientacion.VERTICAL;
                                if (esVertical) {
                                    partIndex = row - barco.getFilaInicio();
                                } else {
                                    partIndex = col - barco.getColumnaInicio();
                                }

                                IShape shape = barco.getTipo().getShape();
                                contentToDraw = shape.getShapePart(partIndex, esVertical, false, false);
                            }
                        }
                        break;

                    case GOLPEADA:
                        // Dibuja la bomba y luego la pieza del barco debajo
                        contentToDraw = new Group(
                                new Shape.Bomba().getShapePart(0, false, false, true)
                        );
                        break;

                    case HUNDIDO:
                        // Dibuja el fuego y luego la pieza del barco debajo
                        Barco barcoHundido = tableroLogico.encontrarBarcoEn(row, col).orElse(null);
                        if (barcoHundido != null) {
                            int partIndex;
                            boolean esVertical = barcoHundido.getOrientacion() == Barco.Orientacion.VERTICAL;
                            if (esVertical) {
                                partIndex = row - barcoHundido.getFilaInicio();
                            } else {
                                partIndex = col - barcoHundido.getColumnaInicio();
                            }
                            Node piezaBarco = barcoHundido.getTipo().getShape().getShapePart(partIndex, esVertical, true, false);
                            Node efectoFuego = new Shape.Fuego().getShapePart(0, false, true, false);
                            contentToDraw = new Group(piezaBarco, efectoFuego); // Fuego encima de la pieza
                        }
                        break;

                    case AGUA:
                        contentToDraw = new Shape.Agua().getShapePart(0, false, false, false);
                        break;
                }

                if (contentToDraw != null) {
                    // Usamos el nuevo método simple de Board.java
                    boardVisual.setCellContent(row, col, contentToDraw);
                }
            }
        }
    }


    /**
     * Presenta el tablero de la CPU de manera funcional.
     */
    private void configurarTableroParaDisparos() {
        for (int row = 0; row < cpuBoard.getBoardSize(); row++) {
            for (int col = 0; col < cpuBoard.getBoardSize(); col++) {
                StackPane cell = cpuBoard.getCell(row, col);
                final int r = row;
                final int c = col;
                cell.setOnMouseClicked(event -> handlePlayerShot(r, c));
            }
        }
    }


    /**
     * Controla los disparos del jugador
     *
     * @param row La fila del disparo
     * @param column La columna del disparo
     */
    private void handlePlayerShot(int row, int column) {
        if (!jugador.isTurno() || juegoTerminado) return;

        Barco barcoGolpeado = cpu.tablero.disparos(row, column);
        sincronizarTableroVisual(cpu, cpuBoard, false);

        if (cpu.tablero.todosLosBarcosHundidos()) {
            finDelJuego(true);
            return;
        }

        if (barcoGolpeado == null) {
            cambiarTurno();
        }
    }


    /**
     * Alterna entre los turnos de los jugadores
     *
     */
    private void cambiarTurno() {
        jugador.setTurno(!jugador.isTurno());
        cpu.setTurno(!cpu.isTurno());
        actualizarIndicadorTurno();

        if (cpu.isTurno() && !juegoTerminado) {
            // Usar un temporizador para simular que la CPU "piensa"
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(this::turnoCPU, 2, TimeUnit.SECONDS);
            scheduler.shutdown();
        }
    }


    /**
     * Maneja los turnos de la CPU
     */
    private void turnoCPU() {
        if(juegoTerminado) return;

        int r, c;
        // Estrategia simple: disparar al azar a una casilla no disparada.
        do {
            r = random.nextInt(jugador.tablero.getTAMANIO());
            c = random.nextInt(jugador.tablero.getTAMANIO());
        } while (jugador.tablero.getCasilla(r, c) != Casilla.VACIA && jugador.tablero.getCasilla(r, c) != Casilla.BARCO);

        final int finalR = r;
        final int finalC = c;

        // Ejecutar en el hilo de la UI
        Platform.runLater(() -> {
            Barco barcoGolpeado = jugador.tablero.disparos(finalR, finalC);
            sincronizarTableroVisual(jugador, playerBoard, true);

            if (jugador.tablero.todosLosBarcosHundidos()) {
                finDelJuego(false);
                return;
            }

            if (barcoGolpeado != null) { // Si la CPU acierta, vuelve a tirar.
                turnoCPU();
            } else {
                cambiarTurno();
            }
        });
    }

    /**
     * Demuestra que los barcos se han hundido, por ende se termina el juego
     *
     * @param jugadorGana Verifica si el jugador gano
     */
    private void finDelJuego(boolean jugadorGana) {
        juegoTerminado = true;
        String titulo = jugadorGana ? "¡Victoria!" : "¡Derrota!";
        String mensaje = jugadorGana ? "¡Has hundido toda la flota enemiga!" : "¡La CPU ha hundido tu flota!";

        Platform.runLater(() -> {
            IndicadorCentral.setText(mensaje);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin de la Partida");
            alert.setHeaderText(titulo);
            alert.setContentText(mensaje);
            alert.showAndWait();
        });
    }

    /**
     * Muestra los turnos durante el juego
     */
    private void actualizarIndicadorTurno() {
        if (juegoTerminado) return;
        if (jugador.isTurno()) {
            IndicadorCentral.setText("¡Tu turno! Dispara en el tablero enemigo.");
        } else {
            IndicadorCentral.setText("Turno de la CPU. Espera...");
        }
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
