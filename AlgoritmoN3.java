public class AlgoritmoN3 implements Algoritmo{
    private Controlador ctrl;
    private int len;


    public AlgoritmoN3(Controlador controlador,int length) {
        this.ctrl = controlador;
        this.len = length;
    }

    @Override
    public long ejecutarAlgoritmo() {
        Matriz n1 = Matriz.generarMatrizAleatoria(len);
        Matriz n2 = Matriz.generarMatrizAleatoria(len);
        long init = System.nanoTime();
        Matriz matriz = Matriz.producto(n1, n2);
        long diff = System.nanoTime() - init;
        return diff;
    }
}
