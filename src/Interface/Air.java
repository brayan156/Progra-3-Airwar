package Interface;

import javafx.scene.image.ImageView;

public class Air extends ImageView {
	/*Atributes*/
	private double posx, posy;
	private boolean empty;
	
	
	/*Constructor*/
	public Air(double x, double y) {
		this.posx = x;
		this.posy = y;
		this.setEmpty(true);
	}
	
	
	/*Getters*/
	public double getPosy() {
		return posy;
	}

	public double getPosx() {
		return posx;
	}

	public boolean isEmpty() {
		return empty;
	}

	
	/*Setters*/
	public void setPosx(int posx) {
		this.posx = posx;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
}
