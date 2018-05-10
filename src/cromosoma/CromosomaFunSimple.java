package cromosoma;

public class CromosomaFunSimple extends Cromosoma{

	public CromosomaFunSimple(int hMaxima, boolean completa) {
		super(hMaxima, completa);
		this._tipoCrom = "FUN_SIMPLE";
	}
	
	public CromosomaFunSimple(Cromosoma c) {
		super(c);
	}

	@Override
	public boolean mutacion(double prob) {
		// TODO Auto-generated method stub
		return false;
	}

}
