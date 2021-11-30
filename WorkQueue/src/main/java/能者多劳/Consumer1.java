package 能者多劳;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-11-30 14:48
 */
public class Consumer1 {
   public static void main(String[] args) throws IOException {
      Connection connection = RabbitMQUtils.getConnection();
      final Channel channel = connection.createChannel();
      channel.queueDeclare("work",true,false,false,null);
      channel.basicQos(1);//设置队列每次只发一个消息给消费者
      channel.basicConsume("work",false,new DefaultConsumer(channel){
         @Override
         public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            try {
               //第一个Customer确认的慢
               Thread.sleep(2000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("Consumer-1："+new String(body));
            //调用channel中的basicAck方法来确认消息
            //参数①：确认具体队列中的哪一个消息
            //参数②：是否开启多个消息同时确认
            channel.basicAck(envelope.getDeliveryTag(),false);
         }
      });
   }
}
