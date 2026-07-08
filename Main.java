public class Main {

    private static Controlador cntrl;
    public static void main(String[] args) {
        metodoPrincipal();
    }

    private static void metodoPrincipal() {
        cntrl = new Controlador(new Main());
        new Vista(cntrl);
    }  
}
