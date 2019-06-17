package GameElements;

public class ruta {
    public char inicio, fin;
    public double distancia,peligro,peso;

    public ruta(char inicio, char fin, double distancia) {
        this.inicio = inicio;
        this.fin = fin;
        this.distancia = distancia;
        this.peso = distancia;
    }

    public void bajarpeligro (){
        peligro-=10;
        peso-=10;
    }

    public char getInicio() {
        return inicio;
    }

    public void setInicio(char inicio) {
        this.inicio = inicio;
    }

    public char getFin() {
        return fin;
    }

    public void setFin(char fin) {
        this.fin = fin;
    }

    public double getPeligro() {
        return peligro;
    }

    public void setPeligro(double peligro) {
        this.peligro = peligro;
        this.peso=this.distancia+this.peligro;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
