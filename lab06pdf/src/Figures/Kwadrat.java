package Figures;

public class Kwadrat extends Prostokat{

    public Kwadrat(String kolor, double bok, Point point) {
        super(kolor, bok, bok, point);
    }
    public double getBok(){
        return this.szer;
    }

    public void setBok(double bok){
        this.szer = bok;
        this.wys = bok;
    }

    @Override
    public String opis(){
        return "Kwadrat, kolor: " + this.kolor + " o boku: " + this.getBok();
    }
}
