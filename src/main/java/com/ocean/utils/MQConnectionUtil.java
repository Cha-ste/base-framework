package com.ocean.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 连接工具类
 */
public class MQConnectionUtil {
    private static final String RABBIT_HOST = "localhost";

    private static final String RABBIT_USERNAME = "guest";

    private static final String RABBIT_PASSWORD = "guest";

    private static Connection connection = null;

    /**
     * 获取连接
     *
     * @return Connection
     */
    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBIT_HOST);
        factory.setUsername(RABBIT_USERNAME);
        factory.setPassword(RABBIT_PASSWORD);

        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
