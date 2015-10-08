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
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static String nickname;
    private static String HOST = "167.205.32.46";
    private static String EXCHANGE_NAME = "broadcaster";
    
    public static void main(String [] args) {
        Runnable send_thread;
        send_thread = new Runnable() {
            @Override
            public void run() {
                // Intialization
                Client_Send CS = new Client_Send (HOST, EXCHANGE_NAME);
                Scanner input = new Scanner(System.in);
                String mode = "", channelName = "", msg = "";
                generateUname();

                // Operation
                boolean stopper = false;
                while (!stopper) {
                    mode = input.next();
                    if (mode.equals("/EXIT"))
                        stopper = true;
                    else if (mode.equals("/NICK")) {
                        String nicknameTMP = input.next();
                        
                        System.out.println("Ganti nickname berhasil!");
                    }
                }
            }
        };
        new Thread(send_thread).start();
        
        Runnable recv_thread = new Runnable() {
            @Override
            public void run() {
                
            }
        };
        new Thread(recv_thread).start();
    }
    
    private static ConnectionFactory createConnection(String _host) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(_host);
        return factory;
    }
    
    private static void generateUname(){
	String usernames[] = {"Ludger","Elle","Jude","Milla","Alvin","Rowen","Elize","Leia"};
	String uname;
        Random rand = new Random();
	
	uname = usernames[(int)(rand.nextInt(usernames.length))] + (int) rand.nextInt(50) + 1;
	System.out.println("Username: " + uname);
	
	nickname = uname;
    }
}
