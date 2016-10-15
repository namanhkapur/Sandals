package com.sandals.sandals;

/**
 * Created by namanh on 10/15/2016.
 */

public class People {

    public String name;
    public int feeling;
    public String string_feeling;
    public String status;
    private int iconID;

    public People(String name, int feeling) {
        this.name = name;
        this.feeling = feeling;
        this.string_feeling = "";
        this.status = "";
        this.iconID = 0;
    }

    public String getName() {
        return name;
    }

    private void setMood(int mood) {
        feeling = mood;

        switch (feeling) {
            case 0:
                string_feeling = "Joy";
                break;
            case 1:
                string_feeling = "Anger";
                break;
            case 2:
                string_feeling = "Anxiety";
                break;
            case 3:
                string_feeling = "Surprise";
                break;
            case 4:
                string_feeling = "Trust";
                break;
            case 5:
                string_feeling = "Grief";
                break;
            case 6:
                string_feeling = "Fear";
                break;
            case 7:
                string_feeling = "love";
                break;
            default:
                string_feeling = "Null";
        }
    }

    public String getMood() {
        return string_feeling;
    }

    public void setStatus(String message, int limit) {
        if (message.length() <= limit) {
            status = message;
        } else
            status = "Error";
    }

    public String getStatus() {
        return status;
    }

    // for picture capability
    public void setIconID(int picture) {
        iconID = picture;
    }

    public int getIconID(int picture) {
        return iconID;
    }
}
