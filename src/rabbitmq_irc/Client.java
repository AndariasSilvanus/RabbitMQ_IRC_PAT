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

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Client {
    private static String NICKNAME;
//    private static String HOST = "167.205.32.46";
    private static String HOST = "localhost";
    public static ArrayList<String> listNick;
    public static ArrayList<String> listChannel;
    
    public static void main(String [] args) {
        listNick = new ArrayList<String>();
        listChannel = new ArrayList<String>();
        
        // Intialization
        Client_SendRecv CS = new Client_SendRecv (HOST);
        Scanner input = new Scanner(System.in);
        String mode = "", channelName = "", msg = "";

        // Mode list
        System.out.println("Ketik '/NICK nickname_baru' untuk mengganti nickname Anda");
        System.out.println("Ketik '/JOIN nama_channel_tujuan' untuk bergabung ke channel tujuan");
        System.out.println("Ketik '/LEAVE nama_channel' untuk meninggalkan channel tertentu");
        System.out.println("Ketik apapun untuk mengirim pesan Anda secara broadcast");
        System.out.println("Kirim pesan ke channel tertentu dengan mengetik @nama_channel dan dilanjutkan dengan pesan Anda");
        System.out.println("Ketik '/EXIT' untuk keluar dari program\n");
        
        // Set username
        generateUname();
        while (listNick.contains(NICKNAME))
            generateUname();
        CS.setNickName(NICKNAME);
        
        // Operation
        boolean stopper = false;
        while (!stopper) {
            mode = input.next();
            if (mode.equals("/EXIT")) {
                stopper = true;
                CS.closeConnection();
            }
            else if (mode.equals("/NICK")) {
                String nicknameTMP = input.next();
                if (listNick.contains(nicknameTMP))
                    System.out.println("Nickname '" + nicknameTMP + "' sudah terdaftar, silahkan coba dengan username lain");
                else {
                    NICKNAME = nicknameTMP;
                    CS.setNickName(NICKNAME);
                    System.out.println("Ganti nickname berhasil!");
                }
            }
            else if (mode.equals("/JOIN")) {
                String newChannel = input.next();
                if (!listChannel.contains(newChannel)) {
                    listChannel.add(newChannel);
                    CS.joinChannel(newChannel);
                    System.out.println("Anda sudah berhasil bergabung di channel '"+newChannel+"'!");
                }
                else
                    System.out.println("Anda sudah tergabung di channel '"+newChannel+"'");
            }
            else if (mode.equals("/LEAVE")) {
                String channeLeave = input.next();
                if (listChannel.contains(channeLeave)) {
                    listChannel.remove(channeLeave);
                    CS.leaveChannel(channeLeave);
                    System.out.println("Anda sudah berhasil keluar channel '"+channeLeave+"'!");
                }
                else
                    System.out.println("Anda belum tergabung di channel '"+channeLeave+"'");
            }
            else{
                if (mode.charAt(0) == '@') { // Message channel X
                    channelName = mode.substring(1, mode.length());
                    msg = input.nextLine();
                    String message = "[" + channelName + "]" + " (" + NICKNAME + ") " + msg;
                    CS.sendMessage(channelName, message);
                }
                else { // Message to all channel
                    msg = mode + input.nextLine();
                    if (!listChannel.isEmpty()) {
                        for (String channelTmp : listChannel) {
                            String message = "[" + channelTmp + "]" + " (" + NICKNAME + ") " + msg;
                            CS.sendMessage(channelTmp, message);
                        }
                    }
                    else
                        System.out.println("Anda belum terdaftar ke channel manapun");
                }
            }
        }
    }
    
    private static void generateUname(){
	String usernames[] = {"Ludger","Elle","Jude","Milla","Alvin","Rowen","Elize","Leia"};
	String uname;
        Random rand = new Random();
	
	uname = usernames[(int)(rand.nextInt(usernames.length))] + (int) rand.nextInt(50) + 1;
	System.out.println("Username Anda: " + uname + "\n");
	
	NICKNAME = uname;
    }
}
