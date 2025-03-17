package Figures;

public class Prostokat extends Figura{
	double wys=0, szer=0;
	public Prostokat(double wys,double szer){
		this.wys = wys;
		this.szer = szer;
	}

	public Prostokat(String kolor, double wys, double szer, Point point) {
		super(kolor);
		this.point = point;
		this.wys = wys;
		this.szer = szer;
	}

	double getPowierzchnia() {
        return (szer * wys);
    }

	public void przesun(float x, float y){
		this.point.x+=x;
		this.point.y+=y;
	}

	@Override
	public String opis(){
		return "Prostokat, kolor: " + this.kolor + " wys: " + this.wys + " szer: " + this.szer;
	}
}