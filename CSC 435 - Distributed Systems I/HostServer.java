// Initiates migration from the web client, migrates the agent, captures the new address of the agent, and installs this in dynamic HTML.
// In the final step, send it back to the client as part of the one request-response step.

// Import all necessary modules, including socket facilitation and readers
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class AgentWorker extends Thread {
  
  // Client connection socket
  Socket sock; 
  
  // Track the state of the agent using counters
  agentHolder parentAgentHolder; 
  
  // Track port for the corresponding request
  int localPort; 
  
  // AgentWorker constructor
  AgentWorker (Socket s, int prt, agentHolder ah) {
    sock = s;
    localPort = prt;
    parentAgentHolder = ah;
  }
  
  public void run() {
    
    // Initialize in/out vars
    PrintStream out = null;
    BufferedReader in = null;
    
    // Hardcoding localhost to faciliate local connection
    String NewHost = "localhost";
    
    // Main worker port
    int NewHostMainPort = 4242;
    
    // Buffer string
    String buf = "";
    
    // Variable for new port
    int newPort;
    
    // Variables for server-client exchanges
    Socket clientSock;
    BufferedReader fromHostServer;
    PrintStream toHostServer;
    
    try {
      out = new PrintStream(sock.getOutputStream());
      in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      
      // Scan client input
      String inLine = in.readLine();
      
      // Compute length of content to give flexibility in terms of which web browser is used
      StringBuilder htmlString = new StringBuilder();
      
      // Print log of request
      System.out.println();
      System.out.println("Request line: " + inLine);
      
      // If the request says migrate, then the user is given a new port
      if(inLine.indexOf("migrate") > -1) {
  
  // New socket with port 4242
  clientSock = new Socket(NewHost, NewHostMainPort);
  fromHostServer = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
  
  // Get next open port from host
  toHostServer = new PrintStream(clientSock.getOutputStream());
  toHostServer.println("Please host me. Send my port! [State=" + parentAgentHolder.agentState + "]");
  
  // Flush this output stream and write buffered output bytes
  toHostServer.flush();
  
  for(;;) {
    
    // Check that port number is valid
    buf = fromHostServer.readLine();
    if(buf.indexOf("[Port=") > -1) {
      break;
    }
  }
  
  // Fetch port by exploiting substring/format
  String tempbuf = buf.substring( buf.indexOf("[Port=")+6, buf.indexOf("]", buf.indexOf("[Port=")) );
  
  // Find integer that corresponds to new port
  newPort = Integer.parseInt(tempbuf);
  
  // Print to console
  System.out.println("newPort is: " + newPort);
  
  // HTML reply 
  htmlString.append(AgentListener.sendHTMLheader(newPort, NewHost, inLine));
  
  // Confirm migration request was received
  htmlString.append("<h3>We are migrating to host " + newPort + "</h3> \n");
  htmlString.append("<h3>View the source of this page to see how the client is informed of the new location.</h3> \n");
  
  // Submit finished HTML 
  htmlString.append(AgentListener.sendHTMLsubmit());
  
  //log that we are killing the waiting server at the port
  System.out.println("Killing parent listening loop.");
  
  // Fetch socket from parentAgentHolder
  ServerSocket ss = parentAgentHolder.sock;
  
  // Close port
  ss.close();
  
  } 

  else if(inLine.indexOf("person") > -1) {
        
  // Increase the state number in response to new input
  parentAgentHolder.agentState++;
  
  // Show the user agent state
  htmlString.append(AgentListener.sendHTMLheader(localPort, NewHost, inLine));
  htmlString.append("<h3>We are having a conversation with state   " + parentAgentHolder.agentState + "</h3>\n");
  htmlString.append(AgentListener.sendHTMLsubmit());
  
  } 

  else {

  // If the request was not found ...
  htmlString.append(AgentListener.sendHTMLheader(localPort, NewHost, inLine));
  htmlString.append("You have not entered a valid request!\n");
  htmlString.append(AgentListener.sendHTMLsubmit());    }
      
      // Yield HTML content
      AgentListener.sendHTMLtoStream(htmlString.toString(), out);
      
      // Close socket
      sock.close();
      
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }}


// This class holds the state information, which facilitates migration between ports
class agentHolder {
  
  // Serversocket object
  ServerSocket sock;
  
  // Variable for tracking state
  int agentState;
  
  // Agent constructor
  agentHolder(ServerSocket s) { sock = s;}
}

class AgentListener extends Thread {
  
  // Socket and port variables
  Socket sock;
  int localPort;
  
  // Listener constructor
  AgentListener(Socket As, int prt) {
   
  // Socket and port contents of the constructor
    sock = As;
    localPort = prt;
  }
  
  // All agent states start initialized at 0
  int agentState = 0;
  
  // Initiated when server starts listening 
  public void run() {
    BufferedReader in = null;
    PrintStream out = null;
    String NewHost = "localhost";
    System.out.println("In AgentListener Thread");  
    
    try {
      
      String buf;
      out = new PrintStream(sock.getOutputStream());
      in =  new BufferedReader(new InputStreamReader(sock.getInputStream()));
      
      // Look at the first line in the buffer
      buf = in.readLine();
      
      // If there is something in the buffer, read in that request
      if(buf != null && buf.indexOf("[State=") > -1) {
        
  // Yield the agent's state
  String tempbuf = buf.substring(buf.indexOf("[State=")+7, buf.indexOf("]", buf.indexOf("[State=")));
  
  // Search for the integer value of agent state
  agentState = Integer.parseInt(tempbuf);
  
  // Print to console
  System.out.println("agentState is: " + agentState);
  
      }
      
      System.out.println(buf);
      
      // Store and build HTML response using StringBuilder
      StringBuilder htmlResponse = new StringBuilder();
      
      // Show port and form contents
      htmlResponse.append(sendHTMLheader(localPort, NewHost, buf));
      htmlResponse.append("Now in Agent Looper starting Agent Listening Loop\n<br />\n");
      htmlResponse.append("[Port="+localPort+"]<br/>\n");
      
      // Send HTML response
      htmlResponse.append(sendHTMLsubmit());
      
      // Show HTML response
      sendHTMLtoStream(htmlResponse.toString(), out);
      
      // Open connection at port
      ServerSocket servsock = new ServerSocket(localPort,2);
      
      // Spawn new agent and give corresponding socket and state
      agentHolder agenthold = new agentHolder(servsock);
      agenthold.agentState = agentState;
      
      // Listen and accept new connections
      while(true) {
      sock = servsock.accept();
  
  // Confirm receipt of connection
  System.out.println("Got a connection to agent at port " + localPort);
  
  // Spawn new agent worker and start it
  new AgentWorker(sock, localPort, agenthold).start();
      }
      
    } catch(IOException ioe) {
      
      // If we're unable to establish a connection ... 
      System.out.println("Either connection failed, or just killed listener loop for agent at port " + localPort);
      System.out.println(ioe);
    }
  }
 
  static String sendHTMLheader(int localPort, String NewHost, String inLine) {
    
    StringBuilder htmlString = new StringBuilder();
    
    // Build the HTML form
    htmlString.append("<html><head> </head><body>\n");
    
    // Section to tell user about the request
    htmlString.append("<h2>This is for submission to PORT " + localPort + " on " + NewHost + "</h2>\n");
    htmlString.append("<h3>You sent: "+ inLine + "</h3>");
    htmlString.append("\n<form method=\"GET\" action=\"http://" + NewHost +":" + localPort + "\">\n");
    
    // User input section
    htmlString.append("Enter text or <i>migrate</i>:");
    htmlString.append("\n<input type=\"text\" name=\"person\" size=\"20\" value=\"YourTextInput\" /> <p>\n");
    
    return htmlString.toString();
  }

  static String sendHTMLsubmit() {
    return "<input type=\"submit\" value=\"Submit\"" + "</p>\n</form></body></html>\n";
  }
  
  static void sendHTMLtoStream(String html, PrintStream out) {
    
  // Print HTML 1.1 validation
    out.println("HTTP/1.1 200 OK");
    
    // Calculate the size of the content
    out.println("Content-Length: " + html.length());
    
    // Specify content type
    out.println("Content-Type: text/html");
    
    out.println("");    
    out.println(html);
  }
}

public class HostServer {
  
  // Start listening
  public static int NextPort = 3000;
  
  public static void main(String[] a) throws IOException {
    
  // Variables for length and port number
    int q_len = 6;
    int port = 4242;
    
    Socket sock;
    
    ServerSocket servsock = new ServerSocket(port, q_len);
    
    // Print to console confirming that server is ready to accept requests
    System.out.println("Siddharth's DIA Master receiver started at port 4242 ... ");
    System.out.println("Connect from 1 to 3 browsers using \"http:\\\\localhost:4242\"\n");
    
    // Listen on port or migrate agent
    while(true) {
      
      // Get new port to migrate request
      NextPort = NextPort + 1;
      
      // Accept client connection request
      sock = servsock.accept();
      
      // Message confirming that new agent listener has started at port
      System.out.println("Starting AgentListener at port " + NextPort);
      
      // New listener agent for more requests
      new AgentListener(sock, NextPort).start();
    }
    
  }
}