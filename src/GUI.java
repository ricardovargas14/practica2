import java.awt.*;

import javax.swing.*;

public class GUI extends JFrame {
    private JPanel panelMazos;
    private JPanel panelMazoOriginal;
    private JPanel panelMazoControlado;
    private JButton buttonMezclarMazo;

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
    private Baraja mazoControlado;

    public GUI() {
        super("Cartas Five Crowns");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);

        crearCartas();
        crearPaneles();
        actualizarPaneles();
    }

    /**
     * Este método crea una baraja de completa de 58 cartas, extrae 10 cartas de
     * la baraja y las guarda en el mazo original.
     * 
     * También crea un mazo vacío que se usará para guardar las cartas que se
     * remueven.
     * 
     * Esto después se cambiara para que utilice pilas en vez de barajas.
     */
    private void crearCartas() {
        Baraja baraja = new Baraja();
        baraja.crearBaraja();
        baraja.barajar();

        mazoOriginal = new Baraja();
        for (int i = 0; i < 10; i++) {
            mazoOriginal.agregarCarta(baraja.removerCarta());
        }

        mazoControlado = new Baraja();
    }

    /**
     * Este método crea los paneles que se van a mostrar en la ventana.
     */
    private void crearPaneles() {
        // Panel de control:
        panelControl = new JPanel();
        panelControl.setLayout(new BoxLayout(panelControl, BoxLayout.Y_AXIS));
        panelControl.setBorder(BorderFactory.createTitledBorder("Remover cartas"));
        add(panelControl, BorderLayout.WEST);

        // Panel do control -> Panel Número
        panelNumero = new JPanel();
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
        panelFigura = new JPanel();
        panelFigura.setLayout(new GridLayout(1, 0));
        panelFigura.setBorder(BorderFactory.createTitledBorder("Figura"));
        panelFigura.add(checkBoxFiguraJ = new JCheckBox("J"));
        panelFigura.add(checkBoxFiguraK = new JCheckBox("K"));
        panelFigura.add(checkBoxFiguraQ = new JCheckBox("Q"));

        panelControl.add(panelFigura);

        // Panel de control -> Panel Color
        panelColor = new JPanel();
        panelColor.setLayout(new GridLayout(0, 1));
        panelColor.setBorder(BorderFactory.createTitledBorder("Color"));
        panelColor.add(checkBoxColorRojo = new JCheckBox("Rojo"));
        panelColor.add(checkBoxColorNegro = new JCheckBox("Negro"));
        panelColor.add(checkBoxColorVerde = new JCheckBox("Verde"));
        panelColor.add(checkBoxColorAzul = new JCheckBox("Azul"));
        panelColor.add(checkBoxColorAmarillo = new JCheckBox("Amarillo"));

        panelControl.add(panelColor);

        // Panel de control -> Panel Tipo
        panelTipo = new JPanel();
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

        GridLayout layoutBaraja = new GridLayout(1, 0);
        int altoPanelBaraja = getSize().height / 2;
        // Panel de los mazos -> Panel del mazo original:
        JPanel panelContenedorMazoOriginal = new JPanel();
        panelContenedorMazoOriginal.setLayout(new BoxLayout(panelContenedorMazoOriginal, BoxLayout.Y_AXIS));
        panelContenedorMazoOriginal.setBorder(BorderFactory.createTitledBorder("Mazo"));
        panelContenedorMazoOriginal.setPreferredSize(new Dimension(0, altoPanelBaraja));
        panelMazoOriginal = new JPanel();
        panelMazoOriginal.setLayout(layoutBaraja);
        panelContenedorMazoOriginal.add(panelMazoOriginal);
        panelMazos.add(panelContenedorMazoOriginal);

        // Panel de los mazos -> Panel del mazo original -> Botón de mezclar mazo:
        buttonMezclarMazo = new JButton("Mezclar mazo");
        buttonMezclarMazo.addActionListener(e -> {
            mazoOriginal.barajar();
            actualizarPaneles();
        });
        buttonMezclarMazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenedorMazoOriginal.add(buttonMezclarMazo);

        // Panel de los mazos -> Panel del mazo controlado:
        panelMazoControlado = new JPanel();
        panelMazoControlado.setLayout(layoutBaraja);
        panelMazoControlado.setBorder(BorderFactory.createTitledBorder("Cartas removidas"));
        panelMazoControlado.setPreferredSize(new Dimension(0, altoPanelBaraja));
        panelMazos.add(panelMazoControlado);

        revalidate();
    }

    /**
     * Listener del botón de actualizar que se encuentra en el panel de control.
     */
    private void listener() {
        mazoControlado = new Baraja();

        // por valor:
        for (Component component : panelNumero.getComponents()) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                int numero = Integer.parseInt(checkBox.getText());
                for (Carta carta : mazoOriginal.getCartas()) {
                    if (carta.getValor() == numero) {
                        mazoControlado.agregarCarta(carta);
                    }
                }
            }
        }

        // por figura:
        for (Component component : panelFigura.getComponents()) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                String figura = checkBox.getText();
                for (Carta carta : mazoOriginal.getCartas()) {
                    if (carta.getValor() == 11 && figura.equals("J")) {
                        mazoControlado.agregarCarta(carta);
                    } else if (carta.getValor() == 12 && figura.equals("Q")) {
                        mazoControlado.agregarCarta(carta);
                    } else if (carta.getValor() == 13 && figura.equals("K")) {
                        mazoControlado.agregarCarta(carta);
                    }
                }
            }
        }

        // por color:
        for (Component component : panelColor.getComponents()) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                String color = checkBox.getText();
                for (Carta carta : mazoOriginal.getCartas()) {
                    if (carta.getValor() != 0 && carta.getColor().equalsIgnoreCase(color)) {
                        mazoControlado.agregarCarta(carta);
                    }
                }
            }
        }

        // por tipo:
        for (Component component : panelTipo.getComponents()) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                String tipo = checkBox.getText();
                for (Carta carta : mazoOriginal.getCartas()) {
                    if (carta.getValor() == 0 && tipo.equals("Joker")) {
                        mazoControlado.agregarCarta(carta);
                    } else if (carta.getValor() != 0 && tipo.equals("Normal")) {
                        mazoControlado.agregarCarta(carta);
                    }
                }
            }
        }

        // Se actualiza el panel mazo controlado:
        actualizarPaneles();
    }

    private void actualizarPaneles() {
        // Se actualiza el panel mazo original:
        panelMazoOriginal.removeAll();
        for (Carta carta : mazoOriginal.getCartas()) {
            panelMazoOriginal.add(new CartaGUI(carta));
        }

        // Se actualiza el panel mazo controlado:
        panelMazoControlado.removeAll();
        for (Carta carta : mazoControlado.getCartas()) {
            panelMazoControlado.add(new CartaGUI(carta));
        }

        repaint();
        revalidate();
    }

    public static void main(String[] args) {
        new GUI();
    }
}
