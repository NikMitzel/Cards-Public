package Backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseConnection {
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

    ////updates the database to current date //working
    //public DatabaseConnection() {
    //    try (Statement statement = connection.createStatement()) {
    //        statement.executeUpdate("UPDATE dictionary SET nextlearndate = CURRENT_TIMESTAMP WHERE nextlearndate < CURRENT_TIMESTAMP");
    //    } catch (Exception e) {
    //        System.out.println(e + "    - in DatabaseConnection");
    //    }
    //}

    //SQL statements:
    //Add Card to the database //working
    public void addCard(String word, String translation, String translation2) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO dictionary (ID, word, translation, translation2, field, nextlearndate) VALUES (0, '" + word + "', '" + translation + "', '" + translation2 + "', 0 ,  CURRENT_TIMESTAMP )");
        } catch (Exception e) {
            System.out.println(e + "    - in addCard");
        }
    }

    //Check if a given answer is right //working
    public boolean checkAnswer(int ID, String answer) {
        if (answer != null && !answer.equals("") && !answer.equals("null")) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM dictionary WHERE ID = " + ID);
                resultSet.next();

                if (answer.equalsIgnoreCase(resultSet.getString(3)) || answer.equalsIgnoreCase(resultSet.getString(4))) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(System.currentTimeMillis());
                    switch (resultSet.getInt(5)) {
                        case 0:
                            statement.executeUpdate("UPDATE dictionary SET field = 1 WHERE ID = " + ID);
                            cal.add(Calendar.DATE, 2);
                            statement.executeUpdate("UPDATE dictionary SET nextlearndate = '" + new Date(cal.getTimeInMillis()) + "' WHERE ID = " + ID);
                            break;
                        case 1:
                            statement.executeUpdate("UPDATE dictionary SET field = 2 WHERE ID = " + ID);
                            cal.add(Calendar.DATE, 10);
                            statement.executeUpdate("UPDATE dictionary SET nextlearndate = '" + new Date(cal.getTimeInMillis()) + "' WHERE ID = " + ID);
                            break;
                        case 2:
                            statement.executeUpdate("UPDATE dictionary SET field = 3 WHERE ID = " + ID);
                            cal.add(Calendar.DATE, 30);
                            statement.executeUpdate("UPDATE dictionary SET nextlearndate = '" + new Date(cal.getTimeInMillis()) + "' WHERE ID = " + ID);
                            break;
                        case 3:
                            statement.executeUpdate("UPDATE dictionary SET field = 4 WHERE ID = " + ID);
                            cal.add(Calendar.DATE, 90);
                            statement.executeUpdate("UPDATE dictionary SET nextlearndate = '" + new Date(cal.getTimeInMillis()) + "' WHERE ID = " + ID);
                            break;
                        case 4:
                            cal.add(Calendar.DATE, 90);
                            statement.executeUpdate("UPDATE dictionary SET nextlearndate = '" + new Date(cal.getTimeInMillis()) + "' WHERE ID = " + ID);
                            break;
                        default:
                            return false;
                    }
                    return true;
                } else {
                    statement.executeUpdate("UPDATE dictionary SET field = 0 WHERE ID = " + ID);
                    statement.executeUpdate("UPDATE dictionary SET nextlearndate = CURRENT_TIMESTAMP WHERE ID = " + ID);
                    return false;
                }

            } catch (Exception e) {
                System.out.println(e + "    - in giveAnswer");
            }
        }
        return false;
    }

    //Putting out a random Card as Card Object  //working
    public Card getRandomCard() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE dictionary SET nextlearndate = CURRENT_TIMESTAMP WHERE nextlearndate < CURRENT_TIMESTAMP");
            ResultSet randomCard = statement.executeQuery("SELECT * FROM dictionary WHERE DATE(nextlearndate) = CURDATE() ORDER BY RAND()");
            randomCard.next();
            return new Card(randomCard.getInt(1), randomCard.getString(2), randomCard.getString(3), randomCard.getString(4));
        } catch (Exception e) {
            System.out.println(e + "    -   in getRandomCard");
        }
        return new Card(-1,"no cards for this day", " ", "");
    }

    //Putting out a boolean weather there are Cards for today //working
    public boolean today() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE dictionary SET nextlearndate = CURRENT_TIMESTAMP WHERE nextlearndate < CURRENT_TIMESTAMP");
            ResultSet randomCard = statement.executeQuery("SELECT count(*) FROM dictionary WHERE DATE(nextlearndate) = CURDATE()");
            randomCard.next();
            if (randomCard.getInt(1) == 0) {
                return false;
            }else return true;

        } catch (Exception e) {
            System.out.println(e + "    -   in today");
        }
        return false;
    }

    //puts out all Cards in a Card ArrayList   //working
    public ArrayList<Card> getAllCards() {
        try (Statement statement = connection.createStatement()) {
            ArrayList<Card> cards = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dictionary ORDER BY field");
            while (resultSet.next()) {
                cards.add(new Card(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
            return cards;
        } catch (Exception e) {
            System.out.println(e + "    - in getAllCard");
        }
        return new ArrayList<Card>();
    }

    //deletes a Cards with its ID  //working
    public void delCard(int ID) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM dictionary WHERE ID = " + ID);
        } catch (Exception e) {
            System.out.println(e + "    - in delCard");
        }
    }
}