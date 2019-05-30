package GameElements;


import java.util.Random;

import Interface.Controller;
import Interface.Transition;
import Others.BasicFunctions;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Plane extends ImageView {
		private static final int TimeOut = BasicFunctions.ParseInt(BasicFunctions.getPropertyKey("flyOutTime"));
		private Random random = new Random();

		double posx;
		double posy;
		private long flyOutTime=0;
		private String plane,url;
		private boolean Alive = true;
    	private Transition planeTransition = new Transition(this);
    	
    	
	/*Constructor*/
    public Plane(double x, double y) {
        super();
        this.setPlane(EnumPlanes.getRandomAvion());
        this.setUrl("file:src/Media/"+plane+".PNG");
        this.posx=x;
        this.posy=y;
        this.setX(this.posx);
        this.setY(this.posy);
        this.setId(String.valueOf(AirPort.counter));  AirPort.counter+=1;
        this.setFlyOutTime();
    }
    
    //crear imagen
    public void createimg(){
    	Image img = new Image(url, 40,40, true, true);
        this.setImage(img);
    }
    
    public boolean isAlive() {
		return Alive;
	}

	public void setAlive(boolean alive) {
		Alive = alive;
	}

	//dibujo avion
    public void draw(AnchorPane anchorPane) {
    	createimg();
    	anchorPane.getChildren().add(this);
    }
	public void kill(AnchorPane anchorPane) throws InterruptedException {
        this.setImage(new Image("file:src/Media/explosion.PNG"));
        Thread.sleep(1000);
		anchorPane.getChildren().remove(this);
		Alive = false;
	}
	
	public void printCoordinates() {
    	System.out.println("Coordenadas X "+posx+" \nCoordenadas Y "+posy);
	}
    
    /*Getters*/
    public double getPosx() {
		return posx;
	}
    
	public double getPosy() {
		return posy;
	}

	public long getFlyOutTime() {
		System.out.println("Plane FlyOutTime: "+flyOutTime);
		return flyOutTime;
	}

	public String getUrl() {
		return url;
	}
	
	public String getPlane() {
		return plane;
	}

	/*Setters*/
	public void setXY(double X, double Y) {
		this.posx = X;
		this.posy = Y;
		this.setX(X);
		this.setY(Y);
	}

	public void setFlyOutTime() {
	    this.flyOutTime = random.nextInt(TimeOut - Controller.peligrosidad*10)+2500;
	}
	
    public void setPlane(String avion) {
		this.plane = avion;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		String str = "PLANE "+getId()+" --> img:"+plane+",posx:"+posx+",posy:"+posy;
		return str;
	}

	public void setTransition(double destX, double destY) {
		this.planeTransition.setTo(destX, destY);
		this.planeTransition.start();
		int deploying = this.planeTransition.getDuration();
		System.out.println("\nDeploying : "+deploying);
		BasicFunctions.sleep(deploying);
		this.setXY(destX, destY);
	}

}
