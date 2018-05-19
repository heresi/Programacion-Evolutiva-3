package poblacion;

public class AGenetico {
	private Poblacion _poblacion;
	private int _num_max_gen;
	private int _gen_actual;
	private double _prob_cruce;
	private double _prob_mut;
	private double _mejorApt;
	private String _mejorFen;
	private double _elitismo;
	private int _tam;
	private double _trunc;
	private String _seleccion;
	private String _mutacion;
	private String _ini;
	private int _hMax;
	private boolean _creciente;
	
	public AGenetico(int max_gen, double cruce, double mut, double elitismo, int tam, String seleccion, double trunc, String choice_mut, String ini, int hMax, boolean creciente)  {
		_num_max_gen = max_gen;
		_gen_actual = 0;
		_prob_cruce = cruce;
		_prob_mut = mut;
		_trunc = trunc;
		_tam = tam;
		_mutacion = choice_mut;
		_seleccion = seleccion;
		_elitismo = elitismo;
		_ini = ini;
		_hMax = hMax;
		_creciente = creciente;
		_poblacion = FactoriaPoblaciones.getPoblacion(tam,seleccion, choice_mut, elitismo, trunc, ini,hMax, creciente);
	}
	
	public AGenetico() {
		_gen_actual = 0;
	}

	public void ejecutaAG(Solucion sol) {
		_mejorApt = _poblacion.getMejorApt();
		_mejorFen = _poblacion.getMejorFen();
		sol.set_fenotipo(_mejorFen);
		while(_gen_actual < _num_max_gen) {
			System.out.println(_mejorFen+" :"+_mejorApt +" -> "+_poblacion.getMejorFen() + "  :" + _poblacion.getMejorApt());
			_poblacion.seleccion();
			_poblacion.cruce(_prob_cruce);
			_poblacion.mutacion(_prob_mut);
			_poblacion.seleccionElitismo();
			if(_mejorApt < _poblacion.getMejorApt()) { 
				_mejorApt = _poblacion.getMejorApt();
				_mejorFen = _poblacion.getMejorFen();
				sol.set_fenotipo(_mejorFen);
			}
			sol.add(_mejorApt, _poblacion.getMejorApt(), _poblacion.media());
			_gen_actual++;
			System.out.print(".");
			if(_gen_actual %40 == 0)System.out.print("\n");
		}
		System.out.println(_mejorFen+" :"+_mejorApt +" -> "+_poblacion.getMejorFen() + "  :" + _poblacion.getMejorApt());
	}
	
	public void Inicialia() {
		this._poblacion =FactoriaPoblaciones.getPoblacion(_tam, _seleccion, _mutacion, _elitismo, _trunc, _ini, _hMax, _creciente);
	}
	
	public Poblacion get_poblacion() {
		return _poblacion;
	}

	public void set_poblacion(Poblacion _poblacion) {
		this._poblacion = _poblacion;
	}

	public int get_num_max_gen() {
		return _num_max_gen;
	}

	public void set_num_max_gen(int _num_max_gen) {
		this._num_max_gen = _num_max_gen;
	}

	public int get_gen_actual() {
		return _gen_actual;
	}

	public void set_gen_actual(int _gen_actual) {
		this._gen_actual = _gen_actual;
	}

	public double get_prob_cruce() {
		return _prob_cruce;
	}

	public void set_prob_cruce(double _prob_cruce) {
		this._prob_cruce = _prob_cruce;
	}

	public double get_prob_mut() {
		return _prob_mut;
	}

	public void set_prob_mut(double _prob_mut) {
		this._prob_mut = _prob_mut;
	}

	public double get_mejorApt() {
		return _mejorApt;
	}

	public void set_mejorApt(double _mejorApt) {
		this._mejorApt = _mejorApt;
	}

	public double get_elitismo() {
		return _elitismo;
	}

	public void set_elitismo(double _elitismo) {
		this._elitismo = _elitismo;
	}

	public int get_tam() {
		return _tam;
	}

	public void set_tam(int _tam) {
		this._tam = _tam;
	}

	public double get_trunc() {
		return _trunc;
	}

	public void set_trunc(double _trunc) {
		this._trunc = _trunc;
	}

	public String get_seleccion() {
		return _seleccion;
	}

	public void set_seleccion(String _seleccion) {
		this._seleccion = _seleccion;
	}

	public String get_mutacion() {
		return _mutacion;
	}

	public void set_mutacion(String _mutacion) {
		this._mutacion = _mutacion;
	}

	public String get_ini() {
		return _ini;
	}

	public void set_ini(String _ini) {
		this._ini = _ini;
	}

	public int get_hMax() {
		return _hMax;
	}

	public void set_hMax(int _hMax) {
		this._hMax = _hMax;
	}

	public boolean is_creciente() {
		return _creciente;
	}

	public void set_creciente(boolean _creciente) {
		this._creciente = _creciente;
	}
}
