package com.onkar.chat_app.Controller;


import com.onkar.chat_app.Entities.Message;
import com.onkar.chat_app.Entities.Room;
import com.onkar.chat_app.Repositories.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;

@RestController
@RequestMapping("api/v1/rooms")
@CrossOrigin("http://localhost:3000")
public class RoomController
{
    private RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository)
    {
        this.roomRepository = roomRepository;
    }

    @PostMapping
    public ResponseEntity<?>createRoom(@RequestBody String roomId)
    {
        if(roomRepository.findByRoomId(roomId)!=null)

        //Room is already there
        {
            return ResponseEntity.badRequest().body("Room is Already Exists");
        }


        //Create the room
        Room room =new Room();
        room.setRoomId(roomId);
        Room saveRoom=roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId)
    {
        Room room=roomRepository.findByRoomId(roomId);
        if(room==null)
        {
            return ResponseEntity.badRequest().body("Room is not found");
        }
        return ResponseEntity.ok(room);

    }

    //Get Messages of Room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessage(@PathVariable String roomId,
                                                    @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(value = "size", defaultValue = "20", required = false) int size) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Message> messages = room.getMessage(); // must come first

        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> paginatedMessage = messages.subList(start, end);

        return ResponseEntity.ok(paginatedMessage);
    }


}



