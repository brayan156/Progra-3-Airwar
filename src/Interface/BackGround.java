package Interface;

import GameElements.Air;
import GameElements.AirCraft;
import GameElements.AirPort;
import GameElements.Plane;
import Listas.Edge;
import Listas.Graph;
import Listas.NodoList;
import Listas.Vertice;
import Others.BasicFunctions;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BackGround {
	
		@FXML private AnchorPane mapAnchorPane;
	    @FXML private Text aliveText;
	    @FXML private Text  slayedText; 
		
		/*Atributes*/
		private int alive=0, slayed=0, risk=0;
	    private NodoList<Vertice> verticeList = new NodoList<>();
	    private NodoList<Air> airList = new NodoList<>();
	    private NodoList<AirPort> airportsList = new NodoList<>();
	    private NodoList<Plane> planesList = new NodoList<>();
	    private AirPort lastPort = null;
	    private Graph graph;
    

	/*Constructor*/
	public BackGround() {
		setTerritory();
	}
	//OBTENER GRAFO
    private void setGraph() {
    	this.graph = new Graph();
    	for(int i=0; i<verticeList.getLargo(); i++) {
    		graph.addNode(verticeList.get(i));
    	}
    	NodoList<Vertice> ruta = new NodoList<Vertice>();
    	ruta = graph.hastPathDFS("1", "3");
    	if (ruta==null){System.out.println("No hay ruta ->" +ruta); return;}
    	ruta.print();
    }
	//GENERAR AIRZONES
    private void setTerritory() {
		Vertice first = null;
    	for(int i=0; i<4; i++) {
    		//posiciones random...
    		double x  = BasicFunctions.getRandomNum(900)+50;
    		double y  = BasicFunctions.getRandomNum(700)+50;
    		//Air del vertice
    		AirPort airport = new AirPort(x, y);
    		airportsList.addLast(airport);
    		airList.addLast(airport);
    		//Vertice
    		if (i==0) {
	    		first = new Vertice(airport);
	    		verticeList.addLast(first);
    		}else {
        		Vertice vertice1 = new Vertice(airport);
        		Vertice vertice2 = getRandomVertice();
        		vertice1.addEdge(new Edge(vertice1, vertice2, getDistance(vertice1, vertice2)));
        		verticeList.addLast(vertice1);
//        		System.out.println(vertice1.toString());
    		}
    	}
    	for(int i=0; i<3; i++) {
    		//posiciones random...
    		double x  = BasicFunctions.getRandomNum(900)+50;
    		double y  = BasicFunctions.getRandomNum(700)+50;
    		//Air del vertice
    		AirCraft aircraft = new AirCraft(x, y);
    		airList.addLast(aircraft);
    		//Vertice
    		Vertice vertice1 = new Vertice(aircraft);
    		Vertice vertice2 = getRandomVertice();
    		vertice1.addEdge(new Edge(vertice1, vertice2, getDistance(vertice1, vertice2)));
    		verticeList.addLast(vertice1);
//    		System.out.println(vertice1.toString());
    	}
		Vertice vertice2 = getRandomVertice();
    	first.addEdge(new Edge(first, vertice2, getDistance(first, vertice2)));
		verticeList.addLast(first);
//		System.out.println(first.toString());
    	setGraph();
    }
    //random vertice de la lista.
    private Vertice getRandomVertice() {
    	if (verticeList.getLargo()-1==0) return verticeList.get(0);
    	int random = BasicFunctions.getRandomNum(verticeList.getLargo()-1);
    	return verticeList.get(random);
    }
    //calcular distancia entre los nodos.
    private double getDistance(Vertice vertice1, Vertice vertice2) {
		//formula de distancia entre dos puntos de un plano y pitagoras.
		double distanciaEnX = Math.abs(vertice2.getZone().getPosx() - vertice1.getZone().getPosx());
		double distanciaEnY = Math.abs(vertice2.getZone().getPosy() - vertice1.getZone().getPosy());
		double hipotenusa = Math.sqrt(Math.pow(distanciaEnX, 2)+Math.pow(distanciaEnY, 2));
//		System.out.println("Distancia en X > "+ distanciaEnX+ "Distancia en Y > "+ distanciaEnY);
		System.out.println("Hipotenusa > "+hipotenusa);
    	return hipotenusa;
    }

    
    /*metodos para buscar por medio de grafos*/
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
//    	return airportsList.get(0);
    	int i =0;
    	NodoList<AirPort> vacios = new NodoList<AirPort>();
    	while (i<airportsList.getLargo()) {
    		if (airportsList.get(i).isEmpty()) {
    			vacios.addLast(airportsList.get(i));}
    		i++;
    	}
    	if (vacios.getLargo()==0) return null;
    	return randomAirport(vacios);
    }
    //random airport
    private AirPort randomAirport(NodoList<AirPort> vacios) {
    	int random = BasicFunctions.getRandomNum(airportsList.getLargo()-1);
//    	System.out.println("random "+random);
    	if (lastPort!=null && lastPort.equals(airportsList.get(random))) return null;
    	lastPort = airportsList.get(random);
    	return airportsList.get(random); 
    }
    /*metodos para buscar por medio de grafos*/
    
    
    
    //ADD AVION
    public void addPlane(Plane plane) {
		this.planesList.addLast(plane);;
	}




	/*Getters*/
	public Graph getGraph() {
		return graph;
	}
	
	public int getRisk() {
		return risk;
	}
	
	//lists
	public NodoList<AirPort> getAirports() {
		return airportsList;
	}
	public NodoList<Air> getZones() {
		return airList;
	}
	public NodoList<Plane> getPlanes() {
		return planesList;
	}

	//map
    public AnchorPane getAnchorPane() {
			return mapAnchorPane;
	}
    
    //scores
    public int getAlive() {
		return alive;
	}
	public int getSlayed() {
		return slayed;
	}
	public Text getAliveText() {
		return aliveText;
	}
	public Text getSlayedText() {
		return slayedText;
	}
	public int getTankSpeed() {
		int TankSpeed = BasicFunctions.getRandomNum(10);
		return TankSpeed;
	}

	
	
	
	/*Setters*/
	public void setRisk(int risk) {
		this.risk = risk;
	}
	public void setCurrent(Air zone, Plane plane) {
		for (int i =0; i<airList.getLargo(); i++) {
			if (airList.get(i).getId().equals(zone.getId())) {
				airList.get(i).setPlane(plane);
				return;}}
	}
	
	//map
	public void setAnchorPane(AnchorPane mapAnchorPane) {
		this.mapAnchorPane = mapAnchorPane;
	}
	
    //scores
	public void setAliveText(Text aliveText) {
		this.aliveText = aliveText;
	}
	public void setSlayedText(Text slayedText) {
		this.slayedText = slayedText;
	}
	public void setAlive() {
		this.alive+=1;
    	this.aliveText.setText(String.valueOf(alive));
	}
	public void setSlayed() {
		this.alive-=1;
		this.slayed+=1;
		this.aliveText.setText(String.valueOf(alive));
		this.slayedText.setText(String.valueOf(slayed));
	}



    
    
}
