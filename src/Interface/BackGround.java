package Interface;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import GameElements.Air;
import GameElements.AirCraft;
import GameElements.AirPort;
import GameElements.Plane;
import Listas.Grafo;
import Listas.NodoList;
import Listas.Nodolista;
import Others.BasicFunctions;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BackGround {
	
		@FXML private AnchorPane mapAnchorPane;
	    @FXML private Text aliveText;
	    @FXML private Text  slayedText; 
		
		/*Atributes*/
    	private Random random = new Random();

		private int alive=0, slayed=0;
	    private NodoList<Air> airList = new NodoList<>();
	    private NodoList<AirPort> airportsList = new NodoList<>();
	    private NodoList<Plane> planesList = new NodoList<>();
	    private AirPort lastPort = null;
		private List<Character> arrayLetras;
		private Grafo graph;
    

	/*Constructor*/
	public BackGround() {
		setArrays();
		setTerritory();
	}
	private void setArrays() {
    	this.arrayLetras = Arrays.asList(
    			'a','b','c','d','e','f','g',
    			'h','i','j','k','l','m','n',
    			'o','p','q','r','s','t','u',
    			'v','w','x','y','z');
	}
    
	//GENERAR AIRZONES
    private void setTerritory() {
		//posiciones random...

		
		//string del grafo
    	String serieNodos = "";
		//iteracion para generar puertos
    	for(int i=0; i<21; i++) {
    		double x  = BasicFunctions.getRandomNum(900)+50;
    		double y  = BasicFunctions.getRandomNum(700)+50;
    		if (i%2==0) {
    			/*logica de 
    			 * para 
    			 * obtener 
    			 * x y y*/
    			AirPort airport = new AirPort(x, y, this.arrayLetras.get(i));
    			//agregar en listas y string...
    			serieNodos = serieNodos+airport.getid();
        		airList.addLast(airport);
        		airportsList.addLast(airport);
    		}else {
    			/*logica de 
    			 * para 
    			 * obtener 
    			 * x y y*/
    			AirCraft aircraft = new AirCraft(x, y, this.arrayLetras.get(i));
    			//agregar en listas y string...
    			serieNodos = serieNodos+aircraft.getid();
        		airList.addLast(aircraft);    			
    		}
    	}
    	System.out.println("SerieNodos: "+serieNodos);
    	setGraph(serieNodos);
    }
    
	//crear grafo
    private void setGraph(String serieNodos) {
    	this.graph = new Grafo(serieNodos);
    	setRoutes();
    }
    
    //Vincular nodos del grafo.
    private void setRoutes() {
		Air air = null;
		for(int i=0; i<airList.getLargo(); i++) {
    		air = airList.get(i);
    		char[] nodes = getRandomAir(air);
    		/*ver lo de las
    		 *  distancias y
    		 *   lo de donde
    		 *    se dibujan 
    		 *    los air*/
    		graph.agregarRuta(air.getid(), nodes[0], 3);
    		graph.agregarRuta(air.getid(), nodes[1], 3);
		}
	}
	//array con dos random air diferentes al original y diferentes entre si.
    private char[] getRandomAir(Air air) {
    	Air tmp1, tmp2 = null;
    	String nodes = "";
    	while(true) {
    		tmp1 = randomAir(random);
    		tmp2 = randomAir(random);
    		if (tmp1!=air  &&  tmp2!=air  &&  tmp1!=tmp2);
    			nodes = ""+tmp1.getid()+tmp2.getid();
    			break;
    	}
    	return nodes.toCharArray();
    }private Air randomAir(Random random) {
    	int r = random.nextInt(airList.getLargo()-1);
    	return airList.get(r);
    }

//    //calcular distancia entre los nodos.
//    private double getDistance(Vertice vertice1, Vertice vertice2) {
//		//formula de distancia entre dos puntos de un plano y pitagoras.
//		double distanciaEnX = Math.abs(vertice2.getZone().getPosx() - vertice1.getZone().getPosx());
//		double distanciaEnY = Math.abs(vertice2.getZone().getPosy() - vertice1.getZone().getPosy());
//		double hipotenusa = Math.sqrt(Math.pow(distanciaEnX, 2)+Math.pow(distanciaEnY, 2));
////		System.out.println("Distancia en X > "+ distanciaEnX+ "Distancia en Y > "+ distanciaEnY);
//		System.out.println("Hipotenusa > "+hipotenusa);
//    	return hipotenusa;
//    }
    
    //ADD AVION
    public void addPlane(Plane plane) {
		this.planesList.addLast(plane);;
	}
    
    //SEARCH AIR
    public Air searchAir(Air current) {
    	Air next = null;
    	while(true) {
    		next = randomAir(random);
    		if (next!=current);
    			break;
    	}
    	return next;
    }
   
    //SEARCH AIRPORT
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
    }
    //random airport
    private AirPort randomAirport(NodoList<AirPort> vacios) {
    	int random = BasicFunctions.getRandomNum(vacios.getLargo()-1);
    	if (lastPort!=null && lastPort.equals(airportsList.get(random))) return null;
    	lastPort = airportsList.get(random);
    	return airportsList.get(random); 
    }    
   
    
    
    
	/*Getters*/
	public Grafo getGraph() {
		return graph;
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
	public Air getChar(NodoList<Air> zones, char c) {
    	Nodolista<Air> tmp = airList.getHead();
    	while(tmp!=null) {
    		if (c == tmp.getNodo().getid())
    			return tmp.getNodo();
    		tmp=tmp.next;
    	}
		return null;
	}
	
	
	/*Setters*/
	public void setCurrent(Air zone, Plane plane) {
		for (int i =0; i<airList.getLargo(); i++) {
			if (zone.getid() == airList.get(i).getid()) {
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
