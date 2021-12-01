import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-12-01 13:07
 */
public class Provider {
   @Test
   public void provideMessage() throws IOException {
      //创建唯一的一个TCP连接
      Connection connection = RabbitMQUtils.getConnection();
      //TCP多路复用
      Channel channel = connection.createChannel();
      channel.exchangeDeclare("log_direct","direct");
      //在发送消息的时候就需要指定 Routing Key
      String routingKey = "info";
      String exchange = "log_direct";
      channel.basicPublish(exchange,routingKey,null,("这是根据Direct模型发布的【"+routingKey+"】类消息").getBytes());
      RabbitMQUtils.close(channel,connection);
   }
}
