public class App {

	// 1. Longitud del cromosoma: 4
	// 2. Conjunto de elementos del cromosoma: {0, 1}
	// 3. Número de individuos de la población: 2
	// 4. Para la creación de la primera generación:
	//    4.1. Probabilidad del elemento '0': num aleatorio < 0.5
	//    4.2. Probabilidad del elemento '1': num aleatorio >= 0.5
	// 5. Probabilidad de emparejamiento: 0.7
	// 6. Probabilidad de mutación: 0.3
	
	public static double[] generaIndividuo(double[] aleatorios, int pos) {
		double individuo[] = new double[4];
		for (int i = 0; i < individuo.length; i++){
			individuo[i] = aleatorios[pos] < 0.5 ? 0 : 1;
			pos++;
		}
		return (individuo);
	}
	
    public static void main(String[] args) throws Exception {

		double aleatorios[] = new double[32];
		for (int i = 0; i < 32; i++) {
			aleatorios[i] = Math.round(Math.random() * 100) / 100.0;
		}
		int cromosoma = (int)(Math.random() * 2) + 1;

		double individuo1[] = generaIndividuo(aleatorios, 0);
		double individuo2[] = generaIndividuo(aleatorios, 4);

		for (int i = 0; i < aleatorios.length; i++)
			System.out.print(aleatorios[i] + " ");
	}
}
