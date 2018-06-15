package app;

import java.io.*;
import java.net.*;

public class App {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
              
        System.out.println("Running on: " + InetAddress.getLocalHost());
        ServerSocket server = new ServerSocket(8005,100);
           
        while(true){
            Socket socket = server.accept();
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
         
            String msg = (String)in.readObject();                              
            System.out.println(msg);    
             
            InetAddress addr = InetAddress.getByName("224.0.0.3");
            MulticastSocket mSocket = new MulticastSocket();                 
            DatagramPacket packet = new DatagramPacket(msg.getBytes(),
                   msg.getBytes().length, addr, 8888);
            mSocket.send(packet);                 
       }               
    } 
}
