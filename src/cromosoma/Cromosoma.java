package cromosoma;


public abstract class Cromosoma {
	protected double _aptitud;
	protected Arbol _arbol;
	protected int _hMaxima;
	protected String _fenotipo;
	protected String _tipoCrom;
	
	public Cromosoma(int hMaxima, boolean completa) {
		_hMaxima = hMaxima;
		_arbol = new Arbol();
		if(completa)
			_arbol.aleatorioCompleta(_hMaxima);
		else
			_arbol.aleatorioCreciente(_hMaxima);
	}
	
	public Cromosoma(Cromosoma c) {
		//copia de arbol
		this._aptitud = c._aptitud;
		this._fenotipo = c._fenotipo;
		this._hMaxima = c._hMaxima;
		this._tipoCrom = c._tipoCrom;
	}
	
	public double aptitud() {
		return 0.0;
	}
	public void fenotipo() {
		_fenotipo = "";
	}
	
	public abstract boolean mutacion(double prob);
}
