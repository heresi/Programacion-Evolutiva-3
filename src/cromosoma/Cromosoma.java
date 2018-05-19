package cromosoma;

import java.util.Iterator;
import java.util.StringTokenizer;

import datos.ConjuntoCasos;


public abstract class Cromosoma {
	protected double _aptitud;
	protected Arbol _arbol;
	protected int _hMaxima;
	protected String _fenotipo;
	protected String _tipoCrom;
	protected boolean _completa;
	protected ConjuntoCasos _casos;
        
	public Cromosoma(ConjuntoCasos casos, int hMaxima, boolean completa) {
		_hMaxima = hMaxima;
		_casos = casos;
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
		this._casos = c._casos;
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
            _aptitud = 0;
            for(int i = 0; i < _casos.get_num_casos(); i++) 
				_aptitud += Math.pow(aptitud(_arbol, _casos.getCasos()[i].getEntrada()) - _casos.getCasos()[i].getSalida(),2);
            return -_aptitud;
	}
        private double aptitud(Arbol a, double valor){
            double aux = 0.0;
            if (a.get_valor().equalsIgnoreCase("log"))
                aux = Math.log(aptitud(a.get_hijo(true),valor)); 
            if (a.get_valor().equalsIgnoreCase("sqrt"))
                aux = Math.sqrt(aptitud(a.get_hijo(true),valor));
            if (a.get_valor().equalsIgnoreCase("+"))
                aux=aptitud(a.get_hijo(true),valor)+aptitud(a.get_hijo(false),valor);
            if (a.get_valor().equalsIgnoreCase("-"))
                aux=aptitud(a.get_hijo(true),valor)-aptitud(a.get_hijo(false),valor);
            if (a.get_valor().equalsIgnoreCase("*"))
                aux=aptitud(a.get_hijo(true),valor)*aptitud(a.get_hijo(false),valor);
            if (a.get_valor().equalsIgnoreCase("/"))
                aux=aptitud(a.get_hijo(true),valor)/aptitud(a.get_hijo(false),valor);
            if (a.get_valor().equalsIgnoreCase("A"))
                aux = valor;
            if (a.get_valor().equalsIgnoreCase("B"))
                aux = valor;
                
            
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
