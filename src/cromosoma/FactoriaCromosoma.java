package cromosoma;

public class FactoriaCromosoma {
        
	public static Cromosoma getCromosoma(String mutacion,int _hMax, boolean iniCompleta) {
		switch(mutacion) {
		case("TERM_SIMPLE"):
			return new CromosomaTermSimple(_hMax, iniCompleta);
		case("FUN_SIMPLE"):
			return new CromosomaFunSimple(_hMax, iniCompleta);// se queja por que modifique este en concreto por que necesitaba saber si el arbol se construia completo o no, 
                        //modifica el cromosoma o esta funcion segun creas
		case("ARBOL"):
			return new CromosomaArbol(_hMax, iniCompleta);
        case("PERMUTACION"):
            return new CromosomaPermutacion(_hMax, iniCompleta);
		default:
			return new CromosomaTermSimple(_hMax, iniCompleta);
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
