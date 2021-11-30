import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author HuangSir
 * @date 2021-11-30 19:52
 */
public class Provider {
   @Test
   public void provideMessage() throws IOException {
      //根据工具类获取连接
      Connection connection = RabbitMQUtils.getConnection();
      //获取通道
      Channel channel = connection.createChannel();
      //声明一个交换机，参数①名字叫叫做niao，参数②类型就是专用于扇出（广播、发布订阅模型）的交换机
      channel.exchangeDeclare("niao","fanout");
      //发送消息，这里的routingKey也就是路由选项在我们的广播fanout模型中挤乎是不存在的
      channel.basicPublish("niao","",null,"Fanout扇出模型".getBytes());
      //关闭连接释放资源
      RabbitMQUtils.close(channel,connection);
   }
}
