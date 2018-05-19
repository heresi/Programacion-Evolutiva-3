package poblacion;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

public class PoblacionTorneoProbabilistico extends Poblacion{

	private double _prob;
	public PoblacionTorneoProbabilistico(int tam, String mut, double elitismo, double trunc, String ini, int hMax, boolean creciente) {
		super(tam, mut, elitismo, ini, hMax, creciente);
		_prob = trunc;
	}

	@Override
	public void seleccion() {
		// TODO Auto-generated method stub
		Cromosoma[] padres = new Cromosoma[_tam];
        Cromosoma mejor;
        Random r = new Random();
        int aux;
        double ran;
        for(int i =0; i<_tam; i++){
            do{
                aux = r.nextInt(_tam);
            }while(aux!=i);
            ran = r.nextDouble();
            mejor = (ran<=_prob)?_pob[i]:_pob[aux];
            padres[i] = FactoriaCromosoma.getCromosomaCopia(mejor);
        }
        System.arraycopy(padres, 0, _pob, 0, _tam);
	}

}
