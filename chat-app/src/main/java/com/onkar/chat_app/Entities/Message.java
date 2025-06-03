package com.onkar.chat_app.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message
{
  private String sender;
  private String content;
  private LocalDateTime timeStamp;

  public Message(String sender,String content)
  {
      this.sender=sender;
      this.content=content;
      this.timeStamp=LocalDateTime.now();
  }
}

