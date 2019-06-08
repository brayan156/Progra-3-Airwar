package Loops;

import java.util.TimerTask;

import GameElements.AirCraft;
import GameElements.AirPort;
import GameElements.Plane;
import Interface.Controller;

public class TimerAnimation  extends  TimerTask{
			private Plane plane;
	
		public TimerAnimation(Plane plane) {
			this.plane = plane;
		}

		@Override
		public void run(){ 
			toDo();
		}
		
		public void toDo(){
			int odd = Controller.background.getOdd();
			Object course=null;
			AirPort courseA = null;
			AirCraft courseB =null;
			//ver en cual buscar.
			if (odd==0) {course = Controller.background.searchAirPort();}
			else if (odd==1) {course = Controller.background.searchAirCraft();}
			//obtener posiciones.
			if (course==null) return;
			if(course instanceof AirPort) {
			    System.out.println("\nInstance of AirPort");
				courseA = (AirPort) course;
				plane.setTransition(courseA.getPosx(), courseA.getPosy());
			}else if(course instanceof AirPort) {
			    System.out.println("\nInstance of AirCraft");
				courseB = (AirCraft) course;
				plane.setTransition(courseB.getPosx(), courseB.getPosy());
			}
		}
}
