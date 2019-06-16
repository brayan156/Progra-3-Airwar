package GameElements;


import Interface.Controller;
import Loops.TimeSchedule;
import Loops.TimerAnimation;
import javafx.scene.control.Tooltip;

public class AirPort extends Air {
	/*Atributes*/	
	public static int counter=0;
    public TimeSchedule schAnim;
    
    
	/*Constructor*/
	public AirPort(double x, double y) {
		super(x, y);
	}
	
    public void generatePlane() {
    	if (!this.isEmpty()) return;
    	//actualizar avion del aereopuerto.
    	this.plane = new Plane(this.posx, this.posy);
    	plane.setCurrentZone(this);
    	schAnim = new TimeSchedule(new TimerAnimation(plane), plane); //animacion
	    plane.draw(Controller.background.getAnchorPane()); //dibujar
	    
	    //funciones..
	    updateStats();
	    showDetails();
    }
    
    
    public void updateStats() {
	    Controller.background.addPlane(plane);	//agregar a la lista de aviones
	    Controller.background.setAlive();
    }
    
    
    public void showDetails() {
//    	System.out.println("\rNUM. PLANES: "+Controller.background.getPlanes().getLargo());
//    	Controller.background.getPlanes().print();
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
