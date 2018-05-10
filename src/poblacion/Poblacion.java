package poblacion;
//copiar metodos de seleccion de anteriores.
import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;
import datos.ConjuntoCasos;

public class Poblacion {
	protected Cromosoma[] _pob;
	protected Cromosoma[] _mejores;
	protected int _num_mejores;
	protected double[] _puntuacion;
	protected double _suma_aptitud;
	protected double[] _punt_acum;
	protected int _tam;
	protected String _sel;
	protected String _cruce;
	protected String _mut;
	protected boolean _hayElitismo;
	protected boolean _completa;
	protected ConjuntoCasos _prueba;
	
	
	public Poblacion(int tam, String sel, String cruce, String mut, double  elitismo, double trunc, String ini, int hMax) {
		_hayElitismo = elitismo > 0;
		_prueba = new ConjuntoCasos();
		_tam = tam;
		_sel = sel;
		_cruce = cruce;
		_mut = mut;
		_num_mejores = (elitismo > 0.0)?(int) Math.ceil(_tam*elitismo): 1;
		_pob = new Cromosoma[_tam];
		_mejores = new Cromosoma[_num_mejores];
		_puntuacion = new double[_tam];
		_punt_acum = new double[_tam];
		_completa = ini.equals("COMPLETA");
		double punt = 0;
		double apt;
		int indice_mejores = 0;
		int altura = hMax;
		//generar poblaciones
		for(int i = 0; i < _tam;i++) {
			if(ini.equals("R&H")) {
				//a completar
			}
			_pob[i] = FactoriaCromosoma.getCromosoma(_mut, altura, _completa);
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
	private int buscarMenor(int tam, Cromosoma array[]) {
		int menor_act = 0;
		for(int i = 1; i < tam;i++)
			if(array[i].aptitud() < array[menor_act].aptitud())
				menor_act = i;
		return menor_act;
	}
}
