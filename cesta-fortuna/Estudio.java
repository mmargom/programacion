import java.util.InputMismatchException;
import java.util.Scanner;
public class Estudio {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== LA CESTA DE LA FORTUNA ===");
        System.out.print("¿Cómo te llamas, concursante? ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            nombre = "Anónimo";
        }

        Concursante concursante = new Concursante(nombre);
        Cesta cesta = new Cesta();

        boolean plantado = false;
        boolean bolaNegra = false;

        System.out.println("\n¡Bienvenido/a, " + concursante.getNombre() + "!");
        System.out.println("La cesta contiene " + cesta.obtenerTotalBolas() + " bolas. Cuidado con la negra...");

        while (!plantado && !bolaNegra && cesta.obtenerTotalBolas() > 0) {
            System.out.println("\n--------------------------------");
            System.out.println("Premio actual: " + concursante.getPremioAcumulado() + " €");
            System.out.println("Bolas en la cesta: " + cesta.obtenerTotalBolas());
            int opcion = leerOpcion();

            if (opcion == 1) {
                char bola = cesta.sacarBola();
                switch (bola) {
                    case 'B':
                        System.out.println("Has sacado una bola BLANCA: +" + Concursante.VALOR_BOLA_BLANCA + " €");
                        concursante.sumarPremio(Concursante.VALOR_BOLA_BLANCA);
                        break;
                    case 'A':
                        System.out.println("¡Has sacado una bola AZUL!: +" + Concursante.VALOR_BOLA_AZUL + " €");
                        concursante.sumarPremio(Concursante.VALOR_BOLA_AZUL);
                        break;
                    case 'N':
                        System.out.println("¡Oh no! Has sacado la bola NEGRA...");
                        concursante.bancarrota();
                        bolaNegra = true;
                        break;
                    default:
                        System.out.println("La cesta está vacía.");
                        break;
                }
            } else {
                plantado = true;
            }
        }

        System.out.println("\n================================");
        if (bolaNegra) {
            System.out.println("Bancarrota, " + concursante.getNombre() + ". Te vas a casa con 0 €.");
        } else if (plantado) {
            System.out.println("Sabia decisión, " + concursante.getNombre() + ". Te plantas.");
            System.out.println("Te llevas " + concursante.getPremioAcumulado() + " €.");
        } else {
            System.out.println("¡Has vaciado la cesta sin sacar la negra, " + concursante.getNombre() + "!");
            System.out.println("Te llevas " + concursante.getPremioAcumulado() + " €.");
        }
        System.out.println("¡Gracias por participar y hasta la próxima!");
    }

   
    private static int leerOpcion() {
        while (true) {
            System.out.println("1. Sacar bola de la cesta");
            System.out.println("2. Plantarse y llevarse el dinero");
            System.out.print("Elige una opción: ");
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 
                if (opcion == 1 || opcion == 2) {
                    return opcion;
                }
                System.out.println("El presentador no te ha entendido, por favor pulsa 1 o 2.\n");
            } catch (InputMismatchException e) {
                scanner.nextLine(); 
                System.out.println("El presentador no te ha entendido, por favor pulsa 1 o 2.\n");
            }
        }
    }
}