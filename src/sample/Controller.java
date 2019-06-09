package sample;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    public Long timei,timef;
    @FXML ImageView tanque;

    public void keypressed(KeyEvent event){
        System.out.println("pongo el click");
        if (event.getCode().equals(KeyCode.SPACE)){

            ImageView img = (ImageView) (event.getSource());
            timei = System.nanoTime();
            System.out.print("Pressed");
        }
    }
    public void keyreleased(KeyEvent event){
        System.out.println("quito el click");
        if (event.getCode().equals(KeyCode.SPACE)){
            ImageView img = (ImageView) (event.getSource());
            timef = System.nanoTime() - timei;
            System.out.println(timef.toString());
            System.out.print("Pressed");
        }
    }
    public void hola(){System.out.println("hola");}
}
