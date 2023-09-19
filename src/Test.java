import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;

public class Test {
    public static void main(String[] args) {
        Baraja baraja = new Baraja();
        baraja.crearBaraja();
        baraja.barajar();
        System.out.println("Cantidad de cartas: " + baraja.getCantidadDeCartas());
        System.out.println("Baraja:\n" + baraja);

        // Se prueba JCarta:
        JCarta jCarta = new JCarta(baraja.removerCarta());
        
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 0));
        for (int i = 0; i < 5; i++) {
            frame.add(new JCarta(baraja.removerCarta()));
        }
        // frame.add(jCarta);
        frame.setVisible(true);


    }


}
