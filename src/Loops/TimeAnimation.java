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
		if (plane.getCamino().getLargo()==0)
			newRoute();
		else { 
			if(plane.getTransitionDone())
				nextDestiny();
		}
	}
		
	private void newRoute() {
		Air zone = Controller.background.searchAir(plane.getCurrentZone());
		if (zone == null) return;
		if(plane.getTransitionDone() && plane.getCamino().getLargo()==0);
			newRoute(Controller.background.getGraph().encontrarRutaMinimaDijkstra(plane.getCurrentZone().getid(), zone.getid()));
			System.out.println(plane.toString()+" en "+plane.getCurrentZone() +" va a:"+Controller.background.getGraph().encontrarRutaMinimaDijkstra(plane.getCurrentZone().getid(), zone.getid()));
	}
	private void newRoute(String camino) {
		if (camino == null) {
			toDo(); return;
		}
		camino = camino.replaceAll(",", "");
		char[] arrayCamino = camino.split(":")[1].toCharArray();
		if (camino.split(":")[0].equals("0")){newRoute(); return;}
		plane.setCamino(arrayCamino);
		NodoList<Character> ruta=new NodoList<>();
		for(int i =0; i<arrayCamino.length;i++){
			ruta.addLast(arrayCamino[i]);
		}
		plane.setTooltipText(ruta, arrayCamino[0]);
//		Air next = getAir(plane.getCamino(), 0);
//		if (next == null) {
//			toDo(); return;
//		}
		System.out.println(camino);
		System.out.println("voy a "+bg.getChar(zonesList, arrayCamino[1]));
		plane.setTransition(plane.getCurrentZone(), bg.getChar(zonesList, arrayCamino[1]));
		plane.setOnNode(1);	
	}
	
	private void nextDestiny() {
		int current = plane.getOnNode();
		System.out.println("Current "+current+" Size "+plane.getCamino().getLargo());
		if (current >= plane.getCamino().getLargo()-1 && plane.getCamino().get(current).equals(plane.getCurrentZone().getid())) {
			plane.setCamino(null);
			return;
		}
		System.out.println(plane.getCurrentZone().getid()+","+plane.getCamino().get(current));
		if (plane.getCamino().get(current).equals(plane.getCurrentZone().getid())) {
			System.out.println("voy a cambiar"+plane.toString()+"a la siguiente zona");
			plane.setTooltipText(plane.getCamino(), plane.getCamino().get(current));
//		Air next = getAir(plane.getCamino(), current);
//		if (next == null) {
//			toDo(); return;
//		}
			plane.setTransition(plane.getCurrentZone(), bg.getChar(zonesList, plane.getCamino().get(current+1)));
			plane.setOnNode(current + 1);
		}
	}
}
