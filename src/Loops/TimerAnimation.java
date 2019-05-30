package Loops;

import java.util.TimerTask;

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
			AirPort course = Controller.searchPort();
			if (course == null) return;
		    plane.setTransition(course.getPosx(), course.getPosy());
		    System.out.println("\nANIMATION DONE");
		}
}
