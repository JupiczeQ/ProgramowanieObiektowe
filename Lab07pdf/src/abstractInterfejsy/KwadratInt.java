package abstractInterfejsy;

public class KwadratInt implements FiguraGeometryczna{
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
