/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
/**
 *
 * @author devin
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         ServerSocket server = null;
        Socket socket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        boolean exit = false;
        try{
            server = new ServerSocket(8004,100);
           
            while(!exit){
                socket = server.accept();
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                
                String inputStr = (String)in.readObject();
                
                if(!inputStr.equals("0")){
                    out.writeObject("H3110 " + inputStr);
                }
                else{
                    
                    exit = true;
                    out.writeObject("EXITING");
                    try{
                        server.close();
                        }catch(Exception e){}
                }
                in.close();
                out.close();
                socket.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            
        
    }
    
}
