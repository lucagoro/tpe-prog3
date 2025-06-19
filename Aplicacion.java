import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aplicacion {
    
    public static void main(String[] args) {
        String archivo = "archivo.txt";

        int piezasTotales = 0;
        List<Maquina> maquinas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Lee la primera línea: cantidad total de piezas
            piezasTotales = Integer.parseInt(br.readLine().trim());

            // Lee el resto: cada línea tiene nombre y cantidad de piezas que produce
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0].trim();
                int piezas = Integer.parseInt(partes[1].trim());

                maquinas.add(new Maquina(nombre, piezas));
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        Backtracking back = new Backtracking();
        List<Maquina> resultadoBack = back.minimizarXBacktracking(maquinas, piezasTotales);

        Greedy greedy = new Greedy();
        List<Maquina> resultadoGreedy = greedy.minimizarXGreedy(maquinas, piezasTotales);

        // Imprime la solución
        System.out.println("Solución Backtracking:");
        if (back == null || resultadoBack.isEmpty()) { 
            System.out.println("No se encontró una secuencia válida.");
        } else {
            System.out.println("Secuencia óptima:");
            for (Maquina m : resultadoBack) {
                System.out.print(m.nombre + " ");
            }
            System.out.println();
            System.out.println("Piezas producidas: " + piezasTotales);
            System.out.println("Puestas en funcionamiento: " + resultadoBack.size());
            System.out.println("Estados generados: " + back.getCantEstadosGenerados());
        }
        System.out.println();

        System.out.println("Solución Greedy:");
        if (resultadoGreedy == null || resultadoGreedy.isEmpty()) {
            System.out.println("No se encontró una secuencia válida.");
        } else {
            System.out.println("Secuencia óptima:");
            for (Maquina m : resultadoGreedy) {
                System.out.print(m.nombre + " ");
            }
            System.out.println();
            System.out.println("Piezas producidas: " + piezasTotales);
            System.out.println("Puestas en funcionamiento: " + resultadoGreedy.size());
            System.out.println("Candidatos considerados: " + greedy.getCandidatosConsiderados());
        }
        System.out.println();
    }
}
