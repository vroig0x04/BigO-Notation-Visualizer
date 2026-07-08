
public class AlgoritmoNLogn implements Algoritmo{
    Controlador cntrl;
    Modelo modelo;

    public AlgoritmoNLogn(Controlador controlador) {
        this.cntrl = controlador;
        this.modelo = cntrl.getModeloNLogn();
    }
    @Override
    public long ejecutarAlgoritmo() {
        long tiempo_inicial=System.nanoTime();
        mergeSort(0,modelo.getLength()-1);
        long tiempo_final=System.nanoTime();
        long tiempo_total=tiempo_final-tiempo_inicial;
        return tiempo_total;
    }

    public void merge(int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i) {
            // L[i]=arr[l+i]
            L[i] = modelo.getElementoArray(l + i);
        }
        for (int j = 0; j < n2; ++j) {
            // R[j]=array[m+1+j]
            
            R[j] = modelo.getElementoArray(m + 1 + j);
        }

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                // [k] = L[i]
                modelo.setElementoArray(k, L[i]);
                i++;
            } else {
                // arr[k] = R[j];
                modelo.setElementoArray(i, R[j]);
                j++;
            }
            k++;
        }

        while (i < n1) {
            // arr[k] = L[i]
            modelo.setElementoArray(i, L[i]);
            i++;
            k++;
        }

        while (j < n2) {
            // arr[k] = R[j]
            modelo.setElementoArray(i, R[j]);
            j++;
            k++;
        }
    }

    public void mergeSort(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(l, m);
            mergeSort(m + 1, r);

            merge(l, m, r);
        }
    }
}
