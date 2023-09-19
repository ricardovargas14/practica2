import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;

public class Test {
    public static void main(String[] args) {
        Baraja baraja = new Baraja();
        baraja.crearBaraja();
        System.out.println("Cantidad de cartas: " + baraja.getCantidadDeCartas());
        System.out.println("Baraja:\n" + baraja);

        // Se prueba JCarta:
        JCarta jCarta = new JCarta(baraja.removerCarta());
        
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(jCarta, BorderLayout.CENTER);
        frame.setVisible(true);


    }


}
