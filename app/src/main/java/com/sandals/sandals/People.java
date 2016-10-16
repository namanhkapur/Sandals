package com.sandals.sandals;

/**
 * Created by namanh on 10/15/2016.
 */

public class People {

    public String name;
    public int feeling;
    public int mood;
    public String status;
    public int userID;
    public String iconID;
    public String email;
    public long phoneNumber;

    public People(String name) {
        this.name = name;
        this.mood = 9;
        this.status = "";
        this.iconID = "";
        this.phoneNumber = 0;
    }

    public void setFeeling(int f) { feeling = f; }

    public void setEmail(String e) { email = e; }

    public String getName() {
        return name;
    }

    public void setMood(int feeling) {

        switch (feeling) {
            case 0:
                mood = R.mipmap.happy;
                break;
            case 1:
                mood = R.mipmap.mad;
                break;
            case 2:
                mood = R.mipmap.nervous;
                break;
            case 3:
                mood = R.mipmap.surprised;
                break;
            case 4:
                mood = R.mipmap.excited;
                break;
            case 5:
                mood = R.mipmap.confused;
                break;
            case 6:
                mood = R.mipmap.afraid;
                break;
            case 7:
                mood = R.mipmap.love;
                break;
            case 8:
                mood = R.mipmap.sad;
                break;
            default:
                mood = R.mipmap.sad;
        }

    }

    public int getMood() {
        return mood;
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

    public void setPhoneNumber(long num){
        phoneNumber = num;
    }

    public long getPhoneNumber(){return phoneNumber;}
}
