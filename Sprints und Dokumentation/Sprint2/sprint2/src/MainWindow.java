import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

//TODO: Wenn man einen Sprint kopiert,
// muss man erst eine neue GUI erstellen, dann separat die Komponenten der
// .form-Datei in die neue kopieren sowie die Klasse.

public class MainWindow extends JFrame implements ActionListener {
    private JTable table;
    private JScrollPane sp;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem lernenMenuBTN, loeschenMenuBTN, vokabelAddBTN;

    public void init(){
        this.setTitle("Vokabelliste");

        String[][] data = {
                {"Fish", "Fisch"},
                {"Stone", "Stein"}
        };

        String[] columnData = {"Englisch", "Deutsch"};

        table = new JTable(data, columnData);
        table.setSize(400,400);

        sp = new JScrollPane(table);

        addMenu();
        this.add(sp);


        this.setSize(400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    private void addMenu(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menü");
        lernenMenuBTN = new JMenuItem("Vokabel lernen");
        loeschenMenuBTN = new JMenuItem("Vokabel löschen");
        vokabelAddBTN = new JMenuItem("Vokabel hinzufügen");

        menu.add(lernenMenuBTN);
        menu.add(loeschenMenuBTN);
        menu.add(vokabelAddBTN);
        menuBar.add(menu);

        //Action listener
        lernenMenuBTN.addActionListener(this);
        loeschenMenuBTN.addActionListener(this);
        vokabelAddBTN.addActionListener(this);


        this.setJMenuBar(menuBar);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "Vokabel lernen")){
            VocLearnWindow t = new VocLearnWindow();
            t.init();
        }
        if(Objects.equals(e.getActionCommand(), "Vokabel löschen")){
            DeleteWindow t = new DeleteWindow();
            t.init();
        }
        if(Objects.equals(e.getActionCommand(), "Vokabel hinzufügen")){
            AddVocWindow t = new AddVocWindow();
            t.init();
        }
    }
}
