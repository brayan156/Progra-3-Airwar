package Interface;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainController extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage gameStage) throws Exception{
    	
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene scene = new Scene(root);
        gameStage.setScene(scene);
        gameStage.initStyle(StageStyle.UNDECORATED);
        gameStage.setResizable(false);
        gameStage.getIcons().add(new Image("/Media/tank.png"));
        gameStage.show();
    }


}
