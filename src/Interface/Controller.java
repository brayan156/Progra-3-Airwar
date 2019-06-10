package Interface;

import GameElements.AirPort;
import GameElements.Bala;
import Listas.NodoList;
import Loops.TimeSchedule;
import Loops.TimerGenerate;
import Others.BasicFunctions;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
	    private long timei,timef;
	    private Boolean presionado=false;
	    private NodoList<Bala> listabala= new NodoList<>();


        private AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    int cont;
                    for (cont=0;cont<listabala.getLargo();cont++){
                        Bala bala=listabala.get(cont);
                        bala.setY(bala.getY()-bala.getVelocidad());
                        //aqui tambien va ver si esta dentro del rango de un avion eliminar a ambos y subir la peligrosidad al camino
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    
    
	/*initializer*/ 
    public void initialize() throws InterruptedException {
    	BasicFunctions.music(); //music
        batteryImageView.setFocusTraversable(true);
        batteryImageView.requestFocus();
    	TankEvent(); //tank listener-movement
//    	gentrTask();
        timer.start();
    	drawAir();
    	toDo();
    	toDo();
    	toDo();
    	toDo();
    	
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
    public void drawAir() {
    	for(int i=0; i<background.getZones().getLargo(); i++) { 
	        Rectangle r = new Rectangle();
	        r.setX(background.getZones().get(i).getPosx());
	        r.setY(background.getZones().get(i).getPosy());
	        r.setWidth(40);
	        r.setHeight(40);
//	        r.setArcWidth(50);
//	        r.setArcHeight(50);
    		if (background.getZones().get(i) instanceof AirPort) {
    	        r.setFill(Color.RED);
    	        r.setStroke(Color.BLUE);
    		}else {
    	        r.setFill(Color.GREEN);
    	        r.setStroke(Color.ORANGE);
    		}
	        mapAnchorPane.getChildren().add(r);
    	}
    }
   
    //tank event
    public void TankEvent() {
    	batteryImageView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE){
                if (!presionado) {
                    System.out.println("he clickeado");
                    timei = System.currentTimeMillis();
                    presionado=true;
                }
            }
        });
        batteryImageView.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.SPACE){
                ImageView img=(ImageView) (event.getSource());
                System.out.println("quite click");
                if (presionado) {
                    timef = System.currentTimeMillis()-timei;
                    Double velocidad= (Double.parseDouble(String.valueOf(timef))/1000+1)*3;
                    System.out.println(img.getTranslateX());
                    Bala bala=new Bala(img.getTranslateX(),img.getY(),velocidad);
                    mapAnchorPane.getChildren().add(bala);
                    listabala.addLast(bala);
                    presionado=false;

                }
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
		System.out.println(transition.getFromX());
//		System.out.println("Empieza Movimiento Battery");
    }

    //generate loop
    private void gentrTask() {
	    schGen = new TimeSchedule(new TimerGenerate(mapAnchorPane));
    }

    private void toDo(){
        AirPort airport = Controller.background.searchAirPort();
        if (airport==null) return;
        airport.generatePlane(mapAnchorPane);
        airport.print();
//		System.out.println("Plane Succesfully Created");
        return;
    }
    
	private void toDo(){
		AirPort airport = Controller.background.searchAirPort();
		if (airport==null) return;
		airport.generatePlane(mapAnchorPane);
		airport.print();
//		System.out.println("Plane Succesfully Created");
		return;
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