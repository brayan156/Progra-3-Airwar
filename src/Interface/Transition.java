package Interface;

import java.util.Random;

import GameElements.Plane;
import Others.BasicFunctions;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Transition {
	private TranslateTransition transition;
    private Random random = new Random();
    private Plane plane;
    private int duration;
	
	public Transition(Plane nodo) {
		this.plane = nodo;
		this.transition = new TranslateTransition();
		setDuration(BasicFunctions.ParseInt(BasicFunctions.getPropertyKey("duration")));
	}
	
	public Transition (Plane nodo, double destX, double destY) {
		this.plane = nodo;
		this.transition = new TranslateTransition();
		setTo(destX, destY);
//		plane.setXY(destX, destY);
		setDuration(BasicFunctions.ParseInt(BasicFunctions.getPropertyKey("duration")));
	}

	public void start() {
		transition.setNode(plane);
		transition.play();
		System.out.println("\rDespega "+plane.toString());
	}
	
	public void setTo(double destX, double destY) {
    	double transX = destX - plane.getPosx();
    	double transY = destY - plane.getPosy();
    	System.out.println("\n\n================================");
    	System.out.println("Plane >   "+plane.getPosx()+" "+plane.getPosy());
    	System.out.println("Dest  >   "+destX+" "+destY);
    	System.out.println("Mover >>  "+transX+" "+transY);
		this.transition.setToX(transX);
		this.transition.setToY(transY);
	}
	
	public int getDuration() {
		System.out.println("Transition Duration: "+duration);
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = random.nextInt(duration- Controller.peligrosidad*10)+1;
		transition.setDuration(Duration.seconds(this.duration)); //Duracion por ciclo
	}
}
