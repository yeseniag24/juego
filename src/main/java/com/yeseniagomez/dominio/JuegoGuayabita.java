package com.yeseniagomez.dominio;

import javax.swing.*;

public class JuegoGuayabita {
    private int pote;
    private Jugador[] jugadores;
    private int jugadorActual;
    private int rondasJugadas;

    public JuegoGuayabita(int numJugadores, int apuestaMinima) {
        this.pote = numJugadores * apuestaMinima;
        this.jugadores = new Jugador[numJugadores];
        this.jugadorActual = 0;
        this.rondasJugadas = 0;
    }

    public void registrarJugador(int indice, String nombre, int dinero) {
        jugadores[indice] = new Jugador(nombre, dinero);
    }

    public int lanzarDado() {
        return (int) (Math.random() * 6) + 1;
    }

    public boolean jugadorPuedeApostar(int cantidad) {
        return jugadores[jugadorActual].getDinero() >= cantidad;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void salirDelJuego() {
        JOptionPane.showMessageDialog(null, "Juego interrumpido. Gracias por jugar.");
        System.exit(0);
    }

    public void jugar() {
        JOptionPane.showMessageDialog(null, "Estado del juego:\n" +
                "Pote actual: $" + pote + "\n" +
                "Ronda: " + (rondasJugadas + 1) + "\n" +
                "Jugador actual: " + jugadores[jugadorActual].getNombre());

        int dado = lanzarDado();
        JOptionPane.showMessageDialog(null, "Jugador " + jugadores[jugadorActual].getNombre() + ": Has sacado un " + dado);

        if (dado == 1 || dado == 6) {
            JOptionPane.showMessageDialog(null, "¡Perdiste la oportunidad de apostar! Siguiente jugador.");
        } else {
            int opcion = JOptionPane.showOptionDialog(null, "¿Quieres apostar, retirarte o salir?", "Opciones",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Apostar", "Retirarse", "Salir"}, "Apostar");

            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    String apuestaStr = JOptionPane.showInputDialog("Ingresa tu apuesta (máximo $" + pote + "):");
                    int apuesta = Integer.parseInt(apuestaStr);

                    if (apuesta > pote) {
                        JOptionPane.showMessageDialog(null, "La apuesta no puede ser mayor que el pote actual.");
                    } else if (jugadorPuedeApostar(apuesta)) {
                        int nuevoDado = lanzarDado();
                        JOptionPane.showMessageDialog(null, "Has sacado un " + nuevoDado);

                        if (nuevoDado > dado) {
                            JOptionPane.showMessageDialog(null, "¡Ganaste $" + apuesta + "!");
                            jugadores[jugadorActual].sumarDinero(apuesta);
                            pote -= apuesta;
                        } else {
                            JOptionPane.showMessageDialog(null, "Perdiste $" + apuesta + ".");
                            jugadores[jugadorActual].restarDinero(apuesta);
                            pote += apuesta;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No tienes suficiente dinero para realizar esa apuesta.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingresa un número válido.");
                }
            } else if (opcion == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Te has retirado. Siguiente jugador.");
                jugadores[jugadorActual].retirarse();
            } else {
                salirDelJuego();
            }
        }


        jugadorActual = (jugadorActual + 1) % jugadores.length;

        if (jugadorActual == 0) {
            rondasJugadas++;
            JOptionPane.showMessageDialog(null, "Fin de la ronda " + rondasJugadas);
        }
    }

    public boolean juegoTerminado() {

        return pote <= 0 || todosRetirados();
    }

    private boolean todosRetirados() {
        for (Jugador jugador : jugadores) {
            if (!jugador.isRetirado()) {
                return false;
            }
        }
        return true;
    }

    public Jugador obtenerGanador() {

        Jugador ganador = jugadores[0];
        for (int i = 1; i < jugadores.length; i++) {
            if (jugadores[i].getDinero() > ganador.getDinero()) {
                ganador = jugadores[i];
            }
        }

        return ganador;
    }
}
