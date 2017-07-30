import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
/* Accepts a request to create an access token from the client
  class once a user has already logged in
 */
public class Server {
    public static void main(String[] args) throws IOException {
        SystemMenu menu = new SystemMenu();
        ServerSocket serverSocket = new ServerSocket(1342);

        System.out.println("Waiting for client on port " +  serverSocket.getLocalPort() + "...");
        Socket socket = serverSocket.accept(); //accept the incoming request to the incoming request (ss)
        System.out.println("Connection has been made to " + socket.getRemoteSocketAddress());

        DataInputStream in = new DataInputStream(socket.getInputStream());//enables messages to be received from client
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        System.out.println(in.readUTF());//reads message from client
        System.out.println("Authentication Code: ");
/**
 * Creates verification code which will be sent to the Client class
 */
        int number, temp;
        Random rand = new Random();
        number = rand.nextInt(5000) + 1000;//accept the number client wants to pass
        temp = number * 3;//Temp is the generated token number
        PrintStream p = new PrintStream(socket.getOutputStream());
        p.println(temp); //sends number back to client
        System.out.println(temp);

    }


}
