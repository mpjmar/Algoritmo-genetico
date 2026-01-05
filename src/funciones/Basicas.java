package funciones;

public class Basicas {
	public static double[] generaAleatorios(int cantidad) {
		double aleatorios[] = new double[cantidad];
		for (int i = 0; i < cantidad; i++) {
			aleatorios[i] = Math.round(Math.random() * 100) / 100.0;
		}
		muestraArray(aleatorios, "Valores aleatorios: ");
		return aleatorios;
	}

	public static void muestraArray(double[] array, String mensaje) {
		System.out.println();
		System.out.println(mensaje);
		System.out.println("-".repeat(mensaje.length()));
		for (int i = 1; i <= array.length; i++) {
			System.out.printf("%3.2f ", array[i - 1]);
			if (i != 0 && i % 8 == 0)
				System.out.println();
		}
		System.out.println("\n");
	}

	public static void muestraArray(int[] array, String mensaje) {
		System.out.println("\n" + mensaje);
		System.out.println("-".repeat(mensaje.length()));
		System.out.print("  ");
		for (int i = 1; i <= array.length; i++) {
			System.out.printf("%d ", array[i - 1]);
			if (i != 0 && i % 8 == 0)
				System.out.println();
		}
		System.out.println("\n");
	}

    public static int deBinarioADecimal(int[] binario) {
        int decimal = 0;
        int potencia = 0;
        for (int i = binario.length - 1; i >= 0 ; i--)
            decimal += (int)(Math.pow(2, potencia++)) * binario[i];
        return decimal;
    }

    public static int[] deDecimalABinario(int num, int longitud) {
        int[] binario = new int[longitud];
        for (int i = binario.length - 1; i >= 0; i--) {
			binario[i] = num % 2;
			num /= 2;
		}
        return binario;
    }
}
