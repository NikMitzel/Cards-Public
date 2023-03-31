import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteWindow extends JFrame {
    private JPanel mainPanel;
    private JTextField textField;
    private JButton loeschenButton;
    private JLabel statusLabel;


    public void init() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        loeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Knopf gedrückt");
            }
        });

        this.setTitle("Vokabel löschen");
        this.setContentPane(mainPanel);
        this.setSize(400, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
