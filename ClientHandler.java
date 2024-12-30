import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;


public class ClientHandler implements Runnable{
    private BufferedReader reader;
    private PrintWriter pw;
    public ClientHandler(Socket socket) {
        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pw = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e){
            System.out.println("Oh no!");
        }
        
    }
    @Override
    public void run(){
        try {
            String line;
            if ((line = this.reader.readLine()).startsWith("GET")){
                pw.println("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n\r\n" +
                "<html><body><h1>Welcome to the Simple Web Server</h1></body></html>");
                // pw.flush();            
            }
            reader.close();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
