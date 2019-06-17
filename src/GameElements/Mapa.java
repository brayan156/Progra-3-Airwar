package GameElements;

import java.awt.*;

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
    private String darcontinente (double x, double y){
        if (x<x1 && y<y1){return "cont1";}
        else if (x<x2 && y>y2){return "cont2";}
        else if (x>x3 && y>y3){return "cont3";}
        else {return "agua";}
    }
}
