package GameElements;

import javafx.scene.image.ImageView;

public class Air extends ImageView {
	/*Atributes*/
	protected double posx;
	protected double posy;
	protected Plane plane=null;
	protected int id;
	private static int idCounter=0;
	
	/*Constructor*/
	public Air(double x, double y) {
		this.posx = x;
		this.posy = y;
		idCounter+=1;
		this.setId(String.valueOf(idCounter));	
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
	
}
