package datos;

public class ConjuntoCasos {
	private Caso[] _casos;
	private int _num_casos;
	
	public ConjuntoCasos() {
		_num_casos = 6;
		_casos[0] = new Caso(0.72,0.61);
		_casos[1] = new Caso(1.00,1.00);
		_casos[2] = new Caso(1.52,1.84);
		_casos[3] = new Caso(5.20,11.9);
		_casos[4] = new Caso(9.53,29.4);
		_casos[5] = new Caso(19.1,83.5);
	}

	public Caso[] getCasos() {
		return _casos;
	}

	public int get_num_casos() {
		return _num_casos;
	}
}
