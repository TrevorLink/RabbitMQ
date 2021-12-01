import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-12-01 14:39
 */
public class Provider {
   @Test
   public void provideMessage() throws IOException {
      Connection connection = RabbitMQUtils.getConnection();
      Channel channel = connection.createChannel();
      String routingKey="user.login";
      String exchange= "topic_exchange";
      channel.exchangeDeclare(exchange,"topic");
      channel.basicPublish(exchange,routingKey,null,("基于Topic模型发送的消息,routingKey是【"+routingKey+"]").getBytes());
      RabbitMQUtils.close(channel,connection);
   }
}
