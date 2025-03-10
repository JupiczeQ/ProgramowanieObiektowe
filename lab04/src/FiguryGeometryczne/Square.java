package FiguryGeometryczne;

public class Square {
    private double a;

    public Square(double a) {
        this.a = a;
    }
    public double area(){
        return a*a;
    }
    public double circumference(){
        return 4*a;
    }
    public void show(){
        System.out.println("Kwadrat, dł. boku: " + a + " pole: " + area() + " obwód: " + circumference());
    }
}
