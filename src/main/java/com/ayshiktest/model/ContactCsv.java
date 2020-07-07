package com.ayshiktest.model;


import com.opencsv.bean.CsvBindByName;
import org.dozer.Mapping;

public class ContactCsv {

    private long id;

    @CsvBindByName(column = "Given Name")
    private String firstName;

    @CsvBindByName(column = "Family Name")
    private String lastName;

    @Mapping("phoneNumber")
    @CsvBindByName(column = "Phone 1 - Value")
    private String phoneNumber;

    @CsvBindByName(column = "E-mail 1 - Value")
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactCsv() {}
    public ContactCsv(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
