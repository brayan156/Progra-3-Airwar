package Interface;

import GameElements.Air;
import GameElements.AirPort;
import Loops.TimeSchedule;
import Others.BasicFunctions;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
	    @FXML private Text  countText =  new Text(); 
	    //game backgrounds elements
	    public static BackGround background = new BackGround();
	    public TimeSchedule schGen;
	    private int count;
    
    
	/*initializer*/ 
    public void initialize() throws InterruptedException {
    	setGraphics();
    	setMusic();
    	TankEvent(); 
    	drawAir();
    	gentrTask();
//    	toDo();
//    	toDo();
    	
	}
    
    //music
    private void setMusic() {
    	BasicFunctions.music();
    }
    
    //mapa y labels
    private void setGraphics() {
    	background.setAnchorPane(mapAnchorPane);
    	background.setAliveText(aliveText);
    	background.setSlayedText(slayedText);
	}

    
	//draw zones
    public void drawAir() {
    	for(int i=0; i<background.getZones().getLargo(); i++) { 
    		Air zone = background.getZones().get(i);
    		final StackPane s = new StackPane();
    		final Rectangle r = new Rectangle();
	        final Text t = new Text ();
    		if (zone instanceof AirPort) {
    	        r.setFill(Color.RED);
    	        r.setStroke(Color.BLUE);
    		}else {
    	        r.setFill(Color.GREEN);
    	        r.setStroke(Color.ORANGE);
    		}
	        t.setText(zone.getId());
	        t.setStyle("-fx-text-fill: white; -fx-font-size: 25px;");
	        r.setWidth(40);
	        r.setHeight(40);
	        r.setArcWidth(50);
	        r.setArcHeight(50);
	        s.setLayoutX(background.getZones().get(i).getPosx());
	        s.setLayoutY(background.getZones().get(i).getPosy());
	        s.getChildren().addAll(r, t);
	        mapAnchorPane.getChildren().add(s);
    	}
    }
   
    
    
    //tank listener-movement
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
    	TankMovement(false);
    }
    
    
    //tank movement
    private void TankMovement(boolean comeback) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(background.getTankSpeed())); //Duracion por ciclo
		transition.setCycleCount(1);  //-1 = INDEFINITE Cycle
		transition.setNode(batteryImageView); //nodo.
		transition.setOnFinished(event -> {
			if (comeback) {TankMovement(false);}
			else {TankMovement(true);}
			return;
		});
		if (comeback) {
			transition.setToX(0);
		}else {
			transition.setToX(mapAnchorPane.getPrefWidth()-batteryImageView.getFitWidth());
		}
		transition.play();
    }
    
    //generate loop
    private void gentrTask() {
    	long time = BasicFunctions.ParseLong(BasicFunctions.getPropertyKey("spawn"));
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(time), generate -> {
			countdownTask();
			AirPort airport = Controller.background.searchAirPort();
			if (airport==null) {return;}
			else if (Controller.background.getAlive() == 6) {System.out.println("MAXIMO DE AVIONES EN PANTALLA (6)"); return;}
			airport.generatePlane();
			System.out.print("Plane Succesfully Created in : "); airport.print();
		}));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
    }
    
    private void countdownTask() {
    	this.count=3;
    	Timeline cd = new Timeline(new KeyFrame(Duration.seconds(1), write -> {
    		countText.setText(count+" s");
    		this.count-=1;
    	}));
    	cd.setCycleCount(4);
    	cd.play();
    }
    

    
}