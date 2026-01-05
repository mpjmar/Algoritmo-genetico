import funciones.Funciones;
import funciones.Basicas;

public class App {

	// 1. Longitud del cromosoma: 4
	// 2. Conjunto de elementos del cromosoma: {0, 1}
	// 3. Número de individuos de la población: 2
	// 4. Para la creación de la primera generación:
	//    4.1. Probabilidad del elemento '0': num aleatorio < 0.5
	//    4.2. Probabilidad del elemento '1': num aleatorio >= 0.5
	// 5. Probabilidad de emparejamiento: 0.7
	// 6. Probabilidad de mutación: 0.3

    public static void main(String[] args) throws Exception {

		// solicitamos un valor objetivo 
		System.out.print("Introduce el resultado a buscar: ");
		int objetivo = Integer.parseInt(System.console().readLine());

		// generamos los valores con los que vamos a trabajar
		double[] aleatorios = Basicas.generaAleatorios(256);

		// obtenemos los 2 primeros individuos que fonman la generación inicial y muestra sus cromosomas
		int[] individuo1 = Funciones.generaIndividuo(aleatorios, 1);
		int[] individuo2 = Funciones.generaIndividuo(aleatorios, 2);

		Funciones.buscaResultados(aleatorios, objetivo, individuo1, individuo2);
	}
}
