package cromosoma;

public class CromosomaArbol extends Cromosoma{
	
	
	
	public CromosomaArbol(int hMaxima, boolean completa) {
		super(hMaxima, completa);
		this._tipoCrom = "ARBOL";
	}

	public CromosomaArbol(Cromosoma c) {
		super(c);
	}

	@Override
	public boolean mutacion(double prob) {
		// TODO Auto-generated method stub
		return false;
	}

}
