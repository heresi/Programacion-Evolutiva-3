package cromosoma;

public class CromosomaTermSimple extends Cromosoma{

	public CromosomaTermSimple(int hMaxima, boolean completa) {
		super(hMaxima, completa);
		_tipoCrom = "TERM_SIMPLE";
	}

	public CromosomaTermSimple(Cromosoma c) {
		super(c);
	}
	@Override
	public boolean mutacion(double prob) {
		return false;
	}
	

}
