package com.example.chen.listviewtest;

import java.io.Serializable;

public class ListContent implements  Serializable{
    private String name;
    private int textViewId;
    private boolean checkStatus;


    public ListContent(String name,int textViewId) {
        this.name = name;
        this.textViewId = textViewId;


    }
    public String getName() {
        return this.name;
    }
    public int getTextViewId() {
        return this.textViewId;
    }
    public boolean getCheckStatus() { return this.checkStatus;}
    public void setCheckStatus(boolean checkStatus) {this.checkStatus = checkStatus;}

}
