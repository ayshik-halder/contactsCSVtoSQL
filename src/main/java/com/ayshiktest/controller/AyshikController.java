package com.ayshiktest.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.ayshiktest.entity.Contact;
import com.ayshiktest.exception.ConflictException;
import com.ayshiktest.exception.CustomGeneralException;
import com.ayshiktest.exception.ResourceNotFoundException;
import com.ayshiktest.model.ContactCsv;
import com.ayshiktest.repo.ContactRepo;
import com.ayshiktest.service.IAyshikService;
import com.ayshiktest.util.AyshikUtil;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/demo")
@CrossOrigin
public class AyshikController {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	AyshikUtil util;

	@Autowired
	IAyshikService ayshikService;

	 @Autowired
     ContactRepo contactRepo;

	@PostMapping("/upload")
	public List<ContactCsv> fileRead(@RequestBody MultipartFile file) throws CustomGeneralException {

		List<Contact> contactsList = ayshikService.fileRead(file);
		List<ContactCsv> contacts = util.mapList(contactsList, ContactCsv.class);
		return contacts;
	}

	@GetMapping("/getContact/{id}")
	public ContactCsv getContact(@PathVariable long id) throws ResourceNotFoundException {
		return mapper.map(ayshikService.getContact(id), ContactCsv.class);
	}

	@GetMapping("/getAllContacts")
	public List<ContactCsv> getAllContacts() {
		return util.mapList(ayshikService.getAllContacts(), ContactCsv.class);
	}

	@PostMapping("/addContact")
	public ContactCsv addContact(@RequestBody ContactCsv contactCsv) throws ConflictException, CustomGeneralException {
		if (contactCsv.allPropertiesNull()) throw new CustomGeneralException("All values should not be null.");
		Contact contact = mapper.map(contactCsv, Contact.class);
		contact = ayshikService.addContact(contact);
		contactCsv = mapper.map(contact, ContactCsv.class);
		return contactCsv;
	}

	@GetMapping("/getContactsByFirstName")
	public List<ContactCsv> getContactsByFirstName(@RequestParam("firstName") String firstName) {
		return util.mapList(ayshikService.getContactsByFirstName(firstName), ContactCsv.class);
	}

	@GetMapping("/getContactsByLastName")
	public List<ContactCsv> getContactsByLastName(@RequestParam("lastName") String lastName) {
		return util.mapList(ayshikService.getContactsByLastName(lastName), ContactCsv.class);

	}

	@DeleteMapping("/deleteAllTemp")
	public ResponseEntity<Void> deleteAllTemp() {
		ayshikService.deleteAllTemp();
		return new ResponseEntity<Void>(HttpStatus.valueOf(204));
	}

	@DeleteMapping("/deleteContact/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable long id) throws ResourceNotFoundException {
		ayshikService.deleteContact(id);
		return new ResponseEntity<Void>(HttpStatus.valueOf(204));
	}

	@PutMapping("/updateContact")
	public ContactCsv updateContact(@RequestBody ContactCsv contactCsv) throws ResourceNotFoundException, ConflictException {
		Contact contact = mapper.map(contactCsv, Contact.class);
		contact = ayshikService.updateContact(contact);
		contactCsv = mapper.map(contact, ContactCsv.class);
		return contactCsv;
	}

	@GetMapping("/search")
	public List<ContactCsv> search(@RequestParam("value") String value) {
		return util.mapList(ayshikService.search(value), ContactCsv.class);
	}
	 /*
	 @PostMapping("/addContacts")
	 public Contact addContact(@RequestBody Contact contact){
	 	if(contact.getEmail() == null || contact.getEmail().trim().isEmpty()) contact.setEmail("NA");
		 contact = contactRepo.save(contact);
	 	return contact;
	 }

	@GetMapping("/getAllcontacts")
	public List<Contact> getAllcontacts(){
		List<Contact> contacts= (List<Contact>) contactRepo.findAll();
		return contacts;
	}

	  List<AyshikModel> models = new ArrayList<AyshikModel>();

	  @GetMapping("/hello") public String helloworld() {

	  return "Hello World!!"; }

	  @GetMapping("/getAyshikModel") public AyshikModel getAyshikModel() {

	  return new AyshikModel("Ayshik","BCA806", "Howrah"); }

	  @GetMapping("/hello/{myName}") public String hello(@PathVariable(name =
	  "myName", required = true) String name) {
	  	return "Hello!! " + name; } //Sending data between URls

	  @PostMapping("/hello") public AyshikModel addData(@RequestBody AyshikModel
	  ayshikModel) {

	  models.add(ayshikModel);

	  return ayshikModel; }

	  @GetMapping("/hello/search") public AyshikModel
	  searchDataByName(@RequestParam(name = "name", required = false) String name)
	  throws Exception {

	  for(AyshikModel model : models) { if(model.getName().equalsIgnoreCase(name))
	  { return model; } } throw new Exception("Could not Find!!"); }

	/*
	@Autowired
	StudentRepo studentRepo;
	
	@PostMapping()
	public Student create(@RequestBody Student student) {
		
		return studentRepo.save(student);
	}
	
	@GetMapping()
	public List<Student> getAllStudents(){
		
		return (List<Student>)studentRepo.findAll();
	}
	
	@GetMapping("/search")
	public List<Student> searchAllStudents(@RequestParam(name = "add", required = false) String add){
		
		
		return studentRepo.findByAddress(add);
	}
	*/
	
}
