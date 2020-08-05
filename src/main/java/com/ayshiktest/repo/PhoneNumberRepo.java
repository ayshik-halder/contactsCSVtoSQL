package com.ayshiktest.repo;

import com.ayshiktest.entity.Contact;
import com.ayshiktest.entity.PhoneNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepo extends CrudRepository<PhoneNumber, Long> {

    List<PhoneNumber> findByContact(Contact contact);
    List<PhoneNumber> findByPhoneNumberContainingIgnoreCase(String number);
}
