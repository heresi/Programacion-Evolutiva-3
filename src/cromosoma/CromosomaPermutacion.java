package cromosoma;

import java.util.Iterator;
import java.util.Random;

/**
 * cromosoma con mutacion por permutacion
 * @author Carlos
 */
public class CromosomaPermutacion extends Cromosoma{

	public CromosomaPermutacion(int hMaxima, boolean completa) {
		super(hMaxima, completa);
	}

	public CromosomaPermutacion(Cromosoma c) {
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
                    double i = r.nextDouble();
                    if (!(aux.get_valor().equalsIgnoreCase("a") || aux.get_valor().equalsIgnoreCase("b") || aux.get_valor().equalsIgnoreCase("log") || aux.get_valor().equalsIgnoreCase("sqrt"))&& i<prob){
                        aux.permutar();
                        encontrado = true;
                    }
                }
                return encontrado;
	}

}
