package funciones;

public class Funciones {

    // emparejamos los 2 individuos calculando el punto de corte de los cromosomas
    // sobre el que se va a hacer el intercambio con el siguiente valor del conjunto
    public static emparejaIndividuos(int[] individuo1, int[] individuo2, int corte) {

    }

    public static int calculaPuntoDeCorte(double valor, int longitudCromosoma) {
        int puntosPosibles = longitudCromosoma - 1;
        
    }

    // calculamos que individuo de los dos es el mejor (el que más se acerca al valor)
    public static int calculaFitness(int indiv1, int indiv2, int valor) {
        return Math.abs(valor - indiv1) < Math.abs(valor - indiv2) ? indiv1 : indiv2;
    }

    // convertimos el cromosoma a decimal
    public static int deBinarioADecimal(int[] array) {
        int decimal = 0;
        int potencia = 0;
        for (int i = array.length - 1; i >= 0 ; i--)
            decimal += (int)(Math.pow(2, potencia++)) * array[i];
        return decimal;
    }
    
    // genera un individuo (si el valor es < 0.5 será 0 y si es >= será 1)
	public static int[] generaIndividuo(double[] valores) {
		int individuo[] = new int[valores.length];
		for (int i = 0; i < individuo.length; i++)
			individuo[i] = valores[i] < 0.5 ? 0 : 1;
		return (individuo);
	}

	// extrae valores del array de aleatorios
	public static double[] extraeValores(double[] aleatorios, int pos, int longitud) {
		double[] valores = new double[longitud];
		for (int i = 0; i < valores.length; i++)
			valores[i] = aleatorios[pos++];
		return valores;
	}

	// generamos el conjunto de valores con los que vamos a trabajar
	public static double[] generaAleatorios(int cantidad) {
		double aleatorios[] = new double[cantidad];
		for (int i = 0; i < cantidad; i++) {
			aleatorios[i] = Math.round(Math.random() * 100) / 100.0;
		}
		return aleatorios;
	}

	public static void muestraCromosoma(double[] valores, int[] array, int individuo) {
		System.out.printf("Cromosoma del individuo %d%n", individuo);
		System.out.println("-".repeat(57));
		System.out.print("|");
		for (int i = 0; i < valores.length; i++)
			System.out.printf(" %.2f |", valores[i]);
		System.out.println();

		System.out.println("-".repeat(57));
		System.out.print("|");
		for (int i = 0; i < array.length; i++)
			System.out.printf("   %d  |", array[i]);
		System.out.println();
		System.out.println("-".repeat(57));
		System.out.println();
	}

	public static void muestraArray(double[] array) {
		System.out.println();
		System.out.println("Valores aleatorios: ");
		System.out.println("-------------------");
		for (int i = 1; i <= array.length; i++) {
			System.out.printf("%3.2f ", array[i - 1]);
			if (i != 0 && i % 8 == 0)
				System.out.println();
		}
		System.out.println("\n");
	}
}
