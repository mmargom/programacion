public class Aventurero {

    public static final int CURACION_POCION = 20;

    private String nombre;
    private int hpActual;
    private int hpMaximo;
    private int dañoBase;
    private int pociones;

    public Aventurero(String nombre, int hpMaximo, int dañoBase, int pociones) {
        this.nombre = nombre;
        this.hpMaximo = hpMaximo;
        this.hpActual = hpMaximo;
        this.dañoBase = dañoBase;
        this.pociones = pociones;
    }

    public void atacar(Enemigo objetivo) {
        int daño = dañoBase;
        objetivo.recibirDaño(daño);
        System.out.println(nombre + " golpea a " + objetivo.getNombre()
                + " y le hace " + daño + " de daño.");
    }

    public void atacarCritico(Enemigo objetivo) {
        int daño = dañoBase * 2;
        objetivo.recibirDaño(daño);
        System.out.println("GOLPE CRITICO! " + nombre + " hace " + daño
                + " de daño a " + objetivo.getNombre() + ".");
    }

    public boolean beberPocion() {
        if (pociones <= 0) {
            System.out.println("No te quedan pociones!");
            return false;
        }
        pociones--;
        hpActual = hpActual + CURACION_POCION;
        if (hpActual > hpMaximo) {
            hpActual = hpMaximo;
        }
        System.out.println(nombre + " bebe una pocion y se cura. HP: "
                + hpActual + "/" + hpMaximo);
        return true;
    }

    public void recibirDaño(int daño) {
        hpActual = hpActual - daño;
        if (hpActual < 0) {
            hpActual = 0;
        }
    }

    public boolean estaCaido() {
        return hpActual <= 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHpActual() {
        return hpActual;
    }

    public void setHpActual(int hpActual) {
        this.hpActual = hpActual;
    }

    public int getHpMaximo() {
        return hpMaximo;
    }

    public void setHpMaximo(int hpMaximo) {
        this.hpMaximo = hpMaximo;
    }

    public int getDañoBase() {
        return dañoBase;
    }

    public void setDañoBase(int dañoBase) {
        this.dañoBase = dañoBase;
    }

    public int getPociones() {
        return pociones;
    }

    public void setPociones(int pociones) {
        this.pociones = pociones;
    }
}