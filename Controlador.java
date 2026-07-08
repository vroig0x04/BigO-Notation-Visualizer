
public class Controlador {
    private Main prog; // Referència al programa principal
    private Modelo modelo_lineal;
    private Modelo modelo_nlogn;
    private Modelo modelo_cuadratico;
    private Algoritmo alg;
    private static final int TAMAÑOINICIAL = 1;
    private static final int MAXSIZE = 100000;
    private static final int INCREMENTO = 100;

    public Controlador(Main prog) {
        this.prog = prog;
    }

    /**
     * Retorna el programa principal asociado al contralador
     * 
     * @return instancia del programa
     */
    public Main getPrograma() {
        return prog;
    }

    public Main getPractica_1() {
        return prog;
    }

    public int[] getIncrementos() {
        int[] incrementos = new int[MAXSIZE / INCREMENTO];
        int temp = TAMAÑOINICIAL;
        for (int i = 0; i < incrementos.length; i++) {
            incrementos[i] = temp;
            temp += INCREMENTO;
        }
        return incrementos;
    }

    /*
     * Repetir 5 veces almacenando el menor tiempo
     */
    public long[] getDatosLineal() {
        long[] datos = new long[(MAXSIZE*7) / (INCREMENTO*100)];
        int index = 0;
        for (int j = 0; j < 50; j++) {
            for (int size = TAMAÑOINICIAL; size < (MAXSIZE*7) + TAMAÑOINICIAL; size += (INCREMENTO*100)) {
                this.modelo_lineal = new Modelo(size);
                alg = new AlgoritmoLineal(this);
                long tiempo = alg.ejecutarAlgoritmo();
                if (datos[index] == 0) {
                    datos[index] = tiempo;
                } else if (tiempo < datos[index]) {
                    datos[index] = tiempo;
                }
                System.out.println("J: " + j + " index: " + index + " tiempo: " + datos[index]);

                index++;
            }
            index = 0;
        }

        return datos;
    }

    public long[] getDatosNLog() {
        long[] datos = new long[(MAXSIZE / 2) / INCREMENTO];
        final int finalincremento = INCREMENTO;
        int index = 0;
        for (int j = 0; j < 25; j++) {
            for (int size = TAMAÑOINICIAL; size < (MAXSIZE / 2) + TAMAÑOINICIAL; size += finalincremento) {
                this.modelo_nlogn = new Modelo(size);
                alg = new AlgoritmoNLogn(this);
                long tiempo = alg.ejecutarAlgoritmo();
                if (datos[index] == 0) {
                    datos[index] = tiempo;
                } else if (tiempo < datos[index]) {
                    datos[index] = tiempo;
                }
                System.out.println("J: " + j + " index: " + index + " tiempo: " + datos[index]);

                index++;
            }
            index = 0;
        }

        return datos;
    }

    public long[] getDatosN2() {
        final int finalsize = MAXSIZE / 25;
        final int finalincremento = INCREMENTO / 4;
        long[] datos = new long[finalsize / finalincremento];
        int index = 0;
        for (int j = 0; j < 25; j++) {
            for (int size = TAMAÑOINICIAL; size < finalsize + TAMAÑOINICIAL; size += finalincremento) {
                modelo_cuadratico = new Modelo(size);
                alg = new AlgoritmoN2(this);
                long tiempo = alg.ejecutarAlgoritmo();
                if (datos[index] == 0) {
                    datos[index] = tiempo;
                } else if (tiempo < datos[index]) {
                    datos[index] = tiempo;
                }
                System.out.println("J: " + j + " index: " + index + " tiempo: " + datos[index]);
                index++;
            }
            index = 0;
        }

        return datos;

    }

    public long[] getDatosN3() {
        final int finalsize = MAXSIZE / 100;
        final int finalincremento = INCREMENTO /4;
        long[] datos = new long[finalsize / finalincremento];
        int index = 0;
        for (int j = 0; j < 4; j++) {
            for (int size = TAMAÑOINICIAL; size < finalsize + TAMAÑOINICIAL; size += finalincremento) {
                alg = new AlgoritmoN3(this, size);
                long tiempo = alg.ejecutarAlgoritmo();
                if (datos[index] == 0) {
                    datos[index] = tiempo;
                } else if (tiempo < datos[index]) {
                    datos[index] = tiempo;
                }
                System.out.println("J: " + j + " index: " + index + " tiempo: " + tiempo);
                index++;
            }
            index = 0;
        }

        return datos;
    }

    public Modelo getModeloLineal() {
        return modelo_lineal;
    }

    public Modelo getModeloNLogn() {
        return modelo_nlogn;
    }

    public Modelo getModeloCuadratico() {
        return modelo_cuadratico;
    }

    public int getTamañoInicial() {
        return TAMAÑOINICIAL;
    }

    public int getIncremento() {
        return INCREMENTO;
    }

    public int getMaxTamaño() {
        return MAXSIZE;
    }
}
