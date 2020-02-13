package com.andy.rabbitmq.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {

    private Integer id;

    private String content;

    private String timeStamp;

}
