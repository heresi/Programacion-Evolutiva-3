package cromosoma;

import java.util.Iterator;
import java.util.StringTokenizer;


public abstract class Cromosoma {
	protected double _aptitud;
	protected Arbol _arbol;
	protected int _hMaxima;
	protected String _fenotipo;
	protected String _tipoCrom;
	protected boolean _completa;
        
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
	public void print(){
            _arbol.print(0);
        }
        public void cruce (Cromosoma c){
            _arbol = _arbol.cruce(c._arbol);
        }
        
	public double aptitud() {
            _aptitud = aptitud(_arbol);
            return _aptitud;
	}
        private double aptitud(Arbol a){
            double aux = 0.0;
            if (a.get_valor().equalsIgnoreCase("log"))
                aux = Math.log(aptitud(a.get_hijo(true))); 
            if (a.get_valor().equalsIgnoreCase("sqrt"))
                aux = Math.sqrt(aptitud(a.get_hijo(true)));
            if (a.get_valor().equalsIgnoreCase("+"))
                aux=aptitud(a.get_hijo(true))+aptitud(a.get_hijo(false));
            if (a.get_valor().equalsIgnoreCase("-"))
                aux=aptitud(a.get_hijo(true))-aptitud(a.get_hijo(false));
            if (a.get_valor().equalsIgnoreCase("*"))
                aux=aptitud(a.get_hijo(true))*aptitud(a.get_hijo(false));
            if (a.get_valor().equalsIgnoreCase("/"))
                aux=aptitud(a.get_hijo(true))/aptitud(a.get_hijo(false));
            if (a.get_valor().equalsIgnoreCase("A"))
                aux = 5;
            if (a.get_valor().equalsIgnoreCase("B"))
                aux = 5;
                
                
                
                return aux;
            
        }
        
        
        
        public String fenotipo() {
            _fenotipo = fenotipo(_arbol);
            return _fenotipo;
        }
	private String fenotipo(Arbol a) {
            String aux = "";
            if(a.get_valor().equalsIgnoreCase("log")){
                aux = "log( ";
                aux += a.get_hijo(true)!=null?fenotipo(a.get_hijo(true)):"";
                aux += ") ";
                return aux;
            }
                
            
            if (a.get_valor().equalsIgnoreCase("sqrt")){
                aux = "sqrt( ";
                aux += a.get_hijo(true)!=null?fenotipo(a.get_hijo(true)):"";
                aux += ") ";
                return aux;
            }

            aux += a.get_hijo(true)!=null?fenotipo(a.get_hijo(true)):"";
            aux += a.get_valor() + " ";
            aux += a.get_hijo(false)!=null?fenotipo(a.get_hijo(false)):"";
            
            return aux;
	}
	
	public abstract boolean mutacion(double prob);
}
