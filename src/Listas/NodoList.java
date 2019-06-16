package Listas;

public class NodoList <T> {
    int largo;
    Nodo<T> head= null;


    public void addLast (T e){
        if (this.head==null){
            this.head= new Nodo<T>(e);
            largo++;
        }
        else {
            Nodo <T> tmp= this.head;
            while (tmp.next!= null) {
                tmp = tmp.next;
            }
            tmp.next=new Nodo<>(e);
            largo++;
        }
    }
    
    public void addFirst(T e) {
        Nodo<T> n = new Nodo<>(e);
        n.next=this.head;
        head=n;
        largo++;
    }
	public void eliminar(T t){
		if (this.head.getNodo().equals(t)){
			System.out.println(t);
			System.out.println(this.head);
			this.head=this.head.next;
			largo-=1;
		}
		else{
			Nodo<T>tmp=this.head;
			while (tmp.next!=null){
				if (tmp.next.getNodo().equals(t)){
					System.out.println(t);
					System.out.println(tmp);
					tmp.next=tmp.next.next;
					largo-=1;
					break;
				}
				else {
					tmp=tmp.next;
				}
			}
		}
	}
    
	public void addAll(NodoList<T> list){
		for (int i =0; i<list.getLargo(); i++) {
			this.addLast(list.get(i));
		}
	}
    
    
	public Nodo<T> getLast() {
		if (this.head==null) {return null;}
		Nodo <T> tmp= this.head;
        while (tmp.next!= null) {
            tmp = tmp.next;
        }return tmp;
	}
    
	public void removeLast() {
		if (this.head==null) {return;}
		else if (this.head.next == null){ this.head=null; largo--; return;}
		Nodo <T> tmp= this.head;
        while (tmp.next.next!= null) {
            tmp = tmp.next;
        }tmp.next = null; largo--;
	}
	

	public boolean contains(T string) {
    	Nodo<T> tmp = this.head;
    	while (tmp != null) {
    		if (tmp.getNodo().equals(string)) {
    			return true;
    		} tmp = tmp.next;
    	}
		return false;
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		String str=">> LIST <<";
    	Nodo<T> tmp = this.head;
    	while (tmp != null) {
    		str = str+"\n|||"+tmp.getNodo().toString();
    		tmp = tmp.next;
    	}
    	return str;
    	
	}
	
	public NodoList<T> getCompared(NodoList<T> list2){
		NodoList<T> compared = new NodoList<>();
		int size =  list2.getLargo();
		for (int i =0; i<size; i++) {
			if (!this.contains(list2.get(i)));
			compared.addLast(list2.get(i));
		}return compared;
	}
 
    public T get(int index){
    	Nodo<T> tmp = this.head;
//		System.out.println("Largo "+largo);
    	int contador = 0;
    	while (tmp != null) {
    		if (contador==index) {
//        		System.out.println("index "+index);
    			return tmp.getNodo();
    		}
    		tmp=tmp.next;
    		contador++;
    	}System.out.println("NO EXISTE INDICE");
    	return null;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public Nodo <T> getHead() {
        return head;
    }

    public void setHead (Nodo<T> head) {
        this.head = head;
    }
	public void empty() {
		 this.head = null;
		 largo = 0;
	}
}
