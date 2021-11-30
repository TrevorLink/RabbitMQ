package 平均分配;

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
      Channel channel = connection.createChannel();
      channel.queueDeclare("work",true,false,false,null);
      channel.basicConsume("work",true,new DefaultConsumer(channel){
         @Override
         public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            System.out.println("Consumer-2："+new String(body));
         }
      });
   }
}
