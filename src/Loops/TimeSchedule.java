package Loops;

import java.util.Timer;

import GameElements.Plane;

public class TimeSchedule {
	
	private Timer timer = new Timer();
	private long flyOutTime;
	
	
	public TimeSchedule(TimerAnimation timerAnimation, Plane plane) {
		this.flyOutTime = plane.getFlyOutTime();
		start(timerAnimation);

	}
	
	
	private void start(TimerAnimation timerAnimation) {
	    timer.scheduleAtFixedRate(timerAnimation, 0, flyOutTime);
	}
}
