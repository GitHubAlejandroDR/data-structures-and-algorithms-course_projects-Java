package pr2EDA;

// Nombre: Alejandro Domínguez Recio
// DNI: 29614238J
// Curso: 2º
// Grado: Ingeniería de la Salud
// Grupo: 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


// Esqueleto basico para el ejercicio "Secuencias Geneticas con listas"
public class Gen {

	//TODO: Gen tiene un array de secuencias geneticas. 
	//      Las posiciones del array estaran inicialmente a null 
	private Secuencia[] arraySecuencias;
	
	
	public Gen(int size) {
		//TODO: crear array de secuencias del tamanio indicado
		arraySecuencias = new Secuencia[size];
		 for (int i=0; i<size; ++i)
	        {
	            arraySecuencias[i]=new Secuencia(); 
	        }
	}

	
	
	
	protected void procesar(String linea) {
		/* TODO: linea es una cadena que contiene
		 * instruccion parametro_1 parametro_2 ... parametro_n
		 * 1. Extraer de linea la instruccion a ejecutar y sus parametros
		 * 2. Ejecutar la instruccion correspondiente 
		 */
		/*PARTE 1: Solo se procesan las instrucciones:
		 * insert, remove, print, findmotif y profile
		 * Nota: al eleminar una secuencia del array, esa posicion se queda a null
		 */
		Scanner sc=new Scanner(linea);
        String comando=sc.next(); //lee la instruccion (insert,remove,print,...)
        /*PARTE 1: Solo se procesan las instrucciones:
        * insert, remove, print, findmotif y profile
        * Nota: al eleminar una secuencia del array, esa posicion se queda a null
        */
        //insert pos tipo secuencia
        int pos;
		switch (comando){
			case "insert":
				pos=sc.nextInt(); //lee pos
				String tipo=sc.next();	 //lee el tipo
				String secuencia = "";
				if (sc.hasNext()) secuencia=sc.next();
				
				if (pos>=0 && pos<arraySecuencias.length) arraySecuencias[pos] = new Secuencia(tipo, secuencia);
				else throw new RuntimeException("pos esa fuera de rango");
			break;
			
			case "remove":
				pos=sc.nextInt();  ///leo la pos

				//al eleminar una secuencia del array, esa posicion se queda a null
                if (pos>=0 && pos<arraySecuencias.length) arraySecuencias[pos] = null; //.borraSecuencia();
                else throw new RuntimeException("pos esa fuera de rango");
			break;
			case "print":
				if (sc.hasNext()) {
					pos=sc.nextInt();

					if (pos>=0 && pos<arraySecuencias.length) System.out.println(pos +" "+ arraySecuencias[pos].toString());
					else throw new RuntimeException("pos esa fuera de rango");
				}else{
					for (int indice=0; indice<arraySecuencias.length; indice++)
					{
						if (arraySecuencias[indice]!=null) System.out.println(indice +" "+ arraySecuencias[indice].getListaBases());
					}
				}
			break;			
			case "findmotif":
				pos=sc.nextInt(); //lee pos
				String motivo=sc.next();	 //lee el motivo
				List<Integer> lp = null;
				
				//la lista con las posiciones de inicio de todas las apariciones de la sub-secuencia motivo
				if (pos>=0 && pos<arraySecuencias.length) lp = arraySecuencias[pos].findMotif(motivo);
				else throw new RuntimeException("pos esa fuera de rango");
				
				if (!lp.isEmpty()){
					for (lp.moveToStart(); lp.currPos() < lp.length(); lp.next())
					{
						if (lp.currPos() < lp.length() - 1) System.out.print(lp.getValue() +" ");
						else System.out.print(lp.getValue() +" ");
					}
					System.out.println();
				}
			break;
			default:
				int siz =sc.nextInt();  
				int[][] m= profileMatrix(siz);
				
				for (int i=0;i<4;i++){
					for (int j=0;j<siz;j++){
						System.out.print(m[i][j] +" ");
					}
					System.out.println();
				}
		}
		/*PARTE 2: Ademas de las instrucciones de la parte 1 hay que procesar:
		 * clip, copy, swap, transcribe
		 */
		
	}
	
	//PARTE 1: Funciones auxiliares para implementar las instrucciones profile y consensus
	
	public int[][] profileMatrix(int length){
		/* TODO: Genera el perfil (matriz 4 x length) usando las secuencias de tipo ADN, 
		 * que tengan una longitud mayor o igual a length.  Si la longitud es mayor 
		 * solo considera los length primeros nucleotidos. 
		 */ 
		  int [][] perfil=new int[4][length];  
          for (int i=0;i<4;i++){
            for (int j=0;j<length;j++){
                 perfil[i][j]=0;
            }
         }

       
        for (int i=0;i<arraySecuencias.length;i++){
        	if (arraySecuencias[i] !=null && arraySecuencias[i].getFormaGenetica().compareTo("ADN")==0) {
                  
             String secuencia=arraySecuencias[i].getListaBases();
                 
                  if (secuencia.length()>=length){
                         int fila;
                          for(int j=0; j<length;j++){
                               if (secuencia.charAt(j)=='A'){
                                   fila=0;
                               }else if (secuencia.charAt(j)=='C'){
                                   fila=1;
                               }else if (secuencia.charAt(j)=='G'){
                                   fila=2;
                               }else {
                                   fila=3;
                               }
                               perfil[fila][j]++;
                          }
                   }
                  
        }
           }
		return perfil;

          
        
	}
	
		
	
	
	/* A PARTIR DE AQUI NO MODIFICAR */
	
	/** Lee las instrucciones de fichero y las procesa */	
	public void leerFichero(String nombreFichero) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
			String linea;
			while ((linea = br.readLine()) != null)   {
				procesar(linea);
				  
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Programa principal.
	 *  arg[0] es el numero maximo secuencias que se almacenaran
	 *  arg[1] es el nombre del fichero a procesar
	 */
	public static void main(String[] args) {
		Gen gen = new Gen(Integer.parseInt(args[0]));
		gen.leerFichero(args[1]);	
	}
	
	

}
