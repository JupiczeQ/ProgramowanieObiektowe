package FiguryGeometryczne;

public class Circle {
    private double r;
    public Circle(double r) {
        this.r = r;
    }
    public double area(){
        return Math.PI * r * r;
    }
    public double circumference(){
        return 2 * Math.PI * r;
    }
    public void show(){
        System.out.println("Koło, promień: " + r + " pole: " + area() + " obwód: " + circumference());
    }
}
