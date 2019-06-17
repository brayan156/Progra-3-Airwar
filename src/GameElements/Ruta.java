package GameElements;


public class Ruta {
    public char inicio, fin;
    public double distancia,peligro,peso, pesoair;

    public Ruta(char inicio, char fin, double distancia, Boolean esairport1, Boolean esairport2) {
        this.inicio = inicio;
        this.fin = fin;
        this.distancia = distancia;
        pesoair=0;
        peligro=0;
        sumartipoair(esairport1);
        sumartipoair(esairport2);
        this.peso = distancia+this.pesoair;
    }

    public void bajarpeligro (){
        if (peligro<10){
            peso-=peligro;
            peligro=0;
        }
        else {
            peligro -= 10;
            peso -= 10;
        }
    }
    private void sumartipoair(Boolean esairport){
        if (esairport){pesoair+=50;}
        else {pesoair+=100;}
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
        this.peligro += peligro;
        this.peso=this.distancia+this.peligro+this.pesoair;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }


    @Override
    public String toString() {
        String str = distancia+":{"+inicio+"~"+fin+"}";
        return str;

    }
}