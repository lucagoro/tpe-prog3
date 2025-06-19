import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Greedy {
    private int candidatosConsiderados;

    public Greedy() {
        this.candidatosConsiderados = 0;
    }

    /*
        * Estrategia de resolución - Técnica Greedy:
        * - Candidatos: las máquinas disponibles, cada una con la cantidad de piezas que produce.
        * - Estrategia de selección: se ordenan las máquinas de mayor a menor según la cantidad de piezas producidas.
        *   Se elige siempre la máquina que más produce y se la agrega tantas veces como sea posible sin superar el límite.
        *   Luego se pasa al siguiente candidato.
        * - Consideraciones: si no se encuentra una combinación exacta que sume el total de piezas requeridas, 
        *   se retorna null. En la clase Aplicación se maneja este caso mostrando un mensaje al usuario.
    */

    public List<Maquina> minimizarXGreedy(List<Maquina> maquinas, int piezasLimite) {
        List<Maquina> solucionActual = new ArrayList<>();
        int sumaActual = 0;

        List<Maquina> copiaMaquinas = new ArrayList<>(maquinas);
        Collections.sort(copiaMaquinas, Comparator.comparingInt(Maquina::getPieza).reversed());


        while (!copiaMaquinas.isEmpty() && sumaActual < piezasLimite) {
            Maquina m = copiaMaquinas.get(0);  
            candidatosConsiderados++; 
            while (sumaActual + m.getPieza() <= piezasLimite) {
                sumaActual += m.getPieza();
                solucionActual.add(m);
            }
            copiaMaquinas.remove(0);
        }

        if(sumaActual == piezasLimite) {
            return solucionActual;
        } else {
            return null;
        }
    }

    public int getCandidatosConsiderados() {
        return candidatosConsiderados;
    }
}
