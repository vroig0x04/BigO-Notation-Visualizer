import java.util.Random;

public class AlgoritmoLineal implements Algoritmo{
    private Controlador ctrl;
    private Modelo modelo;
    
    public AlgoritmoLineal(Controlador controlador) {
        this.ctrl = controlador;
        this.modelo = controlador.getModeloLineal();
    }
    
    @Override
    public long ejecutarAlgoritmo(){
        Random rnd= new Random();
        long tiempo_inicial=System.nanoTime();
        buscarElementoEnArray(1000000);
        long tiempo_final=System.nanoTime();
        long tiempo_total=tiempo_final-tiempo_inicial;
        return tiempo_total;        
    }

    /**
     * Búsqueda lineal. Devuelve el elemento buscado en caso de encontralo,
     * sinó devuelve {@code -1}. O(n)
     * 
     * @param elemento representa el elemeto a buscar
     * @return elemento encontrado. -1 en caso contrario
     * 
     */
    public int buscarElementoEnArray(int elemento) {
        for (int i = 0; i < modelo.getLength(); i++) {
            if (modelo.getElementoArray(i) == elemento) {
                return i;
            }

        }
        return -1;
    }
}
