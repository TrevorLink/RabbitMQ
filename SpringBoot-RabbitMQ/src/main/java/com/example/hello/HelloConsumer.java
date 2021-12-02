package com.example.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Hello模型的消费者
 * @author HuangSir
 * @date 2021-12-02 10:48
 */
@Component//注册为IOC容器中的一个组件
//表示这个类是消息的消费者，并且绑定的队列是hello队列
@RabbitListener(queuesToDeclare = @Queue(value = "hello",durable = "false",exclusive = "false",autoDelete = "true"))
public class HelloConsumer {
   @RabbitHandler//这个方法是消费者消费消息时触发的回调函数
   public void consumeMessage(String message){
      System.out.println("message = " + message);
   }
}
