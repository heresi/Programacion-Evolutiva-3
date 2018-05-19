package poblacion;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

public class PoblacionTorneoDeterministico extends Poblacion{

	public PoblacionTorneoDeterministico(int tam, String mut, double elitismo, String ini, int hMax, boolean creciente) {
		super(tam, mut, elitismo, ini, hMax, creciente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void seleccion() {
		// TODO Auto-generated method stub
		Cromosoma[] padres = new Cromosoma[_tam];
        Cromosoma mejor;
        Random r = new Random();
        int aux;
        for(int i =0; i<_tam; i++){
            do{
                aux = r.nextInt(_tam);
            }while(aux!=i);
            mejor = (_pob[i].aptitud()>_pob[aux].aptitud())?_pob[i]:_pob[aux];
            padres[i] = FactoriaCromosoma.getCromosomaCopia(mejor);
        }
        System.arraycopy(padres, 0, _pob, 0, _tam);
	}

}
