package com.example.battleship_.model;

import com.example.battleship_.model.IShape;

import java.util.HashMap;
import java.util.Map;

public class PreparacionManager {

    private boolean vertical = false;
    private IShape barcoSeleccionado;
    private Map<IShape, Integer> barcosDisponibles = new HashMap<>();

    public PreparacionManager() {
        // Inicializa la cantidad de cada tipo de barco
        // Ejemplo: 1 portaaviones, 2 submarinos, 3 destructores, 4 fragatas
        // Usualmente usarías enums o clases identificadoras, aquí simple:
        barcosDisponibles.put(new com.example.battleship_.model.Shape.Portaaviones(), 1);
        barcosDisponibles.put(new com.example.battleship_.model.Shape.Submarino(), 2);
        barcosDisponibles.put(new com.example.battleship_.model.Shape.Destructor(), 3);
        barcosDisponibles.put(new com.example.battleship_.model.Shape.Fragata(), 4);
    }

    public boolean isVertical() {
        return vertical;
    }

    public void toggleOrientacion() {
        vertical = !vertical;
    }

    public IShape getBarcoSeleccionado() {
        return barcoSeleccionado;
    }

    public void setBarcoSeleccionado(IShape barco) {
        barcoSeleccionado = barco;
    }

    public int barcosDisponibles(IShape barco) {
        return barcosDisponibles.getOrDefault(barco, 0);
    }

    public boolean usarBarco(IShape barco) {
        int cantidad = barcosDisponibles(barco);
        if (cantidad > 0) {
            barcosDisponibles.put(barco, cantidad - 1);
            return true;
        }
        return false;
    }

    // Aquí puedes agregar métodos para validar ubicaciones, obtener posiciones, etc.
}
