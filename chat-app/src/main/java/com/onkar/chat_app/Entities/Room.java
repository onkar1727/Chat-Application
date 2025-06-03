package com.onkar.chat_app.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Rooms")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room
{
    @Id
    private String id;
    private String roomId;

    private List<Message> messages=new ArrayList<>();


    public List<Message> getMessage()
    {
            return messages;
    }

    public void setRoomId(String roomId)
    {
            this.roomId=roomId;
    }
}
