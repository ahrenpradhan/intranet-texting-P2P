import java.awt.Color;
import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahren Pradhan
 */
public class ClientTCP extends javax.swing.JTextArea{
    public String uName;
    String IPAddress;
    public javax.swing.JTextArea textarea;
    public javax.swing.JTextArea message;
    ClientTCP(String x,String IPAddress, javax.swing.JTextArea message, javax.swing.JTextArea textarea)
    {
        this.uName = x;
        this.IPAddress = IPAddress;
        this.IPAddress = "127.0.0.1";
        this.textarea = textarea;
        this.message = message;
    }
    void updateIP(String IPAddress)
    {
        //this.IPAddress = IPAddress;
        this.IPAddress = "127.0.0.1";
    }
    public void sendMessage(String str)
    {
        try{
            //message.setText(null);
            Socket s;
            System.out.println(IPAddress + "--------------" + str);
            s = new Socket(IPAddress,5000);
            System.out.println(IPAddress + "--------------" + str);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(uName+" : "+str);
            System.out.println("*****************************************************");
            textarea.setForeground(Color.green);
            textarea.append("\nSent : "+str);
            // data output stream terminated
            s.close();      // Socket connection terminated
        }catch(IOException e){
                System.out.println(e);
        }

    }
}
