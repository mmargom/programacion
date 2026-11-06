public class Socio {

    private String nombre;
    private int numeroSocio;
    private int contrasena;
    private int librosPrestados;
    private final int maxPrestamos = 2;

    public Socio(String nombre, int numeroSocio, int contrasena) {
        this.nombre = nombre;
        this.numeroSocio = numeroSocio;
        this.contrasena = contrasena;
        this.librosPrestados = 0;
    }

    public boolean comprobarContrasena(int pin) {
        return pin == contrasena;
    }

    public boolean puedePedir() {
        return librosPrestados < maxPrestamos;
    }

    public void pedirLibro(Libro libro) {
        if (puedePedir()) {
            if (Biblioteca.prestar(libro)) {
                librosPrestados++;
                System.out.println("Préstamo realizado: ahora tienes \"" + libro.getTitulo() + "\".");
            } else {
                System.out.println("El libro ya está prestado.");
            }
        } else {
            System.out.println("Has alcanzado el límite de préstamos (" + maxPrestamos + ").");
        }
    }

    public void devolverLibro(Libro libro) {
        if (libro.isDisponible()) {
            System.out.println("Ese libro no está prestado, no hay nada que devolver.");
            return;
        }
        Biblioteca.devolver(libro);
        if (librosPrestados > 0) {
            librosPrestados--;
        }
        System.out.println("Has devuelto \"" + libro.getTitulo() + "\".");
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Número de socio: " + numeroSocio);
        System.out.println("Libros prestados: " + librosPrestados);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public int getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(int librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public int getMaxPrestamos() {
        return maxPrestamos;
    }
}