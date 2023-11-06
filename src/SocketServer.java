package src;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.lang.Thread;


public class SocketServer{


    private int port;
    private boolean isServerOnline;

    private String clientMessage;
    private ServerSocket serverSocket;
    private ArrayList<String> keywords;
    

    public SocketServer(int port){

        this.port = port;

        keywords = new ArrayList<String>();

        try{
            serverSocket = new ServerSocket(port);
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void initiateSocketServer(){
        System.out.println("Socket Server Initialized");
        
        Thread thread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()){
                for(int i = 0; i < keywords.size(); i++){
                    addKeyword(keywords.get(i));
                }
            }
        });

        thread.start();

        thread.interrupt();
        
        System.out.println(thread.interrupted());

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
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read input from the client
                clientMessage = in.readLine();

                if(searchKeyword(clientMessage).equals("shutdown")){
                    // whatever you want to do
                    // you can use out.println("your message here" to return a message to the device connected to the socket)
                }

                else{
                    System.out.println("Message sent does not equal keyword");
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


    public void addKeyword(String word){
        keywords.add(word);
    }


    public void removeKeyword(String word){

        for (int i = 0; i < keywords.size(); i++){
            if (keywords.get(i).equals(word)){
                keywords.remove(i);
            }
        }
    }


    public String searchKeyword(String word){


        for (int i = 0; i < keywords.size(); i++){
            if(keywords.get(i).equals(word)){
                return keywords.get(i);
            }
        }

        return "String not found";
    }


    public boolean isWordInKeyword(String word){

        for (int i = 0; i < keywords.size(); i++){
            if(keywords.get(i).equals(word)){
                return true;
            }
        }

        return false;
    }

    public void getKeywords(){

        for (int i = 0; i < keywords.size(); i++){
            System.out.println(keywords.get(i));
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