package Interface;

import GameElements.Air;
import GameElements.AirCraft;
import GameElements.AirPort;
import GameElements.Plane;
import GameElements.Tuple;
import Listas.Nodo;
import Listas.NodoList;
import Others.BasicFunctions;
import javafx.scene.Node;

public class BackGround {
	
	/*Atributes*/
	private int TankSpeed=5, alive=0, slayed=0, risk=0;
    private NodoList<Air> airList = new NodoList<>();
    private NodoList<AirPort> airportsList = new NodoList<>();
    private NodoList<Plane> planesList = new NodoList<>();
    private AirPort lastPort = null;
    
    /*Constructor*/
	public BackGround() {
		setTerritory();
	}
	
	
	//GENERAR AIRZONES
    private void setTerritory() {
    	for(int i=0; i<4; i++) {
    		double x  = BasicFunctions.getRandomNum(900)+50;
    		double y  = BasicFunctions.getRandomNum(700)+50;
    		AirPort airport = new AirPort(x, y);
    		airportsList.addLast(airport);
    		airList.addLast(airport);
    		System.out.println("AirPort:"+airport.getId()+" x:"+x+" y:"+y);
    	}
    	for(int i=0; i<3; i++) {
    		double x  = BasicFunctions.getRandomNum(900)+50;
    		double y  = BasicFunctions.getRandomNum(700)+50;
    		AirCraft aircraft = new AirCraft(x, y);
    		airList.addLast(aircraft);
    		System.out.println("AirCraft:"+aircraft.getId()+" x:"+x+" y:"+y);
    	}
    }
    
    
    //buscar zone
    public Air search() {
    	int i =0;
    	NodoList<Air> vacios = new NodoList<Air>();
    	while (i<airList.getLargo()) {
    		if (airList.get(i).isEmpty()) {
    			vacios.addLast(airList.get(i));}
    		i++;
    	}if (vacios.getLargo()==0) return null;
    	return randomZone(vacios);
    }
    //random zone
    private Air randomZone(NodoList<Air> vacios) {
    	int random = BasicFunctions.getRandomNum(airList.getLargo()-1);
    	return airList.get(random);
    }
    
    //buscar airport
    public AirPort searchAirPort() {
    	int i =0;
    	NodoList<AirPort> vacios = new NodoList<AirPort>();
    	while (i<airportsList.getLargo()) {
    		if (airportsList.get(i).isEmpty()) {
    			vacios.addLast(airportsList.get(i));}
    		i++;
    	}
    	if (vacios.getLargo()==0) return null;
    	return randomAirport(vacios);
    }//random airport
    private AirPort randomAirport(NodoList<AirPort> vacios) {
    	int random = BasicFunctions.getRandomNum(airportsList.getLargo()-1);
    	System.out.println("random "+random);
    	if (lastPort!=null && lastPort.equals(airportsList.get(random))) return null;
    	lastPort = airportsList.get(random);
    	return airportsList.get(random); 
    }
    
    //ADD AVION
    public void addPlane(Plane plane) {
		this.planesList.addLast(plane);;
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
	public NodoList<Air> getZones() {
		return airList;
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

  public void setCurrent(Air zone, Plane plane) {
	for (int i =0; i<airList.getLargo(); i++) {
		if (airList.get(i).getId().equals(zone.getId())) {
			airList.get(i).setPlane(plane);
			return;	
		}
	}
  }


    
    
}
