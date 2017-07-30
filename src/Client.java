import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

/***
 * Tells the server class to create a new access token once a user has successfully logged in
 */

public class Client
{

    public static void main(String [] args) throws IOException
    {

        System.out.println("Connecting to server...");
        Scanner scan = new Scanner(System.in); //accepts input from user
        Socket soc = new Socket("127.0.0.1", 1342);
        System.out.println("You are now connected to " + soc.getRemoteSocketAddress() + "\n");

        InputStream inFromServer = soc.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);

/**
 * Calls the display method from SystemMenu so that the user can log in or register
 */
        SystemMenu sys = new SystemMenu();
        sys.displayMenu();
        sys.login();
        Random rand = new Random();

        /**
         * If a user has successfully logged in, it will tell the server class that a new login request has been made.
         * The Server class will then create an access token, then send it back to the client class.
         * The user will then be prompted to enter the verification code, if the entered code matches the generated code,
         * the user will then be verified
         *
         */
        if (sys.state)
        {
            OutputStream outToServer = soc.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer); //allows messages to be sent to client
            out.writeUTF("New Login Request...");//sends message to server

            int number, token, temp;
            Scanner scanServer = new Scanner(soc.getInputStream()); //get input stream from server
            number = rand.nextInt(5000) + 1000;
            PrintStream p = new PrintStream(soc.getOutputStream()); //Pass on the number to the server
            p.println(number); //pass number to the server... basically print it on the server class
            temp = scanServer.nextInt();//accepts generated token from server
            System.out.println("-------------------------- \n");

            System.out.print("Enter authentication code: "); //accepts number from the user
            token = scan.nextInt();

            if(token == temp)
            {
                System.out.println("You are verified!");
            }else {
                System.out.println("Sorry, we could not verify you.");
            }

        }
  }
}