package Figures;

public class Okrag extends Figura{
    Point srodek = new Point();
    double promien;

    public Okrag(Point srodek, double promien) {
        this.srodek = srodek;
        this.promien = promien;
    }

    public Okrag(){
        this.srodek = new Point(0,0);
        this.promien = 0;
    }

    public double getPowierzchnia(){
        return Math.PI*promien*promien;
    }

    public double getSrednica(){
        return 2*this.promien;
    }

    public double getPromien() {
        return promien;
    }

    public void setPromien(double promien) {
        this.promien = promien;
    }

    public boolean wSrodku(Point p){
        double dx = p.x-srodek.x;
        double dy = p.y-srodek.y;
        return (dx*dx + dy*dy) < (promien*promien);
    }
}
