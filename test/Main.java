package test;
import src.SocketServer;
import java.lang.Thread;

public class Main {
    
    public static void main(String[] args){

        SocketServer server = new SocketServer(9997);
        

        server.initiateSocketServer();
        server.startSocketServer();

        // try {
        //     Thread.sleep(1000);
        // } 
        
        // catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // System.out.println(server.toString());

        // try {
        //     Thread.sleep(1000);
        // } 
        
        // catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // server.getKeywords();

        // System.out.println(server.toString());
        // server.getMethodNames();
        
    }

}
