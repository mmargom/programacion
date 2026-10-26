public class Enemigo {

    private String nombre;
    private int hpActual;
    private int hpMaximo;
    private int dañoBase;

    public Enemigo(String nombre, int hpMaximo, int dañoBase) {
        this.nombre = nombre;
        this.hpMaximo = hpMaximo;
        this.hpActual = hpMaximo;
        this.dañoBase = dañoBase;
    }

    public void recibirDaño(int daño) {
        hpActual = hpActual - daño;
        if (hpActual < 0) {
            hpActual = 0;
        }
    }

    public boolean estaDerrotado() {
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
}