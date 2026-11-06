import java.util.Random;

public class Cesta {

    private int bolasBlancas;
    private int bolasAzules;
    private int bolasNegras;

    public Cesta() {
        this.bolasBlancas = 10;
        this.bolasAzules = 1;
        this.bolasNegras = 1;
    }

    public int obtenerTotalBolas() {
        return bolasBlancas + bolasAzules + bolasNegras;
    }


    public char sacarBola() {
        char bola = extraerBola(bolasBlancas, bolasAzules, bolasNegras);

        switch (bola) {
            case 'B':
                bolasBlancas--;
                break;
            case 'A':
                bolasAzules--;
                break;
            case 'N':
                bolasNegras--;
                break;
            default:
                break;
        }
        return bola;
    }

    private static char extraerBola(int b, int a, int n) {
        Random rand = new Random();
        int totalBolas = b + a + n;

        if (totalBolas == 0) return 'V';

        int tirada = rand.nextInt(totalBolas) + 1;

        if (tirada <= b) {
            return 'B';
        } else if (tirada <= b + a) {
            return 'A';
        } else {
            return 'N';
        }
    }

    public int getBolasBlancas() {
        return bolasBlancas;
    }

    public int getBolasAzules() {
        return bolasAzules;
    }

    public int getBolasNegras() {
        return bolasNegras;
    }
}