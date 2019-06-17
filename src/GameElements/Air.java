package GameElements;

import javafx.scene.image.ImageView;

public class Air extends ImageView {
	/*Atributes*/
	protected double posx;
	protected double posy;
	protected Plane plane=null;
	protected char id;
	
	/*Constructor*/
	public Air(double x, double y, char id) {
		this.posx = x;
		this.posy = y;
		this.id = id;	
	}
	
	public void receivePlane(Plane plane) {
		this.plane = plane;
	}

	public boolean isEmpty() {
		if (plane==null) return true;
		return false;
	}
	
    public void print() {
    	System.out.println(toString());
    }
    
	@Override
	public String toString() {
		String str = "[Air : "+getId()+"] ("+posx+","+posy+")";
		return str;
	}
	
	public String getShortDetails() {
		String str = "[Zone : "+getId()+"]";
		return str;
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


	/*Setters*/
	public void setPosx(int posx) {
		this.posx = posx;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public void setPlane(Plane plane) {
		this.plane=plane;
	}
	public void setPlanesZone() {
		this.plane.setCurrentZone(this);
	}

	public char getid() {
		return id;
	}

	public void setid(char id) {
		this.id = id;
	}
	
}
