package GameElements;


import java.util.Random;

import Interface.Controller;
import Interface.Transition;
import Others.BasicFunctions;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Plane extends ImageView {
		private static final int TimeOut = BasicFunctions.ParseInt(BasicFunctions.getPropertyKey("flyOutTime"));
		private Random random = new Random();
		private boolean done = true;

		double posx;
		double posy;
		private long flyOutTime=0;
		private String img,url;
		private boolean Alive = true;
    	private Transition planeTransition = new Transition(this);
		private Air air = null;
		private Tooltip tooltip = new Tooltip("loading...");
		private int rangox,rangoy;
    
	/*Constructor*/
    public Plane(double x, double y) {
        super();
        this.setPlane(EnumPlanes.getRandomAvion());
        this.setUrl("file:src/Media/"+img+".PNG");
        this.posx=x;
        this.posy=y;
        this.setX(this.posx);
        this.setY(this.posy);
		this.rangox=40;
		this.rangoy=40;
        AirPort.counter+=1;
        this.setId(String.valueOf(AirPort.counter));  
        this.setFlyOutTime();
        installTooltip();
    }
    
	
    private void installTooltip() {
      Tooltip.install(this, this.tooltip);
    }
  
    //crear imagen
    public void createimg(){
    	Image img = new Image(url, rangox,rangoy, true, true);
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
    	try {anchorPane.getChildren().add(this);}
    	catch(IllegalStateException i) {
    		System.out.println("\rNO SE DIBUJA AVION");
    		i.printStackTrace();
    	}
    }
	public void slaye(AnchorPane anchorPane) throws InterruptedException {
//        this.setImage(new Image("file:src/Media/explosion.PNG"));
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
	public double getrealx() {
		return posx+this.getTranslateX();
	}

	public double getrealy() {
		return posy+this.getTranslateY();
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
	public Tooltip getTooltip() {
		return tooltip;
	}
	public Air getCurrentZone() {
		return air;
	}
	public boolean getTransitionDone() {
		return done;
	}


	/*Setters*/
	public void setXY(double X, double Y) {
		this.posx = X;
		this.posy = Y;
		this.setX(X);
		this.setY(Y);
	}
	public void setFlyOutTime() {
//	    this.flyOutTime = random.nextInt(TimeOut - Controller.background.getRisk()*5)+2;
	    this.flyOutTime = random.nextInt(5)+2;
//	    System.out.println(flyOutTime);
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
//		System.out.println("\\\\\\\\\\\\\\\\\\\"");
//		System.out.print("From : ");prevZone.print();
//		System.out.print("To   : ");nextZone.print();
		done=false;
		planeTransition.setTo(nextZone.getPosx(), nextZone.getPosy());
		setTooltipText(prevZone, nextZone);
		planeTransition.start(); /*start animation*/
//		System.out.println(planeTransition.getTransition().getToX());
//		System.out.println(planeTransition.getTransition().getToY());
		/*prints*/
//		System.out.print("\nDeploying : "+planeTransition.getDuration()+" seg. (plane:"+getId()+") -> ");nextZone.print();
//		System.out.println(this.getLocalToSceneTransform());
//		BasicFunctions.sleep(planeTransition.getDuration()+2);
		planeTransition.getTransition().setOnFinished(event -> {
//			System.out.print("\nbefore : "); print();
//			this.setXY(getX()+getTranslateX(), getY()+getTranslateY());
//		    this.setTranslateX(0);
//		    System.out.print("after : "); print();
//		    this.setTranslateY(0);
			air = nextZone;
			done=true;
		});
	}
	public void setCurrentZone(Air air) {
		this.air = air;
	}
	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}
	public void setTooltipText(Air prev, Air next) {
		String str = "Plane "+getId() + " | "+ prev.getShortDetails() + " -> " + next.getShortDetails();
		this.tooltip.setText(str);
	}




}
