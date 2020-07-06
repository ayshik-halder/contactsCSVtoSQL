package com.ayshiktest.service;

import com.ayshiktest.entity.Contact;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAyshikService {
    List<Contact> fileRead(@RequestParam("file") MultipartFile file);

}
