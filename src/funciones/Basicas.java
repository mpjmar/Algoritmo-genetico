package funciones;

/**
 * Clase de utilidades básicas para el algoritmo genético.
 * Proporciona funciones para generar números aleatorios, mostrar arrays
 * y convertir entre representaciones binarias y decimales.
 * 
 * @author M. Paz Jimenez Martin
 * @version 1.0
 */

public class Basicas {
	/**
     * Genera un array de números aleatorios con dos decimales de precisión.
     * Los valores generados están en el rango [0.00, 1.00].
     * Muestra el array generado por consola.
     * 
     * @param cantidad Número de valores aleatorios a generar
     * @return Array con los números aleatorios generados
     */
	public static double[] generaAleatorios(int cantidad) {
		double aleatorios[] = new double[cantidad];
		for (int i = 0; i < cantidad; i++) {
			aleatorios[i] = Math.round(Math.random() * 100) / 100.0;
		}
		muestraArray(aleatorios, "Valores aleatorios: ");
		return aleatorios;
	}

	/**
     * Muestra un array de valores decimales por consola con formato de tabla.
     * Los valores se muestran con 2 decimales, 8 valores por línea.
     * 
     * @param array Array de valores decimales a mostrar
     * @param mensaje Título descriptivo del array
     */
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

	/**
     * Muestra un array de valores enteros por consola con formato de tabla.
     * Los valores se muestran 8 por línea.
     * 
     * @param array Array de valores enteros a mostrar
     * @param mensaje Título descriptivo del array
     */
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

	/**
     * Convierte un número binario a su representación decimal.
     * 
     * @param binario Array que representa el número en binario (cada posición es un bit)
     * @return Valor decimal correspondiente
     */
    public static int deBinarioADecimal(int[] binario) {
        int decimal = 0;
        int potencia = 0;
        for (int i = binario.length - 1; i >= 0 ; i--)
            decimal += (int)(Math.pow(2, potencia++)) * binario[i];
        return decimal;
    }

	/**
     * Convierte un número decimal a su representación binaria de longitud fija.
     * 
     * @param num Número decimal a convertir
     * @param longitud Número de bits del resultado (longitud del array binario)
     * @return Array que representa el número en binario
     */
    public static int[] deDecimalABinario(int num, int longitud) {
        int[] binario = new int[longitud];
        for (int i = binario.length - 1; i >= 0; i--) {
			binario[i] = num % 2;
			num /= 2;
		}
        return binario;
    }
}
