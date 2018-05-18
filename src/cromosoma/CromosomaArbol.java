package cromosoma;

import java.util.Iterator;
import java.util.Random;

/**
 * cromosoma por mutacion de recrear un sub arbol
 * @author Carlos
 */
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
		Iterator<Arbol> it = this._arbol.iterator();
                Random r = new Random();
                boolean encontrado = false;
                Arbol aux;
                while(it.hasNext() && !encontrado){
                    aux = it.next();
                    double a=r.nextDouble();
                    if (!(aux.get_valor().equalsIgnoreCase("a") || aux.get_valor().equalsIgnoreCase("b"))&& a<prob){
                        aux.recrear(_completa);
                            
                        encontrado = true;
                    }
                }
                
                return encontrado;
	}

}
