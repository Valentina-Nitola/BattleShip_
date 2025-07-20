package com.example.battleship_.model;

 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;

/**
 * Representa un barco en el juego de batalla naval.
 * Contiene informacion de su tipo, tamaño orientacion y estado.
 * @author Braulio
 */
public class Barco implements Serializable {

    private TipoBarco tipo;
    private Orientacion orientacion;
    private int filaInicio;
    private int columnaInicio;
    private List<Boolean> partesGolpeadas;
    private boolean hundido;


    /**
     * Enumeracion que define los tipos de barco.
     */
    public enum TipoBarco {
        PORTAAVIONES (4,"Portaaviones"),
        SUBMARINO (3,"Submarino"),
        DESTRUCTOR (2,"Destructor"),
        FRAGATA(1,"Fragata");

        private final int tamanio;
        private final String nombre;

        TipoBarco(int tamanio, String nombre) {
            this.tamanio = tamanio;
            this.nombre = nombre;
        }

        public int getTamanio() {return tamanio;}
        public String getNombre() {return nombre;}
    }

    /**
     * Enumeracion que define la orientacion del barco.
     */
    public enum Orientacion {
        HORIZONTAL,VERTICAL;
    }

    /**
     * Constructor del barco.
     * @param tipo Tipo del barco
     * @param orientacion Orientacion el barco
     * @param filaInicio Fila inicial de colocacion
     * @param columnaInicio Columna inicial de colocacion
     */
    public Barco(TipoBarco tipo, Orientacion orientacion, int filaInicio, int columnaInicio) {
        this.tipo = tipo;
        this.orientacion = orientacion;
        this.filaInicio = filaInicio;
        this.columnaInicio = columnaInicio;
        this.partesGolpeadas = new ArrayList<>();
        this.hundido = false;


        //inicia todas las partes como no golpeadas
        for (int i = 0 ; i < tipo.getTamanio(); i++){
            partesGolpeadas.add(false);
        }
    }

    /**
     * Obtiene las coordenadas ocupadas por el barco.
     * @return Lista de coordenadas fila,columna
     */
    public List<int []> getCordenadas() {
        List<int []> coordenadas = new ArrayList<>();

        for (int i = 0 ; i < tipo.getTamanio(); i++){
            if (orientacion == Orientacion.HORIZONTAL){
                coordenadas.add(new int[]{filaInicio,columnaInicio + i});
            }else {
                coordenadas.add(new int[]{filaInicio + i,columnaInicio});
            }
        }

        return coordenadas;
    }

    /**
     * Verifica si la coordenada pertenece al barco.
     * @param fila Fila a verificar
     * @param columna Columna a verificar
     * @return true si la coordenada pertenece al barco
     */
    public boolean contieneCoordenadas(int fila, int columna) {
        List<int[]> coordenadas = getCordenadas();

        for (int[] coord : coordenadas) {
            if (coord[0] == fila && coord[1] == columna){
                return true;
            }

        }
        return false;
    }

    /**
     * Recibe un impacto en una coordenada especifica
     * @param fila Fila de impacto
     * @param columna Columna de impacto
     * @return true si la coordenada pertenece al barco
     */
    public boolean recibirImpacto(int fila, int columna) {
        List<int[]> coordenadas = getCordenadas();

        for (int i = 0 ; i < coordenadas.size(); i++ ) {
            int[] coord = coordenadas.get(i);
            if  (coord[0] == fila && coord[1] == columna){
                partesGolpeadas.set(i, true);
                verificarSiHunido();
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el barco se hundio completamente
     */
    public void verificarSiHunido() {
        hundido = partesGolpeadas.stream().allMatch(golpeada -> golpeada);
    }

    /**
     * verifica si una parte especifica del barco ha sido golpeada
     * @param fila
     * @param columna
     * @return
     */
    public boolean estanGolpeadasEn(int  fila, int columna) {
        List<int[]> coordenadas = getCordenadas();

        for (int i = 0 ; i < coordenadas.size(); i++ ) {
            int[] coord = coordenadas.get(i);
            if (coord[0] == fila && coord[1] == columna){
                return partesGolpeadas.get(i);
            }
        }
        return false;
    }

    //getters y setters

    public TipoBarco getTipo() {return tipo;}
    public Orientacion getOrientacion() {return orientacion;}
    public int getFilaInicio() {return filaInicio;}
    public int getColumnaInicio() {return columnaInicio;}
    public boolean isHundido() {return hundido;}

    public void setOrientacion (Orientacion orientacion) {this.orientacion = orientacion;}
    public void setPosicion (int fila,int columna ) {
        this.filaInicio = fila;
        this.columnaInicio = columna;
    }
}
