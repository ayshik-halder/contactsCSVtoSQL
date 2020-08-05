package com.ayshiktest.entity;

import org.dozer.Mapping;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "contact_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "contact_id_seq", sequenceName = "contact_id_seq")
    @Column(name = "contactId")
    private long contactId;

    @Column(nullable = false)
    private String firstName;

    @Column()
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    private List<PhoneNumber> phoneNumber = new ArrayList<>();

    @Column()
    private String email = "NA";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
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

    public List<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact() {
    }

    public Contact(long contactId, String firstName, String lastName, String email) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
