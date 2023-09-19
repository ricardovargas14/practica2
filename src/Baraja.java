import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que modela una baraja de Five Crowns.
 */
public class Baraja {
    private ArrayList<Carta> cartas;

    /**
     * Constructor de la clase.
     */
    public Baraja() {
        cartas = new ArrayList<>();
    }

    /**
     * Método que crea las cartas de la baraja.
     */
    public void crearBaraja() {
        // Se crean las cartas de valor 3 a 13 para cada palo:
        for (int i = 3; i <= 13; i++) {
            for (Palo palo : Palo.values()) {
                if (palo == Palo.joker)
                    continue;
                cartas.add(new Carta(i, palo));
            }
        }
        // Se crean 3 jokers:
        for (int i = 0; i < 3; i++) {
            cartas.add(new Carta(0, Palo.joker));
        }
    }

    /**
     * Método que regresa la cantidad de cartas que hay en la baraja.
     * @return Cantidad de cartas en la baraja.
     */
    public int getCantidadDeCartas() {
        return cartas.size();
    }

    /**
     * Método que mezcla las cartas de la baraja.
     */
    public void barajar() {
        Collections.shuffle(cartas);
    }

    /**
     * Método que remueve la primera carta de la baraja.
     * @return Carta removida.
     */
    public Carta removerCarta() {
        return cartas.remove(0);
    }

    /**
     * Método que regresa la baraja de cartas.
     * @return Baraja de cartas.
     */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * Método que asigna una baraja de cartas.
     * @param cartas Baraja de cartas.
     */
    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cartas.size(); i++) {
            sb.append(cartas.get(i).toString());
            if (i < cartas.size() - 1) 
                sb.append("\n");
        }
        return sb.toString();
    }

    
}
