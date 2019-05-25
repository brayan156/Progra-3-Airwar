package Interface;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller {

    @FXML private AnchorPane mapAnchorPane = new AnchorPane();
    @FXML private ImageView batteryImageView = new ImageView();
    @FXML private Rectangle r= new Rectangle();
    

    
    
    
	//initializer 
    public void initialize() throws InterruptedException {	
		/*initialize calls*/
		batteryMovement();
	}
    
    
    /*AIR BATTERY MOVEMENT*/
    private void batteryMovement() throws InterruptedException {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5)); //Duracion por ciclo
		transition.setCycleCount(-1);  //-1 = INDEFINITE Cycle
		transition.setAutoReverse(true);
		transition.setToX(mapAnchorPane.getPrefWidth()-batteryImageView.getFitWidth()+12);
		transition.setNode(r);
//		transition.setNode(batteryImageView);
		transition.play();
    }
    
    
    

    
}