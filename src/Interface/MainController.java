package Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class MainController extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage gameStage) throws Exception{
    	
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene scene = new Scene(root);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {gameStage.close();}});
        gameStage.setTitle("Air War");
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.getIcons().add(new Image("/Media/battery.png"));
        gameStage.show();
    }

}
