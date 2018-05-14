package cromosoma;

import java.util.Iterator;
import java.util.StringTokenizer;


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
                this._arbol = new Arbol(c._arbol,null);
		//copia de arbol
		this._aptitud = c._aptitud;
		this._fenotipo = c._fenotipo;
		this._hMaxima = c._hMaxima;
		this._tipoCrom = c._tipoCrom;
	}
	
	public double aptitud() {
            _aptitud = 0.0;
            StringTokenizer st = new StringTokenizer(_fenotipo);
            _aptitud = Double.parseDouble(st.nextToken());//presupongo que A o B son numeros, cambiarlo
            
            while(st.hasMoreTokens()){
                String token = st.nextToken();
                if (token.equalsIgnoreCase("+"))
                    _aptitud += Double.parseDouble(st.nextToken());
                if (token.equalsIgnoreCase("-"))
                    _aptitud -= Double.parseDouble(st.nextToken());
                if (token.equalsIgnoreCase("+"))
                    _aptitud *= Double.parseDouble(st.nextToken());
                if (token.equalsIgnoreCase("-"))
                    _aptitud /= Double.parseDouble(st.nextToken());
                if (token.equalsIgnoreCase("sqrt"))
                    _aptitud = Math.sqrt(_aptitud);
                if (token.equalsIgnoreCase("log"))
                    _aptitud = Math.log(_aptitud);//base e? o cambiar a base 10?
            }
            return _aptitud;
	}
        
	public void fenotipo() {
            _fenotipo = "";
            Iterator<Arbol> it = _arbol.iterator();
            while(it.hasNext()){
                _fenotipo += it.next().get_valor()+ " ";
            }
            
	}
	
	public abstract boolean mutacion(double prob);
}
