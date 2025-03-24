package abstractInterfejsy;

public class Kwadrat extends Figura{
    public double a = 2;
    @Override
    public double Pole() {
        return Math.pow(a,2);
    }

    @Override
    public double Obwod() {
        return 4*a;
    }
}
