package com.example.demo.rep;

import com.example.demo.model.PersonModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonModelRep extends CrudRepository<PersonModel, Long> {
}
