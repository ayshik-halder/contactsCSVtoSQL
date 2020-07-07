package com.ayshiktest.service;

import com.ayshiktest.entity.Contact;
import com.ayshiktest.model.ContactCsv;
import com.ayshiktest.repo.ContactRepo;
import com.ayshiktest.util.AyshikUtil;
import com.google.common.base.Strings;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AyshikService implements IAyshikService {

    @Autowired
    ContactRepo contactRepo;

    @Autowired
    AyshikUtil util;

    @Override
    public List<Contact> fileRead(@RequestParam("file") MultipartFile file) {

        List<ContactCsv> contacts = new ArrayList<>();
        List<Contact> contactsList = new ArrayList<>();
        if (!file.isEmpty()) {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Object> csvToBean = new CsvToBeanBuilder<>(reader)
                        .withType(ContactCsv.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<Object> cons = csvToBean.parse();
                contactsList = util.mapList(cons, Contact.class);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            contactsList = (List<Contact>) contactRepo.saveAll(contactsList);
        }
        return  contactsList;
    }

    @Override
    public List<Contact> getAllContacts() {
        return (List<Contact>) contactRepo.findAll();
    }

    @Override
    public Contact addContact(Contact contact) {
        System.out.println(contact.toString());
        return contactRepo.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact contactFromDb = contactRepo.findById(contact.getId());
        if (!Strings.isNullOrEmpty(contact.getFirstName())) contactFromDb.setFirstName(contact.getFirstName());
        if (!Strings.isNullOrEmpty(contact.getLastName())) contactFromDb.setLastName(contact.getLastName());
        if (!Strings.isNullOrEmpty(contact.getEmail())) contactFromDb.setEmail(contact.getEmail());
        if (!Strings.isNullOrEmpty(contact.getPhoneNumber())) contactFromDb.setPhoneNumber(contact.getPhoneNumber());
        contactFromDb = contactRepo.save(contactFromDb);
        return contactFromDb;
    }

    @Override
    public List<Contact> getContactsByFirstName(String firstName) {
        return contactRepo.findByFirstNameIgnoreCase(firstName);
    }

    @Override
    public List<Contact> getContactsByLastName(String lastName) {
        return contactRepo.findByLastNameIgnoreCase(lastName);
    }

    @Override
    public List<Contact> getContactsByEmail(String email) {
        return contactRepo.findByEmailIgnoreCase(email);
    }

    @Override
    public void deleteAllTemp() {

        List<Contact> list1 = contactRepo.findByLastNameIgnoreCase("Da");
        List<Contact> list2 = contactRepo.findByFirstNameIgnoreCase("WorlPOOL");
        List<Contact> list3 = contactRepo.findByLastNameAndFirstNameAllIgnoreCase("WashING", "WorlPOOL");
        List<Contact> list4 = contactRepo.findByLastNameOrFirstNameAllIgnoreCase("BanerJEE", "WorlPOOL");
        List<Contact> list5 = contactRepo.findByEmailIgnoreCase("SANDIP@testEmail.COM");
        List<Contact> list6 = contactRepo.findByEmailContainingIgnoreCase("email");
        List<Contact> list7 = contactRepo.findByFirstNameContainingIgnoreCase("z");
        List<Contact> list8 = contactRepo.findByLastNameContainingIgnoreCase("ER");
      
        contactRepo.deleteAll();
    }

    @Override
    public void deleteContact(long id) {
        contactRepo.deleteById(id);
    }

    @Override
    public Contact getContact(long id) {
        return contactRepo.findById(id);
    }

    @Override
    public List<Contact> search(String value) {

        List<Contact> contactsEmail = contactRepo.findByEmailContainingIgnoreCase(value);
        contactsEmail.addAll(contactRepo.findByFirstNameContainingIgnoreCase(value));
        contactsEmail.addAll(contactRepo.findByLastNameContainingIgnoreCase(value));
        contactsEmail.addAll(contactRepo.findByPhoneNumberContainingIgnoreCase(value));
        return contactsEmail;

    }
}
