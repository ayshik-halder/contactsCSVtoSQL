package com.ayshiktest.service;

import com.ayshiktest.entity.Contact;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAyshikService {
    List<Contact> fileRead(@RequestParam("file") MultipartFile file);
    List<Contact> getAllContacts();
    Contact addContact(Contact contact);
    List<Contact> getContactsByFirstName(String firstName);
    List<Contact> getContactsByLastName(String lastName);
    List<Contact> getContactsByEmail(String email);
    void deleteAllTemp();
}
