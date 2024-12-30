import java.io.*;
import java.net.*;

public class SimpleWebServer {
    public static void main(String[] args) {
        int port = 80; // Define the port to listen on

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Web server started. Listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    handleClientRequest(clientSocket);
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Read the HTTP request
            String requestLine = in.readLine();
            System.out.println("Received request: " + requestLine);

            // Simple response to any GET request
            if (requestLine != null && requestLine.startsWith("GET")) {
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                                      "Content-Type: text/html\r\n\r\n" +
                                      "<html><body><h1>Welcome to the Simple Web Server</h1></body></html>";
                out.println(httpResponse);
            }

        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
