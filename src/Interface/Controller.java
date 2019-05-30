package Interface;

import java.io.IOError;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import GameElements.AirPort;
import GameElements.Plane;
import Listas.Nodo;
import Listas.NodoList;
import Loops.TimeSchedule;
import Loops.TimerGenerate;
import Others.BasicFunctions;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller {

	//game graffics
    @FXML private AnchorPane mapAnchorPane = new AnchorPane();
    @FXML private ImageView batteryImageView = new ImageView();
    @FXML private Rectangle r= new Rectangle();
    @FXML private ProgressBar powerProgressBar = new ProgressBar();
    @FXML private Text planecounterText =  new Text(); 
    @FXML private Text  killcounterText =  new Text(); 

    //game settings
    public static int battery_lapseSpeed, peligrosidad=0, planecounter = 0, killcounter = 0;
    public static NodoList<AirPort> airportsList = new NodoList<>();
    public static NodoList<Plane> planesList = new NodoList<>();

    public TimeSchedule schGen;
    
    
    
	//initializer 
    public void initialize() throws InterruptedException {
    	BasicFunctions.music(); //pone la musica del juego.
    	batteryMovement(); //movimiento de la barra
    	airZones();
    	gentrTask();
	}

/*=====================================================================================*/  
/*Interface Scores*/    
	//ALIVETEXT
    public void setPlaneText() {
    	planecounter+=1;
    	planecounterText.setText(String.valueOf(planecounter));
    }
    
    
	//KILLTEXT
    public void setKillText() {
    	killcounter+=1;
    	killcounterText.setText(String.valueOf(killcounter));
    }
    
    
	//AIR BATTERY MOVEMENT 
    private void batteryMovement() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(battery_lapseSpeed)); //Duracion por ciclo
		transition.setCycleCount(-1);  //-1 = INDEFINITE Cycle
		transition.setAutoReverse(true);
		transition.setToX(mapAnchorPane.getPrefWidth()-batteryImageView.getFitWidth());
		transition.setNode(batteryImageView);
		transition.play();
		System.out.println("Empieza Movimiento Battery");
    }

/*=====================================================================================*/
/*Logics*/   
    //GENERAR AIRZONES
    private void airZones() {
    	for(int i=0; i<8; i++) {
    		double x  = BasicFunctions.getRandomNum(300);
    		double y  = BasicFunctions.getRandomNum(400);
    		AirPort airport = new AirPort(x, y);
    		airportsList.addLast(airport);
    		System.out.println("AirPort:"+airport.getid()+" x:"+x+" y:"+y);
    	}
    }
    
    private void gentrTask() {
	    schGen = new TimeSchedule(new TimerGenerate(mapAnchorPane));
    }
    
    
    //BUSCAR ATERRIZAJE
    public static AirPort searchPort() {
    	Integer[] ports = randomPorts();
    	for (int i=0; i<ports.length; i++) {
    		AirPort Destination = airportsList.get(i);
    		if (Destination.isEmpty());
    			return Destination;
    	}
		return null;
    }
    private static Integer[] randomPorts() {
    	Integer[] ports = new Integer[Controller.airportsList.getLargo()];
    	for (int i=0; i<ports.length; i++) {
    		ports[i] = BasicFunctions.getRandomNum(ports.length);
    	}return ports;
    }
    
/*=====================================================================================*/
    //VOLAR HACIA ZONA
//    public void flyPlane(Plane plane, AirPort zoneDestination) {
//    	//movimiento
//	    plane.setTransition(zoneDestination.getPosx(), zoneDestination.getPosy());    	
//    	//Dormir mientras avion llega
//    	
//    	//Aterriza
//    	//Dormir mientras es tiempo de volar otra vez.
//    	plane.setFlyOutTime();
//    	long flyTimeOut = plane.getFlyOutTime();
//    	BasicFunctions.sleep(flyTimeOut);
//    	flyPlane(plane, searchPort()); //BUSCAR ATERRIZAJE
//    	
//    }
    
//	private void inspectTask() {
//	long time = 7*1000;
//	Timer timer = new Timer();
//	timer.schedule(new TimerTask(){
//	    public void run(){
//	    	try{drawPlane();}
//	    	catch(IllegalStateException e){System.out.println("No se pudo dibujar");}
//	    }}, 0, time);
//}

//public void drawPlane() {
//	Nodo<Plane> nodo = planesList.getLast();
//	if(nodo==null) return;
//	Plane plane = nodo.getNodo();
//	plane.createimg();
//	System.out.println("\nPlane"+plane.getId());
//	mapAnchorPane.getChildren().add(plane);
//}

    
}