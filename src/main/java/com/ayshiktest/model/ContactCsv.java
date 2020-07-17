package com.ayshiktest.model;


import com.google.common.base.Strings;
import com.opencsv.bean.CsvBindByName;
import org.dozer.Mapping;

public class ContactCsv {

    private long id;

    @CsvBindByName(column = "Given Name")
    private String firstName;

    @CsvBindByName(column = "Family Name")
    private String lastName;

    @CsvBindByName(column = "Phone 1 - Value")
    private String phoneNumber1;

    @CsvBindByName(column = "Phone 1 - Type")
    private String phoneNumberType1;

    @CsvBindByName(column = "Phone 2 - Value")
    private String phoneNumber2;

    @CsvBindByName(column = "Phone 2 - Type")
    private String phoneNumberType2;

    @CsvBindByName(column = "E-mail 1 - Value")
    private String email;

    public ContactCsv() {
    }

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

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumberType1() {
        return phoneNumberType1;
    }

    public void setPhoneNumberType1(String phoneNumberType1) {
        this.phoneNumberType1 = phoneNumberType1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getPhoneNumberType2() {
        return phoneNumberType2;
    }

    public void setPhoneNumberType2(String phoneNumberType2) {
        this.phoneNumberType2 = phoneNumberType2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactCsv{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                ", phoneNumberType1='" + phoneNumberType1 + '\'' +
                ", phoneNumber2='" + phoneNumber2 + '\'' +
                ", phoneNumberType2='" + phoneNumberType2 + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean allPropertiesNull() {
        return (Strings.isNullOrEmpty(this.firstName)
                && Strings.isNullOrEmpty(this.lastName)
                && Strings.isNullOrEmpty(this.phoneNumber1)
                && Strings.isNullOrEmpty(this.phoneNumber2)
                && Strings.isNullOrEmpty(this.email)
        );
    }
}
