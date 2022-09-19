package com.example.demo.rep;

import com.example.demo.model.BaseShopItemModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseShopItemModelRep extends CrudRepository<BaseShopItemModel, Long> {
}
