import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VocLearnWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField germanTextField;
    private JTextField englishTextField;
    private JButton ueberpruefenButton;
    private JLabel pointsLabel;



    public void init(){
        //Windows-Look
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ueberpruefenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Knopf gedrückt");
            }
        });

        this.setContentPane(mainPanel);
        this.setTitle("Vokabeln lernen");
        this.setLocationRelativeTo(null);
        this.setSize(700, 160);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}