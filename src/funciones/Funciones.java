package funciones;

public class Funciones {
	public static int posValores = 0;
/* 
    // calculamos que individuo de los dos es el mejor (el que más se acerca al valor)
    public static int calculaFitness(int indiv1, int indiv2, int valor) {
        return Math.abs(valor - indiv1) < Math.abs(valor - indiv2) ? indiv1 : indiv2;
    } */

	// emparejamos los 2 individuos calculando el punto de corte de los cromosomas
    // sobre el que se va a hacer el intercambio con el siguiente valor del conjunto
    public static int[][] emparejaIndividuos(double[] aleatorios, int[] cromosoma1, int[] cromosoma2) {
		int longCromosoma = cromosoma1.length;
		int[][] poblacion = new int[4][longCromosoma];
		int puntoCorte = calculaPuntoDeCorte(aleatorios, posValores, longCromosoma);
		....
		return poblacion;
    }

	public static int calculaPuntoDeCorte(double[] aleatorios, int posValores, int longCromosoma) {
        int puntosPosibles = longCromosoma - 1;
        double[] valor = extraeValores(aleatorios, 1);
		int puntoCorte = (int)(valor[0] * puntosPosibles);
		return puntoCorte;
    }

	/* public static boolean validaGenotipos(int[] genotipos, int objetivo) {
		for (int i = 0; i < genotipos.length; i++) {
			if (genotipos[i] > objetivo)
				System.out.printf("El valor del individuo %d supera el valor objetivo.%n", i + 1);
			else if (genotipos[i] == objetivo)
				System.out.printf("El valor del individuo %d es el mismo que el valor objetivo.%n", i + 1);
			else
				System.out.println("Ambos individuos son válidos.");
		}
		System.out.printf("La suma de ambos individuos es: %d%n", genotipos[0] + genotipos[1]);
		return genotipos[0] < objetivo && genotipos[1] < objetivo;
	} */

	public static int[] obtieneResultado(int[][] poblacion, int objetivo) {
		int[] genotipos = new int[poblacion.length];
		for (int i = 0; i < poblacion.length; i++) {
			genotipos[i] = Basicas.deBinarioADecimal(poblacion[i]);
			System.out.printf("El individuo %d vale %d%n", i + 1, poblacion[i]);
		}
		validaGenotipos(genotipos, objetivo);
		return genotipos;
	}

    
    // genera un individuo (si el valor es < 0.5 será 0 y si es >= será 1)
	public static int[] generaIndividuo(double[] aleatorios, int individuo) {
		double valores[] = extraeValores(aleatorios, 8);
		int genotipo[] = new int[8];
		for (int i = 0; i < valores.length; i++)
			valores[i] = aleatorios[i] < 0.5 ? 0 : 1;
		muestraCromosoma(genotipo, individuo);
		return (genotipo);
	}

	// extrae valores del array de aleatorios
	public static double[] extraeValores(double[] aleatorios, int longitud) {
		double[] valores = new double[longitud];
		for (int i = 0; i < valores.length; i++)
			valores[i] = aleatorios[posValores++];
		return valores;
	}

	public static void muestraCromosoma(int[] valores, int individuo) {
		System.out.printf("Cromosoma del individuo %d%n", individuo);
		/* System.out.println("-".repeat(57));
		System.out.print("|");
		for (int i = 0; i < valores.length; i++)
			System.out.printf(" %.2f |", valores[i]);
		System.out.println(); */

		System.out.println("-".repeat(57));
		System.out.print("|");
		for (int i = 0; i < valores.length; i++)
			System.out.printf("   %d  |", valores[i]);
		System.out.println("\n" + "-".repeat(57) + "\n");
	}
}
