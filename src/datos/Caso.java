package datos;

public class Caso {
	double entrada;
	double salida;

	public Caso() {
		entrada = 0.72;
		salida = 0.61;
	}
	
	public Caso(double entrada, double salida) {
		this. entrada = entrada;
		this. salida = salida;
	}

	public double getEntrada() {
		return entrada;
	}

	public double getSalida() {
		return salida;
	}
}