import java.awt.*;

public enum UIColors {
    LINKCOLOR(59,130,246),
    HLCOLOR(245,101,101);

    private final Color color;

    UIColors(int red, int green, int blue){
        this.color = new Color(red,green,blue);
    }

    public Color getColor() {
        return color;
    }
}
