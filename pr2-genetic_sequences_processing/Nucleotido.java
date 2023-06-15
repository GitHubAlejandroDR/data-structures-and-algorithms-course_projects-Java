package pr2EDA;


public class Nucleotido {
	
	//Un nucleotido es una de las siguientes letras = {A, C,G,T,U}
	private char base;
	
	public Nucleotido(char valor){
		//TODO: constructor
		base = valor;
	}
	
	//TODO: Implementar funciones para leer y modificar la letra del nuecleotido
	
	
	public char getBase() {
		return base;
	}

	public void setBase(char base) {
		this.base = base;
	}

	@Override
	public String toString(){
		//TODO: devuelve la representacion del nucleotido como una cadena de caracteres
		return Character.toString(base);
	}
		
}
