package pr2EDA;


public class Secuencia {
	/* TODO: La secuencia genetica tiene un tipo (ADN o ARN) y
	 * una lista de nucleotidos. Para la lista, usar la implementacion 
	 * dinamica LList vista en clase
	 */	
	
	
	
	
	private String formaGenetica;
	private LList <Nucleotido> listaBases;
	
	public Secuencia(){
		/* TODO:Constructor
		 * Crea una secuencia vacia y sin especificar el tipo */
		listaBases = new LList<Nucleotido>();
		formaGenetica = null;
	}
	public Secuencia(String tipo, String cadena){
		/* TODO: Constructor 
		 * Crea una secuencia del tipo especificado y 
		 * con los nucleotidos que aparecen en la cadena */		
		formaGenetica = tipo;
		listaBases = new LList<Nucleotido>();
      	 for (int i=0;i<cadena.length();i++){
             Nucleotido nuevoNucleotido = new Nucleotido(cadena.charAt(i));
             listaBases.append(nuevoNucleotido);
      	 }
	}
	
	/* Las siguientes funciones son propuestas para ayudar 
	 * a implementar las instrucciones. El alumno puede definir las que estime oportunas.
	 */
		
	/*PARTE 1*/
	@Override
	public String toString() {		
		/* TODO: Devuelve una cadena caracteres que represeta la secuancia genetica. 
		 * El formato debe ser el siguiente : TIPO <espacio> NUCLEOTIDOS
		 * Ejemplo: ADN AAAGTCTGAC*/
		StringBuilder representacion =new StringBuilder();
    	representacion.append(formaGenetica);
    	representacion.append(" ");
    	for (listaBases.moveToStart();listaBases.currPos()<listaBases.length();listaBases.next()){
    		listaBases.append(listaBases.getValue());
    	}
    	return representacion.toString();
	}
	
	
	public List<Integer> findMotif(String motivo) {
		/* TODO: Busca en la secuencia todas las apariciones exactas del motivo 
		 * y devuelve una lista con las posiciones de inicio de las apariciones. 
		 * Si no encuentra el motivo devuelve una lista vacia.
		 */
		List<Integer> lista =new LList<Integer>();
		int longitud=motivo.length();

        for (int i=0;i<listaBases.length();i++){
                String subSecuencia = "";
	        	 for(int j=i; j< longitud+i; ++j) {
	        		subSecuencia += getNucleotido(j);
	        	 }
           if (subSecuencia.compareTo(motivo)==0){ 
               lista.append(i);
           }
        }
        return lista;
	}
	
	public Nucleotido getNucleotido(int p) {
		
		/*TODO: Devuelve el nucleotido que se encuentra en la 
		 * posicion p de la secuencia. Si p es una posicion fuera 
		 * de la lisa devuelve null
		 */
		listaBases.moveToPos (p);
        return listaBases.getValue();
		
	}
	
	
	
	public String getFormaGenetica() {
		return formaGenetica;
	}
	public void setFormaGenetica(String formaGenetica) {
		this.formaGenetica = formaGenetica;
	}
	public String getListaBases() {
		String sec="";
	      for (listaBases.moveToStart();listaBases.currPos()<listaBases.length();listaBases.next()){
		    		sec+=listaBases.getValue();
		    	}
	      return sec;
	}
	public void setListaBases(LList<Nucleotido> listaBases) {
		this.listaBases = listaBases;
	}
	
	public void removeSecuencia() {
		formaGenetica="";
		listaBases.clear();
   }
	
	/*____________PARTE 2____________*/
	public void clip(int start, int end){
		/* TODO: Reemplazar la secuencia por la sub-secuencia que se obtiene con
		 * todas las letras desde la letra en la posicion start hasta la letra en posicion end. 
		 * Si end es un valor fuera de la lista, se considera hasta el final de la secuencia.
		 */
	}
	
	public void transcribe() {
		/*TODO: Convierte una secuencia de ADN en otra de ARN. Para ello se 
		 * cambian todas las T por U, y despues se cambian todas las letras por su complementarias 
		 * Es decir las A se transforman en U y viceversa, las C se transforman en G y viceversa. 
		 * Una vez terminada la operacion se invierte la secuencia. 
		 * Ejemplo: la secuencia TGAC primero se transforma en UGAC, luego en ACUG 
		 * y finalmente se invierte dando como resultado GUCA.
		 */
		
	}
	
	public String subsecuencia(int start, int end){
		/* TODO:  Devuelve una cadena de caracteres con los nucleotidos de la secuencia
		 * desde la posicion start a end, es decir devuelve una subsecuencia.
		 */
		return null;
	}
	public void reemplazar(String sub, int pos) {
		/* TODO: Reemplaza los nucleotidos de la secuencia 
		 * desde la posicion pos en adelante por los de la subsecuencia sub.
		 */
		
	}
	
}
