package Loops;


import GameElements.Air;
import GameElements.Plane;
import Interface.BackGround;
import Interface.Controller;
import Listas.NodoList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimeAnimation {
		private Plane plane;
		private long flyOutTime;
		private static BackGround bg = Controller.background;
		private static NodoList<Air> zonesList = bg.getZones();
		
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
		if (plane.getCamino()==null)
			newRoute();
		else { 
			if(plane.getTransitionDone())
				nextDestiny();
		}
	}
		
	private void newRoute() {
		Air zone = Controller.background.searchAir(plane.getCurrentZone());
		if (zone == null) return;
		if(plane.getTransitionDone());
			newRoute(Controller.background.getGraph().encontrarRutaMinimaDijkstra(plane.getCurrentZone().getid(), zone.getid()));
	}
	private void newRoute(String camino) {
		if (camino == null) {
			toDo(); return;
		}
		camino = camino.replaceAll(",", "");
		char[] arrayCamino = camino.split(":")[1].toCharArray();
		plane.setCamino(arrayCamino);
		plane.setTooltipText(arrayCamino, arrayCamino[0]);
//		Air next = getAir(plane.getCamino(), 0);
//		if (next == null) {
//			toDo(); return;
//		}
		plane.setTransition(plane.getCurrentZone(), bg.getChar(zonesList, arrayCamino[0]));
		plane.setOnNode(1);	
	}
	
	private void nextDestiny() {
		int current = plane.getOnNode();
		System.out.println("Current "+current+" Size "+plane.getCamino().length);
		if (current >= plane.getCamino().length-1) {
			plane.setCamino(null);
			return;
		}
		plane.setTooltipText(plane.getCamino(), plane.getCamino()[current]);
//		Air next = getAir(plane.getCamino(), current);
//		if (next == null) {
//			toDo(); return;
//		}
		plane.setTransition(plane.getCurrentZone(), bg.getChar(zonesList, plane.getCamino()[current]));
		plane.setOnNode(current+1);
	}
}
