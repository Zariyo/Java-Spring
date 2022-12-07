package pl.edu.ug.lab.wpluzek.lab8.Domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Person {
    @NotNull(message = "Nazwa jest obowiazkowa")
    private String name;
    private int age;
    private String postalCode;
    private int incomes;

    public Person(@NotNull(message = "Nazwa jest obowiazkowa") String name) {
        this.name = name;
    }

    public Person() {
    }

    private boolean accept;

//    public Person(@NotNull(message = "Nazwa jest obowiazkowa") String name, int age, String postalCode, int incomes, boolean accept) {
//        this.name = name;
//        this.age = age;
//        this.postalCode = postalCode;
//        this.incomes = incomes;
//        this.accept = accept;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getIncomes() {
        return incomes;
    }

    public void setIncomes(int incomes) {
        this.incomes = incomes;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
