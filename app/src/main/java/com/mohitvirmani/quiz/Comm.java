package com.mohitvirmani.quiz;

public class Comm {
    private String mComment;
    private String mCommentName;
    private String mCommentImage;
    private String mCommentRecieve;

    public Comm() {
    }

    public Comm(String comment, String name, String rec) {
        mComment = comment;
        mCommentName = name;
        mCommentRecieve = rec;
    }

    public String getmComment() {
        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }
//
    public String getmCommentName() {
        return mCommentName;
    }

    public void setmCommentName(String mCommentName) {
        this.mCommentName = mCommentName;
    }

//    public String getmCommentImage() {
//        return mCommentImage;
//    }
//
//    public void setmCommentImage(String mCommentImage) {
//        this.mCommentImage = mCommentImage;
//    }
//
    public String getmCommentRecieve() {
        return mCommentRecieve;
    }

    public void setmCommentRecieve(String mCommentRecieve) {
        this.mCommentRecieve = mCommentRecieve;
    }
}
