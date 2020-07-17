package com.ayshiktest.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumber implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "phone_number_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "phone_number_id_seq", sequenceName = "phone_number_id_seq")
    @Column(name = "phoneNumberId")
    private long phoneNumberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", nullable = false)
    private Contact contact;

    @Column()
    private String phoneNumber;

    @Column()
    private String phoneNumberType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public PhoneNumber() {
    }

    public long getPhoneNumberId() {
        return phoneNumberId;
    }

    public void setPhoneNumberId(long phoneNumberId) {
        this.phoneNumberId = phoneNumberId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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

    public PhoneNumber(Contact contact, String phoneNumber, String phoneNumberType) {
        this.contact = contact;
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumberId=" + phoneNumberId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneNumberType='" + phoneNumberType + '\'' +
                '}';
    }
}
