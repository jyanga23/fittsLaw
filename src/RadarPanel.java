import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class RadarPanel extends JPanel {
    private ArrayList<Shape> dotList;
    private int border;
    private Random ran;

    private int numDots;

    private String id;
    private int interval;

    private int mode;



    private boolean special;
    public RadarPanel() {
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(500,500));
        dotList = new ArrayList<Shape>();
        border = 20;
        mode = 1;
        interval = 150;
        id = "";
        ran = new Random();
        setSpecial(ran.nextBoolean());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        System.out.println("ID: " + id);
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
        System.out.println("INTERVAL: " + interval);
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public void setNumDots(int num) {
        numDots = num;
    }

    public void setMode(String mode) {
        switch (mode) {
            case "Color" : this.mode = 1; break;
            case "Shape" : this.mode = 2; break;
            case "Combo" : this.mode = 3; break;
        }
    }

    public String getMode() {
        String mode = "";
        switch (this.mode) {
            case 1 : mode = "Color"; break;
            case 2 : mode = "Shape"; break;
            case 3 : mode = "Combo"; break;
        }
        return mode;
    }

    public void start(){
        setSpecial(ran.nextBoolean());

        if (mode == 1) {
            ColorModeStart();
        }
        if (mode == 2) {
            ShapeModeStart();
        }
        if (mode == 3) {
            ComboModeStart();
        }
    }

    private void ColorModeStart(){
        setSpecial(ran.nextBoolean());

        for(int i = 0; i < numDots; i ++){
            generateDot(Color.BLUE);
        }
        if (isSpecial()) {
            generateDot(Color.RED);
        }
        repaint();
    }

    private void ShapeModeStart(){
        setSpecial(ran.nextBoolean());

        for(int i = 0; i < numDots; i ++){
            generateDot(Color.RED);
        }
        if (isSpecial()) {
            generateSquare(Color.RED);
        }
        repaint();

    }

    private void ComboModeStart(){
        setSpecial(ran.nextBoolean());

        for(int i = 0; i < numDots / 2; i ++){
            generateSquare(Color.RED);
        }
        for(int i = 0; i < numDots / 2; i ++){
            generateDot(Color.BLUE);
        }
        if (isSpecial()) {
            generateDot(Color.RED);
        }
        repaint();

    }
    public void stop(){
        System.out.println("Stop");
        dotList.clear();

    }

    public void done(String id, int interval) {
        // id, numDots, interval;
        try {
            FileWriter fw = new FileWriter("results.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw);
            outFile.println(getId() + "," + getMode() + "," + numDots + "," + getInterval());
            outFile.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void generateDot(Color color){
        int x1 = ran.nextInt(getWidth() - border*2) + border;
        int y1 = ran.nextInt(getHeight() - border*2) + border;

        Dot  d = new Dot(new Point(x1,y1), color);
        dotList.add(d);
    }

    private void generateSquare(Color color){
        int x1 = ran.nextInt(getWidth() - border*2) + border;
        int y1 = ran.nextInt(getHeight() - border*2) + border;

        Square  s = new Square(new Point(x1,y1), color);
        dotList.add(s);
    }


    @Override
    protected synchronized void paintComponent(Graphics g) {
        g = (Graphics) g;
        super.paintComponent(g);
        for (Shape d:dotList){
            d.paint(g);
        }
        repaint();
    }

}
