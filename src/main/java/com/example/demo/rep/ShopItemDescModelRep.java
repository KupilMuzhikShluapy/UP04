package com.example.demo.rep;

import com.example.demo.model.ShopItemDescModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopItemDescModelRep extends CrudRepository<ShopItemDescModel, Long> {
}
