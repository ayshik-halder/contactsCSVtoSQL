package com.ayshiktest.controller;

import java.util.List;

import com.ayshiktest.entity.Contact;
import com.ayshiktest.exception.ConflictException;
import com.ayshiktest.exception.CustomGeneralException;
import com.ayshiktest.exception.ResourceNotFoundException;
import com.ayshiktest.model.ContactCsv;
import com.ayshiktest.model.ContactModel;
import com.ayshiktest.repo.ContactRepo;
import com.ayshiktest.service.IAyshikService;
import com.ayshiktest.util.AyshikUtil;
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
	public List<ContactModel> fileRead(@RequestBody MultipartFile file) throws CustomGeneralException {
		List<ContactModel> contactsList = ayshikService.fileRead(file);
		return contactsList;
	}

	@GetMapping("/getContact/{id}")
	public ContactModel getContact(@PathVariable long id) throws ResourceNotFoundException {
		return ayshikService.getContact(id);
	}

	@GetMapping("/getAllContacts")
	public List<ContactModel> getAllContacts() {
		return ayshikService.getAllContacts();
	}

	@PostMapping("/addContact")
	public ContactModel addContact(@RequestBody ContactModel contactModel) throws ConflictException, CustomGeneralException {
		util.validateIncomingPhoneNumberList(contactModel.getPhoneNumber());
		return ayshikService.addContact(contactModel);
	}

	@GetMapping("/getContactsByFirstName")
	public List<ContactModel> getContactsByFirstName(@RequestParam("firstName") String firstName) {
		return ayshikService.getContactsByFirstName(firstName);
	}

	@GetMapping("/getContactsByLastName")
	public List<ContactModel> getContactsByLastName(@RequestParam("lastName") String lastName) {
		return ayshikService.getContactsByLastName(lastName);

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
	public ContactModel updateContact(@RequestBody ContactModel contactModel) throws ResourceNotFoundException, ConflictException, CustomGeneralException {
		util.validateIncomingPhoneNumberList(contactModel.getPhoneNumber());
		return ayshikService.updateContact(contactModel);
	}

	@GetMapping("/search")
	public List<ContactModel> search(@RequestParam("value") String value) {
		return ayshikService.search(value);
	}
}
