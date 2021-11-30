import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HuangSir
 * @date 2021-11-30 9:51
 */
public class Provider {
   @Test
   public void provideMessage() throws IOException, TimeoutException {
     /*
      //创建一个连接mq的连接工厂
      ConnectionFactory connectionFactory = new ConnectionFactory();
      //设置连接的主机IP
      connectionFactory.setHost("120.24.179.229");
      //设置连接的端口号（15672是管理页面端口，5672才是TCP连接的端口）
      connectionFactory.setPort(5672);
      //设置连接的虚拟主机
      connectionFactory.setVirtualHost("/Hello");
      //设置连接的用户和密码
      connectionFactory.setUsername("Niaotendo");
      connectionFactory.setPassword("hyy2845964844");
      //获取连接
      Connection connection = connectionFactory.newConnection();
      */
      Connection connection = RabbitMQUtils.getConnection();
      //获取连接中的通道
      Channel channel = connection.createChannel();
      //为通道绑定消息队列
         //参数①durable：是否持久化队列（RabbitMQ服务关闭后是否将队列持久化到磁盘里）
         //参数②exclusive：当前的connection是否独占这个队列，如果为false就表示其他的Connection也可以连接到这个队列
         //参数③utoDelete：消费完成后是否自动删除
         //参数④argument额外参数（先空着）
      channel.queueDeclare("helloQueue",true,false,false,null);
      System.out.println("生产消息");
      //发布消息
         //参数①exchange表示交换机名称
         //参数②routingKey路由到哪一个队列
         //参数③props携带的额外属性
         //参数④消息内容
      channel.basicPublish("","helloQueue", MessageProperties.PERSISTENT_TEXT_PLAIN,"Hello RabbitMQ(持久化版)".getBytes());

      channel.close();
      connection.close();
   }
}
