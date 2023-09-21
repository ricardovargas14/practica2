import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Esta clase es la que se encarga de mostrar las cartas en el tablero.
 */
public class CartaGUI extends JPanel {

    private Carta carta;
    private ImageIcon imagenCarta;
    
    /**
     * Constructor de la clase.
     * @param carta Carta que se va a mostrar.
     */
    public CartaGUI(Carta carta) {
        super();
        this.carta = carta;
        this.setOpaque(false);
        cargarImagen();
    }

    /**
     * Método que carga la imagen de la carta.
     */
    private void cargarImagen() {
        String ruta = System.getProperty("user.dir") + "/src/img/";
        if (carta.getPalo() == Palo.joker) {
            ruta += "joker.png";
        } else {
            ruta += carta.getValor() + "-" + carta.getPalo() + ".png";
        }
        imagenCarta = new ImageIcon(ruta);
    }

    /**
     * Getter de la carta.
     * @return Carta.
     */
    public Carta getCarta() {
        return carta;
    }

    /**
     * Setter de la carta.
     * @param carta Carta.
     */
    public void setCarta(Carta carta) {
        this.carta = carta;
        cargarImagen();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Obtiene la imagen original
        Image imgOriginal = imagenCarta.getImage();
        int anchoOriginal = imgOriginal.getWidth(this);
        int altoOriginal = imgOriginal.getHeight(this);

        // Calcula el nuevo ancho y alto manteniendo la relación de aspecto
        int nuevoAncho = anchoOriginal;
        int nuevoAlto = altoOriginal;

        if (anchoOriginal > anchoPanel) {
            nuevoAncho = anchoPanel;
            nuevoAlto = (int) (nuevoAncho * ((double) altoOriginal / anchoOriginal));
        }

        if (nuevoAlto > altoPanel) {
            nuevoAlto = altoPanel;
            nuevoAncho = (int) (nuevoAlto * ((double) anchoOriginal / altoOriginal));
        }

        // Centra la imagen en el panel
        int x = (anchoPanel - nuevoAncho) / 2;
        int y = (altoPanel - nuevoAlto) / 2;

        // Escala y dibuja la imagen manteniendo la relación de aspecto
        g.drawImage(imgOriginal, x, y, nuevoAncho, nuevoAlto, this);
    }
}
