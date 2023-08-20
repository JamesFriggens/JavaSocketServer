package test;
import src.SocketServer;

public class Main {
    
    public static void main(String[] args){

        SocketServer server = new SocketServer(9997);

        server.initiateSocketServer();

        System.out.println("test");
        server.startSocketServer();

        // 
        System.out.println("test2");
        System.out.println(server);
        

    }

}
