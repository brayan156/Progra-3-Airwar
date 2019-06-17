package GameElements;


import java.util.Random;

import Interface.Controller;
import Interface.Transition;
import Listas.Nodo;
import Listas.NodoList;
import Others.BasicFunctions;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Plane extends ImageView {
		private static final int TimeOut = BasicFunctions.ParseInt(BasicFunctions.getPropertyKey("flyOutTime"));
		private Air air = null;
		private boolean Alive = true;
		private boolean done = true;
		private long flyOutTime=0;
		private String img,url;
		private Transition planeTransition = new Transition(this);
		double posx, posy;
    	private Random random = new Random();
		private int rangox,rangoy, onNode;
		private NodoList<Character> camino = new NodoList<>();
		private Tooltip tooltip = new Tooltip("loading... "+flyOutTime+"s");
    
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
    
	
    //crear imagen
    public void createimg(){
    	Image img = new Image(url, rangox,rangoy, true, true);
        this.setImage(img);
    }
  
    //dibujo avion
    public void draw( ) {
    	createimg();
    	try {Controller.background.getAnchorPane().getChildren().add(this);}
    	catch(IllegalStateException i) {
    		System.out.println("\rNO SE DIBUJA AVION");
    		i.printStackTrace();
    	}
    }
    
    private void installTooltip() {
	  Tooltip.install(this, this.tooltip);
	}


	public boolean isAlive() {
		return Alive;
	}


	public String print(NodoList<Character> camino, char id) {
		String str= "";
		for (int i=0; i<camino.getLargo(); i++) {
			
			str=str+" -> "+camino.get(i);
			if (id == camino.get(i)) {
				str=str+"*";
			}
		}
//		System.out.println("CAMINO: "+str.substring(4));

		return str.substring(4);
	}


	public void slaye(AnchorPane anchorPane) throws InterruptedException {
	//        this.setImage(new Image("file:src/Media/explosion.PNG"));
			anchorPane.getChildren().remove(this);
			Alive = false;
		}


	/*Getters*/
	public Air getCurrentZone() {
		return air;
	}

	public long getFlyOutTime() {
		return flyOutTime;
	}

	public int getOnNode() {
		return onNode;
	}


	public String getPlane() {
		return img;
	}

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

	public NodoList<Character> getCamino() {
		return camino;
	}
	public Tooltip getTooltip() {
		return tooltip;
	}

	public boolean getTransitionDone() {
		return done;
	}


	public String getUrl() {
		return url;
	}	
	
	
	/*Setters*/
	public void setAlive(boolean alive) {
		Alive = alive;
	}


	public void setCurrentZone(Air air) {
		this.air = air;
	}


	public void setFlyOutTime() {
	    this.flyOutTime = BasicFunctions.getRandomNum(4)+2;
	    System.out.println("tiempo de salida= "+flyOutTime);
	}


	public void setOnNode(int onNode) {
		this.onNode = onNode;
	}


	public void setPlane(String avion) {
		this.img = avion;
	}
	public void setCamino(char[] cs) {
		NodoList<Character> ruta=new NodoList<>();
		if (cs!=null) {
			for (int i = 0; i < cs.length; i++) {
				ruta.addLast(cs[i]);
			}
		}
		this.camino = ruta;
	}
    public void setTooltipText(NodoList<Character> arrayCamino, char id) {
		String str = "Plane "+getId() + " | "+ print(arrayCamino, id);
		this.tooltip.setText(str);
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
	public void setUrl(String url) {
		this.url = url;
	}

	public void setXY(double X, double Y) {
		this.posx = X;
		this.posy = Y;
		this.setX(X);
		this.setY(Y);
	}


	
	
	
	@Override
	public String toString() {
		String str = "[PLANE "+getId()+"] ("+posx+","+posy+")"+"(img:"+img+")";
		return str;
	}

}
