package com.ayshiktest;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.sym.Name;

@RestController
@RequestMapping("/demo")

public class AyshikController {
	
	/*
	 * List<AyshikModel> models = new ArrayList<AyshikModel>();
	 * 
	 * @GetMapping("/hello") public String helloworld() {
	 * 
	 * return "Hello World!!"; }
	 * 
	 * @GetMapping("/getAyshikModel") public AyshikModel getAyshikModel() {
	 * 
	 * return new AyshikModel("Ayshik","BCA806", "Howrah"); }
	 * 
	 * @GetMapping("/hello/{myName}") public String hello(@PathVariable(name =
	 * "myName", required = true) String name) {
	 * 
	 * return "Hello!! " + name; } //Sending data between URls
	 * 
	 * @PostMapping("/hello") public AyshikModel addData(@RequestBody AyshikModel
	 * ayshikModel) {
	 * 
	 * models.add(ayshikModel);
	 * 
	 * return ayshikModel; }
	 * 
	 * @GetMapping("/hello/search") public AyshikModel
	 * searchDataByName(@RequestParam(name = "name", required = false) String name)
	 * throws Exception {
	 * 
	 * for(AyshikModel model : models) { if(model.getName().equalsIgnoreCase(name))
	 * { return model; } } throw new Exception("Could not Find!!"); }
	 */
	
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
	
	
}
