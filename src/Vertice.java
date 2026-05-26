import java.util.ArrayList;
import java.util.List;

public class Vertice {
    int id;
    String nombre;
    List<Vertice> salidas;
    int gradoEntrada;

    public Vertice(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        salidas = new ArrayList<>();
        gradoEntrada = 0;
    }

    public int getGradoS(){
        return salidas.size();
    }

    @Override
    public String toString() {
        return nombre;
    }
}
