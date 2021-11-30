import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HuangSir
 * @date 2021-11-30 10:16
 */
public class Consumer {
   public static void main(String[] args) throws IOException, TimeoutException {
//      ConnectionFactory connectionFactory = new ConnectionFactory();
//      connectionFactory.setHost("120.24.179.229");
//      connectionFactory.setPort(5672);
//      connectionFactory.setVirtualHost("/Hello");
//      connectionFactory.setUsername("Niaotendo");
//      connectionFactory.setPassword("hyy2845964844");
//      Connection connection = connectionFactory.newConnection();
      Connection connection = RabbitMQUtils.getConnection();
      Channel channel = connection.createChannel();
      channel.queueDeclare("helloQueue",true,false,false,null);
      channel.basicConsume("helloQueue",true,new DefaultConsumer(channel){
         @Override
         //最后一个参数body就是消息队列中取出的消息
         public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            System.out.println("消费消息：" + new String(body));
         }
      });
   }
}
