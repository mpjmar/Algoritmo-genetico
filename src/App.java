import funciones.Funciones;

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
		int posValores = 0;

		// solicitamos un valor objetivo 
		System.out.print("Que resultado quieres buscar: ");
		int objetivo = Integer.parseInt(System.console().readLine());

		// generamos los valores con los que vamos a trabajar
		double[] aleatorios = Funciones.generaAleatorios(64);
		Funciones.muestraArray(aleatorios);

		// obtenemos los 2 primeros individuos que fonman la generación inicial
		double[] valores = Funciones.extraeValores(aleatorios, posValores, 8);
		posValores += 8;
		int[] individuo1 = Funciones.generaIndividuo(valores);
		Funciones.muestraCromosoma(valores, individuo1, 1);
		
		valores = Funciones.extraeValores(aleatorios, posValores, 8);
		posValores += 8;
		int[] individuo2 = Funciones.generaIndividuo(valores);
		Funciones.muestraCromosoma(valores, individuo2, 2);

		int decimal1 = Funciones.deBinarioADecimal(individuo1);
		System.out.printf("El individuo %d vale %d%n", 1, decimal1);
		int decimal2 = Funciones.deBinarioADecimal(individuo2);
		System.out.printf("El individuo %d vale %d%n", 2, decimal2);
		int mejor = Funciones.calculaFitness(objetivo, decimal1, decimal2);
		System.out.printf("El individuo %d es el mejor ejemplar.%n", decimal1 == mejor ? 1 : 2);

		// para ver si cada individuo es apto para el emparejamiento, sacamos los 2 valores siguientes
		// si el primero esta entre [0-0.48] el individuo 1 se emparejará
		// si el segundo está entre [0.48-1] el individuo 2 se emparejará
		double[] empareja1 = Funciones.extraeValores(aleatorios, posValores, 1);
		posValores += 1;
		System.out.println("Resultado individuo 1: " + empareja1);
		double[] empareja2 = Funciones.extraeValores(aleatorios, posValores, 1);
		posValores += 1;
		System.out.println("Resultado individuo 2: " + empareja2);

		System.out.printf("El individuo 1%s se empareja", empareja1[0] <= 0.48 ? "" : " no");
		System.out.printf("El individuo 2%s se empareja", empareja2[0] >= 0.48 ? "" : " no");
		
		//if (empareja1[0] > 0.48 || empareja2[0] < 0.48)


		// la probabilidad de emparejamiento es del 0.7 y lo decide el siguiente número 
		// del conjunto de valores
		double[] emparejamiento = Funciones.extraeValores(aleatorios, posValores, 1);
		posValores += 1;
		System.out.printf("La probabilidad de emparejamiento es: %n", emparejamiento[0]);
		if (emparejamiento[0] <= 0.7)
			Funciones.emparejaIndividuos(individuo1, individuo2);

		
	}
}
