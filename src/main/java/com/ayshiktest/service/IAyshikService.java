package com.ayshiktest.service;

import com.ayshiktest.entity.Contact;
import com.ayshiktest.exception.ConflictException;
import com.ayshiktest.exception.CustomGeneralException;
import com.ayshiktest.exception.ResourceNotFoundException;
import com.ayshiktest.model.ContactModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAyshikService {
    List<ContactModel> fileRead(MultipartFile file) throws CustomGeneralException;
    List<ContactModel> getAllContacts();
    ContactModel addContact(ContactModel contactModel) throws ConflictException;
    ContactModel updateContact(ContactModel contactModel) throws ResourceNotFoundException, ConflictException;
    List<ContactModel> getContactsByFirstName(String firstName);
    List<ContactModel> getContactsByLastName(String lastName);
    List<ContactModel> getContactsByEmail(String email);
    void deleteAllTemp();
    void deleteContact(long id) throws ResourceNotFoundException;
    ContactModel getContact(long id) throws ResourceNotFoundException;
    List<ContactModel> search(String value);
}
