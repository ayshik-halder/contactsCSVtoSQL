package com.ayshiktest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")

public class AyshikController {
	
	@GetMapping("/hello")
	public String helloworld() {
		
		return "Hello World!!";
	}
	
	@GetMapping("/getAyshikModel")
	public AyshikModel getAyshikModel() {
		
		return new AyshikModel("Ayshik","BCA806", "Howrah");
	}
	
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		
		return "Hello!! " + name;
	} //Sending data between URls
 
}
