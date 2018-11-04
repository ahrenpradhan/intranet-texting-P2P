import java.lang.*;
import java.net.*;
import java.io.*;

public class ServerTCP{
        static Thread client1;
        static Thread client2;
        static send ss1;
        static send ss2;
                public static void main(String args[])
                {
                        String str1,str2;
                        try{
                                ServerSocket socket1 = new ServerSocket(5000);
                                ServerSocket socket2 = new ServerSocket(5001);
                                Socket s1,s2;
                                DataInputStream dis1,dis2;
                                DataOutputStream dos1,dos2;
                                s1 = socket1.accept();
                                s2 = socket2.accept();
                                dis1 = new DataInputStream(s1.getInputStream());
                                dis2 = new DataInputStream(s2.getInputStream());
                                dos1 = new DataOutputStream(s1.getOutputStream());
                                dos2 = new DataOutputStream(s2.getOutputStream());
                                ss1 = new send(dis1,dos2);
                                ss2 = new send(dis2,dos1);
                                client1 = new Thread(ss1);
                                client2 = new Thread(ss2);
                                client1.start();
                                client2.start();
                        }catch(Exception e){
                                System.out.println("\nSERVER ERROR"+e);
                        }
                }
}
class send implements Runnable
{
        DataInputStream dis;
        DataOutputStream dos;
        String str;
        send(DataInputStream dis,DataOutputStream dos) throws IOException
        {
                this.dis = dis;
                this.dos = dos;
        }
        public void run()
        {
                try{
                        while(true)
                        {
                                str = dis.readUTF();
                                dos.writeUTF(str);
                        }
                }catch(Exception e){
                        System.out.println("class send Thread ERROR : "+e);
                }
        }
}
