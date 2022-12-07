package pl.edu.ug.lab.wpluzek.lab7.Model;

import pl.edu.ug.lab.wpluzek.lab7.Model.types.UserType;

import java.util.Date;

public class User {

    private String id;
    private int age;
    private String name;

    private Date registrationDate;

    public String getId() {
        return id;
    }

    private String gender;

    private pl.edu.ug.lab.wpluzek.lab7.Model.types.UserType userType;
    public User(String id, int age, String name, Date registrationDate, String gender) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.registrationDate = registrationDate;
        this.gender = gender;
    }

    public User(String name, int age, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(String id, String name, int age, String gender) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.gender = gender;
    }
}
