package com.mohitvirmani.quiz;

public class CheckedResponse {
    String code =null;
    String name = null;
    boolean selected = false;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public CheckedResponse(String code, String name, boolean selected) {
        this.code = code;
        this.name = name;
        this.selected = selected;

    }

}
