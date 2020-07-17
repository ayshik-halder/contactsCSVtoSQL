package com.ayshiktest.model;

public class PhoneNumberModel {

    private long phoneNumberId;

    private String phoneNumber;

    private String phoneNumberType;

    public long getPhoneNumberId() {
        return phoneNumberId;
    }

    public void setPhoneNumberId(long phoneNumberId) {
        this.phoneNumberId = phoneNumberId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setPhoneNumberType(String phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    public PhoneNumberModel() {
    }

    @Override
    public String toString() {
        return "PhoneNumberModel{" +
                "phoneNumberId=" + phoneNumberId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberType='" + phoneNumberType + '\'' +
                '}';
    }
}
