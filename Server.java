import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class Server {
    public static void run() {
        int port = 8080;
        try(ServerSocket serversocket = new ServerSocket(port)){
            serversocket.setSoTimeout(10000);
            System.out.println("The Server is listening at port " + port);
            System.out.println("Listening for connections......");
            while (true) {
                try{
                    Socket clientSocket = serversocket.accept();
                    System.out.println("Connection Established from client "+clientSocket.getRemoteSocketAddress());
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    Thread thread = new Thread(clientHandler);
                    thread.start();
                }catch(SocketTimeoutException e) {
                    System.out.println("No connection received within the timeout period.");
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       Server.run(); 
    }
}


