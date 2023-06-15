package pr2EDA;


public class LList<E> implements List<E> {

	public class Node<E>{
		private E element;
		private Node<E> next; //referencia al siguiente nodo

		public Node(E it, Node<E> nextval){
			element = it;
			next = nextval;
		}

		public Node(Node<E> nextval) {
			element = null;
			next = nextval;
		}
		public Node<E> setNext(Node<E> nextval) { return next = nextval;}
		public Node<E> next(){ return next;}
		public E setElement(E it) {return element = it;}
		public E element() {return element;}
	}

	//Miembros de la lista
	private Node<E> head, tail, curr; //referencias a la cabeza, cola y posicion actual
	private int cnt; // num de elementos

	//Constructores
	public LList(int size) { this();}
	public LList() {clear();}



	public boolean insert(E it) {
		curr.setNext(new Node<E>(curr.element(), curr.next()));
		curr.setElement(it);
		if(curr == tail)
			tail = curr.next();
		cnt++;
		return true;
	}


	public boolean append(E it) {
		tail.setNext(new Node<E>(null));
		tail.setElement(it);
		tail = tail.next();
		cnt++;
		return true;
	}


	public E remove() {
		if(curr == tail) return null;

		E it = curr.element();
		curr.setElement(curr.next().element());
		if(curr.next() == tail) tail = curr;
		curr.setNext(curr.next().next());
		cnt--;
		return it;
	}


	public void clear() {
		cnt = 0;
		tail = curr = new Node<E>(null);
		head = new Node<E>(tail);
	}


	public E getValue() {return curr.element();}


	public int currPos() {
		int p;
		Node<E> aux = head.next(); //primer elemento de la lista
		for(p=0; aux!=curr; p++)
			aux = aux.next();
		return p;
	}


	public boolean isEmpty() {return cnt==0;}


	public boolean isAtEnd() {return curr==tail;}


	public int length() {return cnt;}


	public void moveToStart() {curr = head.next();}

	public void moveToEnd() {curr = tail;}

	public boolean moveToPos(int p) {
		if(p<0 || p > cnt) return false;

		curr = head.next();
		for(int i=0; i< p; i++)
			curr = curr.next();
		return true;
	}

	public void next() {if(curr!=tail) curr = curr.next();}

	public void prev() {
		if(curr!=head.next()) {
			Node<E> aux = head.next();
			while(aux.next()!= curr)
				aux = aux.next();
			curr = aux;
		}
	}

	@Override
	public String toString() {
		String s="";
		Node<E> aux = head.next();
		for(int i=0; i< cnt; i++) {
			s+=aux.element()+" ";
			aux = aux.next();
		}		
		return s;	
	}

}
