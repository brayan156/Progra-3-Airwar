package GameElements;

public class Tuple {
	
	AirPort airport;
	AirCraft aircraft;
	
	public Tuple(AirPort airport,AirCraft aircraft) {
		this.aircraft=aircraft;
		this.airport=airport;
	}
	public Tuple() {
	}

	public AirPort getAirport() {
		return airport;
	}

	public AirCraft getAircraft() {
		return aircraft;
	}

	public void setAirport(AirPort airport) {
		this.airport = airport;
	}

	public void setAircraft(AirCraft aircraft) {
		this.aircraft = aircraft;
	}
	

}
