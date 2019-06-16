package Listas;

import Interface.Controller;
import javafx.fxml.FXML;
import javafx.scene.shape.Line;

public class Edge {
	private Vertice origin;
    private Vertice destination;
    private double distance;
    private double weight=0,risk=0;
	@FXML private Line line = new Line();


    public Edge(Vertice origin, Vertice destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }
 
    public Vertice getOrigin() {
        return origin;
    }
 
    public void setOrigin(Vertice origin) {
        this.origin = origin;
    }
 
    public Vertice getDestination() {
        return destination;
    }
 
    public void setDestination(Vertice destination) {
        this.destination = destination;
    }
 
    public double getDistance() {
        return distance;
    }
 
    public void setDistance(double distance) {
        this.distance = distance;
    }
 
    @Override
    public String toString() {
        return "Edge [origin=" + origin.getZone() + ", destination=" + 
        		destination.getZone() + ", distance=" + distance + ", weight=" + weight + ", risk="+risk + "]";
    }
    
	public void drawRoute() {
		line.setStartX(origin.getZone().getPosx()); line.setStartY(origin.getZone().getPosy()); 
		line.setEndX(destination.getZone().getPosx()); line.setEndY(destination.getZone().getPosy());
		line.prefWidth(1);
		Controller.background.getAnchorPane().getChildren().add(line);
	}
	
	public void eraseRoute() {
		Controller.background.getAnchorPane().getChildren().remove(line);
		
	}
    
}
