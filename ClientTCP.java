import java.lang.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ClientTCP{
    static Thread send;
    static Thread receive;
    String str;
    static dataReceiver DR;
    static dataSender DS;
    DataOutputStream dos;
    DataInputStream dis;
    public static int main(String args[])
    {
        Socket server;
        int c=0;
        int check=0;
        Scanner ip = new Scanner(System.in);
        while(true){
                        //String roomName;
                        //String password;
                try{
                        if(c==0)
                        {
                            System.out.println("1.Create\n2.Connect");
                            c = ip.nextInt();
                        }
                        if(c==1)
                        {
                                server = new Socket("127.0.0.1",5000);
                                dis = new DataInputStream(server.getInputStream());
                                dos = new DataOutputStream(server.getOutputSTream());
                                System.out.print("Please enter a room name : ");
                                roomName = ip.next();
                                System.out.print("Please enter a password : ");
                                password = ip.next();
                                while(check)
                                {
                                        if((str = dis.readUTF()).compareTo("true")==0)
                                        {
                                                if((str = dis.readUTF()).compareTo(roomName)==0)
                                                {
                                                        if((str = dis.readUTF()).compareTo(password)==0)
                                                                check=0;
                                                        else
                                                                server.close();
                                                }
                                                else
                                                {
                                                        server.close();
                                                        return 0;
                                                }
                                        }
                                }
                        }
                        else
                        {
                                server = new Socket("127.0.0.1",5001);
                                dis = new DataInputStream(server.getInputStream());
                                dos = new DataOutputStream(server.getOutputSTream());
                                System.out.print("Please enter the room name : ");
                                roomName = ip.next();
                                System.out.print("Please enter the password : ");
                                password = ip.next();
                                System.out.println("Waiting for connection");
                                while(check)
                                {
                                        dos.writeUTF("true");
                                        dos.writeUTF(roomName);
                                        dos.writeUTF(password);
                                        str = dis.readUTF();
                                        if(str.compareTo("Successful"))
                                                check=0;
                                        else
                                        {
                                                server.close();
                                                return 0;
                                        }
                                }
                        }
                        DS = new dataSender(server);
                        DR = new dataReceiver(server);
                        send = new Thread(DS);
                        receive = new Thread(DR);
                        send.start();
                        receive.start();
                        while(true){
                        }
                }catch(Exception e){
                    System.out.println("Client ERROR "+e);
                    System.out.println("Client disconnected");
                }
        }
        return 0;
    }
}
class dataReceiver implements Runnable{
    Socket s;
    String str;
    DataInputStream dis;
    dataReceiver(Socket s) throws IOException
    {
        this.s = s;
        dis = new DataInputStream(s.getInputStream());
    }
    public void run()
    {
        try{
                while(true)
                {
                        str = dis.readUTF();
                        System.out.println(str);
                }
        }catch(Exception e){
            System.out.println("dataSender class thread ERROR : "+e);
        }
    }
}
class dataSender implements Runnable{
    Socket s;
    String str;
    DataOutputStream dos;
    Scanner ip;
    dataSender(Socket s) throws IOException
    {
        this.s = s;
        ip = new Scanner(System.in);
        dos = new DataOutputStream(s.getOutputStream());
    }
    public void run()
    {
        try{
                while(true)
                {
                        str = ip.nextLine();
                        dos.writeUTF(str);
                }
        }catch(Exception e){
            System.out.println("dataReceiver class thread ERROR : "+e);
        }
    }
}
