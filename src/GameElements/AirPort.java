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
    	this.plane.setCurrentZone(this);
    	
    	//agregar animacion
	    schAnim = new TimeSchedule(new TimerAnimation(plane), plane);
	    
	    //dibujar
	    plane.draw(anchor);
	    
    	//agregar a la lista de aviones
	    Controller.background.addPlane(plane);
	    
	    System.out.println("\rNUM. PLANES: "+Controller.background.getPlanes().getLargo());
//	    Controller.background.getPlanes().print();
	    return true;
    }
    
    public void print() {
    	System.out.println(toString());
    }
    
	@Override
	public String toString() {
		String str = "[Port : "+getId()+"] ("+posx+","+posy+")";
		return str;
	}
    
}
