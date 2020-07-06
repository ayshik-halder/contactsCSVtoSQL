package com.ayshiktest.service;

import com.ayshiktest.entity.Contact;
import com.ayshiktest.model.ContactCsv;
import com.ayshiktest.repo.ContactRepo;
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

@Service
@Transactional
public class AyshikService implements IAyshikService {



    @Autowired
    ContactRepo contactRepo;

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

                for (int i = 0; i< 30;i ++)   {
                    ContactCsv contact = (ContactCsv) cons.get(i);            contacts.add(contact);
                    String phoneNum = contact.getNumber().replaceAll("\\D+","");
                    Contact contactDb = new Contact(contact.getFirstName(), contact.getLastName(), phoneNum.trim(), contact.getEmail());
                    contactsList.add(contactDb);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            contactsList.forEach(contact -> System.out.println(contact.toString()));
            contactsList = (List<Contact>) contactRepo.saveAll(contactsList);
        }
        return  contactsList;
    }
}
