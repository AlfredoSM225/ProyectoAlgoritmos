import java.util.PriorityQueue;
import java.util.Stack;

public class GrafoDirigidoAciclico {
    private int n;
    private Vertice[] vertices;

    public GrafoDirigidoAciclico(int n) {
        this.n = n;
        vertices = new Vertice[n];

        for (int i = 0; i < n; i++) {
            char letra = (char)(i + 65);
            vertices[i] = new Vertice(i, "" + letra);
        }
    }

    public int gradoDeEntrada(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("Error: vertice fuera de rango.");
        }
        return vertices[i].getGradoEntrada();
    }

    public int gradoDeSalida(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("Error: vertice fuera de rango.");
        }
        return vertices[i].getGradoSalida();
    }

    public int cuantasAristasHay() {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += vertices[i].getGradoSalida();
        }
        return total;
    }

    public boolean adyacente(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Error: vertice fuera de rango.");
        }
        return vertices[i].salidas.contains(vertices[j]);
    }

    public boolean conectados(int i, int j) { 
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Error: vertice fuera de rango.");
        }

        for (Vertice salida : vertices[i].salidas) {
            if (salida == vertices[j]) { return true; }

            if (conectados(indexDeVertice(salida), j)) { return true; }
        }

        return false;
    }

    public String topologicalSort() {
        int[] copiaGrados = new int[n];
        int[] gradosOriginales = new int[n];

        PriorityQueue<Integer> cola = new PriorityQueue<>((a, b) -> {
            int gradoA = gradosOriginales[a];
            int gradoB = gradosOriginales[b];

            if (gradoA != gradoB) {
                return Integer.compare(gradoA, gradoB);
            }
            return Integer.compare(a, b);
        });

        for (int i = 0; i < n; i++) {
            gradosOriginales[i] = vertices[i].getGradoEntrada();
            copiaGrados[i] = gradosOriginales[i];

            if (copiaGrados[i] == 0) {
                cola.add(i);
            }
        }

        String res = "";

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            if (!res.isEmpty()) {
                res += "-";
            }
            res += (char)(actual + 65);

            for (Vertice v : vertices[actual].salidas) {
                copiaGrados[v.id] = copiaGrados[v.id] - 1;

                if (copiaGrados[v.id] == 0) {
                    cola.add(v.id);
                }
            }
        }

        return res;
    }

    public boolean tieneCiclos() {
        boolean[] visitados = new boolean[n];
        Stack<Integer> pila = new Stack<>();

        for (int i = 0; i < n; i++) {
            if(!visitados[i]) {
                if (tieneCiclos(i, visitados, pila)) { return true; }
            }
        }

        return false;
    }

    private boolean tieneCiclos(int actual, boolean[] visitados, Stack<Integer> pila) {
        visitados[actual] = true;
        pila.push(actual);

        for (Vertice salida : vertices[actual].salidas) {
            int indexSalida = indexDeVertice(salida);

            if (!visitados[indexSalida]) {
                if (tieneCiclos(indexSalida, visitados, pila)) { return true; }
            } else if (pila.contains(indexSalida)) {
                return true;
            }
        }

        pila.pop();
        return false;
    }

    public String mostrarEstructura() {
        String texto = "  ";

        for (int j = 0; j < n; j++) {
            char letra = 65;
            letra += j;
            texto = texto + letra + " ";
        }
        texto = texto + "\n";

        for (int i = 0; i < n; i++) {
            char letra = 65;
            letra += i;
            texto = texto + letra + " ";

            for (int j = 0; j < n; j++) {
                if (vertices[i].salidas.contains(vertices[j])) {
                    texto = texto + "1 ";
                } else {
                    texto = texto + "0 ";
                }
            }
            texto = texto + "\n";
        }

        return texto;
    }

    boolean insertarArista(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Error: vertice fuera de rango.");
        }

        if (i == j) { return false; }

        vertices[i].salidas.add(vertices[j]);

        if (tieneCiclos()) {
            vertices[i].salidas.removeLast();
            return false;
        }

        vertices[j].entradas.add(vertices[i]);
        return true;
    }

    public void eliminarAristas() {
        for (int i = 0; i < n; i++) {
            vertices[i].salidas.clear();
            vertices[i].entradas.clear();
        }
    }

    public int indexDeVertice(Vertice vertice) {
        for (int i = 0; i < n; i++) {
            if (vertice == vertices[i]) {
                return i;
            }
        }

        return -1;
    }

    public int getTamaño() {
        return n;
    }
}
