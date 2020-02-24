package com.ocean.queue.simple;

import com.ocean.queue.Constants;
import com.ocean.utils.MQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取连接
        Connection connection = MQConnectionUtil.getConnection();
        System.out.println(connection);
        //创建消息通道
        Channel channel = connection.createChannel(1);
       /*
        * 声明（创建）队列
        * 参数1：队列名称
        * 参数2：为true时server重启队列不会消失
        * 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
        * 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
        * 参数5：建立队列时的其他参数
        */
        channel.queueDeclare(Constants.QUEUE_NAME, false, false, false, null);
        String message = "message! ";
        for (int i = 0; i < 20; i++) {
            message = message + i;
            channel.basicPublish("", Constants.QUEUE_NAME, null, message.getBytes());
            Thread.sleep(1000);
        }
        System.out.println("生产者 send ：" + message);
        channel.close();
        connection.close();
    }
}
