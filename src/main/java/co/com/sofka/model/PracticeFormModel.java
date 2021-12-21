package co.com.sofka.model;

import co.com.sofka.util.Gender;
import co.com.sofka.util.Hobbies;

import java.util.List;

public class PracticeFormModel {
    private String name;
    private String lastName;
    private String email;
    private Gender gender;
    private String mobile;
    private String year;
    private String month;
    private String day;
    private List<String> subject;
    private List <Hobbies> hobbies;
    private String picture;
    private String currentAddres;
    private String state;
    private String city;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public Gender getGender () {
        return gender;
    }

    public void setGender (Gender gender) {
        this.gender = gender;
    }

    public String getMobile () {
        return mobile;
    }

    public void setMobile (String mobile) {
        this.mobile = mobile;
    }

    public String getYear () {
        return year;
    }

    public void setYear (String year) {
        this.year = year;
    }

    public String getMonth () {
        return month;
    }

    public void setMonth (String month) {
        this.month = month;
    }

    public String getDay () {
        return day;
    }

    public void setDay (String day) {
        this.day = day;
    }

    public List<String> getSubject () {
        return subject;
    }

    public void setSubject (List<String> subject) {
        this.subject = subject;
    }

    public List<Hobbies> getHobbies () {
        return hobbies;
    }

    public void setHobbies (List<Hobbies> hobbies) {
        this.hobbies = hobbies;
    }

    public String getPicture () {
        return picture;
    }

    public void setPicture (String picture) {
        this.picture = picture;
    }

    public String getCurrentAddres () {
        return currentAddres;
    }

    public void setCurrentAddres (String currentAddres) {
        this.currentAddres = currentAddres;
    }

    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }
}
