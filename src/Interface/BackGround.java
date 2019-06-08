package Interface;

import GameElements.AirCraft;
import GameElements.AirPort;
import GameElements.Plane;
import Listas.NodoList;
import Others.BasicFunctions;

public class BackGround {
	
	/*Atributes*/
	private int TankSpeed=5, alive=0, slayed=0, risk=0;
    private NodoList<AirPort> airportsList = new NodoList<>();
    private NodoList<AirCraft> aircraftsList = new NodoList<>();
    private NodoList<Plane> planesList = new NodoList<>();
    private static int stop=0;    
    
    
    /*Constructor*/
	public BackGround() {
		setTerritory();
	}
	
	//GENERAR AIRZONES
    private void setTerritory() {
    	for(int i=0; i<8; i++) {
    		double x  = BasicFunctions.getRandomNum(300);
    		double y  = BasicFunctions.getRandomNum(400);
    		AirPort airport = new AirPort(x, y);
    		airportsList.addLast(airport);
    		System.out.println("AirPort:"+airport.getid()+" x:"+x+" y:"+y);
    	}
    }
    
    //BUSCAR airport
    public AirPort searchAirPort() {
    	Integer[] ports = randomAir("Port");
    	int i=0;
    	while (i < ports.length) {
    		AirPort Destination = airportsList.get(i);
    		if (Destination.isEmpty()) {stop=0; return Destination;}
    		i++;
    	}
    	stop+=1;
    	if(stop!= 2) searchAirCraft();
    	return null;
    }
    
    //BUSCAR aircraft
    public AirCraft searchAirCraft() {
    	Integer[] ports = randomAir("Craft");
    	int i=0;
    	while (i < ports.length) {
    		AirCraft Destination = aircraftsList.get(i);
    		if (Destination.isEmpty()) {stop=0; return Destination;}
    		i++;
    	}
    	stop+=1;
    	if(stop!= 2) searchAirPort();
    	return null;
    }
	//RANDOM AIRZONE
    private Integer[] randomAir(String condition) {
    	Integer[] ports = null;
    	if (condition.equals("Port")) {
    		ports = new Integer[airportsList.getLargo()];
    		for (int i=0; i<ports.length; i++) {
    			ports[i] = BasicFunctions.getRandomNum(ports.length);}
    		return ports;
    	}
    	else if (condition.equals("Craft")) {
    		ports = new Integer[aircraftsList.getLargo()];
    		for (int i=0; i<ports.length; i++) {
    			ports[i] = BasicFunctions.getRandomNum(ports.length);}
    		return ports;
    	}
    	return ports;
    }
    
    public void addPlane(Plane plane) {
		this.planesList.addLast(plane);;
	}
    
    public int getOdd() {
    	int odds = BasicFunctions.getRandomNum(1);
    	return odds;
    }
    
    
    
	/*Getters*/
    public int getAlive() {
		return alive;
	}
	public int getSlayed() {
		return slayed;
	}
	public int getTankSpeed() {
		return TankSpeed;
	}
	public NodoList<AirPort> getAirports() {
		return airportsList;
	}
	public NodoList<AirCraft> getAircrafts() {
		return aircraftsList;
	}
	public NodoList<Plane> getPlanes() {
		return planesList;
	}
	public int getRisk() {
		return risk;
	}
	
	
	
	/*Setters*/
	public void setAlive() {
		this.alive+=1;
	}
	public void setSlayed() {
		this.slayed+=1;
	}
	public void setTankSpeed(int TankSpeed) {
		this.TankSpeed = TankSpeed;
	}
	public void setRisk(int risk) {
		this.risk = risk;
	}



	
    
    
}
