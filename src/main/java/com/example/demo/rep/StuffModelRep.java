package com.example.demo.rep;

import com.example.demo.model.StuffModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffModelRep extends CrudRepository<StuffModel, Long> {
}
