import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;

public class SDTForm {
    private JPanel sdtPanel;
    private JButton startButton;
    private RadarPanel rPanel;
    private JLabel hitLabel;
    private JLabel misslabel;
    private JLabel falabel;
    private JLabel crlabel;
    private JTextField subject;
    private JComboBox modeBox;
    private JLabel subjectLabel;
    private JComboBox difficultyBox;
    private Timer timer;
    private int interval;
    private int hit;
    private int miss;
    private int falseAlarm;
    private int correctReject;

    private String id;

    private int streak;


    public SDTForm() {
        int streak = 0;
        int interval = 150;
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rPanel.start();
                timer = new Timer(rPanel.getInterval(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Done");
                        rPanel.stop();
                        timer.stop();
                        feedback();

                    }
                });
                timer.start();
            }
        });

        modeBox.addItem("Color");
        modeBox.addItem("Shape");
        modeBox.addItem("Combo");

        modeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mode = (String) modeBox.getSelectedItem();
                rPanel.setMode(mode);
                rPanel.setInterval(150);
            }
        });

        difficultyBox.addItem(10);
        difficultyBox.addItem(20);
        difficultyBox.addItem(30);
        difficultyBox.addItem(40);
        difficultyBox.addItem(50);

        difficultyBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int difficulty = (int) difficultyBox.getSelectedItem();

                switch (difficulty) {
                    case 10 : rPanel.setNumDots(10); break;
                    case 20 : rPanel.setNumDots(20); break;
                    case 30 : rPanel.setNumDots(30); break;
                    case 40 : rPanel.setNumDots(40); break;
                    case 50 : rPanel.setNumDots(50); break;
                }
            }
        });

        subject.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String id = subject.getText();
                rPanel.setId(id);
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void feedback() {
        boolean isSpecial = rPanel.isSpecial();
        int input = JOptionPane.showConfirmDialog(null,
                "Was there an outlier", "Select an Option...", JOptionPane.YES_NO_OPTION);
        if (isSpecial){
            if(input ==JOptionPane.YES_OPTION) {
                streak++;
                hit++;
            }
            else  {
                rPanel.setInterval(rPanel.getInterval()+25);
                streak = 0;
                miss++;
            }
        } else{
            if(input ==JOptionPane.YES_OPTION) {
                rPanel.setInterval(rPanel.getInterval()+25);
                streak = 0;
                falseAlarm++;
            }
            else  {
                streak++;
                correctReject++;
            }
        }
        hitLabel.setText(hit+ " Hit");
        misslabel.setText(miss + " Miss");
        falabel.setText(falseAlarm + " False Alarm");
        crlabel.setText(correctReject + " Correct Reject");

        if (streak > 9) {
            rPanel.done(id, interval);
            new JOptionPane("Level Complete!");
            streak = 0;
            hit = 0;
            miss = 0;
            correctReject = 0;
            falseAlarm = 0;
            interval = 150;
        }


    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Signal Detection Theory");
        frame.setContentPane(new SDTForm().sdtPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
