package Loops;

import java.util.TimerTask;

import GameElements.Air;
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
			Air zone = Controller.background.search();
			plane.setTransition(plane.getCurrentZone(),zone);
		}
}
