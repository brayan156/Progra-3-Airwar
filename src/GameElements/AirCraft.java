package GameElements;

public class AirCraft extends Air {
	/*Atributes*/	
	
	/*Constructor*/
	public AirCraft(double x, double y, char id) {
	   super(x, y, id);
	}
	
    public void print() {
    	System.out.println(toString());
    }
    
	@Override
	public String toString() {
		String str = "[Craft : "+getid()+"] ("+posx+","+posy+")";
		return str;
	}
	

}
