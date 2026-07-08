
import java.util.Random;

public class Matriz {
    private int[][] matriz;
    private int n;

    public Matriz(int n) {
        this.n = n;
        this.matriz = new int[n][n];
    }

    public static Matriz producto(Matriz a, Matriz b) {
        Matriz resultado = new Matriz(a.n);

        for (int i = 0; i < a.n; i++) {
            for (int j = 0; j < a.n; j++) {
                int sum = 0;
                for (int k = 0; k < a.n; k++) {
                    sum += a.matriz[i][k] * b.matriz[k][j];
                }
                resultado.matriz[i][j] = sum;
            }
        }
        return resultado;
    }

    public int getLength() {
        return n;
    }

     public static Matriz generarMatrizAleatoria(int size) {
        final int MAX_VALUE = 1000; // Genera valores en la mtriz entre -MAX_VALUE ... +MAX_VALUE
        Random rnd = new Random(MAX_VALUE);
        Matriz matriz_random = new Matriz(size);
        for (int i = 0; i < matriz_random.getLength(); i++) {
            for (int j = 0; j <  matriz_random.getLength(); j++) {
                matriz_random.setValor(i, j, rnd.nextInt(2*MAX_VALUE+1)-MAX_VALUE);
            }
        }
        return matriz_random;
    }

    public void setValor(int i, int j, int valor) {
        this.matriz[i][j] = valor;
    }

    public int getValor(int fila,int columna) {
        return matriz[fila][columna];
    }
}