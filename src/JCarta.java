import java.awt.Graphics;
import java.awt.Image;

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
