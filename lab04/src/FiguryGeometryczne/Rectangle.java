package FiguryGeometryczne;

public class Rectangle {
    private double a,b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }
    public double area(){
        return a*b;
    }
    public double circumference(){
        return 2*a + 2*b;
    }
    public void show(){
        System.out.println("Prostokąt, bok a: " + a + " bok b: " + b + " pole: " + area() + " obwód: " + circumference());
    }
}
