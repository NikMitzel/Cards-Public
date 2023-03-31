package UI;

import Backend.Card;
import Backend.DatabaseConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class LearnPage extends JFrame {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Card card;
    boolean a = true;

    public LearnPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cards - learning");
        setSize(830, 1030);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        JPanel learnPage = new JPanel();
        learnPage.setBackground(Color.white);
        learnPage.setLayout(null);
        learnPage.setBounds(0, 0, 830, 1030);
        getContentPane().add(learnPage);


        JButton jStartButton = new JButton(new ImageIcon("src/UI/img/LearnPage_icon.png"));
        jStartButton.setBounds(29, 25, 100, 100);
        jStartButton.setBorderPainted(false);
        jStartButton.setContentAreaFilled(false);
        jStartButton.setFocusable(false);
        jStartButton.addActionListener(e -> {
            dispose();
            new StartPage();
        });
        learnPage.add(jStartButton);

        try {
            BufferedImage learnBanner = ImageIO.read(new File("src/UI/img/LearnPage_banner.png"));
            JLabel startpageIconLabel = new JLabel(new ImageIcon(learnBanner));
            startpageIconLabel.setBounds(160, 25, 646, 107);
            learnPage.add(startpageIconLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        card = databaseConnection.getRandomCard();

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.white);

        JLabel translateLabel = new JLabel("     Translate     ");
        translateLabel.setFont(new Font("Inter", Font.PLAIN, 48));
        translateLabel.setForeground(new Color(124, 78, 255));
        translateLabel.setVisible(true);
        jPanel.add(translateLabel);

        JLabel wordLabel = new JLabel(card.getWord());
        wordLabel.setFont(new Font("Inter", Font.ITALIC, 40));
        wordLabel.setForeground(new Color(124, 78, 255));
        wordLabel.setVisible(true);
        jPanel.add(wordLabel);

        jPanel.setBounds(265, 213, 300, 128);
        learnPage.add(jPanel);

        JLabel correctIconLabel;
        try {
            correctIconLabel = new JLabel(new ImageIcon(ImageIO.read(new File("src/UI/img/Learnpage_correct.png"))));
            correctIconLabel.setBounds(333, 778, 185, 188);
            correctIconLabel.setVisible(false);
            learnPage.add(correctIconLabel);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }

        JLabel incorrectIconLabel;
        try {
             incorrectIconLabel = new JLabel(new ImageIcon(ImageIO.read(new File("src/UI/img/Learnpage_incorrect.png"))));
            incorrectIconLabel.setBounds(333, 778, 185, 188);
            incorrectIconLabel.setVisible(false);
            learnPage.add(incorrectIconLabel);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }

        try {
            BufferedImage textfield = ImageIO.read(new File("src/UI/img/Learnpage_textfield.png"));
            JLabel startpageIconLabel = new JLabel(new ImageIcon(textfield));
            startpageIconLabel.setBounds(95, 432, 640, 126);
            learnPage.add(startpageIconLabel);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        JTextField jTextField = new JFormattedTextField();
        jTextField.setBackground(new

                Color(243, 243, 243));
        jTextField.setForeground(new

                Color(124, 78, 255));
        jTextField.setBorder(null);
        jTextField.setSize(597, 87);
        jTextField.setLocation(130, 450);
        jTextField.setFont(new

                Font("Inter", Font.PLAIN, 48));
        jTextField.addKeyListener(new

                                          KeyListener() {
                                              @Override
                                              public void keyTyped(KeyEvent e) {
                                              }

                                              @Override
                                              public void keyPressed(KeyEvent e) {
                                                  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                                      a = databaseConnection.checkAnswer(card.getID(), jTextField.getText());
                                                      if (a) {
                                                          incorrectIconLabel.setVisible(false);
                                                          correctIconLabel.setVisible(true);
                                                      } else {
                                                          correctIconLabel.setVisible(false);
                                                          incorrectIconLabel.setVisible(true);
                                                      }
                                                      System.out.println(a);
                                                      jTextField.setText("");
                                                      card = databaseConnection.getRandomCard();
                                                      wordLabel.setText(card.getWord());
                                                  }
                                              }

                                              @Override
                                              public void keyReleased(KeyEvent e) {
                                              }
                                          });
        learnPage.add(jTextField);

        JButton jCheckButton = new JButton(new ImageIcon("src/UI/img/LearnPage_Check.png"));
        jCheckButton.setBounds(172, 623, 491, 133);
        jCheckButton.setBorderPainted(false);
        jCheckButton.setContentAreaFilled(false);
        jCheckButton.setFocusable(false);

        jCheckButton.addActionListener(new

                                               ActionListener() {
                                                   @Override
                                                   public void actionPerformed(ActionEvent e) {
                                                       a = databaseConnection.checkAnswer(card.getID(), jTextField.getText());
                                                       if (a) {
                                                           incorrectIconLabel.setVisible(false);
                                                           correctIconLabel.setVisible(true);
                                                       } else {
                                                           correctIconLabel.setVisible(false);
                                                           incorrectIconLabel.setVisible(true);
                                                       }
                                                       System.out.println(a);
                                                       jTextField.setText("");
                                                       card = databaseConnection.getRandomCard();
                                                       wordLabel.setText(card.getWord());
                                                   }
                                               });
        learnPage.add(jCheckButton);

        setVisible(true);
    }
}

