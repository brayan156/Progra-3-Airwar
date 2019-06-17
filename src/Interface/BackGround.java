package Interface;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import GameElements.*;
import Listas.Grafo;
import Listas.NodoList;
import Listas.Nodolista;
import Others.BasicFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
		public NodoList<Ruta> caminos=new NodoList<>();
		private Mapa mapa=new Mapa();
		public NodoList<Line> lineas=new NodoList<>();
		public NodoList<Label> labels=new NodoList<>();

    

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
    		Boolean encima=true;
			Double[] area = null;
    		while (encima) {
    			if (i%2==0) area = mapa.getTierra();
    			else area = mapa.getAgua();
    			System.out.println("SIGUE WHILE : "+area[0]+" "+area[1]);
				encima = encimadeotroair(area[0], area[1]);
			}
			System.out.println(area[0]+" "+area[1]);
//    		System.out.println(encima);
    		if (i%2==0) {
    			AirPort airport = new AirPort(area[0], area[1], this.arrayLetras.get(i));
    			//agregar en listas y string...
    			serieNodos = serieNodos+airport.getid();
        		airList.addLast(airport);
        		airportsList.addLast(airport);
    		}else {
    			AirCraft aircraft = new AirCraft(area[0], area[1], this.arrayLetras.get(i));
    			//agregar en listas y string...
    			serieNodos = serieNodos+aircraft.getid();
        		airList.addLast(aircraft);    			
    		}
    	}
    	System.out.println("SerieNodos: "+serieNodos);
    	setGraph(serieNodos);
    }
    public boolean encimadeotroair(double x,double y){
		int i;
		for (i=0;i<airList.getLargo();i++) {
			Air air=airList.get(i);
			if (Math.abs(x - air.getPosx()) < 100 && Math.abs(y - air.getPosy()) < 100) {
				return true;
			}
		}
		return false;

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
    		NodoList<Air> nodes = getRandomAir(air);
    		Air air1=nodes.get(0);
    		Air air2=nodes.get(1);
    		Ruta camino1=crearcamino(air,air1);
    		Ruta camino2=crearcamino(air,air2);
    		System.out.println(air.getid()+","+air1.getid()+","+air2.getid());
    		pintarlinea(air.getPosx()+20,air1.getPosx()+20,air.getPosy()+20,air1.getPosy()+20);
			pintarlinea(air.getPosx()+20,air2.getPosx()+20,air.getPosy()+20,air2.getPosy()+20);
    		graph.agregarRuta(air.getid(), air1.getid(), (int) camino1.getPeso());
    		graph.agregarRuta(air.getid(), air2.getid(), (int) camino2.getPeso());
		}
	}
	public void pintarlinea(double x1,double x2, double y3, double y4){
		Line linea = new Line(x1,y3,x2,y4);
		// Se asina el color de relleno de la linea
		linea.setStroke(Color.STEELBLUE);
		// Se indica el grosor que tendra nuestra linea
		linea.setStrokeWidth(0.5);
		// se indica el tipo de punta que tendra la linea
		// se agrega la linea al Scene.
		lineas.addLast(linea);
	}
	private Ruta crearcamino(Air air,Air air1){
		Ruta camino;
		for(int i=0; i<caminos.getLargo(); i++) {
			Ruta tmp=caminos.get(i);
			if ((tmp.inicio==air.getid() && tmp.fin==air1.getid()) || (tmp.inicio==air1.getid() && tmp.fin==air.getid())){
				return tmp;
			}
		}
		double distancia = this.getDistance(air, air1);
		if (mapa.interoceanica(air.getPosx(), air.getPosy(), air1.getPosx(), air1.getPosy())) {
			camino = new Ruta(air.getid(), air1.getid(), distancia + 200, soyAirport(air), soyAirport(air1));
		} else {
			camino = new Ruta(air.getid(), air1.getid(), distancia, soyAirport(air), soyAirport(air1));
		}
		caminos.addLast(camino);
		return camino;
	}
	public void cambiarpeso(char i, char f, Ruta camino){
//		System.out.println("voy a cambiar peligrosidad");
//		System.out.println(graph.encontrarRutaMinimaDijkstra(i,f));
		graph.agregarRuta(i,f,(int) camino.getPeso());
//		System.out.println(graph.encontrarRutaMinimaDijkstra(i,f));
	}
	private Boolean soyAirport(Air air){
		for(int i=0; i<airportsList.getLargo(); i++) {
			if (airportsList.get(i).getid()==air.getid()){
				return true;
			}
		}
		return false;
	}
	//array con dos random air diferentes al original y diferentes entre si.
    private NodoList<Air> getRandomAir(Air air) {
    	Air tmp1, tmp2 = null;
    	NodoList <Air> nodes = new NodoList<>();
    	while(true) {
    		tmp1 = randomAir(random);
    		tmp2 = randomAir(random);
    		if (tmp1.getid()!=air.getid()  &&  tmp2.getid()!=air.getid()  &&  tmp1.getid()!=tmp2.getid()) {
				nodes.addLast(tmp1);
				nodes.addLast(tmp2);
				break;
			}
    	}
    	return nodes;
    }private Air randomAir(Random random) {
    	int r = BasicFunctions.getRandomNum(airList.getLargo()-1);
    	return airList.get(r);
    }

    //calcular distancia entre los nodos.
    private double getDistance(Air vertice1, Air vertice2) {
		//formula de distancia entre dos puntos de un plano y pitagoras.
		double distanciaEnX = Math.abs(vertice2.getPosx() - vertice1.getPosx());
		double distanciaEnY = Math.abs(vertice2.getPosy() - vertice1.getPosy());
		double hipotenusa = Math.sqrt(Math.pow(distanciaEnX, 2)+Math.pow(distanciaEnY, 2));
//		System.out.println("Distancia en X > "+ distanciaEnX+ "Distancia en Y > "+ distanciaEnY);
    	return hipotenusa;
    }
    
    //ADD AVION
    public void addPlane(Plane plane) {
		this.planesList.addLast(plane);
		this.labels.addLast(new Label());
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
