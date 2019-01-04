import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.*;
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
public class ServerUDP implements Runnable{
    public String s;
    public ArrayList<String> ipList;
    public int i;
    
    ServerUDP(){
        i=0;
        ipList = new ArrayList<String>();
        System.out.println("-----------------------**********************--------------------");
        //Initialize the iplist array
        try{
            FileReader f = new FileReader("Connections.txt");
            BufferedReader in = new BufferedReader(f);
            String line;
            while((line = in.readLine())!=null){
                    System.out.println(line);
                    String[] temp = line.split(" ");
                    ipList.add(temp[2]);
        }
        }catch(IOException e){
            System.out.println(e);
        }
        System.out.println(ipList);
        System.out.println("-----------------------**********************--------------------");
    }
    
    public void AppendList(String x){
        x = x.trim();
        String[] temp = x.split("@");
        if(ipList.contains(temp[2])==false) {
            System.out.println(temp[2]);
            System.out.println(ipList);
            ipList.add(temp[2]);
            try{
                FileWriter f = new FileWriter("Connections.txt",true);
                BufferedWriter out = new BufferedWriter(f);
                out.newLine();
                String t = temp[0]+" "+temp[1]+" "+temp[2];
                out.write(t);
                out.close();
                f.close();
            }catch(IOException e){
                System.out.println(e);
            }
            i = i + 1;
            System.out.println(temp[2]);
        }
    }
   
    @Override
    public void run(){
        //so save a list of different registrations
        while(true){
            try {
                DatagramSocket ssock = new DatagramSocket(2500);
                byte[] recieveData = new byte[1024];
                DatagramPacket rpack = new DatagramPacket(recieveData,recieveData.length);
                ssock.receive(rpack);
                s = new String(rpack.getData());
                //System.out.println("Recieved Packet------------   " + s + "\n");
                AppendList(s);
                ssock.close();
            } catch (SocketException ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
