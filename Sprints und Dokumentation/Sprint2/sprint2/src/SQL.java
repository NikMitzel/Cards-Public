import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class SQL {
    //Prepare connection
    private static final Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = connection();
    }

    public static Connection connection() {
        String url = "jdbc:mysql://sql7.freesqldatabase.com/sql7608321";
        String username = "sql7608321";
        String password = "Ba6Vcvc1F5";

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //Sprint_1.SQL statements:
        /*
        Sprint_1.SQL statement pattern:
        INSERT: statement.executeUpdate("INSERT INTO vocabulary (VocID, ger, eng, eng2, field) VALUES (0, 'deutsch', 'englisch 1', 'englisch 2', 0)");
        UPDATE: statement.executeUpdate("UPDATE vocabulary SET field = wert WHERE VocID = wert");
        SELECT:
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vocabulary");
            resultSet.next();
             resultSet.getInt(columnIndex)
         */

        //Add vocabulary to the database //working
    public void addVoc(String ger, String eng, String eng2) {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("INSERT INTO vocabulary (VocID, ger, eng, eng2, field) VALUES (0, '" + ger + "', '" + eng + "', '" + eng2 + "', 0)");
        } catch (Exception e) {
            System.out.println(e + "- in addVoc");
        }
    }

        //Check if a given answer is right //working
    public boolean checkAnswer(int vocID, String answer) {
        if (answer != null && !answer.equals("") && !answer.equals("null")) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM vocabulary WHERE VocID = " + vocID);
                resultSet.next();

                if (answer.equalsIgnoreCase(resultSet.getString(3))|| answer.equalsIgnoreCase(resultSet.getString(4))) {
                    if (resultSet.getInt(5) < 4){
                        statement.executeUpdate("UPDATE vocabulary SET field = " + (resultSet.getInt(5) + 1) + " WHERE VocID = " + vocID);
                    }
                    return true;
                } else {
                    if (resultSet.getInt(5) != 0){
                        statement.executeUpdate("UPDATE vocabulary SET field = 0 WHERE VocID = " + vocID);
                    }
                    return false;
                }

            } catch (Exception e) {
                System.out.println(e + " - in giveAnswer");
            }
        }
        return false;
    }

        //Putting out a random vocabulary as Sprint_1.Vocabulary Object  //has to be reworked |comment: change the probability add date
    public Vocabulary getRandomVoc() {
        try (Statement statement = connection.createStatement()){
            ResultSet length = statement.executeQuery("SELECT count(*) FROM vocabulary");
            length.next();
            if (length.getInt(1) == 0) return new Vocabulary(-1,"keine Vokabeln vorhanden", "","",-1);

            /* approx exponential increasing probability for worse learned words
            field0: 41 – 100
            field1: 17 – 40
            field2: 7 – 16
            field3: 3 - 6
            field4: 0 - 2
            */
            Random random = new Random();
            int vocID = 0;
            while (vocID == 0) {
                int r = random.nextInt(100);
                if (r > 40) {
                    ResultSet IDfield0 = statement.executeQuery("SELECT VocID FROM vocabulary WHERE field = 0 ORDER BY RAND()");
                    if (IDfield0.next()) vocID = IDfield0.getInt(1);
                } else if (r > 16) {
                    ResultSet IDfield1 = statement.executeQuery("SELECT VocID FROM vocabulary WHERE field = 1 ORDER BY RAND()");
                    if(IDfield1.next()) vocID = IDfield1.getInt(1);
                } else if (r > 6) {
                    ResultSet IDfield2 = statement.executeQuery("SELECT VocID FROM vocabulary WHERE field = 2 ORDER BY RAND()");
                    if (IDfield2.next()) vocID = IDfield2.getInt(1);
                } else if (r > 3) {
                    ResultSet IDfield3 = statement.executeQuery("SELECT VocID FROM vocabulary WHERE field = 3 ORDER BY RAND()");
                    if (IDfield3.next()) vocID = IDfield3.getInt(1);
                } else if (r > 0) {
                    ResultSet IDfield4 = statement.executeQuery("SELECT VocID FROM vocabulary WHERE field = 4 ORDER BY RAND()");
                    if(IDfield4.next()) vocID = IDfield4.getInt(1);
                }
            }
            ResultSet result = statement.executeQuery("SELECT * FROM vocabulary WHERE VocID =" + vocID);
            result.next();
            return new Vocabulary(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5));
        } catch (Exception e) {
            System.out.println(e + "-in getRandomVoc");
        }
        return null;
    }

        //puts out all vocabularies in a Sprint_1.Vocabulary ArrayList   //working
    public ArrayList<Vocabulary> getAllVoc(){
        try (Statement statement = connection.createStatement()){
            ArrayList<Vocabulary> vocabularies = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vocabulary ORDER BY field");
            while (resultSet.next()) {
                vocabularies.add(new Vocabulary(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
            }
            return vocabularies;
        } catch (Exception e) {
            System.out.println(e + "- in getAllVoc");
        }
        return new ArrayList<Vocabulary>();
    }

        //deletes a vocabulary with its ID  //working
    public void delVoc(int VocID) {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM vocabulary WHERE VocID = " + VocID);
        } catch (Exception e) {
            System.out.println(e + "- in delVoc");
        }
    }
}
