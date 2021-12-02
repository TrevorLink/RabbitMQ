package com.example.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author HuangSir
 * @date 2021-12-02 16:18
 */
@Component
public class FanoutConsumer {
   @RabbitListener(bindings = {
           @QueueBinding(value = @Queue,exchange = @Exchange(value = "log",type = "fanout"))
   })
   public void consume1(String message){
      System.out.println("message1： = " + message);
   }

   @RabbitListener(bindings = {
           @QueueBinding(value = @Queue,exchange = @Exchange(value = "log",type = "fanout"))
   })
   public void consume2(String message){
      System.out.println("message2： = " + message);
   }
}
