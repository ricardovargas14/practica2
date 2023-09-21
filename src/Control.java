import java.util.Collections;
import java.util.Stack;

/**
 * Esta clase se encarga de controlar la aplicación.
 */
public class Control {
    private Stack<Carta> mazoOriginal; // Mazo de cartas original. Este mazo no se modifica.
    private Stack<Carta> mazoParaMostrar; // Mazo de cartas que se muestra en la aplicación.
    private Stack<Carta> eliminadas; // Mazo de cartas que se han eliminado.

    /**
     * Constructor de la clase.
     */
    public Control() {
        mazoOriginal = new Stack<Carta>();
        mazoParaMostrar = new Stack<Carta>();
        eliminadas = new Stack<Carta>();

        // Se crea una baraja para tomar las cartas;
        Baraja baraja = new Baraja();
        baraja.crearBaraja();
        baraja.barajar();

        // Se agregan 10 cartas al mazo original y al mazo para mostrar:
        for (int i = 0; i < 10; i++) {
            mazoOriginal.push(baraja.tomarCarta());
        }

        // se actualizan los mazos:
        actualizarMazos();
    }

    /**
     * Actualiza los mazos de cartas.
     */
    private void actualizarMazos() {
        // se eliminan las cartas del mazo para mostrar:
        mazoParaMostrar.clear();

        // Se agregan las cartas del mazo original al mazo para mostrar:
        for (Carta carta : mazoOriginal) {
            if (eliminadas.contains(carta))
                mazoParaMostrar.push(carta);
        }
    }

    /**
     * Método que mezcla las cartas del mazo original.
     */
    public void mezclarCartas() {
        // Se mezclan las cartas del mazo original:
        Collections.shuffle(mazoOriginal);

        actualizarMazos();
    }

    /**
     * Método que remueve una carta del mazo original, la agrega al mazo de
     * eliminadas y actualiza los mazos.
     * @param carta Carta a remover.
     */
    public void removerCarta(Carta carta) {
        // Se agrega la carta al mazo de eliminadas:
        eliminadas.push(carta);

        // Se actualizan los mazos:
        actualizarMazos();
    }

    /**
     * Método que regresa una carta al mazo original, la elimina del mazo de
     * eliminadas y actualiza los mazos.
     * @param carta Carta a regresar.
     */
    public void regresarCarta(Carta carta) {
        // Se elimina la carta del mazo de eliminadas:
        eliminadas.remove(carta);

        // Se actualizan los mazos:
        actualizarMazos();
    }

    /**
     * Método que regresa el mazo original.
     * @return Mazo original.
     */
    public Stack<Carta> getMazoOriginal() {
        return mazoOriginal;
    }

    /**
     * Método que regresa el mazo para mostrar.
     * @return Mazo para mostrar.
     */
    public Stack<Carta> getMazoParaMostrar() {
        return mazoParaMostrar;
    }

    /**
     * Método que regresa el mazo de eliminadas.
     * @return Mazo de eliminadas.
     */
    public Stack<Carta> getEliminadas() {
        return eliminadas;
    }

    
}
