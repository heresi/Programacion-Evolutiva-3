package poblacion;

public class FactoriaPoblaciones {
	public static Poblacion getPoblacion(int tam, String sel, String mut, double elitismo, double trunc, String ini,
			int hMax, boolean creciente) {
		switch(sel) {
		case("RULETA"):
			return new PoblacionRuleta(tam, mut, elitismo, ini,hMax, creciente);
		case("UNIVERSAL"):
			return new PoblacionUniversalEstocastica(tam, mut, elitismo, ini,hMax, creciente);
		case("TRUNCAMIENTO"):
			return new PoblacionTruncamiento(tam, mut, elitismo, trunc, ini,hMax, creciente);
		case("TORNEO DETERMINISTA"):
			return new PoblacionTorneoDeterministico(tam, mut, elitismo, ini,hMax, creciente);    
        case("TORNEO PROBABILISTA"):
            return new PoblacionTorneoProbabilistico(tam, mut, elitismo, trunc, ini,hMax, creciente);
		default:
			return new PoblacionRuleta(tam, mut, elitismo, ini,hMax, creciente);
		}
	}
}
