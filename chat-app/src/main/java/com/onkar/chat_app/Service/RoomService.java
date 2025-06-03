package com.onkar.chat_app.Service;

import com.onkar.chat_app.Entities.Room;
import com.onkar.chat_app.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room findByRoomId(String roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    public Room createRoom(String roomId) {
        Room room = new Room();
        room.setRoomId(roomId);
        return roomRepository.save(room);
    }
}
