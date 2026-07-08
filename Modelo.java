
import java.util.Random;

public class Modelo {
    private int[] array;

    public Modelo(int size) {
        this.array = generarArrayAleatorio(size);
    }

    private int[] generarArrayAleatorio(int size) {
        Random rnd = new Random();
        int[] array_random = new int[size];
        for (int i = 0; i < size; i++) {
            array_random[i] = rnd.nextInt(1000000);
        }
        return array_random;
    }


    public int getElementoArray(int indice){
        return array[indice];
    }

    public void setElementoArray(int indice,int elemento){
        array[indice]=elemento;
    }
    public int getLength(){
        return array.length;
    }
}
