
public class AlgoritmoN2 implements Algoritmo{

    private Controlador ctr;
    private Modelo modelo;
    public AlgoritmoN2(Controlador controlador) { 
        this.ctr = controlador;
        this.modelo = ctr.getModeloCuadratico();
    }

    @Override
    public long ejecutarAlgoritmo(){
        long tiempo_inicial=System.nanoTime();
        bubbleSort();
        long tiempo_final=System.nanoTime();
        long tiempo_total=tiempo_final-tiempo_inicial;
        return tiempo_total;
    }
    public void bubbleSort() {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < modelo.getLength(); i++) {
            swapped = false;
            for (j = 0; j < modelo.getLength() - 1; j++) {
                if (modelo.getElementoArray(j) > modelo.getElementoArray(j + 1)) {
                    temp = modelo.getElementoArray(j);
                    modelo.setElementoArray(j, modelo.getElementoArray(j + 1));
                    modelo.setElementoArray(j + 1,temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
