/**
 * Enumeración de los palos de una baraja de Five Crowns.
 */
public enum Palo {

    pica("negro"),
    corazón("rojo"),
    trébol("verde"),
    diamante("azul"),
    estrella("amarillo"),
    joker(null);


    private String color;

    private Palo(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    
}
