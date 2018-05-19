package cromosoma;

import datos.ConjuntoCasos;

public class FactoriaCromosoma {
        
	public static Cromosoma getCromosoma(String mutacion,int _hMax, boolean iniCompleta, boolean creciente, ConjuntoCasos casos) {
		switch(mutacion) {
		case("TERM_SIMPLE"):
			return new CromosomaTermSimple(casos, _hMax, iniCompleta);
		case("FUN_SIMPLE"):
			return new CromosomaFunSimple(casos, _hMax, iniCompleta, creciente);
		case("ARBOL"):
			return new CromosomaArbol(casos, _hMax, iniCompleta);
        case("PERMUTACION"):
            return new CromosomaPermutacion(casos,_hMax, iniCompleta);
		default:
			return new CromosomaTermSimple(casos, _hMax, iniCompleta);
		}
	}
	public static Cromosoma getCromosomaCopia(Cromosoma c) {
        String choice = c._tipoCrom;
		switch(choice) {
		case("TERM_SIMPLE"):
			return new CromosomaTermSimple(c);
		case("FUN_SIMPLE"):
			return new CromosomaFunSimple(c);
		case("ARBOL"):
			return new CromosomaArbol(c);
        case("PERMUTACION"):
            return new CromosomaPermutacion(c);
		default:
			return new CromosomaTermSimple(c);
		}
	}
}
