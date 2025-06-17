import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try(PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            String lineFromClient;
            System.out.println("Welcome to the EchoServer, type quit to exit");
            while ((lineFromClient = fromClient.readLine()) != null)
            {
                System.out.println("Received from ["+ clientSocket.getRemoteSocketAddress() +"]"+lineFromClient);
                if(lineFromClient.trim().equalsIgnoreCase("exit")) {
                    break;
                }
                String response = lineFromClient;
                toClient.println(response);
            }
        } catch (IOException e) {
            System.out.println("Exception in client handler "+e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
