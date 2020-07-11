package com.ayshiktest.service;

import com.ayshiktest.entity.Contact;
import com.ayshiktest.exception.ConflictException;
import com.ayshiktest.exception.CustomGeneralException;
import com.ayshiktest.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAyshikService {
    List<Contact> fileRead(@RequestParam("file") MultipartFile file) throws CustomGeneralException;
    List<Contact> getAllContacts();
    Contact addContact(Contact contact) throws ConflictException;
    Contact updateContact(Contact contact) throws ResourceNotFoundException, ConflictException;
    List<Contact> getContactsByFirstName(String firstName);
    List<Contact> getContactsByLastName(String lastName);
    List<Contact> getContactsByEmail(String email);
    void deleteAllTemp();
    void deleteContact(long id) throws ResourceNotFoundException;
    Contact getContact(long id) throws ResourceNotFoundException;
    List<Contact> search(String value);
}
