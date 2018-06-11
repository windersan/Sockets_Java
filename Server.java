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
            server = new ServerSocket(8005,100);
            int flag = 0;
            String user = null;
           
            while(!exit){
                System.out.println("SERVER ACTIVE");
                socket = server.accept();
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
                
                String inputStr = (String)in.readObject();
                                
                if(!inputStr.equals("0")){
                    if(flag==0){
                        user = inputStr;
                        out.writeObject("PASSWORD:");
                        flag = 1;
                    }
                    if(flag==1){
                        if(!inputStr.equals("init")){
                            out.writeObject("PASSWORD INCORRECT");
                        }
                        else{
                            out.writeObject("H3110 " + user);
                            ++flag;                           
                        }
                        
                    }
                    else{
                        out.writeObject("INPUT RECEIVED: " + inputStr);
                    }
                    
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
