package com.example.battleship_.controller;

import com.example.battleship_.model.*;
import com.example.battleship_.view.ComoJugarView;
import com.example.battleship_.view.JuegoView;
import com.example.battleship_.view.MenuView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
 * @author Valentina Nitolam
 * @author Jhojan Moreno
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
     * Instancia del jugador humano.
     */
    JugadorModel jugador = new JugadorModel();

    /**
     * Instancia del jugador CPU.
     */
    JugadorModel Cpu = new JugadorModel();
    @FXML
    private Label IndicadorCentral;

    /**
     * Inicializa la vista del juego, configurando los tableros y las leyendas visuales
     * de los efectos especiales (agua, tocado, hundido) en el layout.
     */
    @FXML
    public void initialize() throws InterruptedException {
        //Esta madre crea los tableros vea pues
        playerBoard = new Board(10);
        cpuBoard = new Board(10);
        tableroPlayer.getChildren().add(playerBoard);
        tableroCPU.getChildren().add(cpuBoard);
        iniciarPartida();


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
     * Inicia una nueva partida de Batleship de piratiñas.
     * Crea los barcos, reparte los barcos a los jugadores y establece el turno.
     */
    public void iniciarPartida() throws InterruptedException {
        //Primero en teoria con los del jugador
        Barco Portaviones = new Barco(Barco.TipoBarco.PORTAAVIONES, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Submarino1 = new Barco(Barco.TipoBarco.SUBMARINO, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Submarino2 = new Barco(Barco.TipoBarco.SUBMARINO, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Destructor1 = new Barco(Barco.TipoBarco.DESTRUCTOR, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Destructor2 = new Barco(Barco.TipoBarco.DESTRUCTOR, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Destructor3 = new Barco(Barco.TipoBarco.DESTRUCTOR, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Fragata1 = new Barco(Barco.TipoBarco.FRAGATA, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Fragata2 = new Barco(Barco.TipoBarco.FRAGATA, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Fragata3 = new Barco(Barco.TipoBarco.FRAGATA, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Fragata4 = new Barco(Barco.TipoBarco.FRAGATA, Barco.Orientacion.HORIZONTAL, 0, 0);
        jugador.Barcos.add(Portaviones);
        jugador.Barcos.add(Submarino1);
        jugador.Barcos.add(Submarino2);
        jugador.Barcos.add(Destructor1);
        jugador.Barcos.add(Destructor2);
        jugador.Barcos.add(Destructor3);
        jugador.Barcos.add(Fragata1);
        jugador.Barcos.add(Fragata2);
        jugador.Barcos.add(Fragata3);
        jugador.Barcos.add(Fragata4);
        jugador.setTurno(true);
        System.out.println("El jugador tiene " + jugador.Barcos.size() + " Barcos");
        //Cpu
        Barco Portaviones2 = new Barco(Barco.TipoBarco.PORTAAVIONES, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Submarino3 = new Barco(Barco.TipoBarco.SUBMARINO, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Submarino4 = new Barco(Barco.TipoBarco.SUBMARINO, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Destructor4 = new Barco(Barco.TipoBarco.DESTRUCTOR, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Destructor5 = new Barco(Barco.TipoBarco.DESTRUCTOR, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Destructor6 = new Barco(Barco.TipoBarco.DESTRUCTOR, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Fragata5 = new Barco(Barco.TipoBarco.FRAGATA, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Fragata6 = new Barco(Barco.TipoBarco.FRAGATA, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Fragata7 = new Barco(Barco.TipoBarco.FRAGATA, Barco.Orientacion.HORIZONTAL, 0, 0);
        Barco Fragata8 = new Barco(Barco.TipoBarco.FRAGATA, Barco.Orientacion.HORIZONTAL, 0, 0);
        Cpu.Barcos.add(Portaviones2);
        Cpu.Barcos.add(Submarino3);
        Cpu.Barcos.add(Submarino4);
        Cpu.Barcos.add(Destructor4);
        Cpu.Barcos.add(Destructor5);
        Cpu.Barcos.add(Destructor6);
        Cpu.Barcos.add(Fragata5);
        Cpu.Barcos.add(Fragata6);
        Cpu.Barcos.add(Fragata7);
        Cpu.Barcos.add(Fragata8);
        System.out.println("El Cpu tiene " + Cpu.Barcos.size() + " Barcos");
        Cpu.setTurno(false);
        Turnos();



        //mostrarBarcosJugador(jugador.getBarcos(), playerBoard);
    }



    public void Turnos() throws InterruptedException {
        if (jugador.isTurno()) {
            IndicadorCentral.setText("Turno del Jugador, Elige una celda");
        } else {
            IndicadorCentral.setText("Turno del Cpu, Eligiendo donde atacar...");
            Cpu.wait(3500);

            Cpu.decision();


        }

    }












    /*
    // Metodo?
    public void mostrarBarcosJugador(List<Barco> barcos, Board board) {
        for (Barco barco : barcos) {
            IShape shape;
            switch (barco.getTipo()) {
                case PORTAAVIONES -> shape = new Shape.Portaaviones();
                case SUBMARINO -> shape = new Shape.Submarino();
                case DESTRUCTOR -> shape = new Shape.Destructor();
                case FRAGATA -> shape = new Shape.Fragata();
                default -> {
                    continue;
                }
            }

            boolean esVertical = barco.getOrientacion() == Barco.Orientacion.VERTICAL;
            boolean estaHundido = barco.isHundido();

            for (int[] coord : barco.getCordenadas()) {
                int fila = coord[0];
                int columna = coord[1];
                boolean fueGolpeada = barco.estanGolpeadasEn(fila, columna);

                board.setCellShape(fila, columna, shape, esVertical, fueGolpeada, estaHundido);
            }
        }

        playerBoard.addClickHandler((fila, columna) -> {
            System.out.println("Click en jugador: " + fila + "," + columna);
        });


    }*/


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
