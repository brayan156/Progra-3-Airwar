package Interface;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

import javafx.scene.image.Image;

public class Plane extends ImageView {
		private static final int TimeOut = 15;
		double posx;
		double posy;
		private int flyOutTime=0;
		private String plane,url;
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
        this.setFlyOutTime();
    }
    
    //crear imagen
    public void createimg(){
    	Image img = new Image(url, 75, 75, true, true);
        this.setImage(img);
    }
    
    //dibujo avion
    public void draw(AnchorPane anchorPane) {
    	createimg();
    	anchorPane.getChildren().add(this);
//    	Controller.setPlaneText();
    }
	public void kill(AnchorPane anchorPane) throws InterruptedException {
        this.setImage(new Image("file:src/Media/explosion.PNG"));
        Thread.sleep(1000);
		anchorPane.getChildren().remove(this);
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
