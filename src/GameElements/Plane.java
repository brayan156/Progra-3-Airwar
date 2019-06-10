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
		private String img,url;
		private boolean Alive = true;
    	private Transition planeTransition = new Transition(this);
		private Air air = null;
    
	/*Constructor*/
    public Plane(double x, double y) {
        super();
        this.setPlane(EnumPlanes.getRandomAvion());
        this.setUrl("file:src/Media/"+img+".PNG");
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
	public void slaye(AnchorPane anchorPane) throws InterruptedException {
        this.setImage(new Image("file:src/Media/explosion.PNG"));
        Thread.sleep(1000);
		anchorPane.getChildren().remove(this);
		Alive = false;
	}
	
	public void print() {
	System.out.println(toString());
	}
	
	@Override
	public String toString() {
		String str = "[PLANE "+getId()+"] ("+posx+","+posy+")"+"(img:"+img+")";
		return str;
	}
	
    
    /*Getters*/
    public double getPosx() {
		return posx;
	}
    
	public double getPosy() {
		return posy;
	}

	public long getFlyOutTime() {
		return flyOutTime;
	}

	public String getUrl() {
		return url;
	}
	
	public String getPlane() {
		return img;
	}

	/*Setters*/
	public void setXY(double X, double Y) {
		this.posx = X;
		this.posy = Y;
		this.setX(X);
		this.setY(Y);
	}

	public void setFlyOutTime() {
	    this.flyOutTime = random.nextInt(TimeOut - Controller.background.getRisk()*10)+2500;
	}
	
    public void setPlane(String avion) {
		this.img = avion;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTransition(Air prevZone, Air nextZone) {
		if (prevZone == null || nextZone == null) return;
		Controller.background.setCurrent(nextZone, this); //set next location to full
		Controller.background.setCurrent(prevZone, null); //set prev location to empty
		planeTransition.setTo(nextZone.getPosx(), nextZone.getPosy());
		planeTransition.start(); /*start animation*/
		/*prints*/
		System.out.print("\nDeploying : "+planeTransition.getDuration()+" seg. (plane:"+getId()+") -> ");nextZone.print();
		BasicFunctions.sleep(planeTransition.getDuration()+2);
		planeTransition.getTransition().setOnFinished(event -> {
			System.out.print("\nbefore : "); print();
			this.setXY(getX()+getTranslateX(), getY()+getTranslateY());
		    this.setTranslateX(0);
		    System.out.print("after : "); print();
		    this.setTranslateY(0);
		});
	}

	public void setCurrentZone(Air air) {
		this.air = air;
	}
	public Air getCurrentZone() {
		return air;
	}

}
