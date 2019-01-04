
import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
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
public class ServerTCP implements Runnable{
    static Thread t;
        Socket c;
        String str;
        public javax.swing.JTextArea textarea;
        DataInputStream dis;
        ServerSocket s;
        ServerTCP(javax.swing.JTextArea textarea)
        {
                try{
                        s = new ServerSocket(5000);
                        this.textarea = textarea;
                }catch(IOException e){
                        System.out.println("Exception in SERVERTCP constructor\t"+e);
                }
        }
    @Override
        public void run()
        {
                try{
                        while(true)
                        {
                                try{
                                        System.out.println("Waiting for connection");
                                        while(c==null){
                                            System.out.println("waiting");
                                            c = s.accept();
                                        }
                                        System.out.println("Connected");
                                        dis = new DataInputStream(c.getInputStream());
                                        str=null;
                                        textarea.setForeground(Color.BLACK);
                                        System.out.println("1111111111111111111111111111111111111");
                                        if( (str=dis.readUTF()) !=null){
                                                textarea.append("\n"+str);
                                        }
                                        dis.close();
                                        c.close();
                                        c = null;
                                }catch(IOException f){
                                        System.out.println(f);
                                }
                        }   
                }catch(Exception e){
                        System.out.println(e);
                }
        }
}
