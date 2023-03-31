package UI;

import Backend.Card;
import Backend.DatabaseConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;

public class DictionaryPage extends JFrame implements ActionListener {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    JTextField textFieldl;
    JTextField textFieldr;

    public DictionaryPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cards - Your dictionary");
        setSize(830,1030);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.white);

        JPanel dictionaryPage = new JPanel();
        dictionaryPage.setLayout(null);
        dictionaryPage.setBackground(Color.white);
        dictionaryPage.setBounds(0,0,830,1030);
        getContentPane().add(dictionaryPage);

        JButton jStartButton = new JButton( new ImageIcon("src/UI/img/LearnPage_icon.png"));
        jStartButton.setBounds(29,25,100,100);
        jStartButton.setBorderPainted(false);
        jStartButton.setContentAreaFilled(false);
        jStartButton.setFocusable(false);
        jStartButton.addActionListener(e -> {
            dispose();
            new StartPage();
        });
        dictionaryPage.add(jStartButton);

        try {
            BufferedImage dictionaryBanner = ImageIO.read(new File("src/UI/img/dictionary_banner.png"));
            JLabel dictionaryBannerLabel = new JLabel(new ImageIcon(dictionaryBanner));
            dictionaryBannerLabel.setBounds(160, 25, 514 ,107);
            dictionaryPage.add(dictionaryBannerLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JButton jReloadButton = new JButton( new ImageIcon("src/UI/img/reload.png"));
        jReloadButton.setBounds(698,29,96,99);
        jReloadButton.setBorderPainted(false);
        jReloadButton.setContentAreaFilled(false);
        jReloadButton.setFocusable(false);
        jReloadButton.addActionListener(e -> {
            dispose();
            new DictionaryPage();
        });
        dictionaryPage.add(jReloadButton);



        // Table
        ArrayList<Card> cards = databaseConnection.getAllCards();
        DefaultTableModel dm = new DefaultTableModel();
        Object[][] objects = new Object[cards.size()][cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            objects[i][0] = cards.get(i).getWord();
            objects[i][1] = cards.get(i).getTranslation();
            objects[i][2] = cards.get(i).getID();
        }
        dm.setDataVector( objects, new Object[]{"word" , "translation" ,"del"});

        JTable table = new JTable(dm);
        table.setRowHeight(40);
        table.getTableHeader().setFont(new Font("Inter", Font.BOLD, 30));
        table.setShowGrid(false);
        table.getTableHeader().setForeground(new Color(0x7C4EFF));
        table.setFont(new Font("Inter", Font.PLAIN, 30));
        table.setForeground(new Color(0x7C4EFF));
        table.setBackground(new Color(0xF1F1F1));
        table.getColumn("del").setCellRenderer(new ButtonRenderer());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumn("del").setPreferredWidth(45);
        table.getColumn("del").setResizable(false);
        table.getColumn("word").setMinWidth(337);
        table.getColumn("word").setResizable(false);
        table.getColumn("translation").setPreferredWidth(337);
        table.getColumn("translation").setResizable(false);
        table.getColumn("del").setCellEditor(new ButtonEditor(new JCheckBox()));


        JScrollPane scroll = new JScrollPane(table);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setBorder(null);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        scroll.setBounds(46,177,737,665);
        scroll.setBorder(null);
        dictionaryPage.add(scroll);

        BufferedImage labelbackground;
        try {
            labelbackground = ImageIO.read(new File("src/UI/img/dictionaryPage_Labelbackground.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel startpageIconLabel = new JLabel(new ImageIcon(labelbackground));
        startpageIconLabel.setBounds(36, 167, 758 ,680);
        dictionaryPage.add(startpageIconLabel);


        JButton plusBTN = new JButton( new ImageIcon("src/UI/img/Discotionary add button.png"));
        plusBTN.setBounds(695,876,87,83);
        plusBTN.setBorderPainted(false);
        plusBTN.setContentAreaFilled(false);
        plusBTN.setFocusable(false);
        plusBTN.addActionListener(e -> {
            databaseConnection.addCard(textFieldl.getText(), textFieldr.getText(), null);
            textFieldr.setForeground(new Color(124,78,255,50));
            textFieldr.setText("Translation");
            textFieldl.setForeground(new Color(124,78,255,50));
            textFieldl.setText("Word");
        });
        dictionaryPage.add(plusBTN);

        //textfield left
        textFieldl = new JFormattedTextField("Word");
        textFieldl.setBackground(new Color(218, 205, 255));
        textFieldl.setForeground(new Color(124,78,255,50));
        textFieldl.setBorder(null);
        textFieldl.setSize(250, 89);
        textFieldl.setLocation(54, 869);
        textFieldl.setFont(new Font("Inter", Font.PLAIN, 30));
        textFieldl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textFieldl.setText("");
                textFieldl.setForeground(new Color(124,78,255));
            }
        });
        dictionaryPage.add(textFieldl);


        // textfield right
        textFieldr = new JFormattedTextField("Translation");
        textFieldr.setBackground(new Color(218, 205, 255));
        textFieldr.setForeground(new Color(124,78,255,50));
        textFieldr.setBorder(null);
        textFieldr.setSize(312, 89);
        textFieldr.setLocation(350, 869);
        textFieldr.setFont(new Font("Inter", Font.PLAIN, 30));
        textFieldr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textFieldr.setText("");
                textFieldr.setForeground(new Color(124,78,255));
            }
        });

        dictionaryPage.add(textFieldr);

        //background label down
        try {
            BufferedImage learnBanner = ImageIO.read(new File("src/UI/img/Dictionary_addfield.png"));
            JLabel startpageIconLabel2 = new JLabel(new ImageIcon(learnBanner));
            startpageIconLabel2.setBounds(38, 862, 758 ,110);
            dictionaryPage.add(startpageIconLabel2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setVisible(true);
    }

    public void reload(){
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
        repaint();
    }

}



