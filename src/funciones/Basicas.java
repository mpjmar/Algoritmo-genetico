package funciones;

public class Basicas {
	// generamos el conjunto de valores con los que vamos a trabajar
	public static double[] generaAleatorios(int cantidad) {
		double aleatorios[] = new double[cantidad];
		for (int i = 0; i < cantidad; i++) {
			aleatorios[i] = Math.round(Math.random() * 100) / 100.0;
		}
		muestraArray(aleatorios);
		return aleatorios;
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

    // convertimos el cromosoma a decimal
    public static int deBinarioADecimal(int[] array) {
        int decimal = 0;
        int potencia = 0;
        for (int i = array.length - 1; i >= 0 ; i--)
            decimal += (int)(Math.pow(2, potencia++)) * array[i];
        return decimal;
    }
}
