package funciones;

/**
 * Clase que contiene las funciones principales del algoritmo genético.
 * Implementa operaciones de cruzamiento, mutación, selección y evaluación
 * de individuos para encontrar dos números que sumen un objetivo dado.
 * 
 * @author M. Paz Jimenez Martin
 * @version 1.0
 */

public class Funciones {

	/** Índice global para controlar la posición actual en el array de números aleatorios */
	public static int posAleatorios = 0;

	/**
     * Genera descendencia hasta encontrar la solución o agotar los recursos (números aleatorios).
     * Ejecuta el ciclo evolutivo del algoritmo genético iterando generaciones hasta
     * encontrar dos individuos cuya suma sea igual al objetivo.
     * 
     * @param aleatorios Array de números aleatorios usado por el algoritmo
     * @param objetivo Valor que deben sumar los dos individuos buscados
     * @param individuo1 Primer individuo de la población inicial (cromosoma binario)
     * @param individuo2 Segundo individuo de la población inicial (cromosoma binario)
     * @throws InterruptedException Si el hilo es interrumpido durante la pausa entre generaciones
     */
	public static void buscaResultados (double[] aleatorios, int objetivo, int[] individuo1, int[] individuo2) throws InterruptedException {
		boolean encontrado = false;
		int[][] poblacion;
		int[] fenotipos;
		int[] mejores = new int[2];
		int generacion = 0;
		do {
			System.out.printf("== GENERACIÓN %d ==%n", generacion++);
			System.out.println("-------------------");
			try {
				// emparejamos a los 2 individuos
				poblacion = generaDescendencia(aleatorios, individuo1, individuo2);
		
				// obtenemos y mostramos los fenotipos (valores decimales) de la población
				fenotipos = obtieneFenotipos(poblacion);
		
				// seleccionamos los 2 mejores individuos
				mejores = calculaFitness(fenotipos, objetivo);

				individuo1 = Basicas.deDecimalABinario(mejores[0], 8);
				individuo2 = Basicas.deDecimalABinario(mejores[1], 8);
				encontrado = mejores[0] + mejores[1] == objetivo;
				
				if (!encontrado) {
					System.out.printf("%nLos mejores individuos (cuya suma se acerca más al objetivo) son %d y %d (%d).%n" , mejores[0], mejores[1], mejores[0] + mejores[1]);
					System.out.printf("No se han encontrado los valores que sumen %d.%n%n", objetivo);
					System.out.printf("Los nuevos individuos que emparejaremos son el %d y el %d.%n", mejores[0], mejores[1]);
					System.out.println("-".repeat(60));
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
				break;
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error inesperado.");
			}
			Thread.sleep(1000);
		} while (!encontrado);

		if (encontrado) {
			System.out.println("\n" + ":".repeat(56));
			System.out.printf("   ENCONTRADOS: Los valores que suman %d son %d y %d%n", objetivo, mejores[0], mejores[1]);
			System.out.println(":".repeat(56));
		}
		else if (generacion > 0)
			System.out.printf("Los mejores valores encontrados son %d y %d (%d).%n", mejores[0], mejores[1], mejores[0] + mejores[1]);
	}
	
    /**
     * Selecciona los 2 mejores individuos de la población evaluando todas las parejas posibles.
     * Calcula la pareja cuya suma se acerca más al objetivo usando la función de fitness.
     * 
     * @param poblacion Array con los fenotipos (valores decimales) de todos los individuos
     * @param objetivo Valor objetivo que deben sumar los dos individuos
     * @return Array con los dos mejores individuos (fenotipos)
     */
    public static int[] calculaFitness(int[] poblacion, int objetivo) {
		int[] mejores = new int[2];
		int suma = 0, diferencia = Integer.MAX_VALUE;
		for (int i = 0; i < poblacion.length; i++) {
			for (int j = i + 1; j < poblacion.length; j++) {
				suma = poblacion[i] + poblacion[j];
				if (Math.abs(suma - objetivo) < diferencia) {
					diferencia = Math.abs(suma - objetivo);
					mejores[0] = poblacion[i];
					mejores[1] = poblacion[j];
				}
			}
		}
		return mejores;
    }

	/**
     * Combina los cromosomas de los padres mediante cruzamiento de un punto
     * y aplica mutación a los hijos generados.
     * 
     * @param aleatorios Array de números aleatorios para determinar punto de corte y mutación
     * @param padre1 Cromosoma del primer padre (array binario)
     * @param padre2 Cromosoma del segundo padre (array binario)
     * @return Matriz con la población completa: 2 padres y 2 hijos
     */
	public static int[][] generaDescendencia(double[] aleatorios, int[] padre1, int[] padre2) {
		int longCromos = padre1.length;
		int puntoCorte = calculaPuntoDeCorte(aleatorios, longCromos);
		int[] hijo1 = new int[longCromos];
		int[] hijo2 = new int[longCromos];
		for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = i <= puntoCorte ? padre1[i] : padre2[i];
			hijo2[i] = i <= puntoCorte ? padre2[i] : padre1[i];
		}
		mutaCromosoma(aleatorios, hijo1);
		mutaCromosoma(aleatorios, hijo2);
		int[][] poblacion = generaPoblacion(padre1, padre2, hijo1, hijo2);
		return poblacion;
	}

	/**
     * Calcula el punto de corte para el cruzamiento de cromosomas
     * utilizando el siguiente valor del conjunto de aleatorios.
     * 
     * @param aleatorios Array de números aleatorios
     * @param longCromosoma Longitud del cromosoma
     * @return Índice del punto de corte para el cruzamiento
     */
	public static int calculaPuntoDeCorte(double[] aleatorios, int longCromosoma) {
        int puntosPosibles = longCromosoma - 1;
        double[] valor = extraeValores(aleatorios, 1);
		int puntoCorte = (int)(valor[0] * puntosPosibles);
		return puntoCorte;
    }

	/**
     * Aplica mutación al cromosoma si el valor extraído de aleatorios es menor o igual a 0.3.
     * Para cada gen, extrae un valor aleatorio y si es <= 0.3, invierte el gen.
     * 
     * @param aleatorios Array de números aleatorios para determinar si muta cada gen
     * @param cromosoma Cromosoma a mutar (se modifica directamente)
     */
	public static void mutaCromosoma(double[] aleatorios, int[] cromosoma) {
		double probBase = 0.3;
		for (int i = 0; i < cromosoma.length; i++) {
			double[] probabilidad = extraeValores(aleatorios, 1);
			if (probabilidad[0] <= probBase)
				cromosoma[i] = 1 - cromosoma[i];
		}
	}

	/**
     * Crea una matriz que contiene la población completa de padres e hijos.
     * 
     * @param padre1 Cromosoma del primer padre
     * @param padre2 Cromosoma del segundo padre
     * @param hijo1 Cromosoma del primer hijo
     * @param hijo2 Cromosoma del segundo hijo
     * @return Matriz 4x8 con la población completa
     */
	public static int[][] generaPoblacion(int[] padre1, int[] padre2, int[] hijo1, int[] hijo2) {
		int[][] poblacion = new int[4][padre1.length];
		for (int i = 0; i < poblacion.length; i++) {
			poblacion[i] = switch (i) {
				case 0 -> padre1;
				case 1 -> padre2;
				case 2 -> hijo1;
				case 3 -> hijo2;
				default -> null;
			};
		}
		return poblacion;
	}

	/**
     * Convierte a decimal el valor binario de los cromosomas de la población
     * y muestra los fenotipos por consola.
     * 
     * @param poblacion Matriz con los genotipos (cromosomas binarios) de la población
     * @return Array con los fenotipos (valores decimales) correspondientes
     */
	public static int[] obtieneFenotipos(int[][] poblacion) {
		int[] fenotipos = new int[poblacion.length];
		for (int i = 0; i < poblacion.length; i++) {
			fenotipos[i] = Basicas.deBinarioADecimal(poblacion[i]);
			System.out.printf("El individuo %d (%s) vale %d%n", i + 1, i < 2 ? "padre" : "hijo", fenotipos[i]);
		}
		return fenotipos;
	}

    
    /**
     * Genera un individuo inicial aleatorio.
     * Cada gen del cromosoma será 0 si el valor aleatorio es menor a 0.5, o 1 si es mayor o igual.
     * 
     * @param aleatorios Array de números aleatorios
     * @param individuo Número identificador del individuo (para mostrar)
     * @return Genotipo del individuo generado (cromosoma binario de 8 bits)
     */
	public static int[] generaIndividuo(double[] aleatorios, int individuo) {
		double valores[] = extraeValores(aleatorios, 8);
		int genotipo[] = new int[8];
		for (int i = 0; i < valores.length; i++)
			genotipo[i] = valores[i] < 0.5 ? 0 : 1;
		muestraCromosoma(genotipo, individuo);
		return (genotipo);
	}

	/**
     * Extrae un subconjunto de valores del array de aleatorios.
     * Utiliza la variable global posAleatorios para controlar la posición actual.
     * 
     * @param aleatorios Array de números aleatorios
     * @param longitud Cantidad de valores a extraer
     * @return Array con los valores extraídos
     * @throws RuntimeException Si no hay suficientes aleatorios disponibles
     */
	public static double[] extraeValores(double[] aleatorios, int longitud) {
		if (posAleatorios + longitud > aleatorios.length) {
			System.out.println();
			throw new RuntimeException("No hay suficientes aleatorios para continuar la búsqueda.\n" + "-".repeat(60));
		}

		double[] valores = new double[longitud];
		for (int i = 0; i < valores.length; i++)
			valores[i] = aleatorios[posAleatorios++];
		return valores;
	}

	/**
     * Muestra visualmente el cromosoma del individuo en formato de tabla.
     * 
     * @param valores Cromosoma a mostrar (array binario)
     * @param individuo Número identificador del individuo
     */
	public static void muestraCromosoma(int[] valores, int individuo) {
		System.out.printf("Cromosoma del individuo %d%n", individuo);
		System.out.println("-".repeat(57));
		System.out.print("|");
		for (int i = 0; i < valores.length; i++)
			System.out.printf("   %d  |", valores[i]);
		System.out.println("\n" + "-".repeat(57) + "\n");
	}
}
