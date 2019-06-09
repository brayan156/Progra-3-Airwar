package GameElements; 
 
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView; 
 
public class Bala extends ImageView { 
    private int posx,posy,valor; 
    private String url; 
    private int rangox,rangoy; 
    private double velocidad; 
    public Bala (int posx, int posy, double velocidad) { 
        super(); 
        this.setUrl("file:src/Media/"+"grenade"+".png"); 
        this.posx=posx; 
        this.posy=posy; 
        this.setX(this.posx); 
        this.setY(this.posy); 
        this.crearimagen(); 
    } 
    public int getPosx() { return posx; } 
 
    public void setPosx(int posx) { 
        this.posx = posx; 
        this.setX(this.posx); 
    } 
 
    public int getPosy() { 
        return posy; 
 
    } 
 
    public void setPosy(int posy) { 
        this.posy = posy; 
        this.setY(this.posy); 
    } 
 
    public int getValor() { 
        return valor; 
    } 
 
    public void setValor(int valor) { 
        this.valor = valor; 
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
 
 
 
