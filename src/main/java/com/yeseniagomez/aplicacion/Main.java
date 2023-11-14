package com.yeseniagomez.aplicacion;

import com.yeseniagomez.dominio.JuegoGuayabita;
import com.yeseniagomez.dominio.Jugador;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ImageIcon icon = new ImageIcon("C:\\Users\\Admin\\juegoGuayabita\\src\\main\\java\\iconoGuayaba.png");
        JOptionPane.showMessageDialog(null, " Bienvenido al juego de la Guayabita", "GUAYABITA",JOptionPane.QUESTION_MESSAGE,icon);
        JOptionPane.showMessageDialog(null,
                "---------Instruciones---------\n El juego de la guayabita\n\"1.Cada jugador tiene una oportunidad para apostar.\n" +
                        "2.Lanza el dado ,Si sacas 1 o 6, pierdes la oportunidad de apostar\n" +
                        "3. Si sacas un número del 2 al 5, puedes apostar por una parte o todo el pote\n" +
                        "4. Si el número siguiente es mayor, ganas la apuesta; de lo contrario, pierdes\n" +
                        "5. El juego termina cuando el pote se agota\n" +
                        "¡¡¡Buena suerte!!!");

        int numJugadores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de jugadores:"));
        int apuestaMinima = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la apuesta mínima inicial:"));

        JuegoGuayabita juego = new JuegoGuayabita(numJugadores, apuestaMinima);


        for (int i = 0; i < numJugadores; i++) {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador " + (i + 1) + ":");
            int dinero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el dinero inicial de " + nombre + ":"));
            juego.registrarJugador(i, nombre, dinero);
        }

        while (!juego.juegoTerminado()) {
            juego.jugar();
        }


        StringBuilder resultado = new StringBuilder("Resultado del juego:\n");
        for (Jugador jugador : juego.getJugadores()) {
            resultado.append(jugador.getNombre()).append(" quedó con $").append(jugador.getDinero()).append("\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }
}
