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
	
        /**
         * geter del valor del nodo
         * @return el string del nodo raiz actual
         */
        public String get_valor(){
            return _valor;
        }
        /**
         * seter del valor del nodo
         * @param v - el nuevo string
         */
        public void set_valor(String v){
            _valor = v;
        }
        /**
         * metodo para eliminar el hijo derecho del arbol actual, usado para cuando se cambia el valor de un nodo a "log" o "sqrt"
         */
        public void podar_arbol(){
            _hd=null;
        }
        
        /**
         * metodo para obtener un hijo del arbol
         * @param izquierdo - si es true devuelve el hijo izquierdo si no el derecho
         * @return el hijo derecho o izquierdo del arbol en funcion del booleano. puede ser null
         */
        public Arbol get_hijo(boolean izquierdo){
            return izquierdo?_hi:_hd;
        }
        /**
         * metodo para cambiar el hijo izquierdo del derecho
         */
        public void permutar(){
            Arbol aux = _hi;
            _hi = _hd;
            _hd = aux;
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
            this._profundidad = padre!=null?padre._profundidad+1:0;
            this._valor = a._valor;
            this._hd = a._hd==null?null:new Arbol(a._hd,this);
            this._hi = a._hi==null?null:new Arbol(a._hi,this);
            
        }
        
       
        /**
         * metodo que crea un nuevo hijo derecho desde este nodo, usado para cuando el valor del nodo deja de ser "log" o "sqrt"
         * @param completa - para saber si hay que crear el arbol completo o no, creo que se puede quitar o hacerlo simpre random segun me has escrito
         */
        public void crear_hd(boolean completa){
            _hd = new Arbol(this,this._profundidad+1,false);
            Random r = new Random();
            if(completa)
                _hd.aleatorioCompleta(r.nextInt(Math.max(0, 5-_profundidad)));
            else
                _hd.aleatorioCreciente(r.nextInt(Math.max(0, 5-_profundidad)));
        }
        
        /**
         * crea un arbol nuevo desde este nodo, sin cambiar este, usado en una mutacion
         * @param completa - para saber si hay que crear el arbol completo o no, creo que se puede quitar o hacerlo simpre random segun me has escrito
         */
        public void recrear(boolean completa){
            _hd = new Arbol(this,this._profundidad+1,false);
            _hi = new Arbol(this,this._profundidad+1,false);
            Random r = new Random();
            if(completa){
                int a = r.nextInt(Math.max(0, 5-_profundidad));
                _hd.aleatorioCompleta(r.nextInt(Math.max(0, a)));
                _hi.aleatorioCompleta(r.nextInt(Math.max(0, a)));
            }
                
            else{
                _hd.aleatorioCreciente(r.nextInt(Math.max(0, 5-_profundidad)));
                _hi.aleatorioCompleta(r.nextInt(Math.max(0, 5-_profundidad)));
            }
                
            
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
	
        /**
         * metodo que elije y cruza dos arboles
         * @param a - el otro arbol a cruzar, se modifiacar este arbol segun el cruce
         * @return el nuevo arbol tras el cruce
         */
        public Arbol cruce (Arbol a){
            Iterator<Arbol> it1 = this.iterator();
            Iterator<Arbol> it2 = a.iterator();
            
            Arbol new1 = null,new2 = null,aux;
            
            
            
            Random r = new Random();
            boolean corte = false;
            
            while(it1.hasNext() && !corte){
                new1 = it1.next();
                if((new1._valor.equalsIgnoreCase("a") || new1._valor.equalsIgnoreCase("b")) && r.nextDouble()<0.3)
                    corte = true;
                else if(new1._padre!=null)
                    corte=r.nextDouble()>0.8;
                    
            }
            if (new1._padre==null){
                new1=new1._hi;
            }
            
            corte = false;
            while(it2.hasNext() && !corte){
                new2 = it2.next();
                if((new2._valor.equalsIgnoreCase("a") || new2._valor.equalsIgnoreCase("b")) && r.nextDouble()<0.3)
                    corte = true;
                else if(new2._padre!=null)
                    corte=r.nextDouble()>0.8;
                    
            }
            if (new2._padre==null){
                new2=new2._hi;
            }
            
            aux = new1;
            if(new1._esHi){
                new1._padre._hi = new Arbol(new2,new1._padre);
                new1._esHi=true;
            }else{
                new1._padre._hd = new Arbol(new2,new1._padre);
                new1._esHi=false;
            }
                
            if(new2._esHi){
                new2._padre._hi = new Arbol(aux,new2._padre);
                new2._esHi=true;
            }else{
                new2._padre._hd = new Arbol(aux,new2._padre);
                new2._esHi=false;
            }
                
            
            while(new1._padre!=null)
                new1 = new1._padre;
            while(new2._padre!=null)
                new2 = new2._padre;
            
            a = new2;   
            
            return new1;
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
        public void print(int p){
            
            
            if(_hd != null)
                this._hd.print(p+1);
            for(int i =0;i<p;i++)
                System.out.print("      ");
            System.out.println(this._valor);
            if(_hi!=null)
                this._hi.print(p+1);
            

        }
}
