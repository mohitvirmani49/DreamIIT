package com.example.quiz;

public class Upload {

    private String mName;
    private String mImageUrl;
    private String mDisplayName;
    private String mDisplayImage;

    public Upload() {
    }


    public Upload(String name, String imageUrl, String username, String image) {
        if (name.trim().equals("")) {
            name = "No Text Description";
        }
        mName = name;
        mImageUrl = imageUrl;
        mDisplayName = username;
        mDisplayImage = image;


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
}
