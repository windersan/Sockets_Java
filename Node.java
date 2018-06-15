package node;

import java.io.*;
import java.net.*;

public class Node {

    public static void main(String[] args) throws IOException {
        
        byte[] buf = new byte[256];
        String str = "We are started!";
        boolean started = false;
        
        MulticastSocket mSocket = new MulticastSocket(8888);
        InetAddress address = InetAddress.getByName("224.0.0.3");
        mSocket.joinGroup(address);
            
        while (true) {
            
            if(!started){
                address = InetAddress.getByName(args[0]);
                Socket socket = new Socket(address, 8005);        
                ObjectInputStream  in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(str);
                started = true;
            }
            
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            mSocket.receive(packet);
            String msg = new String(buf, 0, buf.length);
            System.out.println(msg);
        }  
    }   
}
