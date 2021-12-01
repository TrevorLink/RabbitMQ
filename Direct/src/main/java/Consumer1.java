import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-12-01 13:13
 */
public class Consumer1 {
   public static void main(String[] args) throws IOException {
      Connection connection = RabbitMQUtils.getConnection();
      Channel channel = connection.createChannel();
      channel.exchangeDeclare("log_direct","direct");
      String tempQueue = channel.queueDeclare().getQueue();
      //上面和之前的广播模型一样，但是下面会多出来一个需要把这个临时队列在和交换机绑定的时候设置Routing Key
      //基于 Routing Key 将临时队列和交换机进行绑定
      String exchange = "log_direct";
      //消费者1对应的队列是负责打印的，因此队列的路由是三个
      channel.queueBind(tempQueue,exchange,"info");
      channel.queueBind(tempQueue,exchange,"error");
      channel.queueBind(tempQueue,exchange,"warning");
      channel.queueBind(tempQueue,exchange,"warning");
      channel.queueBind(tempQueue,exchange,"warning");
      channel.queueBind(tempQueue,exchange,"warning");
      channel.queueBind(tempQueue,exchange,"warning");
      channel.queueBind(tempQueue,exchange,"warning");
      //消费消息
      channel.basicConsume(tempQueue,true,new DefaultConsumer(channel){
         @Override
         public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            System.out.println("消费者1："+new String(body));
         }
      });
   }
}
