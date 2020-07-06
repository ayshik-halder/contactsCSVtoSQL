package com.ayshiktest.repo;

import com.ayshiktest.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends CrudRepository<Contact, Long> {
}
