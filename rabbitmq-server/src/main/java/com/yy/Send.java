package com.yy;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

/**
 * Created by yaoliang on 2016/12/13.
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
        log();
    }

    public static void log() {
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();

        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                System.out.println(stackElements[i].getClassName());//返回类的完全限定名，该类包含由该堆栈跟踪元素所表示的执行点。
                System.out.println(stackElements[i].getFileName());//返回源文件名，该文件包含由该堆栈跟踪元素所表示的执行点。
                System.out.println(stackElements[i].getLineNumber());//返回源行的行号，该行包含由该堆栈该跟踪元素所表示的执行点。
                System.out.println(stackElements[i].getMethodName());//返回方法名，此方法包含由该堆栈跟踪元素所表示的执行点。
                System.out.println("-------------第"+i+"级调用-------------------");
            }
        }
    }
}
