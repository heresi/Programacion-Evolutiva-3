package poblacion;
import java.util.Random;

//copiar metodos de seleccion de anteriores.
import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;
import datos.ConjuntoCasos;

public abstract class Poblacion {
	protected Cromosoma[] _pob;
	protected Cromosoma[] _mejores;
	protected int _num_mejores;
	protected double[] _puntuacion;
	protected double _suma_aptitud;
	protected double[] _punt_acum;
	protected int _tam;
	protected String _mut;
	protected boolean _hayElitismo;
	protected boolean _completa;
	protected boolean _creciente;
	protected ConjuntoCasos _prueba;
	
	
	public Poblacion(int tam, String mut, double  elitismo, String ini, int hMax, boolean creciente) {
		_hayElitismo = elitismo > 0;
		_prueba = new ConjuntoCasos();
		_tam = tam;
		_creciente = creciente;
		_mut = mut;
		_num_mejores = (elitismo > 0.0)?(int) Math.ceil(_tam*elitismo): 1;
		_pob = new Cromosoma[_tam];
		_mejores = new Cromosoma[_num_mejores];
		_puntuacion = new double[_tam];
		_punt_acum = new double[_tam];
		_completa = ini.equalsIgnoreCase("COMPLETA");
		double punt = 0;
		double apt;
		int indice_mejores = 0;
		int altura = hMax;
		//generar poblaciones
		for(int i = 0; i < _tam;i++) {
			if(ini.equalsIgnoreCase("R&H")) {
                            if(i!=0 && 0==i%(_tam/hMax)){
                                altura--;
                            }
                            if(i!=0 && 0==i%((_tam/hMax)/2)){
                                _completa = !_completa;
                            }
				//a completar
			}
			_pob[i] = FactoriaCromosoma.getCromosoma(_mut, altura, _completa, _creciente);
			apt = _pob[i].aptitud();
			if(apt > punt || i < _num_mejores) {
				_mejores[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i]);
				if(i+1 < _num_mejores)
					indice_mejores++;
				else {
					indice_mejores = buscarMenor(_num_mejores, _mejores);
					punt = _mejores[indice_mejores].aptitud();	
				}
			}
		}
	}

	public abstract void seleccion();
	
	public void cruce(double prob) {
		int aux = 0;
		boolean eleccion = false;
		Random r = new Random();
		for(int i = 0; i < _tam; i++) 
			if(r.nextDouble() < prob) {
				if(eleccion) {
					_pob[i].cruce(_pob[aux]);
					eleccion = false;
				}
				else {
					aux = i;
					eleccion = true;
				}
			}		
	}
	
	public void mutacion(double prob) {
		for(int i = 0; i < _tam; i++)
			_pob[i].mutacion(prob);
	}
	
	public void seleccionElitismo() {
		if(_hayElitismo) {
			for(int i = 0; i < _num_mejores; i++) {
				_pob[buscarMenor(_tam, _pob)] = FactoriaCromosoma.getCromosomaCopia(_mejores[i]);
			}
		}
		_suma_aptitud = 0;
		int indice_mejores = buscarMenor(_num_mejores, _mejores);
		double puntu = _mejores[indice_mejores].aptitud();
		for(int i = 0; i < _tam; i++) {
			if(_pob[i].aptitud() > puntu) {
				_mejores[indice_mejores] = FactoriaCromosoma.getCromosomaCopia(_pob[i]);
				indice_mejores = buscarMenor(_num_mejores, _mejores);
				puntu = _mejores[indice_mejores].aptitud();
			}
			_puntuacion[i] = _pob[i].aptitud();
			_suma_aptitud += _puntuacion[i];
			_punt_acum[i] = _suma_aptitud;
		}
	}
	
	public String getMejorFen() {
		return _pob[buscarMejor(_tam,_pob)].fenotipo();
	}
	public double getMejorApt() {
		return _pob[buscarMejor(_tam,_pob)].aptitud();
	}
	
	private int buscarMejor(int tam, Cromosoma[] array) {
		int mejor_act = 0;
		for(int i = 1; i < tam;i++)
			if(array[i].aptitud() > array[mejor_act].aptitud())
				mejor_act = i;
		return mejor_act;
	}
	
	public int buscarMenor(int tam, Cromosoma array[]) {
		int menor_act = 0;
		for(int i = 1; i < tam;i++)
			if(array[i].aptitud() < array[menor_act].aptitud())
				menor_act = i;
		return menor_act;
	}
	
	public double media() {
		// TODO Auto-generated method stub
		return _suma_aptitud/_tam;
	}
	
}
