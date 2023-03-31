package UI;

import Backend.DatabaseConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPage extends JFrame {

    public StartPage(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cards");
        setSize(830,1030);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.white);


        JPanel startPage = new JPanel();
        startPage.setLayout(null);
        startPage.setBackground(Color.white);
        startPage.setBounds(0,0,830,1030);
        getContentPane().add(startPage);



        try {
            BufferedImage startpageIcon = ImageIO.read(new File("src/UI/img/Startpage_Icon.png"));
            JLabel startpageIconLabel = new JLabel(new ImageIcon(startpageIcon));
            startpageIconLabel.setBounds(254, 72, 322 ,322);
            startPage.add(startpageIconLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JButton jStartLearningButton = new JButton( new ImageIcon("src/UI/img/StartPage_stratlerning.png"));
        jStartLearningButton.setBounds(135,497,568,170);
        jStartLearningButton.setBorderPainted(false);
        jStartLearningButton.setContentAreaFilled(false);
        jStartLearningButton.setFocusable(false);
        jStartLearningButton.addActionListener(e -> {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            if (databaseConnection.today()){
                dispose();
                new LearnPage();
            } else {
                JOptionPane.showOptionDialog(null, "There are no left Cards for today","Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
            }

        });
        startPage.add(jStartLearningButton);


        JButton jYourDictionaryButton = new JButton( new ImageIcon("src/UI/img/StartPage_yourdictionary.png"));
        jYourDictionaryButton.setBounds(135,746,568,170);
        jYourDictionaryButton.setBorderPainted(false);
        jYourDictionaryButton.setContentAreaFilled(false);
        jYourDictionaryButton.setFocusable(false);
        jYourDictionaryButton.addActionListener(e -> {
            dispose();
            new DictionaryPage();
        });
        startPage.add(jYourDictionaryButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StartPage();
    }

}
