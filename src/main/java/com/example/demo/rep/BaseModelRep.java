package com.example.demo.rep;

import com.example.demo.model.BaseModel;
import com.example.demo.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseModelRep extends CrudRepository<BaseModel, Long> {
    BaseModel findByTitle(String string);
}
