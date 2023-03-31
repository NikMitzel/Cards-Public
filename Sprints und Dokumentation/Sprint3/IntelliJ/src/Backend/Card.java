package Backend;

public class Card {
    private int ID;
    private String word;
    private String translation;
    private String translation2;

    public Card(int ID, String word, String translation, String translation2) {
        this.ID = ID;
        this.word = word;
        this.translation = translation;
        this.translation2 = translation2;
    }

    public int getID() {
        return ID;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public String getTranslation2() {
        return translation2;
    }


    public void setWord(String word) {
        this.word = word;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setTranslation2(String translation2) {
        this.translation2 = translation2;
    }

    @Override
    public String toString() {
        return ID + "\t"+ word + "\t"+ translation + "\t"+ translation2;
    }
}
