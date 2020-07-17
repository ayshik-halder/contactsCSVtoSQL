package com.ayshiktest.service;

import com.ayshiktest.entity.Contact;
import com.ayshiktest.entity.PhoneNumber;
import com.ayshiktest.exception.ConflictException;
import com.ayshiktest.exception.CustomGeneralException;
import com.ayshiktest.exception.ResourceNotFoundException;
import com.ayshiktest.model.ContactCsv;
import com.ayshiktest.model.ContactModel;
import com.ayshiktest.model.PhoneNumberModel;
import com.ayshiktest.repo.ContactRepo;
import com.ayshiktest.repo.PhoneNumberRepo;
import com.ayshiktest.util.AyshikUtil;
import com.google.common.base.Strings;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

@Service
@Transactional
public class AyshikService implements IAyshikService {

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    ContactRepo contactRepo;

    @Autowired
    PhoneNumberRepo phoneNumberRepo;

    @Autowired
    AyshikUtil util;

    @Override
    public List<ContactModel> fileRead(@RequestParam("file") MultipartFile file) throws CustomGeneralException {

        List<ContactCsv> contacts = new ArrayList<>();
        List<ContactModel> toReturn = new ArrayList<>();
        if (!file.isEmpty()) {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Object> csvToBean = new CsvToBeanBuilder<>(reader)
                        .withType(ContactCsv.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<Object> cons = csvToBean.parse();
                contacts = util.mapList(cons, ContactCsv.class);

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomGeneralException(ex.getMessage());
            }
        }
        //Saving To Db
        for (ContactCsv conCsv : contacts) {
            Contact con = mapper.map(conCsv, Contact.class);
            con = contactRepo.save(con);

            List<PhoneNumber> phoneNumberList = convertPhnNum(conCsv, con);
            phoneNumberList = (List<PhoneNumber>) phoneNumberRepo.saveAll(phoneNumberList);

            ContactModel cm = mapper.map(con, ContactModel.class);
            List<PhoneNumberModel> pnm = util.mapList(phoneNumberList, PhoneNumberModel.class);
            cm.setPhoneNumber(pnm);
            toReturn.add(cm);
        }

        return  toReturn;
    }

    private List<PhoneNumber> convertPhnNum(ContactCsv conCsv, Contact con) {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();

        if (!Strings.isNullOrEmpty(conCsv.getPhoneNumber1())) {
            String[] phn1s = conCsv.getPhoneNumber1().split(":::");
            int max = phn1s.length > 2 ? 2 : phn1s.length;
            for (int i = 0; i < max; i++) {
                if(phn1s[i] != null) phn1s[i] = phn1s[i].trim();
                phoneNumberList.add(new PhoneNumber(con, phn1s[i], conCsv.getPhoneNumberType1()));
            }
        }

        if (!Strings.isNullOrEmpty(conCsv.getPhoneNumber2())) {
            String[] phn2s = conCsv.getPhoneNumber2().split(":::");
            int max = phn2s.length > 2 ? 2 : phn2s.length;
            for (int i = 0; i < max; i++) {
                if(phn2s[i] != null) phn2s[i] = phn2s[i].trim();
                phoneNumberList.add(new PhoneNumber(con, phn2s[i], conCsv.getPhoneNumberType1()));
            }
        }

        return phoneNumberList;
    }

    private boolean isDuplicate(List<Contact> elements, Contact element) {
        for(Contact c : elements) {
            if (allPropertiesEqual(c, element)) return true;
        }
        return false;
    }

    private boolean allPropertiesEqual(Contact c1, Contact c2) {
        return (c1.getFirstName().equals(c2.getFirstName()) && c1.getLastName().equals(c2.getLastName())
                && c1.getEmail().equals(c2.getEmail()));
    }

    @Override
    public List<ContactModel> getAllContacts() {

        return util.mapList((List<Contact>) contactRepo.findAll(), ContactModel.class);
    }

    @Override
    public ContactModel addContact(ContactModel contactModel) throws ConflictException {
        //Ignore the incoming Id Field
        contactModel.setContactId(0);
        List<Contact> fromDb = (List<Contact>) contactRepo.findAll();
        Contact contact = mapper.map(contactModel, Contact.class);
        if (isDuplicate(fromDb, contact)) throw new ConflictException("Already Exists!!");

        contact = contactRepo.save(contact);
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (PhoneNumberModel phn : contactModel.getPhoneNumber()) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setPhoneNumber(phn.getPhoneNumber());
            phoneNumber.setPhoneNumberType(phn.getPhoneNumberType());
            phoneNumber.setContact(contact);
            phoneNumbers.add(phoneNumber);
        }
        phoneNumbers = (List<PhoneNumber>) phoneNumberRepo.saveAll(phoneNumbers);
        contactModel = mapper.map(contact, ContactModel.class);
        List<PhoneNumberModel> phoneNumberModels = util.mapList(phoneNumbers, PhoneNumberModel.class);
        contactModel.setPhoneNumber(phoneNumberModels);

        return contactModel;
    }

    @Override
    public ContactModel updateContact(ContactModel contactModel) throws ResourceNotFoundException {
        Contact contactFromDb = contactRepo.findById(contactModel.getContactId());
        if (contactFromDb == null) throw new ResourceNotFoundException("No contacts found for id : " + contactModel.getContactId());

        Contact toSave = new Contact(contactFromDb.getContactId(), contactFromDb.getFirstName(), contactFromDb.getLastName(), contactFromDb.getEmail());
        toSave.setPhoneNumber(contactFromDb.getPhoneNumber());
        if (!Strings.isNullOrEmpty(contactModel.getFirstName())) toSave.setFirstName(contactModel.getFirstName());
        if (!Strings.isNullOrEmpty(contactModel.getLastName())) toSave.setLastName(contactModel.getLastName());
        if (!Strings.isNullOrEmpty(contactModel.getEmail())) toSave.setEmail(contactModel.getEmail());
        toSave = contactRepo.save(toSave);

        // Remove if not present:
        List<PhoneNumber> fromDb = phoneNumberRepo.findByContact(contactFromDb);
        for(PhoneNumber pn : fromDb){
                phoneNumberRepo.deleteById(pn.getPhoneNumberId());
        }

        for (PhoneNumberModel pm : contactModel.getPhoneNumber()) {
            PhoneNumber pn = new PhoneNumber();
            pn.setPhoneNumber(pm.getPhoneNumber());
            pn.setPhoneNumberType(pm.getPhoneNumberType());
            pn.setContact(toSave);
            phoneNumberRepo.save(pn);
        }

        toSave.setPhoneNumber(phoneNumberRepo.findByContact(contactFromDb));

        return mapper.map(toSave, ContactModel.class);
    }

    @Override
    public List<ContactModel> getContactsByFirstName(String firstName) {
        return util.mapList(contactRepo.findByFirstNameIgnoreCase(firstName), ContactModel.class);
    }

    @Override
    public List<ContactModel> getContactsByLastName(String lastName) {
        return util.mapList(contactRepo.findByLastNameIgnoreCase(lastName), ContactModel.class);
    }

    @Override
    public List<ContactModel> getContactsByEmail(String email) {

        return util.mapList(contactRepo.findByEmailIgnoreCase(email), ContactModel.class);
    }

    @Override
    public void deleteAllTemp() {
        phoneNumberRepo.deleteAll();
        contactRepo.deleteAll();
    }


    @Override
    public void deleteContact(long id) throws ResourceNotFoundException {

        Contact contact = contactRepo.findById(id);
        if (contact == null) throw new ResourceNotFoundException("No contacts found for id : " + id);

        List<PhoneNumber> phones = phoneNumberRepo.findByContact(contact);

        phoneNumberRepo.deleteAll(phones);
        contactRepo.delete(contact);
    }

    @Override
    public ContactModel getContact(long id) throws ResourceNotFoundException {
        Contact con = contactRepo.findById(id);
        if (con == null) throw new ResourceNotFoundException("No contacts found for id : " + id);
        return mapper.map(con, ContactModel.class);
    }

    @Override
    public List<ContactModel> search(String value) {

        List<Contact> contacts = new ArrayList<>();
        List<Contact> contactsEmail = contactRepo.findByEmailContainingIgnoreCase(value);
        contactsEmail.addAll(contactRepo.findByFirstNameContainingIgnoreCase(value));
        contactsEmail.addAll(contactRepo.findByLastNameContainingIgnoreCase(value));
        List<PhoneNumber> phones = phoneNumberRepo.findByPhoneNumberContainingIgnoreCase(value);
        for (PhoneNumber phn : phones)
            contactsEmail.add(contactRepo.findById(phn.getContact().getContactId()));
        for (Contact con : contactsEmail) {
            if(!isDuplicate(contacts, con))
                contacts.add(con);
        }
        return util.mapList(contacts, ContactModel.class);

    }
}
