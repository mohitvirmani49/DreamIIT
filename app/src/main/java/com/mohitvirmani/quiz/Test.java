package com.mohitvirmani.quiz;

public class Test {
    String optionMark;
    String correctAns;

    public Test(String optionMark, String correctAns) {
        this.optionMark = optionMark;
        this.correctAns = correctAns;
    }

    public Test() {
    }

    public String getOptionMark() {
        return optionMark;
    }

    public void setOptionMark(String optionMark) {
        this.optionMark = optionMark;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }
}

