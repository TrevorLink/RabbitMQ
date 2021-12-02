package com.example.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author HuangSir
 * @date 2021-12-02 16:32
 */
@Component
public class DirectConsumer {
   @RabbitListener(bindings = {
           @QueueBinding(value = @Queue,exchange = @Exchange(value = "logDirect",type = "direct"),key = {
                   "info","error","warning"
           })
   })
   public void printToScreen(String message){
      System.out.println("printToScreen = " + message);
   }
   @RabbitListener(bindings = {
           @QueueBinding(value = @Queue,exchange = @Exchange(value = "logDirect",type = "direct"),key = {
                   "error"
           })
   })
   public void saveToDisk(String message){
      System.out.println("saveToDisk = " + message);
   }

}
