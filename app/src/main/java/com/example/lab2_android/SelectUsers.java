package com.example.lab2_android;

public class SelectUsers {
    private int position;
    private boolean click;
    public SelectUsers(boolean click, int position) {
        this.click = click;
        this.position = position;
    }

    public boolean getClick () {
        return click;
    }
    public int getPosition () {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
}
