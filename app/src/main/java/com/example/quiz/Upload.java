package com.example.quiz;

public class Upload {

    private String mName;
    private String mImageUrl;
    private String mDisplayName;
    private String mDisplayImage;
    private String mDate;
    private String mAnswer;
    private String mAnsImage;
    private String mAnsDisName;
    private String mAnsDisImg;
    private String mComment;
    private String mCommentName;
    private String mCommentImage;
    private String mCommentRecieve;
    private String mSubject;

    public Upload() {
    }

    public Upload(String name, String imageUrl, String username, String image,
                  String date, String answer, String ansImg, String ansDisName,
                  String AnsDisIg, String comment, String commName, String commImage, String commRec, String sub) {
        if (name.trim().equals("")) {
            name = "No Text Description";
        }
        mName = name;
        mImageUrl = imageUrl;
        mDisplayName = username;
        mDisplayImage = image;
        mDate = date;
        mAnswer = answer;
        mAnsImage = ansImg;
        mAnsDisName = ansDisName;
        mAnsDisImg = AnsDisIg;
        mComment = comment;
        mCommentName = commName;
        mCommentImage = commImage;
        mCommentRecieve = commRec;
        mSubject = sub;

    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmDisplayName() {
        return mDisplayName;
    }

    public void setmDisplayName(String mDisplayName) {
        this.mDisplayName = mDisplayName;
    }

    public String getmDisplayImage() {
        return mDisplayImage;
    }

    public void setmDisplayImage(String mDisplayImage) {
        this.mDisplayImage = mDisplayImage;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getmAnsImage() {
        return mAnsImage;
    }

    public void setmAnsImage(String mAnsImage) {
        this.mAnsImage = mAnsImage;
    }

    public String getmAnsDisName() {
        return mAnsDisName;
    }

    public void setmAnsDisName(String mAnsDisName) {
        this.mAnsDisName = mAnsDisName;
    }

    public String getmAnsDisImg() {
        return mAnsDisImg;
    }

    public void setmAnsDisImg(String mAnsDisImg) {
        this.mAnsDisImg = mAnsDisImg;
    }

    public String getmComment() {
        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }

    public String getmCommentName() {
        return mCommentName;
    }

    public void setmCommentName(String mCommentName) {
        this.mCommentName = mCommentName;
    }

    public String getmCommentImage() {
        return mCommentImage;
    }

    public void setmCommentImage(String mCommentImage) {
        this.mCommentImage = mCommentImage;
    }

    public String getmCommentRecieve() {
        return mCommentRecieve;
    }

    public void setmCommentRecieve(String mCommentRecieve) {
        this.mCommentRecieve = mCommentRecieve;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }
}