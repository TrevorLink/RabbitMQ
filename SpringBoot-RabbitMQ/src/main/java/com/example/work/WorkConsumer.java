package com.example.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author HuangSir
 * @date 2021-12-02 15:55
 */
@Component
public class WorkConsumer {
   //@RabbitListener不仅可以修饰类，而且还可以修饰方法，一个@RabbitListener对应一个消费者
   //现在就不再需要@RabbitHandler标记回调函数了
   @RabbitListener(queuesToDeclare = @Queue("work"))
   public void consume1(String message) {
      System.out.println("消费者1：message = " + message);
   }

   @RabbitListener(queuesToDeclare = @Queue("work"))
   public void consume2(String message) {
      System.out.println("消费者2：message = " + message);
   }
}
