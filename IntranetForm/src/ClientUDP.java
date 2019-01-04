
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahren Pradhan
 */
public class ClientUDP implements Runnable{
    
    String uName;
    String regNo;
    String userIP;
    String s;
    
    ClientUDP(String x,String y,String z){
        uName = x;
        regNo = y;
        userIP = z;
        s = x+"@"+y+"@"+z;
    }
    
    /**
     *
     */
    @Override
    public void run(){
        while(true){
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                try (DatagramSocket csock = new DatagramSocket()) {
                    InetAddress ip=InetAddress.getByName("localhost");
                    byte[] sendData = new byte[1024];
                    sendData = s.getBytes();
                    DatagramPacket spack = new DatagramPacket(sendData,sendData.length,ip,2500);
                    //System.out.println("_______________Packet Sent________________");
                    csock.send(spack);
                }
            } catch (SocketException ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
