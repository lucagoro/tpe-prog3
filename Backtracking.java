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
        * Estrategia de resolución - Técnica Backtracking:
        * - El árbol de exploración comienza vacío. En cada nivel, se generan nuevos estados agregando máquinas a la solución actual,
        *   siempre y cuando la suma total de piezas no supere el límite requerido.
        * - Estados finales: son aquellos que ya no pueden extenderse porque la suma total de piezas es igual o superior al límite.
        * - Estados solución: son los estados finales cuya suma total de piezas es exactamente igual al límite pedido.
        * - Se evalúan todas las combinaciones posibles con repetición de máquinas.
        * - Criterio de optimalidad: se guarda la solución con menor cantidad de máquinas entre todas las válidas.
        * - No se implementó una poda explícita, pero sí se impuso una restricción que evita generar estados
        *   cuyo total parcial supere el máximo de piezas permitidas (ej. si sumaActual + pieza > piezasLimite, no se continúa).
 */


    public List<Maquina> minimizarXBacktracking(List<Maquina> maquinas, int piezasLimite) {
        List<Maquina> solucionActual = new ArrayList<>();
        back(solucionActual, 0, maquinas, piezasLimite, 0);
        return mejorSolucion;
    }

    private void back(List<Maquina> solucionActual, int sumaActual, List<Maquina> maquinas, int piezasLimite, int pos) {
        cantEstadosGenerados++;          
        if (sumaActual == piezasLimite) {
            if (esMejor(solucionActual)) {
                mejorSolucion.clear();
                mejorSolucion.addAll(solucionActual);
            }

        } else {

            for(int i = pos; i < maquinas.size(); i++) {
                if (sumaActual + maquinas.get(i).getPieza() <= piezasLimite) {
                    sumaActual += maquinas.get(i).getPieza();
                    solucionActual.add(maquinas.get(i));
                    back(solucionActual, sumaActual, maquinas, piezasLimite, i);
                    sumaActual -= maquinas.get(i).getPieza();
                    solucionActual.removeLast();
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