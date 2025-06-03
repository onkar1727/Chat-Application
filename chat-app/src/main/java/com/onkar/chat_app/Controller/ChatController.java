package com.onkar.chat_app.Controller;

import com.onkar.chat_app.Entities.Message;
import com.onkar.chat_app.Entities.Room;
import com.onkar.chat_app.Repositories.RoomRepository;
import com.onkar.chat_app.playload.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Controller
@RestController
public class ChatController
{

    @Autowired
    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository)
    {
        this.roomRepository=roomRepository;
    }

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room{roomId}")
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    )
    {
       Room room= roomRepository.findByRoomId(request.getRoomId());

       Message message=new Message();
       message.setContent(request.getContent());
       message.setSender(request.getSender());
       message.setTimeStamp(LocalDateTime.now());

       if(room!=null)
       {
           room.getMessage().add(message);
           roomRepository.save(room);
       }
       else
       {
           throw new RuntimeException("Room not Found !!");
       }

    }

}
