/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rabbitmq_irc;
/**
 *
 * @author Andarias Silvanus
 */

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client_Send {
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private String EXCHANGE_NAME;
    
    public Client_Send (String host, String exchange_name) {
        try {
            factory = new ConnectionFactory();
            factory.setHost(host);
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(exchange_name, "topic");
            EXCHANGE_NAME = exchange_name;
        } 
        catch (IOException ex) {
            Logger.getLogger(Client_Send.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (TimeoutException ex) {
            Logger.getLogger(Client_Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection () {
        try {
            channel.close();
            connection.close();
        } 
        catch (IOException ex) {
            Logger.getLogger(Client_Send.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (TimeoutException ex) {
            Logger.getLogger(Client_Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage (String routingKey, String message) {
        try {
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
//            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
        } 
        catch (IOException ex) {
            Logger.getLogger(Client_Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}