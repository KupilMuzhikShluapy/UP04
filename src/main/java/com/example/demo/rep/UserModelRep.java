package com.example.demo.rep;

import com.example.demo.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRep extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
