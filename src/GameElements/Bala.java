package GameElements; 
 
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView; 
 
public class Bala extends ImageView { 
    private Double posx,posy;
    private String url; 
    private int rangox,rangoy; 
    private double velocidad; 
    public Bala (Double posx, Double posy, double velocidad) {
        super(); 
        this.setUrl("file:src/Media/"+"grenade"+".png"); 
        this.posx=posx; 
        this.posy=posy; 
        this.setX(this.posx); 
        this.setY(this.posy); 
        this.crearimagen();
        this.rangox=40;
        this.rangoy=40;
        this.setFitWidth(rangox);
        this.setFitHeight(rangoy);
        this.velocidad=velocidad;
    }

    public Double getPosx() {
        return posx;
    }

    public void setPosx(Double posx) {
        this.posx = posx;
    }

    public Double getPosy() {
        return posy;
    }

    public void setPosy(Double posy) {
        this.posy = posy;
    }

    public int getRangox() {
        return rangox;
    }

    public void setRangox(int rangox) {
        this.rangox = rangox;
    }

    public int getRangoy() {
        return rangoy;
    }

    public void setRangoy(int rangoy) {
        this.rangoy = rangoy;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public void crearimagen(){
        this.setImage(new Image(url)); 
    } 
    public String getUrl() { 
        return url; 
    } 
    public void setUrl(String url) { 
        this.url = url; 
    } 
} 
 
 
 
