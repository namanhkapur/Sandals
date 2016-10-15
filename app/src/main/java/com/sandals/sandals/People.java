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
        this.iconID = "";
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
                string_feeling = "Surprised";
                break;
            case 4:
                string_feeling = "Excited";
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

    public int getUserID() {
        return userID;
    }

    public int getIconID() {
        return Integer.parseInt(iconID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        People people = (People) o;

        if (name != null ? !name.equals(people.name) : people.name != null) return false;
        return email != null ? email.equals(people.email) : people.email == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
