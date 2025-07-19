package com.example.battleship_.model;

/**
 * Interfaz que define las operaciones básicas para controlar un reproductor multimedia.
 *
 * Esta interfaz es útil para desacoplar la lógica de control multimedia de la lógica del juego.
 */
public interface MediaPlayerWrapper {

    /**
     * Inicia o reanuda la reproducción del contenido multimedia.
     *
     * Las implementaciones pueden manejar la lógica para reproducir desde el principio
     * o continuar desde donde se pausó.
     */
    void play();

    /**
     * Pausa la reproducción del contenido multimedia.
     *
     * Las implementaciones pueden conservar el estado actual para permitir reanudar
     * posteriormente desde el mismo punto.
     */
    void pause();
}
