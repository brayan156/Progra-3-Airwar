package Interface;

import Loops.TimeSchedule;
import Loops.TimerGenerate;
import Others.BasicFunctions;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller {
	
	/*Atributes*/
	//game interface elements
    @FXML private AnchorPane mapAnchorPane = new AnchorPane();
    @FXML private ImageView batteryImageView = new ImageView();
    @FXML private Rectangle r= new Rectangle();
    @FXML private ProgressBar powerProgressBar = new ProgressBar();
    @FXML private Text aliveText =  new Text(); 
    @FXML private Text  slayedText =  new Text(); 
    //game backgrounds elements
    public static BackGround background = new BackGround();
    public TimeSchedule schGen;
    
    
	/*initializer*/ 
    public void initialize() throws InterruptedException {
    	BasicFunctions.music(); //music
    	TankEvent(); //tank listener-movement
//    	gentrTask();
	}
      
    //scores    
    public void setAlive() { //alive
    	background.setAlive();
    	aliveText.setText(String.valueOf(background.getAlive()));
    }
    public void setSlayed() { //slayed
    	background.setSlayed();
    	slayedText.setText(String.valueOf(background.getSlayed()));
    }
   
	
    
    //tank event
    public void TankEvent() {
    	batteryImageView.setOnKeyPressed(event -> {
    		System.out.print(event);
            if (event.getCode() == KeyCode.SPACE){
            	System.out.print("Pressed");
            }
        });
    	TankMovement();
    }
    
    //tank movement
    private void TankMovement() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(background.getTankSpeed())); //Duracion por ciclo
		transition.setCycleCount(-1);  //-1 = INDEFINITE Cycle
		transition.setAutoReverse(true);
		transition.setToX(mapAnchorPane.getPrefWidth()-batteryImageView.getFitWidth());
		transition.setNode(batteryImageView);
		transition.play();
		System.out.println("Empieza Movimiento Battery");
    }

    //generate loop
    private void gentrTask() {
	    schGen = new TimeSchedule(new TimerGenerate(mapAnchorPane));
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