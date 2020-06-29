package com.ayshiktest;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/demo")

public class AyshikController {

	 @Autowired
	 ContactRepo contactRepo;

	@PostMapping("/upload")
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

				for (Object con : cons)
				{
					ContactCsv contact = (ContactCsv) con;
					contacts.add(contact);
					String phoneNum = contact.getNumber().replaceAll("\\D+","");
					Contact contactDb = new Contact(contact.getFirstName(), contact.getLastName(), phoneNum.trim(), contact.getEmail());
					contactsList.add(contactDb);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			contactsList = (List<Contact>) contactRepo.saveAll(contactsList);
		}
		return contactsList;
	}

	@GetMapping("/getAllContacts")
	public List<Contact> getAllContacts() {

		return (List<Contact>) contactRepo.findAll();
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
