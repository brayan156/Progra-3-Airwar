package Loops;


import GameElements.Air;
import GameElements.Plane;
import Interface.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimeAnimation {
		private Plane plane;
		private long flyOutTime;
	
	public TimeAnimation(Plane plane) {
		this.flyOutTime = plane.getFlyOutTime();
		this.plane = plane;
		start();
	}


	private void start() {
    	Timeline timer = new Timeline(new KeyFrame(Duration.seconds(flyOutTime),
				write -> {
					toDo();
					
		}));
	timer.setCycleCount(-1);
	timer.play();
	}
	
	
	public void toDo(){
		Air zone = Controller.background.search();
		if (zone == null) {System.out.println("[ Puerto es null ] : Line35-Controller"); return;}
		else if (zone.equals(plane.getCurrentZone())) {toDo(); return;}
		if(plane.getTransitionDone()) {
//			System.out.println("terminado "+ plane.getId());
			plane.setTransition(plane.getCurrentZone(),zone);}
		else {
//			System.out.println("en proceso "+ plane.getId());
		}
		return;
	}
	
}
