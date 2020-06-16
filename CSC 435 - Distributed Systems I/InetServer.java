// This is a multithreaded server for InetClient that takes webpage name and returns its IP address

// Importing necessary libraries

// Input-Output Module
import java.io.*;

// Java Networking Module
import java.net.*;

// This is the worker thread
class Worker extends Thread {
    
    // Socket variable
    Socket socker;
    Worker (Socket s) {socker = s;}

    public void run() {
        // Fetch data streams from the socket
        PrintStream out = null;
        BufferedReader in = null;

        try {
            in = new BufferedReader (new InputStreamReader(socker.getInputStream()));

            out = new PrintStream(socker.getOutputStream());

            try {
                String name;
                name = in.readLine();
                System.out.println("Looking up " + name);
                printRemoteAddress(name, out);
            }
            catch (IOException x) {
                System.out.println("Server read error");
                x.printStackTrace();
            }

            // Close the socket
            socker.close();
        }	catch (IOException ioe) {System.out.println(ioe);}
    }

    static void printRemoteAddress (String name, PrintStream out) {
        try {
            out.println("Looking up " + name + " ...");

            // Fetch and print host name and host IP
            InetAddress node = InetAddress.getByName(name);
            out.println("Host name : " + node.getHostName());
            out.println("Host IP: " + toText (node.getAddress()));
        }	catch (UnknownHostException ex) {
            out.println("Failed in attempt to look up" + name);

        }
    }

    static String toText (byte ip[]) {
        StringBuffer result = new StringBuffer();
        // Translates the IP address to standard numerical format
        for (int i = 0; i < ip.length; ++i) {
            if (i > 0) result.append(".");
            result.append(0xff & ip[i]);
        }
        return result.toString();
    }
}

public class InetServer {

    public static void main(String[] a) throws IOException{
        // Setting variables for port number and length
        int length = 6;
        int port = 1600;
        Socket socker;

        // Create a socket for server-client connection
        ServerSocket servsocker = new ServerSocket(port, length);

        System.out.println("Siddharth's Inet Server 1.8 starting up, "
                + "listening at port 1600.\n");

        while (true) {

            // Accept and connect a new client
            socker = servsocker.accept();

            // Assign a worker to handle the new client
            new Worker(socker).start();
        }
    }

}