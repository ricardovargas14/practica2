import java.util.Stack;

/**
 * Esta clase modela el control de la aplicación.
 * Se encarga de manejar las barajas de cartas.
 * Se encarga de remover y añadir cartas a las barajas.
 */
public class Control {
    // Las barajas se modelan con una estructura de datos tipo Pila,
    // para ser manejadas solo se deben utilizar los métodos push y pop.
    private Stack<Carta> barajaOriginal;
    private Stack<Carta> barajaModificada;
    private Stack<Carta> cartasRemovidas;
    // - La pila barajaOriginal contiene todas las cartas de la baraja original,
    // esta pila nunca se modifica.
    // - La pila barajaModificada contiene las cartas de la baraja original,
    // esta pila se modifica cada vez que se remueve o se reacomoda una carta,
    // siempre se debe respetar el orden de la baraja original.
    // - La pila cartasRemovidas contiene las cartas que se han removido de la
    // baraja modificada.

    /**
     * Constructor de la clase.
     * @param barajaOriginal Baraja original.
     */
    public Control(Baraja barajaOriginal) {
        this.barajaOriginal = new Stack<>();
        this.barajaModificada = new Stack<>();
        this.cartasRemovidas = new Stack<>();

        // se agregan las cartas de la baraja original al stack
        for (int i = 0; i < barajaOriginal.getCantidadDeCartas(); i++) {
            Carta carta = barajaOriginal.getCartas().get(i);
            this.barajaOriginal.push(carta);
            // también se agregan a la baraja modificada
            this.barajaModificada.push(carta);
        }
    }

    /**
     * Método que remueve una carta de la baraja modificada.
     * @param carta Carta a remover.
     */
    public void removerCarta(Carta carta) {
        Stack<Carta> auxiliar = new Stack<>();
        boolean encontrado = false;

        while (!barajaModificada.isEmpty()) {
            Carta cartaActual = barajaModificada.pop();
            if (cartaActual.equals(carta)) {
                cartasRemovidas.push(cartaActual);
                encontrado = true;
                break;
            }
            auxiliar.push(cartaActual);
        }

        while (!auxiliar.isEmpty()) {
            barajaModificada.push(auxiliar.pop());
        }

        if (!encontrado) {
            System.out.println("La carta no se encontró en la baraja modificada.");
        }
    }

    /**
     * Método que reacomoda una carta en la baraja modificada.
     * @param carta Carta a reacomodar.
     */
    public void añadirCarta(Carta carta) {
        if (cartasRemovidas.contains(carta)) {
            cartasRemovidas.remove(carta);
            barajaModificada.push(carta);
            reacomodarCartas();
        } else {
            System.out.println("La carta no se encontró en las cartas removidas.");
        }
    }

    private void reacomodarCartas() {
        Stack<Carta> auxiliarBarajaOriginal = (Stack<Carta>) barajaOriginal.clone();
        Stack<Carta> nuevaBarajaModificada = new Stack<>();
    
        // Crear una pila auxiliar inversa para preservar el orden correcto
        Stack<Carta> auxiliarInversa = new Stack<>();
    
        while (!auxiliarBarajaOriginal.isEmpty()) {
            Carta cartaActual = auxiliarBarajaOriginal.pop();
            if (barajaModificada.contains(cartaActual)) {
                auxiliarInversa.push(cartaActual);
            }
        }
    
        // Revertir el orden a la nuevaBarajaModificada
        while (!auxiliarInversa.isEmpty()) {
            nuevaBarajaModificada.push(auxiliarInversa.pop());
        }
    
        barajaModificada = nuevaBarajaModificada;
    }
    
        
    


    public Carta[] getBarajaOriginal() {
        return barajaOriginal.toArray(new Carta[barajaOriginal.size()]);
    }

    public Carta[] getBarajaModificada() {
        return barajaModificada.toArray(new Carta[barajaModificada.size()]);
    }

    public Carta[] getCartasRemovidas() {
        return cartasRemovidas.toArray(new Carta[cartasRemovidas.size()]);
    }

    public static void main(String[] args) {
        Baraja baraja = new Baraja();
        baraja.crearBaraja();
        baraja.barajar();
        Baraja cartas = new Baraja();
        for (int i = 0; i < 5; i++) {
            cartas.agregarCarta(baraja.removerCarta());
        }

        Control control = new Control(cartas);
        System.out.println("Baraja original:");
        for (Carta carta : control.getBarajaOriginal()) {
            System.out.println(carta.toString());
        }

        System.out.println("Baraja modificada:");
        for (Carta carta : control.getBarajaModificada()) {
            System.out.println(carta.toString());
        }

        System.out.println("Cartas removidas:");
        for (Carta carta : control.getCartasRemovidas()) {
            System.out.println(carta.toString());
        }

        System.out.println("Remover carta:");
        Carta carta = cartas.removerCarta();
        System.out.println("Se removerá la carta: " + carta.toString());
        control.removerCarta(carta);
        System.out.println("Baraja original:");
        for (Carta cartaActual : control.getBarajaOriginal()) {
            System.out.println(cartaActual.toString());
        }
        System.out.println("Baraja modificada:");
        for (Carta cartaActual : control.getBarajaModificada()) {
            System.out.println(cartaActual.toString());
        }
        System.out.println("Cartas removidas:");
        for (Carta cartaActual : control.getCartasRemovidas()) {
            System.out.println(cartaActual.toString());
        }
        Carta carta2 = cartas.removerCarta();
        System.out.println("se removerá la carta: " + carta2.toString());
        control.removerCarta(carta2);
        System.out.println("Baraja original:");
        for (Carta cartaActual : control.getBarajaOriginal()) {
            System.out.println(cartaActual.toString());
        }
        System.out.println("Baraja modificada:");
        for (Carta cartaActual : control.getBarajaModificada()) {
            System.out.println(cartaActual.toString());
        }
        System.out.println("Cartas removidas:");
        for (Carta cartaActual : control.getCartasRemovidas()) {
            System.out.println(cartaActual.toString());
        }

        System.out.println("Se añadira la carta: " + carta.toString());
        control.añadirCarta(carta);
        System.out.println("Baraja original:");
        for (Carta cartaActual : control.getBarajaOriginal()) {
            System.out.println(cartaActual.toString());
        }
        System.out.println("Baraja modificada:");
        for (Carta cartaActual : control.getBarajaModificada()) {
            System.out.println(cartaActual.toString());
        }
        System.out.println("Cartas removidas:");
        for (Carta cartaActual : control.getCartasRemovidas()) {
            System.out.println(cartaActual.toString());
        }

        System.out.println("Se añadira la carta: " + carta2.toString());
        control.añadirCarta(carta2);
        System.out.println("Baraja original:");
        for (Carta cartaActual : control.getBarajaOriginal()) {
            System.out.println(cartaActual.toString());
        }
        System.out.println("Baraja modificada:");
        for (Carta cartaActual : control.getBarajaModificada()) {
            System.out.println(cartaActual.toString());
        }
        System.out.println("Cartas removidas:");
        for (Carta cartaActual : control.getCartasRemovidas()) {
            System.out.println(cartaActual.toString());
        }
    }
}
