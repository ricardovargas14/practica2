/**
 * Enumeración de los palos de una baraja de Five Crowns.
 */
public enum Palo {

    // Los palos de la baraja de Five Crowns, con su respectivo color.
    pica("negro"),
    corazón("rojo"),
    trébol("verde"),
    diamante("azul"),
    estrella("amarillo"),
    joker(null);

    private String color;

    /**
     * Constructor para los elementos del enum.
     * @param color Color del palo.
     */
    private Palo(String color) {
        this.color = color;
    }

    /**
     * Getter del color del palo.
     * @return Color del palo.
     */
    public String getColor() {
        return color;
    }

}
