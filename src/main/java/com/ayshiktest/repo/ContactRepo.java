package com.ayshiktest.repo;

import com.ayshiktest.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends CrudRepository<Contact, Long> {

    List<Contact> findByLastNameIgnoreCase(String lastName);
    List<Contact> findByFirstNameIgnoreCase(String firstName);
    List<Contact> findByLastNameAndFirstNameAllIgnoreCase(String firstname, String lastname);
    List<Contact> findByLastNameOrFirstNameAllIgnoreCase(String firstname, String lastname);
    List<Contact> findByEmailIgnoreCase(String email);
    List<Contact> findByEmailContainingIgnoreCase(String email);
    List<Contact> findByFirstNameContainingIgnoreCase(String firstName);
    List<Contact> findByLastNameContainingIgnoreCase(String lastName);
}
