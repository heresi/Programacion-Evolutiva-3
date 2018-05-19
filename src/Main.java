import poblacion.AGenetico;
import poblacion.Solucion;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AGenetico gen = new AGenetico(50, 0.7, 0.3, 0.0, 50, "RULETA", 0.1, "ARBOL", "COMP", 3, true);
		Solucion sol = new Solucion(50);
		gen.ejecutaAG(sol);
	}

}
