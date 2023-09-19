import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Esta clase es la que se encarga de mostrar las cartas en el tablero.
 */
public class JCarta extends JPanel {

    private Carta carta;
    private ImageIcon imagenCarta;
    
    public JCarta(Carta carta) {
        super();
        this.carta = carta;
        cargarImagen();
    }

    private void cargarImagen() {
        String ruta = System.getProperty("user.dir") + "/src/img/";
        if (carta.getPalo() == Palo.joker) {
            ruta += "joker.png";
        } else {
            ruta += carta.getValor() + "-" + carta.getPalo() + ".png";
        }
        imagenCarta = new ImageIcon(ruta);
        System.out.println(ruta);
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
        cargarImagen();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(imagenCarta.getImage(), 0, 0, getWidth(), getHeight(), this);
    }    

}
