package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



/**
 * 获取连接和关闭连接的工具类
 * @author HuangSir
 * @date 2021-11-30 10:49
 */
public class RabbitMQUtils {
   private static  ConnectionFactory connectionFactory;
   static {
      connectionFactory= new ConnectionFactory();
      connectionFactory.setHost("120.24.179.229");
      connectionFactory.setPort(5672);
      connectionFactory.setUsername("Niaotendo");
      connectionFactory.setPassword("hyy2845964844");
      connectionFactory.setVirtualHost("/Hello");
   }
   /**
    * 获取连接
    * @return
    */
   public static Connection getConnection(){
      try {
         return connectionFactory.newConnection();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   /**
    * 关闭通道和连接
    * @param channel
    * @param connection
    */
   public static void close(Channel channel,Connection connection){
      try {
       if (channel!=null) channel.close();
        if (connection!=null) connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
