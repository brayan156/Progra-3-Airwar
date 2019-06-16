package GameElements;


import Interface.Controller;
import Loops.TimeAnimation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AirPort extends Air {
	/*Atributes*/	
	public static int counter=0;
    public TimeAnimation schAnim;
    
    
	/*Constructor*/
	public AirPort(double x, double y) {
		super(x, y);
	}
	
    public void generatePlane(AnchorPane mapAnchorPane) {
    	if (!this.isEmpty()) return;
    	//actualizar avion del aereopuerto.
    	this.plane = new Plane(this.posx, this.posy);
    	plane.setCurrentZone(this);
    	schAnim = new TimeAnimation(plane); //animacion
    	
	    plane.draw(mapAnchorPane); //dibujar
//	    animation();
	    //funciones..
	    updateStats();
	    showDetails();
    }
    
    public void animation() {
    	Timeline cd = new Timeline(new KeyFrame(Duration.seconds(plane.getFlyOutTime()), write -> {
    		Air zone = Controller.background.search();
    		System.out.println("todo");
//    		if (zone.equals(plane.getCurrentZone())) {toDo(); return;}
    		plane.setTransition(plane.getCurrentZone(),zone);
    	}));
//    	cd.setCycleCount(1);
    	cd.play();
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
