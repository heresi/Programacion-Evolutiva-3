package cromosoma;

import java.util.Iterator;
import java.util.Random;

/**
 * cromosma con mutacion por terminal simple
 * @author Carlos
 */
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
		Iterator<Arbol> it = this._arbol.iterator();
                Random r = new Random();
                boolean encontrado = false;
                Arbol aux;
                while(it.hasNext() && !encontrado){
                    aux = it.next();
                    if ((aux.get_valor().equalsIgnoreCase("a") || aux.get_valor().equalsIgnoreCase("b"))&& r.nextDouble()<prob){
                        aux.set_valor(aux.get_valor().equalsIgnoreCase("a")?"B":"A");
                        encontrado = true;
                    }
                }
                
                return encontrado;
	}
	

}
