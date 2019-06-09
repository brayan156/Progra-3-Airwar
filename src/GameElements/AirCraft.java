package GameElements;

public class AirCraft extends Air {
	/*Atributes*/	
	
	/*Constructor*/
	public AirCraft(double x, double y) {
	   super(x, y);
	}
	
    public void print() {
    	System.out.println(toString());
    }
    
	@Override
	public String toString() {
		String str = "[Craft : "+getId()+"] ("+posx+","+posy+")";
		return str;
	}
	
}
