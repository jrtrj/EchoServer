import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                try(Socket socket = new Socket(localhost,port);
                    PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
                    BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStreamReader()));
                    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in)){
                       //read the message from user
                       String message = consoleReader.readLine(); 
                       System.out.println("Message from client "+Thread.currentThread().getName());
                       //send the message to server
                       toSocket.println(message);
                       String response = fromSocket.readLine();
                       System.out.println("Recived message from server "+response+" "+Thread.currentThread().getName());
                       } catch (unknownhostexception e) {
                           system.err.println(thread.currentthread().getname() + ": server not found: " + e.getmessage());
                       } catch (ioexception e) {
                           system.err.println(thread.currentthread().getname() + ": i/o error: " + e.getmessage());
                       }
            }


        }
    }
    public static void main(String[] args) {
        int numClients = 100;
        Thread[] clientThreads = new Thread[numclients];
        System.out.println("Starting "+numClients+" number of client threads.");
        for(int i = 0; i < numClients;i++) {
            clientThreads[i] = new Thread(Client.getRunnable(),"Client-"+i);
            clientThreads[i].start();
        }
    }
}
