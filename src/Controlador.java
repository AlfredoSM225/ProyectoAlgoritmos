import java.util.Scanner;

public class Controlador {
    private GrafoDirigidoAciclico grafo;

    public Controlador() {

    }

    public void validarOpcion(String opcionTexto) {
        int opcion = validarEntradaNumerica(opcionTexto);

        switch (opcion) {
            case 1 -> agregarNuevoGrafo();
            case 2 -> agregarNuevaArista();
            case 3 -> eliminarAristas();
            case 4 -> topologicalSort();
            case 5 -> mostrarEstructura();
        }
    }

    public boolean agregarNuevoGrafo() {
        Scanner input = new Scanner(System.in);
        String tamañoTexto = input.next();
        
        int tamaño = validarEntradaNumerica(tamañoTexto);
        if (tamaño <= 0) { return false; }

        grafo = new GrafoDirigidoAciclico(tamaño);
        return true;
    }

    public void agregarNuevaArista() {

    }

    public void eliminarAristas() {

    }

    public void topologicalSort() {

    }

    public void mostrarEstructura() {

    }

    public static int validarEntradaNumerica(String opcionTexto) {
        if (opcionTexto.matches(".*\\D.*")) { return -1; }

        return Integer.parseInt(opcionTexto);
    }

    public void imprimirMenu() {
        System.out.println("(1) Agregar nuevo grafo");
        System.out.println("(2) Agregar nueva arista");
        System.out.println("(3) Eliminar Aristas");
        System.out.println("(4) Topological sort");
        System.out.println("(5) Mostrar estructura");
        System.out.println("(6) Salir");
    }


}
