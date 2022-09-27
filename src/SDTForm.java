import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SDTForm {
    private JPanel sdtPanel;
    private JButton startButton;
    private RadarPanel rPanel;
    private JLabel hitLabel;
    private JLabel misslabel;
    private JLabel falabel;
    private JLabel crlabel;
    private JTextField txtSpeed;
    private JSlider slider1;
    private Timer timer;
    private int hit;
    private int miss;
    private int falseAlarm;
    private int correctReject;


    public SDTForm() {

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double speed = Double.parseDouble(txtSpeed.getText());
                rPanel.start(speed);
                timer = new Timer(1000, new ActionListener() {
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
    }

    private void feedback() {
        boolean isFast = rPanel.isFast();
        int input = JOptionPane.showConfirmDialog(null,
                "Was there a fast dot", "Select an Option...", JOptionPane.YES_NO_OPTION);
        if (isFast){
            if(input ==JOptionPane.YES_OPTION) {
                hit++;
            }
            else  {
                miss++;
            }
        } else{
            if(input ==JOptionPane.YES_OPTION) {
                falseAlarm++;
            }
            else  {
                correctReject++;
            }
        }
        hitLabel.setText(hit+ " Hit");
        misslabel.setText(miss + " Miss");
        falabel.setText(falseAlarm + " False Alarm");
        crlabel.setText(correctReject + " Correct Reject");


    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Signal Detection Theory");
        frame.setContentPane(new SDTForm().sdtPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
