package Connection;

import Interface.AirCraft;
import Interface.AirPort;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class Ruta {
	private double peso=0,peligro=0;
	private double startX, startY, endX, endY;
	private AirPort airport1=null, airport2=null;
	private AirCraft aircraft1=null, aircraft2=null;
	private boolean aterrizaAirCraft=false;
	@FXML private Line line = new Line(startX, startY, endX, endY);
 
	
	public Ruta(Object start, Object end) {
		
		if (start instanceof AirPort) {this.airport1 = (AirPort) start;}
		else if(start instanceof AirCraft) {this.aircraft1 = (AirCraft) start;}
		if (end instanceof AirPort) {this.airport2 = (AirPort) end;}
		else if(end instanceof AirCraft) {this.aircraft2 = (AirCraft) end;}
		
		if (airport1!=null) { 
			this.startX = airport1.getPosx();
			this.startY = airport1.getPosy();}
		else if (aircraft1!=null) {
			this.startX = airport1.getPosx();
			this.startY = airport1.getPosy();}
		
		if (airport2!=null) { 
			this.endX = airport2.getPosx();
			this.endY = airport2.getPosy();}
		else if (aircraft2!=null) {
			this.endX = aircraft2.getPosx();
			this.endY = aircraft2.getPosy();
			aterrizaAirCraft = true;}
		
		calcPeso();
	}

	private void calcPeso() {
		//considerar distancias
		//formula de distancia entre dos puntos de un plano   
		double distanciaEnX = endX - startX;
		double distanciaEnY = endY - startY;
		//pitagoras
		double hipotenusa = Math.sqrt(Math.pow(distanciaEnX, 2)+Math.pow(distanciaEnY, 2));
		System.out.println("Distancia en X > "+ distanciaEnX+ "Distancia en Y > "+ distanciaEnY);
		System.out.println("Hipotenusa > "+hipotenusa);
		//...
		this.peso = hipotenusa;
		
		//considerar zona aterrizaje
		if (aterrizaAirCraft) {
			this.peso += 25; //por asi decir...
		}
		//...
		
		//considerar continental o interoceanica
		//algo ahi...
		//...
		
		//considerar peligro
		//algo ahi..
		this.peligro = 0;
		//...
	}
	
	public void drawRoute(AnchorPane pane) {
		line.setStartX(startX); line.setStartY(startY); line.setEndX(endX); line.setEndY(endY);
		line.prefWidth(1);
		pane.getChildren().add(this.line);
	}
	
	public void eraseRoute(AnchorPane pane) {
		pane.getChildren().remove(this.line);
		
	}
	
}
