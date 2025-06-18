import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                try(Socket socket = new Socket(HOST,PORT);
                    PrintWriter toSocket = new PrintWriter(socket.getOutputStream(),true);
                    BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))
                   ){
                       //read the message from user
                       System.out.println("Enter the message to send");
                       String message = consoleReader.readLine(); 
                       //send the message to server
                       toSocket.println(message);
                       String response = fromSocket.readLine();
                       System.out.println("Recived message from server "+response+" "+Thread.currentThread().getName());
                   } catch (UnknownHostException e) {
                       System.err.println(Thread.currentThread().getName() + ": server not found: " + e.getMessage());
                   } catch (IOException e) {
                       System.err.println(Thread.currentThread().getName() + ": i/o error: " + e.getMessage());
                   }
            }


        };
    }
    public static void main(String[] args) {
        int numClients = 10;
        Thread[] clientThreads = new Thread[numClients];
        System.out.println("Starting "+numClients+" number of client threads.");
        for(int i = 0; i < numClients;i++) {
            clientThreads[i] = new Thread(Client.getRunnable(),"Client-"+i);
            clientThreads[i].start();
        }
    }
}
