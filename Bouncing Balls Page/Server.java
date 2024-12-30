import java.io.*;
import java.net.*;

public class Server{
    private static final int PORT_NUMBER = 80; // HTTP port for the server
    private static ServerSocket serverSocket;
    public Server() {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Server is running...");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startUp(){
        while(true) {
            try{
                Socket socket = serverSocket.accept();
                System.out.println("Client connected...");
                ClientHandler c = new ClientHandler(socket); // some way to deal with the socket/client relationship now
                c.run();
                // Thread t = new Thread(c);
                // t.start();
            } catch (IOException e){
                System.out.println("Something wen wrong");
            }

        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.startUp();
    }
}
