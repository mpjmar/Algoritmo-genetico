import funciones.Funciones;
import funciones.Basicas;

/**
 * Implementación de un algoritmo genético para encontrar dos números binarios
 * cuya suma sea igual a un objetivo dado.
 * 
 * <p>Parámetros del algoritmo:</p>
 * <ul>
 *   <li>Longitud del cromosoma: 8 bits</li>
 *   <li>Conjunto de elementos del cromosoma: {0, 1}</li>
 *   <li>Número de individuos de la población inicial: 2</li>
 *   <li>Probabilidad del elemento '0': número aleatorio &lt; 0.5</li>
 *   <li>Probabilidad del elemento '1': número aleatorio &gt;= 0.5</li>
 *   <li>Probabilidad de mutación: 0.3</li>
 * </ul>
 * 
 * @author [M. Paz Jimenez Martin]
 * @version 1.0
 */

public class App {

	/**
     * Punto de entrada del programa. Ejecuta el algoritmo genético para buscar
     * dos números que sumen el objetivo especificado por el usuario.
     * 
     * <p>Proceso:</p>
     * <ol>
     *   <li>Solicita al usuario el valor objetivo a buscar</li>
     *   <li>Genera un conjunto de 256 números aleatorios para el algoritmo</li>
     *   <li>Crea dos individuos iniciales aleatorios (generación 0)</li>
     *   <li>Ejecuta el algoritmo genético hasta encontrar la solución o agotar recursos</li>
     * </ol>
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     * @throws Exception Si ocurre un error durante la ejecución del algoritmo
     */

    public static void main(String[] args) throws Exception {

		// solicitamos un valor objetivo 
		System.out.print("Introduce el resultado a buscar: ");
		int objetivo = Integer.parseInt(System.console().readLine());

		// generamos los valores con los que vamos a trabajar
		double[] aleatorios = Basicas.generaAleatorios(256);

		// obtenemos los 2 primeros individuos que fonman la generación inicial y muestra sus cromosomas
		int[] individuo1 = Funciones.generaIndividuo(aleatorios, 1);
		int[] individuo2 = Funciones.generaIndividuo(aleatorios, 2);

		// busca y muestra los resultados
		Funciones.buscaResultados(aleatorios, objetivo, individuo1, individuo2);
	}
}
