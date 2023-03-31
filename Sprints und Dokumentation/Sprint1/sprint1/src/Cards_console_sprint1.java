import java.util.ArrayList;
import java.util.Scanner;

public class Cards_console_sprint1 {
    public static void main(String[] args) {
        SQL sql = new SQL();
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                                    Cards                                    |");
        System.out.println("|                           ur vocabulary learn app                           |");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("|                               typ 'exit' to stop                            |");
        System.out.println("|                   typ 'list' to show a list with all vocabulary             |");
        System.out.println("|                              typ anything to start                          |");
        System.out.println("-------------------------------------------------------------------------------");

        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String sc = scanner.next();
        if(sc.equals("list")) {
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|                                Vocabulary List                              |");
            System.out.println("-------------------------------------------------------------------------------");
            System.out.printf("| %-2s | %-20s | %-20s | %-20s |%-2s |%n" , "ID", "German",  "English", "English 2", "F^");
            System.out.println("|-----------------------------------------------------------------------------|");
            ArrayList<Vocabulary> vocabularyArrayList = sql.getAllVoc();
            for (int i = 0; i < vocabularyArrayList.size(); i++) {
                Vocabulary v = vocabularyArrayList.get(i);
                System.out.printf("| %-2d | %-20s | %-20s | %-20s | %-1d |%n" , v.getVocID() , v.getGer(),  v.getEng(), v.getEng2(), v.getField());
            }
            System.out.println("------------------------------------------------------------------------------");

        }
        System.out.println();
        while (!sc.equals("exit")){
            Vocabulary vocabulary = sql.getRandomVoc();
            System.out.println(vocabulary.getGer());
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("answer > ");
            sc = scanner1.next();
            if(sc.equals("exit")) return;
            if(sc.equals("list")) {
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("|                                Vocabulary List                             |");
                System.out.println("------------------------------------------------------------------------------");
                System.out.printf("| %-2s | %-20s | %-20s | %-20s |%-1s |%n" , "ID", "German",  "English", "English 2", "F");
                System.out.println("|----------------------------------------------------------------------------|");
                ArrayList<Vocabulary> vocabularyArrayList = sql.getAllVoc();
                for (int i = 0; i < vocabularyArrayList.size(); i++) {
                    Vocabulary v = vocabularyArrayList.get(i);
                    System.out.printf("| %-2d | %-20s | %-20s | %-20s |%-1d |%n" , v.getVocID() , v.getGer(),  v.getEng(), v.getEng2(), v.getField());
                }
                System.out.println("------------------------------------------------------------------------------");
                continue;
            }
            boolean answer;
            answer = sql.checkAnswer(vocabulary.getVocID(), sc);
            System.out.println("ur answer is " + answer);
            if (!answer) System.out.println("right answer should be: " + vocabulary.getEng());
            System.out.println("______________________________________________________________________________");
        }

    }
}
