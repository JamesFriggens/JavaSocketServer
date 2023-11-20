package src;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.PrintWriter;

import java.lang.Thread;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// class where you put in your custom methods to be executed
// import src.Commands;


public class SocketServer{
    

    private int port;
    private boolean isServerOnline;
    private String password;

    private String clientMessage;
    private ServerSocket serverSocket;
    

    public SocketServer(int port){

        this.port = port;


        try{
            serverSocket = new ServerSocket(port);
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }

    public SocketServer(int port, String password){
        
        this.port = port;
        this.password = password;
        

        try{
            serverSocket = new ServerSocket(port);
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void initiateSocketServer(){
        System.out.println("Socket Server Initialized");      
    }


    public void startSocketServer(){
        
        Thread thread = new Thread(() -> {

        System.out.println("Socket Server Live");

        setServerStatus(true);
        
        while (true){
            
             try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Connection from: " + clientSocket.getInetAddress());

                // Create input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                // PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);


                // Read input from the client
                // clientMessage = in.readLine();

                while ((clientMessage = in.readLine()) != null){
                    
                    // clientMessage = in.readLine();
                    System.out.println(clientMessage);

                    
                    if(clientMessage.equals("exit")){
                        clientSocket.close();
                        break;
                    }

                    Commands commands = new Commands();

                    try {
                        // Use Java reflection to find and invoke the method by name
                        Method method = Commands.class.getMethod(clientMessage);
                        method.invoke(commands);
                    } catch (NoSuchMethodException e) {
                        System.err.println("Method not found: " + clientMessage);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.err.println("Error invoking the method: " + e.getMessage());
                    }
                }
                    
                } 
                
                catch (IOException e) {
                    setServerStatus(false);
                    e.printStackTrace();
                }
                
            
        }
    });

    thread.start();
    }


    public void getMethodNames(){

        Class<?> commands = Commands.class;

        // Get all declared methods, including public, protected, default (package) access, and private methods
        Method[] methods = commands.getDeclaredMethods();

        System.out.println("List of methods in Commands class:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

    }


    public int getPort(){
        return port;
    }


    public void setPort(int num){
        port = num;
    }

    public void setServerStatus(boolean status){
        isServerOnline = status;
    }

    public boolean getServerStatus(){
        return isServerOnline;
    }


    public String toString(){
        return "Server on port " + getPort() + " and online status equals " + getServerStatus();
    }


}