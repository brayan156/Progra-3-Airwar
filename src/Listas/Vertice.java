package Listas;


import GameElements.Air;

public class Vertice {
    private Air zone;
    private NodoList<Edge> edges;
 
    public Vertice(Air zone) {
        this.zone = zone;
    }
 
    public Air getZone() {
        return zone;
    }
 
    public void setZone(Air zone) {
        this.zone = zone;
    }
 
    public NodoList<Edge> getEdges() {
        return edges;
    }
 
    public void addEdge(Edge edge) {
        if (edges == null) {
            edges = new NodoList<Edge>();
        }
        edges.addLast(edge);
    }
 
    @Override
    public String toString() {
        return "\n\tVERTICE [city=" + zone + ", edges=" + edges + "]";
    }
    
}