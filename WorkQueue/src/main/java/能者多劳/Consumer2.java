package 能者多劳;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-11-30 14:48
 */
public class Consumer2 {
   public static void main(String[] args) throws IOException {
      Connection connection = RabbitMQUtils.getConnection();
      final Channel channel = connection.createChannel();
      channel.queueDeclare("work",true,false,false,null);
      channel.basicQos(1);//消息队列每次只发送一条
      channel.basicConsume("work",false,new DefaultConsumer(channel){
         @Override
         public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            System.out.println("Consumer-2："+new String(body));
            //取消自动确认后我们就手动确认消息
            channel.basicAck(envelope.getDeliveryTag(),false);
         }
      });
   }
}
