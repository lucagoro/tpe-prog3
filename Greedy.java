import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Greedy {
    private int candidatosConsiderados;

    public Greedy() {
        this.candidatosConsiderados = 0;
    }

    /*  -------- ACOMODAR EXPLICACION ----------
        Breve explicación de la estrategia de resolución:
        - ¿Cuáles son los candidatos? Los candidatos son las maquinas con sus respectivas piezas.
        - ¿Estrategia de selección de candidatos?
        Ordenamos de mayor a menor las maquinas por su cantidad de piezas para tomar la primera (la mayor)
        y si la cantidad de piezas de esa maquina es menor a la cantidad de piezas limite la agregamos a la solución.
        Y así sucesivamente, se van sumando la cantidad de piezas de las maquinas que se eligen. 
        - Consideraciones respecto a encontrar o no solución: Si no se encuentra solución se retorna null y en la clase Aplicacion
        antes de imprimir la solución pregunto si es null y si lo es imprimo un mensaje: "No se encontró una secuencia válida."
    */

    public List<Maquina> minimizarXGreedy(List<Maquina> maquinas, int piezasLimite) {
        List<Maquina> solucionActual = new ArrayList<>();
        int sumaActual = 0;

        List<Maquina> copiaMaquinas = new ArrayList<>(maquinas);
        Collections.sort(copiaMaquinas, Comparator.comparingInt(Maquina::getPieza).reversed());


        while (!copiaMaquinas.isEmpty() && sumaActual < piezasLimite) {
            Maquina m = copiaMaquinas.get(0); // agarra la maquina con mayor cant de piezas siempre porque estan ordenadas de mayor a menor y vas removiendo
            while (sumaActual + m.getPieza() <= piezasLimite) {
                candidatosConsiderados++;
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
