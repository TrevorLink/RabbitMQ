import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-11-30 14:28
 */
public class Provider {
   @Test
   public void provideMessages() throws IOException {
      Connection connection = RabbitMQUtils.getConnection();
      Channel channel = connection.createChannel();
      channel.queueDeclare("work",true,false,false,null);
      for (int i = 1; i <=50; i++) {
         channel.basicPublish("","work",null,(i+"Work模式的消息").getBytes());
      }
      RabbitMQUtils.close(channel,connection);
   }
}
