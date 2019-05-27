package Interface;


import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Plane extends ImageView {
		private static final int TimeOut = 15;
		double posx;
		double posy;
		private int flyOutTime=0;
		private String plane,url;
		private boolean Alive = true;
		private Random random = new Random();
		
	/*Constructor*/
    public Plane(int posx, int posy) {
        super();
        this.setPlane(EnumPlanes.getRandomAvion());
        this.setUrl("file:src/Media/"+plane+".PNG");
        this.posx=posx;
        this.posy=posy;
        this.setX(this.posx);
        this.setY(this.posy);
        this.setId(String.valueOf(Controller.planeGenerated));
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

	public int getFlyOutTime() {
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
	public void setPosx(int posx) {
		this.posx = posx;
//		this.setX(this.posx);
	}

	public void setPosy(int posy) {
		this.posy = posy;
//		this.setY(this.posy);
	}
	
	public void setPosXY(double nextX, double nextY) {
		this.posx = nextX;
		this.posy = nextY;
//		this.setX(posx);
//		this.setY(posy);
	}

	public void setFlyOutTime() {
	    this.flyOutTime = random.nextInt(TimeOut- Controller.peligrosidad*10)+1;
	}
	
    public void setPlane(String avion) {
		this.plane = avion;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}
