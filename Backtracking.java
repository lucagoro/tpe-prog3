import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    private List<Maquina> mejorSolucion;
    private int cantEstadosGenerados;

    public Backtracking() {     
        this.mejorSolucion = new ArrayList<>();
        this.cantEstadosGenerados = 0;   
    }

    /*
        Breve explicación de la estrategia de resolución:
        El arbol comienza vacio, generando nuevos estados a partir de las maquinas cargadas en el programa.
        El estado final va a ser aquel que tenga la menor cantidad de maquinas y nos imprima exactamente la cantidad de piezas pedidas. La solucion ideal.
        Los estados solucion van a ser aquellos que sean una posible solucion final.
        No hicimos una poda en si, si no que implementamos una restriccion, la cual descarta estados que se pasen del maximo. Si se lleva una suma (7) y sumando la maquina siguiente (10)
        se excede el maximo (17 > MAX), ese estado no se genera.
    */


    public List<Maquina> minimizarXBacktracking(List<Maquina> maquinas, int piezasLimite) {
        List<Maquina> solucionActual = new ArrayList<>();
        back(solucionActual, 0, maquinas, piezasLimite);
        return mejorSolucion;
    }

    private void back(List<Maquina> solucionActual, int sumaActual, List<Maquina> maquinas, int piezasLimite) {
        cantEstadosGenerados++;          // REVISAR 
        if (sumaActual == piezasLimite) {
            if (esMejor(solucionActual)) {
                mejorSolucion = new ArrayList<>(solucionActual); 
            }

        } else {

            for(Maquina m : maquinas) {
                if (sumaActual + m.getPieza() <= piezasLimite) {
                    sumaActual += m.getPieza();
                    solucionActual.add(m);
                    back(solucionActual, sumaActual, maquinas, piezasLimite);
                    sumaActual -= m.getPieza();
                    solucionActual.remove(solucionActual.size() - 1);
                }
            }
        }
    }

    public boolean esMejor(List<Maquina> solucionActual) {
        return mejorSolucion.isEmpty() || solucionActual.size() < mejorSolucion.size();
    }

    public int getCantEstadosGenerados() {
        return cantEstadosGenerados;
    }
    
}