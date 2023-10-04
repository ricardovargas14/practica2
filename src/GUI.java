import java.awt.*;

import javax.swing.*;

public class GUI extends JFrame {
    private JPanel panelMazos;
    private JPanel panelMazoOriginal;
    private JPanel panelMazoControlado;
    private JPanel panelCartasRemovidas;
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
    private Baraja cartasRemovidas;

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
        mazoControlado.setCartas(mazoOriginal.getCartas());

        cartasRemovidas = new Baraja();
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
        Dimension dimensionPanel = new Dimension(0, this.getSize().height / 3);


        // Panel de los mazos -> Panel del mazo original:
        panelMazoOriginal = new JPanel();
        panelMazoOriginal.setLayout(layoutBaraja);
        panelMazoOriginal.setBorder(BorderFactory.createTitledBorder("Mazo original"));
        panelMazoOriginal.setPreferredSize(dimensionPanel);
        panelMazos.add(panelMazoOriginal);

        // Panel de los mazos -> Panel del mazo controlado:
        JPanel panelContenedorMazoControlado = new JPanel();
        panelContenedorMazoControlado.setLayout(new BoxLayout(panelContenedorMazoControlado, BoxLayout.Y_AXIS));
        panelContenedorMazoControlado.setBorder(BorderFactory.createTitledBorder("Mazo controlado"));
        panelContenedorMazoControlado.setPreferredSize(dimensionPanel);
        panelMazoControlado = new JPanel();
        panelMazoControlado.setLayout(layoutBaraja);
        panelContenedorMazoControlado.add(panelMazoControlado);
        panelMazos.add(panelContenedorMazoControlado);
        
        // Panel de los mazos -> Panel del mazo controlado -> Botón de mezclar mazo:
        buttonMezclarMazo = new JButton("Mezclar mazo");

        // Esto hay que modificarlo...
        buttonMezclarMazo.addActionListener(e -> {
            mazoOriginal.barajar();
            actualizarPaneles();
        });

        buttonMezclarMazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelContenedorMazoControlado.add(buttonMezclarMazo);

        // Panel de los mazos -> Panel de las cartas removidas:
        panelCartasRemovidas = new JPanel();
        panelCartasRemovidas.setLayout(layoutBaraja);
        panelCartasRemovidas.setBorder(BorderFactory.createTitledBorder("Cartas removidas"));
        panelCartasRemovidas.setPreferredSize(dimensionPanel);
        panelMazos.add(panelCartasRemovidas);

        revalidate();
    }

    /**
     * Listener del botón de actualizar que se encuentra en el panel de control.
     */
    private void listener() {
        // mazoControlado = new Baraja();

        // por valor:
        for (Component component : panelNumero.getComponents()) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                int numero = Integer.parseInt(checkBox.getText());
                for (int i = 0; i < mazoControlado.getCantidadDeCartas(); i++) {
                    Carta carta = mazoControlado.getCartas().get(i);
                    if (carta.getValor() == numero) {
                        cartasRemovidas.agregarCarta(carta);
                        System.out.println(carta);
                    }
                }
            }
        }

        // por figura:
        for (Component component : panelFigura.getComponents()) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                String figura = checkBox.getText();
                for (int i = 0; i < mazoControlado.getCantidadDeCartas(); i++) {
                    Carta carta = mazoControlado.getCartas().get(i);
                    if (carta.getValor() == 11 && figura.equals("J")) {
                        cartasRemovidas.agregarCarta(carta);
                        System.out.println(carta);
                    } else if (carta.getValor() == 12 && figura.equals("Q")) {
                        cartasRemovidas.agregarCarta(carta);
                        System.out.println(carta);
                    } else if (carta.getValor() == 13 && figura.equals("K")) {
                        cartasRemovidas.agregarCarta(carta);
                        System.out.println(carta);
                    }
                }
            }
        }

        // por color:
        for (Component component : panelColor.getComponents()) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                String color = checkBox.getText();
                for (int i = 0; i < mazoControlado.getCantidadDeCartas(); i++) {
                    Carta carta = mazoControlado.getCartas().get(i);
                    if (carta.getValor() != 0 && carta.getColor().equalsIgnoreCase(color)) {
                        cartasRemovidas.agregarCarta(carta);
                        System.out.println(carta);
                    }
                }
            }
        }

        // por tipo:
        for (Component component : panelTipo.getComponents()) {
            JCheckBox checkBox = (JCheckBox) component;
            if (checkBox.isSelected()) {
                String tipo = checkBox.getText();
                for (int i = 0; i < mazoControlado.getCantidadDeCartas(); i++) {
                    Carta carta = mazoControlado.getCartas().get(i);
                    if (carta.getValor() == 0 && tipo.equals("Joker")) {
                        cartasRemovidas.agregarCarta(carta);
                        System.out.println(carta);
                    } else if (carta.getValor() != 0 && tipo.equals("Normal")) {
                        cartasRemovidas.agregarCarta(carta);
                        System.out.println(carta);
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
        for (int i = 0; i < mazoOriginal.getCantidadDeCartas(); i++) {
            Carta carta = mazoOriginal.getCartas().get(i);
            panelMazoOriginal.add(new CartaGUI(carta));
        }

        // Se actualiza el panel mazo controlado:
        panelMazoControlado.removeAll();
        panelCartasRemovidas.removeAll();
        System.out.println(cartasRemovidas);
        for (int i = 0; i < mazoOriginal.getCantidadDeCartas(); i++) {
            Carta carta = mazoOriginal.getCartas().get(i);
            if (cartasRemovidas.getCartas().contains(carta)) {
                panelCartasRemovidas.add(new CartaGUI(carta));
            }
            else {
                panelMazoControlado.add(new CartaGUI(carta));
            }
        }

        repaint();
        revalidate();
    }

    public static void main(String[] args) {
        new GUI();
    }
}
