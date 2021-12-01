import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-12-01 14:46
 */
public class Consumer1 {
   public static void main(String[] args) throws IOException {
      Connection connection = RabbitMQUtils.getConnection();
      Channel channel = connection.createChannel();
      String exchange="topic_exchange";
      channel.exchangeDeclare(exchange,"topic");
      String tempQueue = channel.queueDeclare().getQueue();//临时队列
      //表示我这个临时队列可以接收user.*占位符的RoutingKey（动态性）
      channel.queueBind(tempQueue,exchange,"user.*");
      channel.basicConsume(tempQueue,true,new DefaultConsumer(channel){
         @Override
         public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            System.out.println("Consumer1："+new String(body));
         }
      });
   }
}
