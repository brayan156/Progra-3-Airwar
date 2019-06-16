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
		pormientras();
//		setTerritory();
	}
	
	//GENERAR AIRZONES
    private void setTerritory() {
    	for(int i=0; i<4; i++) {
    		//posiciones random...
    		double x  = BasicFunctions.getRandomNum(900)+50;
    		double y  = BasicFunctions.getRandomNum(700)+50;
    		//Air del vertice
    		AirPort airport = new AirPort(x, y);
    		airportsList.addLast(airport);
    		airList.addLast(airport);
    		//Vertice
    		Vertice vertice = new Vertice(airport);
    		verticeList.addLast(vertice);
//    		System.out.println(vertice.toString());
    	}
    	for(int i=0; i<3; i++) {
    		//posiciones random...
    		double x  = BasicFunctions.getRandomNum(900)+50;
    		double y  = BasicFunctions.getRandomNum(700)+50;
    		//Air del vertice
    		AirCraft aircraft = new AirCraft(x, y);
    		airList.addLast(aircraft);
    		//Vertice
    		Vertice vertice = new Vertice(aircraft);
    		verticeList.addLast(vertice);
//    		System.out.println(vertice.toString());
    	}
    	setGraph();
    }
    //acomodar grafo
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
    
    private void pormientras() {
		//Air del vertice
		AirPort airport1 = new AirPort(30, 60);
		AirPort airport2 = new AirPort(300, 200);
		AirPort airport3 = new AirPort(500, 500);
		AirCraft aircraft1 = new AirCraft(25, 300);
		AirCraft aircraft2 = new AirCraft(400, 700);
		AirCraft aircraft3 = new AirCraft(200, 476);
		airportsList.addLast(airport1);
		airportsList.addLast(airport2);
		airportsList.addLast(airport3);
		airList.addLast(airport1);
		airList.addLast(airport2);
		airList.addLast(airport3);
		airList.addLast(aircraft1);
		airList.addLast(aircraft2);
		airList.addLast(aircraft3);
		//Vertice
		Vertice vertice1 = new Vertice(aircraft1);
		Vertice vertice2 = new Vertice(aircraft2);
		Vertice vertice3 = new Vertice(aircraft3);
		Vertice vertice4 = new Vertice(airport1);
		Vertice vertice5 = new Vertice(airport2);
		Vertice vertice6 = new Vertice(airport3);
		verticeList.addLast(vertice1);
		verticeList.addLast(vertice2);
		verticeList.addLast(vertice3);
		verticeList.addLast(vertice4);
		verticeList.addLast(vertice5);
		verticeList.addLast(vertice6);
//		System.out.println(vertice1.toString());
//		System.out.println(vertice2.toString());
//		System.out.println(vertice3.toString());
//		System.out.println(vertice4.toString());
//		System.out.println(vertice5.toString());
//		System.out.println(vertice6.toString());
		//edges
		vertice1.addEdge(new Edge(vertice1, vertice2, 100));
		vertice1.addEdge(new Edge(vertice1, vertice3, 90));
        vertice2.addEdge(new Edge(vertice2, vertice4, 350));
        vertice2.addEdge(new Edge(vertice2, vertice3, 150));
        vertice2.addEdge(new Edge(vertice2, vertice5, 340));
        vertice2.addEdge(new Edge(vertice2, vertice1, 100));
        vertice3.addEdge(new Edge(vertice3, vertice1, 90));
        vertice3.addEdge(new Edge(vertice3, vertice4, 100));
        vertice4.addEdge(new Edge(vertice4, vertice5, 20));
        vertice4.addEdge(new Edge(vertice4, vertice2, 350));
        vertice5.addEdge(new Edge(vertice5, vertice1, 350));
		setGraph();
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
