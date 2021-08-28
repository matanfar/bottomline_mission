package com.example.bottomline.repository;

import com.example.bottomline.model.BoyName;
import org.springframework.data.repository.CrudRepository;

public interface NamesRepo extends CrudRepository <BoyName, Integer> {

}
