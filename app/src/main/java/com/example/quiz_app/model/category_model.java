package com.example.quiz_app.model;

//import java.util.Locale;

public class category_model {
    private String categoryName,categoryImage,key;
    int setNum;

    public category_model(String categoryName, String categoryImage,String key,int setNum){
        this.categoryName=categoryName;
        this.categoryImage=categoryImage;
        this.key=key;
        this.setNum=setNum;
    }

    public category_model() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getSetNum() {
        return setNum;
    }

    public void setSetNum(int setNum) {
        this.setNum = setNum;
    }
}
