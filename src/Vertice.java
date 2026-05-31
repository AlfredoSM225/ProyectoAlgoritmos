import java.util.ArrayList;

public class Vertice {
    int id;
    String nombre;
    ArrayList<Vertice> salidas;
    ArrayList<Vertice> entradas;

    public Vertice(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        salidas = new ArrayList<>();
        entradas = new ArrayList<>();
    }

    public int getGrado() {
        return entradas.size() + salidas.size();
    }

    public int getGradoEntrada() {
        return entradas.size();
    }

    public int getGradoSalida(){
        return salidas.size();
    }

    @Override
    public String toString() {
        return nombre;
    }
}
