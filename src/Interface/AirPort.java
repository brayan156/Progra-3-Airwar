package Interface;

import javafx.scene.image.ImageView;

public class AirPort extends ImageView {
	/*Atributes*/
	private int posx, posy;
	private boolean empty;
	
	
	/*Constructor*/
	public AirPort(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
		this.setEmpty(true);
	}
	
	
	/*Getters*/
	public int getPosy() {
		return posy;
	}

	public int getPosx() {
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
