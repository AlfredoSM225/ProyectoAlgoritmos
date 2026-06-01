import java.util.Scanner;

public class Controlador {
    private GrafoDirigidoAciclico grafo;
    private Scanner scanner;
    private int bandera = 0;

    public Controlador() {
        scanner = new Scanner(System.in);

        while (bandera == 0) {
            System.out.println();
            imprimirMenu();
            System.out.println();

            String opcionTexto = scanner.next();
            validarOpcion(opcionTexto);
        }
    }

    public int validarOpcion(String opcionTexto) {
        int opcion = validarEntradaNumerica(opcionTexto);

        switch (opcion) {
            case 1 -> agregarNuevoGrafo();
            case 2 -> agregarNuevaArista();
            case 3 -> adyacenciaEntreNodos();
            case 4 -> conectados();
            case 5 -> eliminarAristas();
            case 6 -> gradoDeSalida();
            case 7 -> gradoDeEntrada();
            case 8 -> topologicalSort();
            case 9 -> mostrarEstructura();
            case 0 -> bandera = 1;
            default ->  {
                System.out.println("Opcion no existente");
                break;
            }
        }

        return opcion;
    }

    public boolean agregarNuevoGrafo() {
        System.out.print("Ingrese el tamaño del nuevo grafo: ");
        String tamañoTexto = scanner.next();

        int tamaño = validarEntradaNumerica(tamañoTexto);
        if (tamaño <= 0) { return false; }

        grafo = new GrafoDirigidoAciclico(tamaño);
        return true;
    }

    public void agregarNuevaArista() {
        if (grafo == null) {
            System.out.println("Grafo no existente");
            return;
        }

        System.out.print("Indice o Nombre del Nodo Inicial: ");
        String indiceTexto = scanner.next();
        int indice1 = validarEntradaNumerica(indiceTexto);

        if (indice1 < 0 || indice1 >= grafo.getTamaño()) {
            System.out.println("Indice fuera de rango");
            return;
        }

        System.out.print("Indice o Nombre de Nodo Destino: ");
        indiceTexto = scanner.next();
        int indice2 = validarEntradaNumerica(indiceTexto);

        if (indice2 < 0 || indice2 >= grafo.getTamaño()) {
            System.out.println("Indice fuera de rango");
            return;
        }

        if (grafo.insertarArista(indice1, indice2)) {
            System.out.println("Arista insertada con exito");
        } else {
            System.out.println("No se pudo insertar la nueva arista");
        }
    }

    public void eliminarAristas() {
        if (grafo == null) {
            System.out.println("Grafo no existente");
            return;
        }

        grafo.eliminarAristas();
    }

    public void adyacenciaEntreNodos() {
        if (grafo == null) {
            System.out.println("Grafo no existente");
            return;
        }

        System.out.print("Ingrese o nombre indice de un nodo: ");
        String indiceTexto = scanner.next();
        int indice1 = validarEntradaNumerica(indiceTexto);

        if (indice1 < 0 || indice1 >= grafo.getTamaño()) {
            System.out.println("Indice fuera de rango");
            return;
        }

        System.out.print("Ingrese el indice o nombre de otro nodo: ");
        indiceTexto = scanner.next();
        int indice2 = validarEntradaNumerica(indiceTexto);

        if (indice2 < 0 || indice2 >= grafo.getTamaño()) {
            System.out.println("Indice fuera de rango");
            return;
        }

        if (grafo.adyacente(indice1, indice2)) {
            System.out.println("Vertices " + "[" + indice1 + "," + indice2 + "] son adyacentes");
        } else {
            System.out.println("Vertices no adyacentes");
        }
    }

    public void conectados() {
        if (grafo == null) {
            System.out.println("Grafo no existente");
            return;
        }

        System.out.print("Indice o Nombre de Nodo Inicial: ");
        String indiceTexto = scanner.next();
        int indice1 = validarEntradaNumerica(indiceTexto);

        if (indice1 < 0 || indice1 >= grafo.getTamaño()) {
            System.out.println("Indice fuera de rango");
            return;
        }

        System.out.print("Indice o Nombre de Nodo Destino: ");
        indiceTexto = scanner.next();
        int indice2 = validarEntradaNumerica(indiceTexto);

        if (indice2 < 0 || indice2 >= grafo.getTamaño()) {
            System.out.println("Indice fuera de rango");
            return;
        }

        if (grafo.conectados(indice1, indice2)) {
            System.out.println("Existe camino entre Vertices " + "[" + indice1 + "," + indice2 + "]");
        } else {
            System.out.println("No existe camino entre los vertices");   
        }
    }

    public void gradoDeSalida() {
        if (grafo == null) {
            System.out.println("Grafo no existente");
            return;
        }

        System.out.print("Indice o Nombre de Nodo: ");
        String indiceTexto = scanner.next();
        int indice = validarEntradaNumerica(indiceTexto);

        if (indice < 0 || indice >= grafo.getTamaño()) {
            System.out.println("Indice fuera de rango");
            return;
        }

        System.out.println("Grado de salida de nodo " + indiceTexto + ": " + grafo.gradoDeSalida(indice));
    }

    public void gradoDeEntrada() {
        if (grafo == null) {
            System.out.println("Grafo no existente");
            return;
        }

        System.out.print("Indice o Nombre de Nodo: ");
        String indiceTexto = scanner.next();
        int indice = validarEntradaNumerica(indiceTexto);

        if (indice < 0 || indice >= grafo.getTamaño()) {
            System.out.println("Indice fuera de rango");
            return;
        }

        System.out.println("Grado de entrada de nodo " + indiceTexto + ": " + grafo.gradoDeEntrada(indice));

    }

    public void topologicalSort() {
        if (grafo == null) {
            System.out.println("Grafo no existente");
            return;
        }

        System.out.println(grafo.topologicalSort());
    }

    public void mostrarEstructura() {
        if (grafo == null) {
            System.out.println("Grafo no existente");
            return;
        }
        
        System.out.println(grafo.mostrarEstructura());
    }

    public int validarEntradaNumerica(String opcionTexto) {
        if (opcionTexto.matches(".*\\D.*")) { return validarIndiceDeLetra(opcionTexto); }

        return Integer.parseInt(opcionTexto);
    }

    public int validarIndiceDeLetra(String opcionTexto) {
        if (opcionTexto.length() > 1) { return -1; }

        if (opcionTexto.toCharArray()[0] < 65 || opcionTexto.toCharArray()[0] > 90) { return -1; }

        return opcionTexto.toCharArray()[0] - 65;
    }

    public void imprimirMenu() {
        System.out.println("(1) Agregar nuevo grafo");
        System.out.println("(2) Agregar nueva arista");
        System.out.println("(3) Adyacencia");
        System.out.println("(4) Conectados");
        System.out.println("(5) Eliminar Aristas");
        System.out.println("(6) Grado de salida de vertice");
        System.out.println("(7) Grado de entrada de vertice");
        System.out.println("(8) Topological sort");
        System.out.println("(9) Mostrar estructura");
        System.out.println("(0) Salir");
    }
}
