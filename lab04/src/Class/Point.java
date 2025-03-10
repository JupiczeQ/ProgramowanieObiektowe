package Class;

public class Point {
    float x,y;

    public float getX() {return x;}

    public void setX(float x) {this.x = x;}

    public float getY() {return y;}

    public void setY(float y) {this.y = y;}

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Point point1){
        point1.x+=5;
        point1.y+=8;
    }
}
