/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author devin
 */
public class Client {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        boolean exit = false;
        
        while(!exit){
            try{
           Scanner scan = new Scanner(System.in);
           
           String str = scan.next();
           if(str.equals("0")){exit = true;}
           
            InetAddress address = InetAddress.getLocalHost();
            Socket socket = new Socket(address, 8004);        
            ObjectInputStream  in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(str);
            str = (String)in.readObject();
            System.out.println(str);
            }
            catch(Exception e){}
            
        }
            
    }
}
    

