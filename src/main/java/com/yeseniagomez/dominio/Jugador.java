package com.yeseniagomez.dominio;

public class Jugador {
    private String nombre;
    private int dinero;
    private boolean retirado;

    public Jugador(String nombre, int dinero) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.retirado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void restarDinero(int cantidad) {
        dinero -= cantidad;
    }

    public void sumarDinero(int cantidad) {
        dinero += cantidad;
    }

    public void retirarse() {
        retirado = true;
    }

    public boolean isRetirado() {
        return retirado;
    }

}

