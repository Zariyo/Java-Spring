package pl.edu.ug.lab.wpluzek.lab9.Domain;

public class Address {

    private Person person;

    public Address(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
