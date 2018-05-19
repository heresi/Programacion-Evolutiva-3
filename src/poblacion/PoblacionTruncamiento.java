package poblacion;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

public class PoblacionTruncamiento extends Poblacion{

	private double _trunc;
	public PoblacionTruncamiento(int tam, String mut, double elitismo, double trunc,
			String ini, int hMax, boolean creciente) {
		super(tam, mut, elitismo, ini, hMax, creciente);
		// TODO Auto-generated constructor stub
		_trunc = trunc;
	}

	@Override
	public void seleccion() {
		// TODO Auto-generated method stub
		int pobDistintas = (int) (_trunc*_tam);
        double puntu = 0;
        int indice_mejores = 0;
        Cromosoma[] padres = new Cromosoma[pobDistintas];
        for(int i = 0; i<_tam;i++){
            if(_pob[i].aptitud() > puntu || i < pobDistintas) {
            		padres[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i]);
		if(i+1 < pobDistintas)
                    indice_mejores++;
		else {
                    indice_mejores = buscarMenor(pobDistintas, padres);
                    puntu = padres[indice_mejores].aptitud();	
		}
            }
        }
        int j=0;
        for(int i = 0; i<_tam;i++){
            _pob[i] = FactoriaCromosoma.getCromosomaCopia(padres[j]);
            j = (j+1)==pobDistintas?0:j+1;
        }
	}

}
