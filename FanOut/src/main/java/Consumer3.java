import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-11-30 21:02
 */
public class Consumer3 {
   public static void main(String[] args) throws IOException {
      Connection connection = RabbitMQUtils.getConnection();
      Channel channel = connection.createChannel();
      //在消费者这里也要通道绑定交换机
      channel.exchangeDeclare("niao","fanout");
      //生成临时队列，每一个消费者都有一个属于自己的临时队列
      String tempQueue = channel.queueDeclare().getQueue();
      //绑定交换机和队列，和之前的一样我们的routingKey在广播发布订阅模型里面几乎是不存在的，所以直接给空字符串即可
      channel.queueBind(tempQueue,"niao","");
      //接收消息
      channel.basicConsume(tempQueue,true,new DefaultConsumer(channel){
         @Override
         public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            System.out.println("消费者3"+new String(body));
         }
      });
   }
}
