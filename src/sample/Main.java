package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene=new Scene(root, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {primaryStage.close();}
            else if (KeyCode.SPACE == event.getCode())
                System.out.print("Pressed Main");
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
