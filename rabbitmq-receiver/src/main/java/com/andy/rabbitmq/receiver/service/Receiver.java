package com.andy.rabbitmq.receiver.service;

import com.andy.rabbitmq.entities.Message;

public interface Receiver {

    void receiver1(Message message);

    void receiver2(Message message);

}
