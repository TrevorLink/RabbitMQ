import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-12-01 13:16
 */
public class Consumer2 {
   public static void main(String[] args) throws IOException {
      Connection connection = RabbitMQUtils.getConnection();
      Channel channel = connection.createChannel();
      channel.exchangeDeclare("log_direct","direct");
      String tempQueue = channel.queueDeclare().getQueue();
      //消费者2是负责把错误的日志持久化到磁盘上，因此只接收error的路由
      String exchange = "log_direct";
      channel.queueBind(tempQueue,exchange,"error");
      //消费消息
      channel.basicConsume(tempQueue,true,new DefaultConsumer(channel){
         @Override
         public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            System.out.println("消费者2："+new String(body));
         }
      });
   }
}
