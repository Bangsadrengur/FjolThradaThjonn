import java.net.*;
import java.io.*;

public class ConnectionOrientedServer {
  public static void main(String args[]) {
    try {
      int serverPort = 7896; // the server port
      ServerSocket listenSocket = new ServerSocket(serverPort);
      while (true) {
        Socket clientSocket = listenSocket.accept();
        Thread runner = new Connection(clientSocket);
        runner.start();
        //Connection c = new Connection(clientSocket); // Handle request
      }
    } catch (IOException e) {
      System.out.println("Listen socket:" + e.getMessage());
    }
  }
}
