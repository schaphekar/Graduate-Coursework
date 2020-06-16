// Client that asks the InetServer for the IP address of a given website

// Importing necessary libraries

// Input-Output
import java.io.*;

// Java Networking
import java.net.*;

public class InetClient {

    public static void main(String[] args) {

        String serverName;

        // If the user just hits enter (no arguments) then default to localhost
        if (args.length < 1)
            serverName = "localhost";

        // Otherwise take the first argument
        else
            serverName = args[0];

        // Print client configuration
        System.out.println("Siddharth's Inet Client, 1.8.\n");
        System.out.println("Using server : " + serverName + ", Port: 1600");

        // Initialize object of class BufferedReader, which will call the constructor
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name;
            do {
                System.out.print("Enter a hostname or an IP address, "
                        + "quit to end.\n");

                // Flush the stream to clear out anything inside the stream
                System.out.flush();
                name = in.readLine();

                // Get address
                if (name.indexOf("quit") < 0)
                    getRemoteAddress(name, serverName);
            }
            while (name.indexOf("quit") < 0);

            System.out.println("Cancelled by user request.");
        }

        catch (IOException x) {x.printStackTrace();}
    }

    static String toText(byte ip[]) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < ip.length; ++i) {
            if (i > 0) result.append(".");
            result.append(0xff & ip[i]);
        }

        return result.toString();

    }

    static void getRemoteAddress(String name, String serverName) {
        Socket socker;
        BufferedReader fromServer;
        PrintStream toServer;
        String textFromServer;

        try {
            // Create new socket
            socker = new Socket(serverName, 1600);

            // Reading input
            fromServer = new BufferedReader(new InputStreamReader(socker.getInputStream()));
            toServer = new PrintStream(socker.getOutputStream());

            toServer.println(name);
            // Clear contents of toServer
            toServer.flush();

            // Print the output from the server
            for (int i = 1; i <= 3; i++) {
                textFromServer = fromServer.readLine();
                if (textFromServer != null)
                    System.out.println(textFromServer);

            }
            // Close the socket
            socker.close();
        }
        catch (IOException x) {
            System.out.println("Socket error.");
            
            // Method on Exception instances which prints the stack trace of the instance
            x.printStackTrace();

        }
    }

}