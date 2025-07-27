package com.example.battleship_.model;

import com.example.battleship_.model.PartidaGuardada;

import java.io.*;

/**
 * Gestiona el guardado y la carga del estado del juego en un archivo.
 */
public class SaveLoadManager {
    private static final String RUTA_GUARDADO = "partida_piratewar.dat";

    /**
     * Guarda el estado actual de la partida en un archivo.
     * @param partida El objeto PartidaGuardada que contiene el estado del juego.
     */
    public static void guardarPartida(PartidaGuardada partida) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_GUARDADO))) {
            oos.writeObject(partida);
            System.out.println("Partida guardada exitosamente en " + RUTA_GUARDADO);
        } catch (IOException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carga el estado de la partida desde un archivo.
     * @return Un objeto PartidaGuardada con los datos del juego, o null si no se pudo cargar.
     */
    public static PartidaGuardada cargarPartida() {
        if (!archivoDeGuardadoExiste()) {
            System.out.println("No se encontró archivo de guardado.");
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_GUARDADO))) {
            PartidaGuardada partida = (PartidaGuardada) ois.readObject();
            System.out.println("Partida cargada exitosamente.");
            return partida;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Verifica si el archivo de guardado existe.
     * @return true si el archivo existe, false en caso contrario.
     */
    public static boolean archivoDeGuardadoExiste() {
        File f = new File(RUTA_GUARDADO);
        return f.exists() && !f.isDirectory();
    }
}