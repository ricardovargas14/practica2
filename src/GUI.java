import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame {
    private JPanel panelMazos;
    private JPanel panelMazoOriginal;
    private JPanel panelMazoControlado;

    // Panel de control:
    private JPanel panelControl;
    // Panel do control -> Panel Número
    private JPanel panelNumero;
    private JCheckBox checkBoxNumero3;
    private JCheckBox checkBoxNumero4;
    private JCheckBox checkBoxNumero5;
    private JCheckBox checkBoxNumero6;
    private JCheckBox checkBoxNumero7;
    private JCheckBox checkBoxNumero8;
    private JCheckBox checkBoxNumero9;
    private JCheckBox checkBoxNumero10;
    // Panel de control -> Panel Figura
    private JPanel panelFigura;
    private JCheckBox checkBoxFiguraJ;
    private JCheckBox checkBoxFiguraK;
    private JCheckBox checkBoxFiguraQ;
    // Panel de control -> Panel Color
    private JPanel panelColor;
    private JCheckBox checkBoxColorRojo;
    private JCheckBox checkBoxColorNegro;
    private JCheckBox checkBoxColorVerde;
    private JCheckBox checkBoxColorAzul;
    private JCheckBox checkBoxColorAmarillo;
    // Panel de control -> Panel Tipo
    private JPanel panelTipo;
    private JCheckBox checkBoxCartaNormal;
    private JCheckBox checkBoxCartaJoker;
    // Panel de control -> Botón de actualizar
    private JButton buttonActualizar;

    private Baraja mazoOriginal;

    public GUI() {
        super("Cartas Five Crowns");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);

        crearCartas();
        crearPaneles();
        actualizarPaneles();
    }

    private void crearCartas() {
        Baraja baraja = new Baraja();
        baraja.crearBaraja();
        baraja.barajar();

        mazoOriginal = new Baraja();
        for (int i = 0; i < 10; i++) {
            mazoOriginal.agregarCarta(baraja.removerCarta());
        }
    }

    private void crearPaneles() {
        // Panel de control:
        panelControl = new JPanel();
        panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.Y_AXIS));
        panelControl.setBorder(BorderFactory.createTitledBorder("Remover cartas"));
        // panelControl.setPreferredSize(new Dimension(200, 0));
        add(panelControl, BorderLayout.WEST);

        // Panel do control -> Panel Número
        JPanel panelNumero = new JPanel();
        panelNumero.setLayout(new GridLayout(0, 1));
        panelNumero.setBorder(BorderFactory.createTitledBorder("Número"));
        panelNumero.add(checkBoxNumero3 = new JCheckBox("3"));
        panelNumero.add(checkBoxNumero4 = new JCheckBox("4"));
        panelNumero.add(checkBoxNumero5 = new JCheckBox("5"));
        panelNumero.add(checkBoxNumero6 = new JCheckBox("6"));
        panelNumero.add(checkBoxNumero7 = new JCheckBox("7"));
        panelNumero.add(checkBoxNumero8 = new JCheckBox("8"));
        panelNumero.add(checkBoxNumero9 = new JCheckBox("9"));
        panelNumero.add(checkBoxNumero10 = new JCheckBox("10"));

        panelControl.add(panelNumero);

        // Panel de control -> Panel Figura
        JPanel panelFigura = new JPanel();
        panelFigura.setLayout(new GridLayout(1, 0));
        panelFigura.setBorder(BorderFactory.createTitledBorder("Figura"));
        panelFigura.add(checkBoxFiguraJ = new JCheckBox("J"));
        panelFigura.add(checkBoxFiguraK = new JCheckBox("K"));
        panelFigura.add(checkBoxFiguraQ = new JCheckBox("Q"));

        panelControl.add(panelFigura);

        // Panel de control -> Panel Color
        JPanel panelColor = new JPanel();
        panelColor.setLayout(new GridLayout(0, 1));
        panelColor.setBorder(BorderFactory.createTitledBorder("Color"));
        panelColor.add(checkBoxColorRojo = new JCheckBox("Rojo"));
        panelColor.add(checkBoxColorNegro = new JCheckBox("Negro"));
        panelColor.add(checkBoxColorVerde = new JCheckBox("Verde"));
        panelColor.add(checkBoxColorAzul = new JCheckBox("Azul"));
        panelColor.add(checkBoxColorAmarillo = new JCheckBox("Amarillo"));

        panelControl.add(panelColor);

        // Panel de control -> Panel Tipo
        JPanel panelTipo = new JPanel();
        panelTipo.setLayout(new GridLayout(0, 1));
        panelTipo.setBorder(BorderFactory.createTitledBorder("Tipo de carta"));
        panelTipo.add(checkBoxCartaNormal = new JCheckBox("Normal"));
        panelTipo.add(checkBoxCartaJoker = new JCheckBox("Joker"));

        panelControl.add(panelTipo);

        // Panel de control -> botón de actualizar
        panelControl.add(buttonActualizar = new JButton("Actualizar"));
        buttonActualizar.addActionListener(e -> listener());

        // Panel de los mazos:
        panelMazos = new JPanel();
        panelMazos.setLayout(new GridLayout(0, 1));
        add(panelMazos, BorderLayout.CENTER);

        GridLayout layoutBaraja = new GridLayout(1, 0, -200, 0);
        int altoPanelBaraja = getSize().height / 2;
        // Panel del mazo original:
        panelMazoOriginal = new JPanel();
        panelMazoOriginal.setLayout(layoutBaraja);
        panelMazoOriginal.setPreferredSize(new Dimension(0, altoPanelBaraja));
        panelMazos.add(panelMazoOriginal);

        // Panel del mazo controlado:
        panelMazoControlado = new JPanel();
        panelMazoControlado.setLayout(layoutBaraja);
        panelMazoControlado.setPreferredSize(new Dimension(0, altoPanelBaraja));
        panelMazos.add(panelMazoControlado);

        revalidate();
    }

    private void listener() {
        System.out.println("Se presionó el botón");
        for (Component component : panelControl.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component c : panel.getComponents()) {
                    if (c instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) c;
                        if (checkBox.isSelected()) {
                            System.out.println(checkBox.getText());
                        }
                    }
                }
            }
        }
    }

    private void actualizarPaneles() {
        // Se actualiza el panel mazo original:
        panelMazoOriginal.removeAll();
        for (Carta carta : mazoOriginal.getCartas()) {
            panelMazoOriginal.add(new JCarta(carta));
        }

        revalidate();
    }

    public static void main(String[] args) {
        new GUI();
    }
}
