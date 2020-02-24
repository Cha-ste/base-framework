package com.ocean.queue.work;

import com.ocean.queue.Constants;
import com.ocean.utils.MQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列消息生产者
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQConnectionUtil.getConnection();
        Channel channel = connection.createChannel(1);
        channel.queueDeclare(Constants.QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 100; i++) {
            String message = "message" + i;
            channel.basicPublish("", Constants.QUEUE_NAME, null, message.getBytes());
            System.out.println("[send]" + message);
        }

        channel.close();
        connection.close();
    }
}
