package funciones;

public class Funciones {
	public static int posAleatorios = 0;

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
	
    // seleccionamos los 2 mejores individuos
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

	// combina cromosomas de los padres y devuelve la poblacion de padres e hijos
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

	// calculamos el punto de corte de los cromosomas sobre el que se va a hacer el intercambio 
	// utilizando el siguiente valor del conjunto
	public static int calculaPuntoDeCorte(double[] aleatorios, int longCromosoma) {
        int puntosPosibles = longCromosoma - 1;
        double[] valor = extraeValores(aleatorios, 1);
		int puntoCorte = (int)(valor[0] * puntosPosibles);
		return puntoCorte;
    }

	public static void mutaCromosoma(double[] aleatorios, int[] cromosoma) {
		double probBase = 0.3;
		for (int i = 0; i < cromosoma.length; i++) {
			double[] probabilidad = extraeValores(aleatorios, 1);
			if (probabilidad[0] <= probBase)
				cromosoma[i] = 1 - cromosoma[i];
		}
	}

	// devuelve una matriz que contiene la poblacion de padres e hijos
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

	// convierte a decimal el valor binario de los cromosomas
	public static int[] obtieneFenotipos(int[][] poblacion) {
		int[] fenotipos = new int[poblacion.length];
		for (int i = 0; i < poblacion.length; i++) {
			fenotipos[i] = Basicas.deBinarioADecimal(poblacion[i]);
			System.out.printf("El individuo %d (%s) vale %d%n", i + 1, i < 2 ? "padre" : "hijo", fenotipos[i]);
		}
		return fenotipos;
	}

    
    // genera un individuo (si el valor es < 0.5 será 0 y si es >= será 1)
	public static int[] generaIndividuo(double[] aleatorios, int individuo) {
		double valores[] = extraeValores(aleatorios, 8);
		int genotipo[] = new int[8];
		for (int i = 0; i < valores.length; i++)
			genotipo[i] = valores[i] < 0.5 ? 0 : 1;
		muestraCromosoma(genotipo, individuo);
		return (genotipo);
	}

	// extrae valores del array de aleatorios
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

	public static void muestraCromosoma(int[] valores, int individuo) {
		System.out.printf("Cromosoma del individuo %d%n", individuo);
		System.out.println("-".repeat(57));
		System.out.print("|");
		for (int i = 0; i < valores.length; i++)
			System.out.printf("   %d  |", valores[i]);
		System.out.println("\n" + "-".repeat(57) + "\n");
	}
}
