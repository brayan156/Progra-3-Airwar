package Loops;


import GameElements.Air;
import GameElements.Plane;
import Interface.Controller;
import Listas.NodoList;
import Listas.Vertice;
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
		if (plane.getRuta()==null) {
			newRoute();
		}else {
			nextVertice();
		}
	}
		
	private void newRoute() {
		Air zone = Controller.background.search();
		if (zone == null) {
			System.out.println("[ Puerto es null ] : Line35-Controller"); 
		}else if (zone.equals(plane.getCurrentZone())) {
			toDo();
		}else {
			if(plane.getTransitionDone()) {
				NodoList<Vertice> ruta = Controller.background.getGraph().hastPathDFS(plane.getCurrentZone().getId(), zone.getId());
				if (ruta==null) {
					System.out.println("NO HAY RUTA DE "+plane.getCurrentZone()+", "+zone);
					toDo();
					return;
				}
				plane.setRuta(ruta);
//				plane.setTooltipText(ruta, plane.getRuta().get(0).getZone().getId());
				plane.setTransition(plane.getCurrentZone(), plane.getRuta().get(0).getZone());
				plane.setVertice(1);			
			}
		}
	}
	
	private void nextVertice() {
		if(plane.getTransitionDone()) {
			int current = plane.getVertice();
			System.out.println("Current "+current+" tam "+plane.getRuta().getLargo());
			if (current == plane.getRuta().getLargo()-1) {
				plane.setRuta(null);
				return;
			}
//			plane.setTooltipText(plane.getRuta(), plane.getRuta().get(current).getZone().getId());
			plane.setTransition(plane.getRuta().get(current).getZone(), plane.getRuta().get(current+1).getZone());
			plane.setVertice(current+1);
		}
	}
	
}
