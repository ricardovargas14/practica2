/**
 * Esta clase modela una carta de una baraja de Five Crowns.
 */
public class Carta {
    private int valor;
    private Palo palo;

    /**
     * Constructor de la clase.
     * @param valor Valor de la carta.
     * @param palo Palo de la carta.
     */
    public Carta(int valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
    }

    /**
     * Getter del valor de la carta.
     * @return Valor de la carta.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Getter del palo de la carta.
     * @return Palo de la carta.
     */
    public Palo getPalo() {
        return palo;
    }

    public String getColor() {
        return palo.getColor();
    }

    /**
     * Setter del valor de la carta.
     * @param valor Valor de la carta.
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Setter del palo de la carta.
     * @param palo Palo de la carta.
     */
    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    @Override
    public String toString() {
        return palo + " " + valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Carta) {
            Carta carta = (Carta) obj;
            return carta.getValor() == this.valor && carta.getPalo() == this.palo;
        }
        return false;
    }

    
    
}
