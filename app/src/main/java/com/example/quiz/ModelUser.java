package com.example.quiz;

public class ModelUser {

    String hisUser;
    String myUser;

    public ModelUser() {
    }

    public ModelUser(String hisUser, String myUser) {
        this.hisUser = hisUser;
        this.myUser = myUser;
    }

    public String getHisUser() {
        return hisUser;
    }

    public void setHisUser(String hisUser) {
        this.hisUser = hisUser;
    }

    public String getMyUser() {
        return myUser;
    }

    public void setMyUser(String myUser) {
        this.myUser = myUser;
    }
}
