package com.example.musicalarm;

public class Alarm {
    int id;
    String time,path;
    Alarm(){ }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Alarm(int id, String time, String path) {
        this.id = id;
        this.time = time;
        this.path = path;
    }
}
