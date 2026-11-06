
public class Concursante {

    public static final int VALOR_BOLA_BLANCA = 100;
    public static final int VALOR_BOLA_AZUL = 200;

    private String nombre;
    private int premioAcumulado;

    public Concursante(String nombre) {
        this.nombre = nombre;
        this.premioAcumulado = 0;
    }

    public void sumarPremio(int cantidad) {
        premioAcumulado += cantidad;
    }

    public void bancarrota() {
        premioAcumulado = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPremioAcumulado() {
        return premioAcumulado;
    }
}