package com.example.demo.rep;

import com.example.demo.model.ShopItemModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopItemModelRep extends CrudRepository<ShopItemModel, Long> {
}
