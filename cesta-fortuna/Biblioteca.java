import java.util.Scanner;

public class Biblioteca {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Preparación
        Libro libro1 = new Libro("El Hobbit", "Tolkien", 1937);
        Libro libro2 = new Libro("Dune", "Frank Herbert", 1965);
        Libro libro3 = new Libro("Momo", "Michael Ende", 1973);

        Socio socio1 = new Socio("Lucía", 101, 1234);
        Socio socio2 = new Socio("Marcos", 102, 4321);

        // Inicio de sesión: número de socio
        System.out.println("--- SOCIOS ---");
        System.out.println(socio1.getNumeroSocio() + " - " + socio1.getNombre());
        System.out.println(socio2.getNumeroSocio() + " - " + socio2.getNombre());
        System.out.print("Introduce tu número de socio: ");
        int numero = lectura();

        while (numero != socio1.getNumeroSocio() && numero != socio2.getNumeroSocio()) {
            System.out.print("Ese socio no existe, inténtalo de nuevo: ");
            numero = lectura();
        }

        Socio socio;
        if (numero == socio1.getNumeroSocio()) {
            socio = socio1;
        } else {
            socio = socio2;
        }

        boolean acceso = false;
        int intentos = 3;
        while (intentos > 0 && !acceso) {
            System.out.print("Contraseña: ");
            int pin = lectura();
            if (socio.comprobarContrasena(pin)) {
                acceso = true;
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("Contraseña incorrecta. Te quedan " + intentos + " intentos.");
                }
            }
        }

        if (!acceso) {
            System.out.println("Acceso denegado.");
            return;
        }

        System.out.println("Bienvenido/a, " + socio.getNombre());

        int opcion;
        do {
            System.out.println();
            System.out.println("1. Ver todos los libros");
            System.out.println("2. Pedir un libro");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Ver mis datos de socio");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = lectura();

            switch (opcion) {
                case 1:
                    mostrarTodos(libro1, libro2, libro3);
                    break;
                case 2:
                    mostrarTodos(libro1, libro2, libro3);
                    System.out.print("¿Qué libro quieres? ");
                    int numPedir = lectura();
                    while (numPedir < 1 || numPedir > 3) {
                        System.out.print("Número no válido (1-3), inténtalo de nuevo: ");
                        numPedir = lectura();
                    }
                    socio.pedirLibro(elegirLibro(numPedir, libro1, libro2, libro3));
                    break;
                case 3:
                    mostrarTodos(libro1, libro2, libro3);
                    System.out.print("¿Qué libro quieres devolver? ");
                    int numDevolver = lectura();
                    while (numDevolver < 1 || numDevolver > 3) {
                        System.out.print("Número no válido (1-3), inténtalo de nuevo: ");
                        numDevolver = lectura();
                    }
                    socio.devolverLibro(elegirLibro(numDevolver, libro1, libro2, libro3));
                    break;
                case 4:
                    socio.mostrarDatos();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opción no válida, elige entre 1 y 5.");
            }
        } while (opcion != 5);

        System.out.println("Te quedan " + socio.getLibrosPrestados() + " libros sin devolver.");
        mostrarMasPrestado(libro1, libro2, libro3);
        System.out.println("¡Hasta pronto!");
    }

    public static boolean prestar(Libro libro) {
        if (libro.isDisponible()) {
            libro.setDisponible(false);
            libro.setVecesPrestado(libro.getVecesPrestado() + 1);
            return true;
        } else {
            return false;
        }
    }

    public static void devolver(Libro libro) {
        libro.setDisponible(true);
    }

    public static int lectura() {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            try {
                numero = Integer.parseInt(sc.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Error: debes escribir un número entero. Inténtalo de nuevo: ");
            }
        }
        return numero;
    }

    public static void mostrarTodos(Libro l1, Libro l2, Libro l3) {
        System.out.println("--- LIBROS DE LA BIBLIOTECA ---");
        System.out.print("1. ");
        l1.mostrarInfo();
        System.out.print("2. ");
        l2.mostrarInfo();
        System.out.print("3. ");
        l3.mostrarInfo();
    }

    public static Libro elegirLibro(int num, Libro l1, Libro l2, Libro l3) {
        if (num == 1) return l1;
        else if (num == 2) return l2;
        else return l3;
    }

    public static void mostrarMasPrestado(Libro l1, Libro l2, Libro l3) {
        int v1 = l1.getVecesPrestado();
        int v2 = l2.getVecesPrestado();
        int v3 = l3.getVecesPrestado();

        if (v1 == 0 && v2 == 0 && v3 == 0) {
            System.out.println("No se ha prestado ningún libro en esta sesión.");
        } else if (v1 > v2 && v1 > v3) {
            System.out.println("El libro más prestado ha sido \"" + l1.getTitulo() + "\" (" + v1 + " veces).");
        } else if (v2 > v1 && v2 > v3) {
            System.out.println("El libro más prestado ha sido \"" + l2.getTitulo() + "\" (" + v2 + " veces).");
        } else if (v3 > v1 && v3 > v2) {
            System.out.println("El libro más prestado ha sido \"" + l3.getTitulo() + "\" (" + v3 + " veces).");
        } else if (v1 == v2 && v2 == v3) {
            System.out.println("Hay un empate entre los tres libros (" + v1 + " veces cada uno).");
        } else if (v1 == v2) {
            System.out.println("Empate entre \"" + l1.getTitulo() + "\" y \"" + l2.getTitulo() + "\" (" + v1 + " veces).");
        } else if (v1 == v3) {
            System.out.println("Empate entre \"" + l1.getTitulo() + "\" y \"" + l3.getTitulo() + "\" (" + v1 + " veces).");
        } else {
            System.out.println("Empate entre \"" + l2.getTitulo() + "\" y \"" + l3.getTitulo() + "\" (" + v2 + " veces).");
        }
    }
}