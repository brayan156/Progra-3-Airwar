package Listas;

import java.util.HashSet;
import java.util.Optional;

import GameElements.Air;

public class Graph {
	private NodoList<Vertice> vertices;
	 
    public void addNode(Vertice vertice) {
        if (vertices == null) {
        	vertices = new NodoList<Vertice>();
        }
        vertices.addLast(vertice);
    }
 
    public NodoList<Vertice> getVertices() {
        return vertices;
    }
     
    private Optional<Vertice> getVertice(String zoneID) {
        for (int i=0; i<vertices.getLargo(); i++) {
        	Vertice vertice = vertices.get(i);
            if (vertice.getZone().getId().equals(zoneID)) {
                return Optional.of(vertice);
            }
        }
        return Optional.empty();
    }
 
    
    public NodoList<Vertice> hastPathDFS(String source, String destination){
    	NodoList<Vertice> route = new NodoList<Vertice>();
    	return hasPathDFS(source, destination, route);
    }
    private NodoList<Vertice> hasPathDFS(String source, String destination, NodoList<Vertice> route) {
        Optional<Vertice> start = getVertice(source);
        Optional<Vertice> end = getVertice(destination);
        if (start.isPresent() && end.isPresent()) {
            return hasPathDFS(start.get(), end.get(), new HashSet<Air>(), route);
        } else {
            return null;
        }
    }
    private NodoList<Vertice> hasPathDFS(Vertice source, Vertice destination, HashSet<Air> visited, NodoList<Vertice> route) {
    	System.out.println(visited);
        if (visited.contains(source.getZone())) {
            return null;
        }
        visited.add(source.getZone());
        if (source == destination) {
        	route.addLast(source);
            return route;
        }
        for (int i=0; i<source.getEdges().getLargo(); i++) {
        	Edge edge = source.getEdges().get(i);
            if (hasPathDFS(edge.getDestination(), destination, visited, route) != null) {
            	route.addLast(source);
                return route;
            }
        }
        return null;
    }
    
 
    @Override
    public String toString() {
        return "Graph [vertices=" + vertices + "]";
    }
}
