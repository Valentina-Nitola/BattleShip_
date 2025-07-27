package com.example.battleship_.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Representa el tablero en el juego de batalla naval.
 * Esta clase gestiona la cuadrícula, la posición de los barcos,
 * y las reglas de interacción como colocar barcos y procesar disparos.
 * Actúa como el "Modelo" principal del estado del juego.
 * @author Jhon Steven Angulo
 * @version 1.0.2
 */
public class Tablero implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int TAMANIO = 10;
    private Casilla[][] cuadricula;
    private List<Barco> barcos;

    /**
     * Constructor de la clase Tablero.
     * Se encarga de inicializar el tablero con una cuadrícula vacía de 10x10
     * y una lista de barcos vacía.
     */
    public Tablero() {
        cuadricula = new Casilla[TAMANIO][TAMANIO];
        barcos = new ArrayList<>();

        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                cuadricula[i][j] = Casilla.VACIA;
            }
        }
    }


    /**
     * Obtiene el estado de una casilla específica en la cuadrícula.
     * @param fila la fila de la Cuadricula
     * @param columna la columna de la Cuadricula
     * @throws IndexOutOfBoundsException Si la fila o columna está fuera de los límites del tablero.
     * @return El estado de la cuadricula (VACIA, BARCO, GOLPEADA, AGUA).
     */

    public Casilla getCasilla(int fila , int columna) {
        if (fila < 0 || fila >= TAMANIO || columna < 0 || columna >= TAMANIO) {
            throw new IndexOutOfBoundsException("Coordenadas fuera de los limites del tablero");
        }
        return cuadricula[fila][columna];
    }

    /**
     * Establece el estado de una casilla específica en la cuadrícula.
     * @param fila la fila de la Cuadricula
     * @param columna la columna de la Cuadricula
     * @param casilla el nuevo estado para la casilla.
     * @throws IndexOutOfBoundsException Si la fila o columna está fuera de los límites del tablero.
     */
    public void setCasilla(int fila , int columna , Casilla casilla) {
        if (fila < 0 || fila >= TAMANIO || columna < 0 || columna >= TAMANIO) {
            throw new IndexOutOfBoundsException("Coordenadas fuera de los limites del tablero");
        }
        cuadricula[fila][columna] = casilla;
    }


    /**
     * Devuelve el tamaño del tablero.
     * @return el tamaño del lado del tablero.
     */
    public int getTAMANIO() {
        return TAMANIO;
    }

    /**
     * Intenta colocar un barco en el tablero en una posición y orientación específicas.
     * El barco solo se colocará si la posición es válida (dentro de los límites y sin solaparse con otros barcos).
     * @param barco El objeto Barco a colocar.
     * @param filaInicial La fila donde comienza la proa del barco.
     * @param columnaInicial La columna donde comienza la proa del barco.
     * @param orientacion La orientación del barco (HORIZONTAL o VERTICAL).
     * @return {@code true} si el barco se colocó con éxito, {@code false} si la posición no era válida.
     */
    public boolean colocarBarco(Barco barco, int filaInicial , int columnaInicial, Barco.Orientacion orientacion) {
        if (!posicionamientoValido(barco, filaInicial, columnaInicial, orientacion)) {
            return false;
        }

        barco.setPosicion(filaInicial, columnaInicial);
        barco.setOrientacion(orientacion);
        for (int i = 0; i < barco.getTipo().getTamanio(); i++) {
            if (orientacion == Barco.Orientacion.HORIZONTAL) {
                cuadricula[filaInicial][columnaInicial + i ] = Casilla.BARCO;
            } else {
                cuadricula[filaInicial + i ][columnaInicial] = Casilla.BARCO;
            }
        }
        barcos.add(barco);
        return true;
    }

    /**
     * Verifica si la posición propuesta para un barco es válida.
     * Una posición es válida si está completamente dentro de los límites del tablero y no se superpone con ningún otro barco existente.
     * @param barco El barco para el cual se valida la posición.
     * @param filaInicial La fila inicial propuesta.
     * @param columnaInicial La columna inicial propuesta.
     * @param orientacion La orientación propuesta.
     * @return {@code true} si la posición es válida, {@code false} en caso contrario.
     */
    public boolean posicionamientoValido (Barco barco, int filaInicial , int columnaInicial, Barco.Orientacion orientacion) {
        if (orientacion == Barco.Orientacion.HORIZONTAL) {
            if (columnaInicial + barco.getTipo().getTamanio() > TAMANIO) return false;
            for (int i = 0; i < barco.getTipo().getTamanio(); i++) {
                if (cuadricula[filaInicial][columnaInicial + i] != Casilla.VACIA) return false;
            }
        } else {
            if (filaInicial + barco.getTipo().getTamanio() > TAMANIO) return false;
            for (int i = 0; i < barco.getTipo().getTamanio(); i++) {
                if (cuadricula[filaInicial + i][columnaInicial] != Casilla.VACIA) return false;
            }
        }
        return true;
    }

    /**
     * Devuelve el arreglo de los barcos
     * @return barcos
     */
    public List<Barco> getBarcos() {
        return barcos;
    }

    /**
     * Busca un barco en las coordenadas especificadas.
     * Este es un método de ayuda interno para localizar qué barco ocupa una casilla.
     * @param fila Fila a buscar.
     * @param columna Columna a buscar.
     * @return Un {@link Optional} que contiene el Barco si se encuentra, o un Optional vacío si no.
     */
    public Optional<Barco> encontrarBarcoEn(int fila, int columna) {
        for (Barco barco : barcos) {
            if (barco.contieneCoordenadas(fila, columna)) {
                return Optional.of(barco);
            }
        }
        return Optional.empty();
    }


    /**
     * Marca todas las casillas de un barco específico como HUNDIDO.
     * @param barco El barco que ha sido hundido.
     */
    public void marcarBarcoComoHundido(Barco barco) {
        for (int[] coord : barco.getCordenadas()) {
            setCasilla(coord[0], coord[1], Casilla.HUNDIDO);
        }
    }

    /**
     * Procesa un disparo en una coordenada específica.
     * Si golpea un barco, actualiza el estado del barco y la cuadrícula.
     *
     * @param fila La fila del disparo.
     * @param columna La columna del disparo.
     * @return El objeto Barco que fue golpeado, o null si el disparo fue al agua o en una casilla ya disparada.
     */
    public Barco disparos(int fila, int columna ) throws IllegalStateException {

        Casilla estadoActual = getCasilla(fila, columna);

        // Validar si la casilla ya fue disparada antes para no hacer trabajo extra
        if (estadoActual == Casilla.GOLPEADA || estadoActual == Casilla.HUNDIDO || estadoActual == Casilla.AGUA) {
            throw new IllegalStateException(
                    "La casilla (" + fila + ", "+ columna + ") ya ha sido disparada. Su estado es: " + estadoActual);
        }
        //Ya se disparó aquí, no hay resultado nuevo.

        Optional<Barco> barcoOpt = encontrarBarcoEn(fila, columna);

        if (barcoOpt.isPresent()) {
            // ¡Es un golpe!
            Barco barcoGolpeado = barcoOpt.get();
            barcoGolpeado.recibirImpacto(fila, columna); // El barco actualiza su estado interno

            // 3. Revisar si el impacto hundió el barco.
            if (barcoGolpeado.isHundido()) {
                // Si se hundió, marcamos TODAS sus partes como HUNDIDO.
                marcarBarcoComoHundido(barcoGolpeado);
            } else {
                // Si solo fue un golpe, marcamos solo esta casilla como GOLPEADA.
                setCasilla(fila, columna, Casilla.GOLPEADA);
            }
            return barcoGolpeado; // Devolvemos el barco para que el juego sepa qué pasó.

        } else {
            // Es agua
            setCasilla(fila, columna, Casilla.AGUA);
            return null;
        }
    }


    /**
     * Verifica si todos los barcos en el tablero han sido hundidos, lo que indica el final del juego.
     * @return {@code true} si todos los barcos en la lista están hundidos, {@code false} en caso contrario o si no hay barcos.
     */
    public boolean todosLosBarcosHundidos() {
        if (barcos.isEmpty()) {
            return false;
        }
        return barcos.stream().allMatch(Barco::isHundido);
    }

    /**
     * Restablece el tablero a su estado inicial.
     * Elimina todos los barcos y limpia la cuadrícula, dejándola lista para una nueva partida.
     */
    public void limpiarTablero(){
        cuadricula = new Casilla[TAMANIO][TAMANIO];
        barcos = new ArrayList<>();

        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                cuadricula[i][j] = Casilla.VACIA;
            }
        }
        barcos.clear();
    }
}