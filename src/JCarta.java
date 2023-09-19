import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Esta clase es la que se encarga de mostrar las cartas en el tablero.
 */
public class JCarta extends JLabel {

    private Carta carta;
    
    public JCarta(Carta carta) {
        super();
        this.carta = carta;
        cargarImagen();
    }

    private void cargarImagen() {
        String ruta = "src/img/";
        if (carta.getPalo() == Palo.joker) {
            ruta += "joker.png";
        } else {
            ruta += carta.getValor() + "-" + carta.getPalo() + ".png";
        }
        setIcon(new ImageIcon(ruta));
        System.out.println(ruta);
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;        
        setIcon(new ImageIcon("src/img/" + carta.getPalo() + carta.getValor() + ".png"));
    }
}
