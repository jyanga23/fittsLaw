import java.awt.*;

public class Square extends Shape{
    protected int radius;
    protected Color color;
    protected double x;
    protected double y;

    public Square(Point center, Color color) {
        this.x = center.x;
        this.y = center.y;
        radius = 5;
        this.color = color;
    }

    public Point getCenter() {
        return new Point((int)x, (int)y);
    }

    public void setCenter(Point p){
        x = p.x;
        y = p.y;
    }

    public int getTop(){
        return (int) y -radius;
    }
    public int getBottom(){
        return (int) y + radius;
    }
    public int getLeft(){
        return (int) x - radius;
    }
    public int getRight(){
        return (int) x +radius;
    }

    public Rectangle getRegion(){
        return  new Rectangle(getTop(),getLeft(), 2*radius, 2 *radius);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(getLeft(), getTop(), radius*2, radius*2 );
    }

    public boolean isInside(Point p){
        Point center = new Point((int)x,(int)y);
        return p.distance(center)<radius;
    }

    public void setColor(Color c){
        this.color = c;
    }

}
