import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddVocWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField germanWortTextField;
    private JTextField englishWortTextField;
    private JButton hinzufuegenBTN;
    private JLabel deutschesWortLabel;
    private JLabel englischWortLabel;
    private JLabel statusLabel;


    public void init(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        hinzufuegenBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSuccess()){
                    statusLabel.setText("Wort hinzugefügt");
                }
                else{
                    statusLabel.setText("Wort konnte nicht hinzugefügt werden.");
                }
            }
        });

        this.setTitle("Vokabel hinzufügen");
        this.setContentPane(mainPanel);
        this.setSize(600,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);


    }

    private boolean isSuccess(){
        return true;
    }
}
