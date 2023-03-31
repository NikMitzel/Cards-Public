public class Vocabulary {
    private int vocID;
    private String ger;
    private String eng;
    private String eng2;
    private int field;

    public Vocabulary(int vocID, String ger, String eng, String eng2, int field) {
        this.vocID = vocID;
        this.ger = ger;
        this.eng = eng;
        this.eng2 = eng2;
        this.field = field;
    }

    public int getVocID() {
        return vocID;
    }

    public String getGer() {
        return ger;
    }

    public String getEng() {
        return eng;
    }

    public String getEng2() {
        return eng2;
    }

    public int getField() {
        return field;
    }

    public void setGer(String ger) {
        this.ger = ger;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public void setEng2(String eng2) {
        this.eng2 = eng2;
    }

    public void setField(int field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return vocID + "\t"+ ger + "\t"+ eng + "\t"+ eng2 + "\t"+ field;
    }
}
