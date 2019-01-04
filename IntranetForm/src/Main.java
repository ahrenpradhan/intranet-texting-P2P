
import java.net.UnknownHostException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ahren Pradhan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws UnknownHostException {
        EntryScreen form = new EntryScreen();
        form.setVisible(true);
        System.out.println(userName);
    }
    
    static String userName;
}
