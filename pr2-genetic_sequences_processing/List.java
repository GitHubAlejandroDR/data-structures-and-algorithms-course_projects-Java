package pr2EDA;


public interface List<E> {
	
	public boolean insert (E item ); 
	
	public boolean append (E item ); 
	
	public E remove (); 
	
	public void clear (); 
	
	public E getValue (); 
	
	public int currPos (); 
	
	public boolean isEmpty (); 
	
	public boolean isAtEnd (); 
	
	public int length (); 

	public void moveToStart (); 
	
	public void moveToEnd (); 
	
	public boolean moveToPos (int p); 
	
	public void next (); 
	
	public void prev (); 
}
