package Loops;

import java.util.Timer;

import GameElements.Plane;
import Others.BasicFunctions;

public class TimeSchedule {
	
	private Timer timer = new Timer();
	private long gPeriod, flyOutTime;
	
	
	public TimeSchedule(TimerGenerate timerGenerate) {
		this.gPeriod = BasicFunctions.ParseLong(BasicFunctions.getPropertyKey("generation_period"));
		start(timerGenerate);
	}
	
	
	public TimeSchedule(TimerAnimation timerAnimation, Plane plane) {
		this.flyOutTime = plane.getFlyOutTime();
		start(timerAnimation);

	}

	
	private void start(TimerGenerate timerGenerate) {
	    timer.scheduleAtFixedRate(timerGenerate, 0, gPeriod);
	}
	
	
	private void start(TimerAnimation timerAnimation) {
	    timer.scheduleAtFixedRate(timerAnimation, 0, flyOutTime);
	}
}
