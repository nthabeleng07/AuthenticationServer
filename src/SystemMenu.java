import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class SystemMenu
{

    private File fileName = new File("Users.txt");
    private File passFile = new File("Pass.txt");
    private String line;
    private String pline;
    private Scanner input;
    private String username, password;
    boolean state; //Checks if a user has logged in or not

    public SystemMenu()
    {
        this.input = new Scanner(System.in);
        this.username = " ";
        this.password = " ";
    }

    public void displayMenu()
    {
        int option = 0;
        System.out.println("Welcome to the Authentication Server");
        System.out.println("--------------------------");
        do {
            System.out.println("\nPlease select an option: ");
            System.out.println("1: Register");
            System.out.println("2: Login");
            option = input.nextInt();
            input.nextLine();
            processInput(option);
        } while (option != 0);
    }

    private void processInput(int i)
    {

        switch (i)
        {

            case 1:
                register();
                break;
            case 2:
                System.out.println("Please log in with your Username and Password.");
                break;
            default:
                System.out.println("Incorrect Input: Please enter a value from 1-2.");
        }
    }

    /**
     * Allows the user to log in. User.txt and Pass.txt will be read in order to validate the user input
     * if the entered username and password matches to the data in the textfiles, then the user will be logged in
     * @return true, if user details are found in the textfiles
     * @return false, if the user details are not found in the textfiles
     */

    public boolean login() {
        List<String> users = new ArrayList<>();
       List<String> pass = new ArrayList<>();

        //read usernames textfile
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(fileName));
            if (!buffer.ready()) {
                throw new IOException(); //Checks if the file can be read
            }
            while ((line = buffer.readLine()) != null) {
                users.add(line);
            }

            buffer.close();
        } catch (IOException e) //Catches any kind of error
        {
            System.out.println("File is not ready----");
        }

    //read passwords textfile
        try {
            BufferedReader b = new BufferedReader(new FileReader(passFile));
            if (!b.ready()) {
                throw new IOException(); //Checks if the file can be read
            }
            while ((pline = b.readLine()) != null) {
                pass.add(pline);
            }

            b.close();
        } catch (IOException e) //Catches any kind of error
        {
            System.out.println("File is not ready----");
        }

        System.out.print("Please enter your Username: ");
        username = input.nextLine();

        while ((username.length() < 1)) {
            System.out.print("Username cannot be blank. Please enter Username: ");
            username = input.nextLine();
        }
            int position;
            String value;
            while(!users.contains(toLowerCase(username))) {
                System.out.print("User does not exist, please try again: ");
                username = input.nextLine();
            }

        System.out.print("Please enter your Password: ");
        position = users.indexOf(username);
        password = input.nextLine();

        while ((password.length() < 1)) {
            System.out.print("Password cannot be blank. Please enter Password: ");
            password = input.nextLine();
        }

        value = pass.get(position);
        while(!password.equals(value))
        {
            System.out.print("Incorrect password. Please try again: ");
            password = input.nextLine();
        }
        if(users.contains(toLowerCase(username)) && password.equals(value)) {
            return state = true;
        }
        else {
            return state = false;

        }
    }

    /**
     * Allows a new user to register an account
     * If the entered username exists, the user will be asked to create a different username that doesn't exist.
     */

    private void register()
    {
        List<String> regUser = new ArrayList<>();
        List<String> regPass = new ArrayList<>();
        List<String> checkUser = new ArrayList<>(); //this will read the users arraylist to check if a user exists

        //Reading Users.txt
        try
        {
            BufferedReader buffer = new BufferedReader(new FileReader(fileName));
            if (!buffer.ready()) {
                throw new IOException(); //Checks if the file can be read
            }
            while ((line = buffer.readLine()) != null) {
                checkUser.add(line);
            }

            buffer.close();
        } catch (IOException e) //Catches any kind of error
        {
            System.out.println("File is not ready----");
        }

        System.out.print("Please enter your Username: ");
        username = input.nextLine();

        while ((username.length() < 1))
        {
            System.out.print("Username cannot be blank. Please enter Username: ");
            username = input.nextLine();
        }

        //If a user exists, the user will be told to create a different username
      while(checkUser.contains(toLowerCase(username))) {
            System.out.print("Username exists, please try again: ");
            username = input.nextLine();
        }

        System.out.print("Please enter your Password: ");
        password = input.nextLine();

        while ((password.length() < 1))
        {
            System.out.print("Password cannot be blank. Please enter Password: ");
            password = input.nextLine();
        }

        //write the names of the users to a file.
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            Writer output = new BufferedWriter(fw);
            if (username.length() > 0)
            {
                regUser.add(toLowerCase(username));
                for (int i = 0; i < regUser.size(); i++)
                {
                    output.write(regUser.get(i) + "\n");
                }
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
            FileWriter f = new FileWriter(passFile, true);
            Writer o = new BufferedWriter(f);
            if (password.length() > 0)
            {
                regPass.add(password);
                for (int i = 0; i < regPass.size(); i++)
                {
                    o.write(regPass.get(i) + "\n");
                }
            }
            o.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" ");
        System.out.println("Thank you for Registering");
        System.out.println("You may now log in with your details");
        System.out.println("-------------------------- \n");
    }

}
