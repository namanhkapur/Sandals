package com.sandals.sandals;

/**
 * Created by namanh on 10/15/2016.
 */

public class People {

    public String name;
    public int feeling;
    public String string_feeling;
    public String status;
    public int userID;
    public String iconID;
    public String email;

    public People(String name) {
        this.name = name;
        this.string_feeling = "";
        this.status = "";
    }

    public void setFeeling(int f) { feeling = f; }

    public void setEmail(String e) { email = e; }

    public String getName() {
        return name;
    }

    private void setMood(int mood) {
        feeling = mood;

        switch (feeling) {
            case 0:
                string_feeling = "Happy";
                break;
            case 1:
                string_feeling = "Mad";
                break;
            case 2:
                string_feeling = "Nervous";
                break;
            case 3:
                string_feeling = "Surprise";
                break;
            case 4:
                string_feeling = "Trust";
                break;
            case 5:
                string_feeling = "Confused";
                break;
            case 6:
                string_feeling = "Afraid";
                break;
            case 7:
                string_feeling = "Love";
                break;
            default:
                string_feeling = "Null";
        }
    }

    public String getMood() {
        return string_feeling;
    }

    public String getEmail() { return email; }

    public void setStatus(String message, int limit) {
        if (message.length() <= limit) {
            status = message;
        } else
            status = "Error";
    }

    public String getStatus() {
        return status;
    }

    public void setUserID(int u) { userID = u; }

    // for picture capability
    public void setIconID(String picture) {
        iconID = picture;
    }

    public String getIconID() {
        return iconID;
    }

}
