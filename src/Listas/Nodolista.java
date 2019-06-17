package Listas;

public class Nodolista<T>{
    private T nodo=null;
    public Nodolista<T> next=null;

    public Nodolista(T nodo){
        this.nodo= nodo;
        this.next= null;
    }

    public Nodolista() {
    }

    public T getNodo() {
        return nodo;
    }

    public void setNodo(T nodo) {
        this.nodo = nodo;
    }

    public Nodolista<T> getNext() {
        return next;
    }

    public void setNext(Nodolista<T> next) {
        this.next = next;
    }
}
