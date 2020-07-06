package com.ayshiktest.model;


import com.opencsv.bean.CsvBindByName;

public class ContactCsv {

    @CsvBindByName(column = "Given Name")
    private String firstName;

    @CsvBindByName(column = "Family Name")
    private String lastName;

    @CsvBindByName(column = "Phone 1 - Value")
    private String number;

    @CsvBindByName(column = "E-mail 1 - Value")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String  getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactCsv() {}
    public ContactCsv(String firstName, String lastName, String number, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number=" + number +
                ", email='" + email + '\'' +
                '}';
    }
}
