package cromosoma;

public class CromosomaPermutacion extends Cromosoma{

	public CromosomaPermutacion(int hMaxima, boolean completa) {
		super(hMaxima, completa);
	}

	public CromosomaPermutacion(Cromosoma c) {
		super(c);
	}

	@Override
	public boolean mutacion(double prob) {
		return false;
	}

}
