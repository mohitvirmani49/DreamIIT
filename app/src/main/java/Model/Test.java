package Model;

public class Test {
    public String optionMarked, correctAns;

    public Test(String optionM, String cOption) {
        optionMarked = optionM;
        correctAns = cOption;

    }

    public Test() {

    }

    public String getOptionMarked() {
        return optionMarked;
    }

    public void setOptionMarked(String optionMarked) {
        this.optionMarked = optionMarked;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }
}
