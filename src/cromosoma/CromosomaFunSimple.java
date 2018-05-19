package cromosoma;

import java.util.Iterator;
import java.util.Random;

import datos.ConjuntoCasos;

/**
 * cromosoma por funcion ssimple
 * @author Carlos
 */
public class CromosomaFunSimple extends Cromosoma{

        
	public CromosomaFunSimple(ConjuntoCasos casos, int hMaxima, boolean completa, boolean cre) {
		super(casos, hMaxima, completa);
		this._tipoCrom = "FUN_SIMPLE";
        this._completa = cre;//
	}
	
	public CromosomaFunSimple(Cromosoma c) {
		super(c);
                this._tipoCrom = "FUN_SIMPLE";
                this._completa = c._completa;
	}

	@Override
	public boolean mutacion(double prob) {
		Iterator<Arbol> it = this._arbol.iterator();
                Random r = new Random();
                boolean encontrado = false;
                Arbol aux;
                int elec;
                String viejo;
                while(it.hasNext() && !encontrado){
                    aux = it.next();
                    double a=r.nextDouble();
                    if (es_funcion(aux.get_valor())&& a<prob){
                        elec =r.nextInt(Consts._cjtoFuns.length);
                        viejo = aux.get_valor();                        
                        
                        aux.set_valor(Consts._cjtoFuns[elec]);
                        if(elec>=4)
                            aux.podar_arbol();
                        if((viejo.equalsIgnoreCase("log") || viejo.equalsIgnoreCase("sqrt")) && elec<4)
                            aux.crear_hd(_completa);
                            
                        encontrado = true;
                    }
                }
                
                return encontrado;
	}
        
        private boolean es_funcion(String s){
            boolean fun = false;
            int i=0;
            while(!fun && i<Consts._cjtoFuns.length){
                fun=s.equalsIgnoreCase(Consts._cjtoFuns[i]);
                i++;
            }
            
            return fun;
        }
}
