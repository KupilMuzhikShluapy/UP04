package com.example.demo.rep;

import com.example.demo.model.AccFavRoomModel;
import org.springframework.data.repository.CrudRepository;

public interface AccFavRoomModelRep extends CrudRepository<AccFavRoomModel, Long> {
    AccFavRoomModel findByAccount_IdAndRoom_Id(Long AccId, Long RoomId);
}
