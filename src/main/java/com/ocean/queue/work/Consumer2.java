package com.ocean.queue.work;

import com.ocean.queue.Constants;
import com.ocean.utils.MQConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 工作隊列消息消费者2
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = MQConnectionUtil.getConnection();
        Channel channel = connection.createChannel(1);
        channel.queueDeclare(Constants.QUEUE_NAME, false, false, false, null);

        //每个消费者在确认消费之前，只发送一个消息给消费者
        // 此处若不设置，默认会使用轮训分发，也就是公平分发，并且一次性把所有消息分发完。
        // 后面需要手动设置确认动作
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                super.handleDelivery(s, envelope, basicProperties, bytes);
                String message = new String(bytes, "utf-8");
                System.out.println("message: " + message);
                try {
                    Thread.sleep(2000L);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 手动确认
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        channel.basicConsume(Constants.QUEUE_NAME, false, consumer);
    }
}
