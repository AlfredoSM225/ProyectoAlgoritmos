import java.util.ArrayList;

public class Vertice {
    int id;
    String nombre;
    ArrayList<Vertice> salidas;
    ArrayList<Vertice> entradas;
    int gradoEntrada;

    public Vertice(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        salidas = new ArrayList<>();
        gradoEntrada = 0;
    }

    public int getGrado() {
        return entradas.size() + salidas.size();
    }

    public int getGradoE() {
        return entradas.size();
    }

    public int getGradoS(){
        return salidas.size();
    }

    @Override
    public String toString() {
        return nombre;
    }
}
