package com.example.bottomline.controller;

import com.example.bottomline.repository.NamesRepo;
import com.example.bottomline.repository.NamesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import static com.example.bottomline.service.NamesService.getNamesByPrefix;

@RestController
public class NamesController {

    @Autowired
    NamesRepo repo;

    @GetMapping("/find/{prefix}")
    public ResponseEntity<List<String>> receiveNames(@PathVariable("prefix") String prefix){
        List<String> names = getNamesByPrefix(prefix);
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
}
