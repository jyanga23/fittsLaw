import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class myPanel extends JPanel {
    private Dot d;
    private ArrayList<Dot> dots = new ArrayList<Dot>();

    public myPanel() {
        setPreferredSize(new Dimension(500,500));
        setBackground(Color.WHITE);
        MousePlay mb = new MousePlay();
        addMouseListener(mb);
        addMouseMotionListener(mb);
    }


    @Override
    protected  void paintComponent(Graphics g){
        super.paintComponent(g);
        if (d != null) {

            int x = d.getX();
            int y = d.getY();
            int r = d.getRadius();
            g.setColor(d.getColor());
            g.fillOval(x - r, y - r, r * 2, r * 2);

            for (Dot theDot: dots){
                g.setColor(theDot.getColor());
                x = theDot.getX();
                y = theDot.getY();
                r = theDot.getRadius();
                g.fillOval(x - r, y - r, r * 2, r * 2);
            }
        }
    }

   private class MousePlay implements MouseListener, MouseMotionListener {

       @Override
       public void mouseClicked(MouseEvent e) {
           System.out.println(e.getPoint());
           d = new Dot(e.getPoint());
           dots.add(d);
           repaint();
       }

       @Override
       public void mousePressed(MouseEvent e) {
           d = new Dot(e.getPoint());
           d.setColor(Color.RED);
           repaint();
       }

       @Override
       public void mouseReleased(MouseEvent e) {
           d.setColor(Color.BLUE);
           dots.add(d);
           repaint();
       }

       @Override
       public void mouseEntered(MouseEvent e) {

       }

       @Override
       public void mouseExited(MouseEvent e) {

       }

       @Override
       public void mouseDragged(MouseEvent e) {
          d.setCenter(e.getPoint());
          repaint();
       }

       @Override
       public void mouseMoved(MouseEvent e) {

       }
   }
}
