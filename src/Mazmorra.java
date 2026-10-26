import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Mazmorra {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Aventurero heroe = new Aventurero("Aragorn", 100, 15, 3);
        Enemigo dragon = new Enemigo("Dragon Rojo", 150, 18);

        System.out.println("=== ENCUENTRO EN LA GUARIDA DEL DRAGON ===");
        System.out.println("Un " + dragon.getNombre() + " bloquea tu camino!");

        while (!heroe.estaCaido() && !dragon.estaDerrotado()) {

            System.out.println("\n--------------------------------");
            System.out.println(heroe.getNombre() + " HP: " + heroe.getHpActual()
                    + "/" + heroe.getHpMaximo() + " | Pociones: " + heroe.getPociones());
            System.out.println(dragon.getNombre() + " HP: " + dragon.getHpActual()
                    + "/" + dragon.getHpMaximo());

            boolean turnoGastado = false;
            while (!turnoGastado) {
                int opcion = leerEntero();

                if (opcion == 1) {
                    int dado = tirarDado20();
                    System.out.println("Has sacado un " + dado + " en el dado.");
                    if (dado == 20) {
                        heroe.atacarCritico(dragon);
                    } else if (dado == 1) {
                        System.out.println("PIFIA! Fallas el ataque.");
                    } else {
                        heroe.atacar(dragon);
                    }
                    turnoGastado = true;
                } else if (opcion == 2) {
                    if (heroe.getPociones() > 0) {
                        heroe.beberPocion();
                        turnoGastado = true;
                    } else {
                        System.out.println("No te quedan pociones, elige otra accion.");
                    }
                } else {
                    System.out.println("Opcion no valida, elige 1 o 2.");
                }
            }

            if (!dragon.estaDerrotado()) {
                System.out.println(dragon.getNombre() + " te ataca y te hace "
                        + dragon.getDañoBase() + " de daño.");
                heroe.recibirDaño(dragon.getDañoBase());
            }
        }

        System.out.println("\n================================");
        if (dragon.estaDerrotado()) {
            System.out.println("VICTORIA! Has derrotado al " + dragon.getNombre() + "!");
        } else {
            System.out.println("Fin de la partida. " + heroe.getNombre() + " ha caido...");
        }
    }

    private static int leerEntero() {
        int numero = 0;
        boolean correcto = false;

        while (!correcto) {
            try {
                System.out.println("\nQue quieres hacer?");
                System.out.println("1. Atacar con arma");
                System.out.println("2. Beber pocion");
                System.out.print("Elige: ");
                numero = sc.nextInt();
                correcto = true;
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un numero! No es momento de entrar en panico.");
                sc.nextLine(); // limpiamos el buffer
            }
        }
        return numero;
    }

    private static int tirarDado20() {
        Random dado = new Random();
        return dado.nextInt(20) + 1;
    }
}