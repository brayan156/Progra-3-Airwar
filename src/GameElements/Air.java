package GameElements;

import javafx.scene.image.ImageView;

public class Air extends ImageView {
	/*Atributes*/
	protected double posx;
	protected double posy;
	protected Plane plane=null;
	protected int id;
	private static int idCounter;
	
	/*Constructor*/
	public Air(double x, double y) {
		this.posx = x;
		this.posy = y;
		this.id = idCounter;
		idCounter+=1;
	}
	
	public void receivePlane(Plane plane) {
		this.plane = plane;
	}

	public boolean isEmpty() {
		if (plane==null) return true;
		return false;
	}

	
	
	/*Getters*/
	public double getPosy() {
		return posy;
	}

	public double getPosx() {
		return posx;
	}
	
	public Plane getPlane() {
		return plane;
	}
	
	public int getid() {
		return id;
	}


	/*Setters*/
	public void setPosx(int posx) {
		this.posx = posx;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public void setEmpty() {
		this.plane =null;
	}
}
