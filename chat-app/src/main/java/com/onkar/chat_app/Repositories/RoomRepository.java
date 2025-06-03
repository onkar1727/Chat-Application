package com.onkar.chat_app.Repositories;

import com.onkar.chat_app.Entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository  extends MongoRepository<Room,String>
{
    Room findByRoomId(String roomId);
}
