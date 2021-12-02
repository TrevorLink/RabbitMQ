package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author HuangSir
 * @date 2021-12-02 10:35
 */
@SpringBootTest(classes = SpringBootRabbitMqApplication.class)
@RunWith(SpringRunner.class)
public class RabbitMQTest {
   @Autowired
   private RabbitTemplate rabbitTemplate;
   //模拟的就是消息的生产者
   @Test
   public void testHello(){
      rabbitTemplate.convertAndSend("hello","Hello模型");
   }

   @Test
   public void testWork(){
      for (int i = 0; i < 10; i++) {
         rabbitTemplate.convertAndSend("work","Work模型");
      }
   }
   @Test
   public void testFanOut(){
      rabbitTemplate.convertAndSend("log","","Fanout模型");
   }
   @Test
   public void testDirect(){
      String routingKey = "error";
      rabbitTemplate.convertAndSend("logDirect",routingKey,"Direct模型");
   }
   @Test
   public void testTopic(){
      String routingKey="order.save";
      rabbitTemplate.convertAndSend("topicExchange",routingKey,"Topic模型");
   }
}
