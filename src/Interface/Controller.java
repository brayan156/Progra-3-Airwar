package Interface;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    public static int battery_lapseSpeed, peligrosidad=0, planecounter = 0, killcounter = 0,planeGenerated=0;
//	private long timerGenerator=1;
    private Random random = new Random();

   
    
    
	//initializer 
    public void initialize() throws InterruptedException {
    	music(); //pone la musica del juego.
    	difficultyLevel(); //nivel de dificultad, setear tiempo de movimiento de la bateria.
    	batteryMovement(); //movimiento de la barra
    	generatePlane();
//    	timerTask();
	}

    
    private static void music() {
    	String songname = "MSTrials-Lol";
    	if (peligrosidad == 5) { songname = "CS16-Lol";}
    	Media hit = new Media(new File("src/Media/"+songname+".mp3").toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(hit);
    	mediaPlayer.play();
    }

    
	//DIFFICULTY
    private void difficultyLevel() {
    	battery_lapseSpeed = 5-peligrosidad;
	}

	//ALIVETEXT
    public void setPlaneText() {
    	planecounter+=1;
    	planeGenerated+=1;
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
//		transition.setNode(r);
		transition.setNode(batteryImageView);
		transition.play();
		System.out.println("Empieza Movimiento Battery");
    }
    
    //RANDOM NUM
    public int getRandomNum(double d) {
    	int num = random.nextInt((int) d)+1;
    	return num;
    }
    
    public void timerTask() throws InterruptedException {
    	Timer timer = new Timer();
    	timer.schedule( new TimerTask()
    	{
    	    public void run()
    	    {
    	        System.out.println("3 Seconds Later");
				generatePlane(searchPort());	

    	    }
    	}, 3000, 3000 //Note the second argument for repetition
    	);
    }
    
    //NEW THREAD
    public void generatePlane() {
    	Thread t = new Thread(new Runnable() {
    		@Override
    		public void run() {
    				generatePlane(searchPort());
    		}
    	});  
	    t.start();
    }
   
    //BIRTH PLAN
    public void generatePlane(AirPort airportBirth){
    	if (!airportBirth.isEmpty()) return;    	
    	//aeropuerto en que se crea manda sus posiciones.
    	Plane plane = new Plane(airportBirth.getPosx(), airportBirth.getPosy());
//    	plane.createimg();
//    	mapAnchorPane.getChildren().add(plane);
    	plane.draw(mapAnchorPane);
    	System.out.println("SE DIBUJAAAAAAA");
    	airportBirth.setEmpty(true);
    	this.setPlaneText();
		flyPlane(plane, searchPort());
    }
    
    //BUSCAR ATERRIZAJE
    public AirPort searchPort() {
    	int x=0;
    	int y=0;
	    x = getRandomNum(700);
    	y = getRandomNum(500);
    	/*obtener otro lugar donde aterrizar buscar en la lista. o como sea*/
    	AirPort airportDestination = new AirPort(x,y);
    	/*obtener otro lugar donde aterrizar buscar en la lista. o como sea*/
    	return airportDestination;
    }
    
    //VOLAR HACIA ZONA
    public void flyPlane(Plane plane, AirPort zoneDestination) {
    	zoneDestination.setEmpty(false); // setear airport a ocupado.    	
    	//movimiento
    	double destX = zoneDestination.getPosx();
    	double destY = zoneDestination.getPosy();
    	Transition planeTransition = new Transition(plane, destX, destY);
    	
    	planeTransition.start();
    	//Dormir mientras avion llega
    	long duration = planeTransition.getDuration();
    	sleep(duration);
    	
    	//Aterriza
    	zoneDestination.setEmpty(true);
    	System.out.println("Aterriza "+plane.getPlane()+plane.getId());
    	plane.setPosXY(zoneDestination.getPosx(), zoneDestination.getPosy());
    	//Dormir mientras es tiempo de volar otra vez.
    	plane.setFlyOutTime();
    	long flyTimeOut = plane.getFlyOutTime();
		sleep(flyTimeOut);
    	flyPlane(plane, searchPort()); //BUSCAR ATERRIZAJE
    	
    }
    
    public void sleep(double sleep) {
    	try {
			Thread.sleep((long) (sleep*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
//			System.out.println("No se duerme el Thread. Error");
		}
    }
    
    

    
}