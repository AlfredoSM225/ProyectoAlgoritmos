import java.util.PriorityQueue;

public class GrafoDirigidoAciclico {
    private int n;
    private Vertice[] vertices;

    public GrafoDirigidoAciclico(int n) {
        this.n = n;
        vertices = new Vertice[n];

        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertice(i, String.valueOf(i));
        }
    }

    public int gradoDeEntrada(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("Error: vertice fuera de rango.");
        }
        return vertices[i].gradoEntrada;
    }

    public int gradoDeSalida(int i) {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException("Error: vertice fuera de rango.");
        }
        return vertices[i].getGradoS();
    }

    public int cuantasAristasHay() {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += vertices[i].getGradoS();
        }
        return total;
    }

    public boolean adyacente(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IllegalArgumentException("Error: vertice fuera de rango.");
        }
        return vertices[i].salidas.contains(vertices[j]);
    }

    boolean conectados(int i, int j) {
        return false;
    }

    public String topologicalSort() {
        int[] copiaGrados = new int[n];
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            copiaGrados[i] = vertices[i].gradoEntrada;

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
            res += vertices[actual].nombre;

            for (Vertice v : vertices[actual].salidas) {
                copiaGrados[v.id] = copiaGrados[v.id] - 1;

                if (copiaGrados[v.id] == 0) {
                    cola.add(v.id);
                }
            }
        }

        return res;
    }

    boolean tieneCiclos() {
        return false;
    }

    public String mostrarEstructura() {
        String texto = "  ";

        for (int j = 0; j < n; j++) {
            texto = texto + j + " ";
        }
        texto = texto + "\n";

        for (int i = 0; i < n; i++) {
            texto = texto + i + " ";

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
        return false;
    }

    public void eliminarAristas() {
        for (int i = 0; i < n; i++) {
            vertices[i].salidas.clear();
            vertices[i].gradoEntrada = 0;
        }
    }
}
