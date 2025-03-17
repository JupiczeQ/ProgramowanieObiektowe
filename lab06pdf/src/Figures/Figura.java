package Figures;

class Figura {
	
	//pola metody i kontruktory
	Point point;
	String kolor = "bialy";
	
	
	Figura(){
		point = new Point(0,0);
	}
	Figura(String kolor){
		this.kolor=kolor;
	}
	Figura(Point point){
		this.point = point;
	}
	String getKolor(){
		return kolor;
	}
	public String opis(){
		return "Klasa Figura. Kolor obiektu: "+kolor;
	}
			
	
}
