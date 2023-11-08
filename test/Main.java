package test;
import src.SocketServer;
import java.lang.Thread;

public class Main {
    
    public static void main(String[] args){

        SocketServer server = new SocketServer(9997);

        server.addKeyword("hi");
        server.addKeyword("bob");
        server.addKeyword("end");
        


        server.initiateSocketServer();
        server.startSocketServer();

        try {
            Thread.sleep(1000);
        } 
        
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(server.toString());

        try {
            Thread.sleep(1000);
        } 
        
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        server.getKeywords();

        System.out.println(server.toString());
        // System.out.println(server.toString());
        
        // Thread thread1 = new Thread(() -> server.startSocketServer());
        // Thread thread2 = new Thread(() -> System.out.println(server.toString()));
        // Thread thread3 = new Thread(() -> server.getKeywords());
        // Thread thread4 = new Thread(() -> server.initiateSocketServer());

        // thread4.start();

        // thread1.start();

        // try {
        //     Thread.sleep(1000);
        // } 
        
        // catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // thread2.start();

        // try {
        //     Thread.sleep(1000);
        // } 
        
        // catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // thread3.start();

        
        
        // try{
        // thread2.join();
        // System.out.println(thread2.isAlive());

      
        // }

        // catch(InterruptedException e) {
        //     e.printStackTrace();
        // }

        // System.out.println("test");
        // server.startSocketServer();

        // // 
        // System.out.println("test2");
        // System.out.println(server);
        

    }

}
