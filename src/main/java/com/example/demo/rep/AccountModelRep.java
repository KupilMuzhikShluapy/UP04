package com.example.demo.rep;

import com.example.demo.model.AccountModel;
import com.example.demo.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface AccountModelRep extends CrudRepository<AccountModel, Long> {
    AccountModel findByUser(UserModel user);
}
