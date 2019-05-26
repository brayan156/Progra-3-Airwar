package Connection;

public class NodoM<T extends Comparable> {
	private T data;
	private NodoM<T> next;
	private NodoM<T> down;

	
	public NodoM(T data) {
		this.data = data;;
		this.next = null;
		this.down = null;
	}
	
	public void displayNodo() {
		System.out.print(data + " ");
	}
	
	
	
	
	
	
	//Getter
	public T getData() {
		return data;
	}
	public NodoM<T> getNext() {
		return next;
	}
	public NodoM<T> getDown() {
		return down;
	}
	
	//Setters
	public void setData(T data) {
		this.data = data;
	}
	public void setNext(NodoM<T> next) {
		this.next = next;
	}
	public void setDown(NodoM<T> down) {
		this.down = down;
	}





	
}
