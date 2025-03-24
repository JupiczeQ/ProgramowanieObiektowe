package abstractInterfejsy;

public class Prostokat extends Figura{
    public double a = 2,b = 3;
    @Override
    public double Pole() {
        return a*b;
    }

    @Override
    public double Obwod() {
        return 2*(a+b);
    }
}
