public class UI {
    public static final int SALIR = 5;
    public static void ImprimirMenu() {
        System.out.println("(1) Agregar nuevo grafo");
        System.out.println("(2) Agregar nueva arista");
        System.out.println("(3) Eliminar Aristas");
        System.out.println("(3) Topological sort");
        System.out.println("(4) Mostrar estructura");
        System.out.println("(" + SALIR + ")" + " Salir");
    }
}
