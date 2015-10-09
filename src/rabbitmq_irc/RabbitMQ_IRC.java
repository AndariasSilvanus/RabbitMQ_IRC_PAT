/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rabbitmq_irc;

//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import java.io.IOException;
//import java.util.Scanner;
//import java.util.concurrent.TimeoutException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Andarias Silvanus
 */
public class RabbitMQ_IRC {

//    private static String nickname;
//    private static String HOST = "167.205.32.46";
//    private static String EXCHANGE_NAME = "broadcaster";
    
    public static void main(String[] args) {
//        Runnable send_thread;
//        send_thread = new Runnable() {
//            @Override
//            public void run() {
//                ConnectionFactory factory = createConnection(HOST);
//                Connection connection;
//                try {
//                    // Create connection
//                    connection = factory.newConnection();
//                    Channel channel = connection.createChannel();
//                    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//                    
//                    // Intialization
//                    Scanner input = new Scanner(System.in);
//                    String mode = "", channelName = "", msg = "";
////                    generateUname();
//                    
//                    // Operation
//                    boolean stopper = false;
//                    while (!stopper) {
//                        mode = input.next();
//                        if (mode.equals("/EXIT"))
//                            stopper = true;
//                        else if (mode.equals("/NICK")) {
//                            String nicknameTMP = input.next();
//                            
//                            System.out.println("Ganti nickname berhasil!");
//                        }
////                        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
//                    }
//                    
//                    // Close connection
//                    channel.close();
//                    connection.close();
//                } 
//                catch (IOException ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                } 
//                catch (TimeoutException ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        };
//        new Thread(send_thread).start();
//        
//        Runnable recv_thread = new Runnable() {
//            @Override
//            public void run() {
//                ConnectionFactory factory = createConnection(HOST);
//                Connection connection;
//                try {
//                    connection = factory.newConnection();
//                    Channel channel = connection.createChannel();
//                    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//                    Scanner input = new Scanner(System.in);
//                    String message = "";
//                    boolean stopper = false;
//                    while (!stopper) {
//                        message = input.nextLine();
//                        if (message.equals("/EXIT"))
//                            stopper = true;
//                        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
//                        System.out.println(" [x] Sent '" + message + "'");
//                    }
//                    channel.close();
//                    connection.close();
//                } 
//                catch (IOException ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                } 
//                catch (TimeoutException ex) {
//                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        };
//        new Thread(recv_thread).start();
    }
//    private static ConnectionFactory createConnection(String _host) {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(_host);
//        return factory;
//    }
}
