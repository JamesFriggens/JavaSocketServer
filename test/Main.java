package test;
import src.SocketServer;

public class Main {
    
    public static void main(String[] args){

        SocketServer server = new SocketServer(9998);

        server.initiateSocketServer();
        server.startSocketServer();

    }

}
