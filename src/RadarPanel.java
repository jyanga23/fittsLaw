import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class RadarPanel extends JPanel {
    private ArrayList<MovingDot> dotList;
    private int border;
    private Random ran;



    private boolean fast;
    public RadarPanel() {
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(500,500));
        dotList = new ArrayList<MovingDot>();
        border = 20;
        ran = new Random();
    }

    public boolean isFast() {
        return fast;
    }

    public void start(double speed){
        for(int i = 0; i < 10; i ++){
            generateDot(.4);
            fast = false;
        }
        if (ran.nextInt(2)<1){
            generateDot(.4 + speed);
            fast = true;
        }
        repaint();
    }
    public void stop(){
        System.out.println("Stop");
        dotList.clear();

    }

    private void generateDot(double speed){
        int x1 = ran.nextInt(getWidth() - border*2) + border;
        int y1 = ran.nextInt(getHeight() - border*2) + border;
        int x2 = ran.nextInt(getWidth() - border*2) + border;
        int y2 = ran.nextInt(getHeight() - border*2) + border;

        MovingDot  d = new MovingDot(new Point(x1,y1), new Point(x2,y2), speed);
        d = new BoundedDotDecorator(d, new Point(getWidth(),getHeight()) );
        //d = new GravityDotDecorator(d);
        dotList.add(d);
        Thread t = new Thread(d);
        t.start();
    }


    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MovingDot d:dotList){
            d.paint(g);
        }
        repaint();

    }

}
