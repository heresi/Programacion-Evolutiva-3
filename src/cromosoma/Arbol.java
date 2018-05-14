package cromosoma;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Arbol implements Iterable<Arbol>{
	private Arbol _hi;
	private Arbol _hd;
	private Arbol _padre;
	private int _profundidad;
	private String _valor;
	private boolean _esRaiz;
	private boolean _esHoja;
	private int _numNodos;
	private boolean _esHi;
	private int _posSimbolo;
	
        public String get_valor(){
            return _valor;
        }
	//constructor vacio inicial
	public Arbol() {
		_hi = null;
		_hd = null;
		_padre = null;
		_profundidad = 0;
		_valor = null;
		_esRaiz = true;
		_esHoja = true;
		_numNodos = 0;
		_esHi = false;
		_posSimbolo = -1;
	}
	
	//constructor para hijo vacio
	public Arbol(Arbol p,int prof,boolean esHi) {
		_hi = null;
		_hd = null;
		_padre = p;
		_profundidad = prof;
		_valor = null;
		_esRaiz = false;
		_esHoja = true;
		_numNodos = 0;
		_esHi = esHi;
		_posSimbolo = -1;
	}
	
        /**
         * por copia
         * @param a - arbol a copiar
         * @param padre - el padre, llamar con null, en la llamada inicial
         */
        public Arbol(Arbol a, Arbol padre){
            this._esHi = a._esHi;
            this._esHoja = a._esHoja;
            this._esRaiz = a._esRaiz;            
            this._numNodos = a._numNodos;
            this._padre = padre;
            this._posSimbolo = a._posSimbolo;
            this._profundidad = a._profundidad;
            this._valor = a._valor;
            this._hd = a._hd==null?null:new Arbol(a._hd,this);
            this._hi = a._hi==null?null:new Arbol(a._hi,this);
            
        }
	//genera aleatorio
	public void aleatorioCompleta(int prof) {
		Random r = new Random();
		if (prof == 0) {
			_posSimbolo = r.nextInt(Consts._cjtoTerms.length);
			_valor = Consts._cjtoTerms[_posSimbolo];
			_numNodos = 1;
		}
		else {
			_posSimbolo = r.nextInt(Consts._cjtoFuns.length);
			_valor = Consts._cjtoFuns[_posSimbolo];
			_esHoja = false;
			if(_posSimbolo < 4) {
				_hi = new Arbol(this,_profundidad+1,true);
				_hd = new Arbol(this,_profundidad+1,false);
				_hi.aleatorioCompleta(prof-1);
				_hd.aleatorioCompleta(prof-1);
				_numNodos = 1 + _hi._numNodos +_hd._numNodos;
			}
			else {
				_hi = new Arbol(this,prof-1,true);
				_hi.aleatorioCompleta(prof-1);
				_numNodos = 1 + _hi._numNodos;
			}
		}
	}
	
	public void aleatorioCreciente(int prof) {
		Random r = new Random();
		_posSimbolo = r.nextInt(Consts._cjtoFuns.length + Consts._cjtoTerms.length);
		if(_posSimbolo <Consts._cjtoTerms.length) {
			_valor = Consts._cjtoTerms[_posSimbolo];
			_numNodos = 1;
		}
		else {
			_posSimbolo -= Consts._cjtoTerms.length;
			_valor = Consts._cjtoFuns[_posSimbolo];
			_esHoja = false;
			if(_posSimbolo < 4) {
				_hi = new Arbol(this,_profundidad+1,true);
				_hd = new Arbol(this,_profundidad+1,false);
				_hi.aleatorioCompleta(prof-1);
				_hd.aleatorioCompleta(prof-1);
				_numNodos = 1 + _hi._numNodos +_hd._numNodos;
			}
			else {
				_hi = new Arbol(this,prof-1,true);
				_hi.aleatorioCompleta(prof-1);
				_numNodos = 1 + _hi._numNodos;
			}
		}
	}

	@Override
	public Iterator<Arbol> iterator() {
		Iterator<Arbol> it = new Iterator<Arbol>() {
			private Arbol next = menor();
			@Override
            public boolean hasNext(){
                return next != null;
            }
			@Override
            public Arbol next(){
                if(!hasNext()) throw new NoSuchElementException();
                Arbol r = next;

                // If you can walk right, walk right, then fully left.
                // otherwise, walk up until you come from left.
                if(next._hd != null) {
                    next = next._hd;
                    while (next._hi != null)
                        next = next._hi;
                    return r;
                }
                while(true) {
                    if(next._padre == null) {
                        next = null;
                        return r;
                    }
                    if(next._esHi) {
                       next = next._padre;
                       return r;
                    }
                    next = next._padre;
                }
             }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
	}
	private Arbol menor() {
		Arbol izq = this;
		while(izq._hi != null)izq = izq._hi;
		return izq;
	}
}
