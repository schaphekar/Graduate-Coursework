// This is a C program that gets file-sys commands from the user, and sends them to a server via a socket
// Then it waits for a reply and outputs the response to the user.

//	Compile with:
//	$ gcc stringLangClient.c -o stringLangClient

// Define header files
#include "clientServer.h"

// Define constants
#define	DEFAULT_HOSTNAME "localhost"

// User input for server name and port
void	obtainUrlAndPort	(int urlLen, char* url, int* portPtr)
{
  //  Application validity check:
  if  ( (url == NULL)  ||  (portPtr == NULL) )
  {
    fprintf(stderr,"BUG: NULL ptr to obtainUrlAndPort()\n");
    exit(EXIT_FAILURE);
  }

  if   (urlLen <= 1)
  {
    fprintf(stderr,"BUG: Bad string length to obtainUrlAndPort()\n");
    exit(EXIT_FAILURE);
  }

  //  Get server name
  printf("Machine name [%s]? ",DEFAULT_HOSTNAME);
  fgets(url,urlLen,stdin);

  char*	cPtr	= strchr(url,'\n');

  if  (cPtr != NULL)
    *cPtr = '\0';

  if  (url[0] == '\0')
    strncpy(url,DEFAULT_HOSTNAME,urlLen);

  // Get port numbe:
  char	buffer[BUFFER_LEN];

  printf("Port number? ");
  fgets(buffer,BUFFER_LEN,stdin);

  *portPtr = strtol(buffer,NULL,10);

}


//  Attempt to connect to the server and returns file-descriptor of socket; if unsuccessful, returns -1
int	attemptToConnectToServer	(const char*	url,
					 int		port
					)
{
  //  Application validity check
  if  (url == NULL)
  {
    fprintf(stderr,"BUG: NULL ptr to attemptToConnectToServer()\n");
    exit(EXIT_FAILURE);
  }

  int socketDescriptor = socket(AF_INET, SOCK_STREAM, 0);

  //  Ask DNS about 'url'
  struct addrinfo* hostPtr;
  int status = getaddrinfo(url,NULL,NULL,&hostPtr);

  if (status != 0)
  {
    fprintf(stderr,gai_strerror(status));
    return(-1);
  }

  //  Attempt to connect to server:
  struct sockaddr_in server;

  // Clear server datastruct
  memset(&server, 0, sizeof(server));

  // Use TCP/IP
  server.sin_family = AF_INET;

  // Tell port # in proper network byte order
  server.sin_port = htons(port);

  // Copy connectivity info from info on server ("hostPtr")
  server.sin_addr.s_addr =
	((struct sockaddr_in*)hostPtr->ai_addr)->sin_addr.s_addr;

  status = connect(socketDescriptor,(struct sockaddr*)&server,sizeof(server));

  if  (status < 0)
  {
    fprintf(stderr,"Could not connect %s:%d\n",url,port);
    return(-1);
  }

  return(socketDescriptor);
}


//  Get text
const char* getText ()
{
  static char	buffer[BUFFER_LEN-2*sizeof(int)];

  printf("  Text? ");
  fgets(buffer,BUFFER_LEN-2*sizeof(int),stdin);

  //  III.  Finished:
  return(buffer);
}

void communicateWithServer (int	socketFd)
{
  char	buffer[BUFFER_LEN+1];
  int	shouldContinue	= 1;

  while  (shouldContinue)
  {
    int	choice;
    int	fileNum;
    int	numBytes;

    do
    {
      printf
	("What would you like to do:\n"
	 "(1) String language\n"
	 "(0) Quit\n"
	 "Your choice? "
	);
      fgets(buffer,BUFFER_LEN,stdin);
      choice = strtol(buffer,NULL,10);
    }
    while  ( (choice < 0)  ||  (choice > 1) );

    switch  (choice)
    {
    case 0 :
      shouldContinue	= 0;
      snprintf(buffer,BUFFER_LEN,"%c",QUIT_CMD_CHAR);
      break;

    case 1 :
      snprintf(buffer,BUFFER_LEN,"%c %s",
	       STRING_LANG_CMD_CHAR,getText()
	      );
      break;

    }

    printf("Sending \"%s\"\n",buffer);
    write(socketFd,buffer,strlen(buffer)+1);
    numBytes = read (socketFd,buffer,BUFFER_LEN);

    if  (numBytes > 0)
      buffer[numBytes] = '\0';

    printf("%s\n",buffer);
  }

}

// Main function that does the work of the client
int	main	()
{
  char	url[BUFFER_LEN];
  int		port;
  int		socketFd;

  obtainUrlAndPort(BUFFER_LEN,url,&port);
  socketFd	= attemptToConnectToServer(url,port);

  if  (socketFd < 0)
    exit(EXIT_FAILURE);

  communicateWithServer(socketFd);
  close(socketFd);

  return(EXIT_SUCCESS);
}
