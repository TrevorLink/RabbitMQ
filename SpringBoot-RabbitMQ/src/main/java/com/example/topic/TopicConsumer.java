package com.example.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author HuangSir
 * @date 2021-12-02 16:47
 */
@Component
public class TopicConsumer {
   @RabbitListener(bindings = {
           @QueueBinding(value = @Queue,exchange = @Exchange(name = "topicExchange",type = "topic"),key = {"user.*"})
   })
   public void user(String message){
      System.out.println("user = " + message);
   }
   @RabbitListener(bindings = {
           @QueueBinding(value = @Queue,exchange = @Exchange(name = "topicExchange",type = "topic"),key = {"order.*"})
   })
   public void order(String message){
      System.out.println("order = " + message);
   }
}
