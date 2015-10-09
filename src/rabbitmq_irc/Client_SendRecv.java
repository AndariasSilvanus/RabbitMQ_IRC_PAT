/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rabbitmq_irc;
/**
 *
 * @author Andarias Silvanus & Rita Sarah
 */

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_SendRecv {
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private String EXCHANGE_USER = "user_exchange";
    private String EXCHANGE_MESSAGE = "msg_exchange";
    private String messageQueue;
    
    public Client_SendRecv (String host) {
        try {
            factory = new ConnectionFactory();
            factory.setHost(host);
            connection = factory.newConnection();
            channel = connection.createChannel();
            
            channel.exchangeDeclare(EXCHANGE_USER, "fanout");
            channel.exchangeDeclare(EXCHANGE_MESSAGE, "topic");
            
            String userQueue = channel.queueDeclare().getQueue();
            channel.queueBind(userQueue, EXCHANGE_USER, "");
            Consumer consumerUser = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String newUsername = new String(body, "UTF-8");
                    Client.listNick.add(newUsername);
                }
            };
            channel.basicConsume(userQueue, true, consumerUser);
            
            messageQueue = channel.queueDeclare().getQueue();
        }
        catch (IOException ex) {
            Logger.getLogger(Client_SendRecv.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (TimeoutException ex) {
            Logger.getLogger(Client_SendRecv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection () {
        try {
            channel.close();
            connection.close();
        } 
        catch (IOException ex) {
            Logger.getLogger(Client_SendRecv.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (TimeoutException ex) {
            Logger.getLogger(Client_SendRecv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage (String channelString, String message) {
        try {
            channel.basicPublish(EXCHANGE_MESSAGE, channelString, null, message.getBytes());
        } 
        catch (IOException ex) {
            Logger.getLogger(Client_SendRecv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void joinChannel (String channelString) {
        try {
            channel.queueBind(messageQueue, EXCHANGE_MESSAGE, channelString);
            Consumer consumerChannel = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(message);
                }
            };
            channel.basicConsume(messageQueue, true, consumerChannel);
        } 
        catch (IOException ex) {
            Logger.getLogger(Client_SendRecv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leaveChannel (String channelString) {
        try {
            channel.queueUnbind(messageQueue, EXCHANGE_MESSAGE, channelString);
        } 
        catch (IOException ex) {
            Logger.getLogger(Client_SendRecv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setNickName (String nick) {
        try {
            channel.basicPublish(EXCHANGE_USER, "", null, nick.getBytes());
        } 
        catch (IOException ex) {
            Logger.getLogger(Client_SendRecv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}