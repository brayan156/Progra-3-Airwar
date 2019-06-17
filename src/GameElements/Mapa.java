package GameElements;

import Others.BasicFunctions;

public class Mapa {
    double x1,x2,x3,y1,y2,y3;

    public Mapa() {
        this.x1 = 505;
        this.x2 = 565;
        this.x3 = 665;
        this.y1 = 203;
        this.y2 = 293;
        this.y3 = 371;
    }
    public Boolean interoceanica (double xi,double yi,double xf,double yf){
        String conti,contf;
        conti=this.darcontinente(xi,yi);
        contf=this.darcontinente(xf,yf);
        if (conti.equals(contf)){
            return false;
        }
        else {return true;}
    }
    public String darcontinente (double x, double y){
        if (x+20<x1 && y+20<y1){return "cont1";}
        else if (x+20<x2 && y+20>y2){return "cont2";}
        else if (x+20>x3 && y+20>y3){return "cont3";}
        else {return "agua";}
    }
    
    
    public Double[] getTierra() {
		int continente = BasicFunctions.getRandomNum(3);
//		System.out.println("Continente :" +continente);
		double x,y = 0;
		if (continente == 1) {			
			x = BasicFunctions.getRandomNum(x1);
			y = BasicFunctions.getRandomNum(y1);
		}
		else if (continente == 2) {
			x = BasicFunctions.getRandomNum(x2-x1)+x1;
			y = BasicFunctions.getRandomNum(y2-y1)+y1;
		}
		else {
			x = BasicFunctions.getRandomNum(x3-x2)+x2;
			y = BasicFunctions.getRandomNum(y3-y2)+y3;
		}
    	String[] var = (x+","+y).split(",");
    	Double[] dob = new Double[2];
    	dob[0]= Double.parseDouble(var[0]);
    	dob[1]= Double.parseDouble(var[1]);
    	return dob;
    }
	public Double[] getAgua() {
		int x,y = 0;
		x = BasicFunctions.getRandomNum(800);
		y = BasicFunctions.getRandomNum(800);
    	String[] var = (x+","+y).split(",");
    	Double[] dob = new Double[2];
    	dob[0]= Double.parseDouble(var[0]);
    	dob[1]= Double.parseDouble(var[1]);
    	return dob;
	}
}
