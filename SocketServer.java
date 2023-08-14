import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class SocketServer{

    private int port;
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

    public void startSocketServer(){

        while (true){

             try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Connection from: " + clientSocket.getInetAddress());

                // Create input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read input from the client
                clientMessage = in.readLine();

                if(searchKeyword(clientMessage)){
                    // whatever you want to do
                    // you can use out.println("your message here" to return a message to the device connected to the socket)
                }

                else{
                    System.out.println("Message sent does not equal keyword");
                }
            } 
            
            catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public Boolean searchKeyword(String word){

        for (int i = 0; i < keywords.size(); i++){
            if(keywords.get(i).equals(word)){
                return true;
            }
        }

        return false;
    }
    
    public int getport(){
        return port;
    }

    public void setport(int num){
        port = num;
    }


}