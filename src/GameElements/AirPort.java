package GameElements;

import Interface.Controller;
import Loops.TimeSchedule;
import Loops.TimerAnimation;
import javafx.scene.layout.AnchorPane;

public class AirPort extends Air {
	/*Atributes*/	
	public static int counter=0;
    public TimeSchedule schAnim;
    
    
	/*Constructor*/
	public AirPort(double x, double y) {
		super(x, y);
	}
	
    public boolean generatePlane(AnchorPane anchor) {
    	if (!this.isEmpty()) return false; 	
    	//aeropuerto en que se crea manda sus posiciones.
    	this.plane = new Plane(this.posx, this.posy);
    	
    	//agregar animacion
	    schAnim = new TimeSchedule(new TimerAnimation(plane), plane);
	    
	    //dibujar
	    plane.draw(anchor);
	    
    	//agregar a la lista de aviones
	    Controller.planesList.addLast(plane);
	    
	    System.out.println("\n\nPLANES NUMBER "+Controller.planesList.getLargo());
	    Controller.planesList.print();
	    return true;
    }
    
}
