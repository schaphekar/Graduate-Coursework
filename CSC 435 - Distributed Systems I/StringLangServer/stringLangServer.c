// This file defines a C program that gets file-sys commands from a client via a socket and executes those commands in their own threads.
// Returns returns the corresponding output back to the client.

//	Compile with:
//	$ gcc stringLangServer.c -o stringLangServer -lpthread

// Including header files for server and pthread create
#include	"clientServer.h"
#include	<pthread.h>	

// Defining constants
#define		STD_OKAY_MSG		    "Okay"
#define		STD_ERROR_MSG		    "Error doing operation"
#define		STD_BYE_MSG		      "Good bye!"
#define		THIS_PROGRAM_NAME	  "stringLangServer"

const int	ERROR_FD		= -1;

// String language function definition
void stringLangFile	(int socketFd, const char* cPtr)
{
  // Open pipes to get output of stringLanguage process
  int	langOut[2];
  char	outBuffer[BUFFER_LEN+1];

  if  (pipe(langOut) < 0)
  {
    snprintf(outBuffer,BUFFER_LEN,"Error creating pipe\n");
    write(socketFd,outBuffer,strlen(outBuffer));
    return;
  }

  //  Create stringLanguage process and have it run spell-checker
  pid_t	stringLangPid	 = fork();

  if  (stringLangPid < 0)

  {
    snprintf(outBuffer,BUFFER_LEN,"Error fork()ing\n");
    write(socketFd,outBuffer,strlen(outBuffer));
    close(langOut[0]);
    return;
  }

  if (stringLangPid == 0)

  {
    close(langOut[0]);
    dup2(langOut[1],STDOUT_FILENO);

    // Run the program STRING_LANG_PROGNAME with cPtr on the cmd line
    execl(STRING_LANG_PROGNAME,STRING_LANG_PROGNAME,cPtr,NULL);

    snprintf(outBuffer,BUFFER_LEN,"Could not find %s\n",STRING_LANG_PROGNAME);

    // Send its output from the pipe to the close stdout file descriptor
    write(langOut[1],outBuffer, strlen(outBuffer));
    exit(EXIT_FAILURE);
  }

  // Close its file descriptor to stdout
  close(langOut[1]);

  //  Read response from spell-checking child process
  int	numChars	= read(langOut[0],outBuffer,BUFFER_LEN);

  // Get input from the pipe, put the '\0' char at the end
  if  (numChars >= 0)

    outBuffer[numChars]	= '\0';

  // Wait for child process to finish
  int	status;

  wait(&status);

  // Send the input back to the client using file descriptor 'fd'
  write(socketFd,outBuffer,strlen(outBuffer));

}

//  To cast vPtr to the pointer type coming from doServer that points to two integers
void* handleClient (void* vPtr)
{
  int* intArray	= (int*)vPtr;
  int fd		    = intArray[0];
  int	threadNum	= intArray[1];

  free(vPtr);
  printf("Thread %d starting ...\n",threadNum);

  // Read command
  char	buffer[BUFFER_LEN];
  char	command;
  int 	shouldContinue	= 1;

  while  (shouldContinue)
  {
    read(fd,buffer,BUFFER_LEN);

    printf("Thread %d received: %s\n",threadNum,buffer);

    command	= buffer[0];

    switch (command)
    {
    case STRING_LANG_CMD_CHAR:
      stringLangFile(fd,buffer+2);
      break;
    
    case QUIT_CMD_CHAR :
      write(fd,STD_BYE_MSG,sizeof(STD_BYE_MSG));
      shouldContinue	= 0;
      break;
    }

  }

  printf("Thread %d quitting.\n",threadNum);
  return(NULL);
}

//  To run the server by accepting client requests from listenFd
void doServer	(int listenFd)
{
  // Server clients:
  pthread_t		     threadId;
  pthread_attr_t	 threadAttr;
  int			         threadCount = 0;

  pthread_attr_init(&threadAttr);
  pthread_attr_setdetachstate(&threadAttr, PTHREAD_CREATE_DETACHED);

  while (1)
  {
    int* clientFdPtr 	= (int*)malloc(2*sizeof(int));

    clientFdPtr[0]	= accept(listenFd, NULL, NULL);
    clientFdPtr[1]	= threadCount++;

    pthread_create(&threadId, &threadAttr, handleClient, clientFdPtr);
  }

  pthread_attr_destroy(&threadAttr);

}

//  Fetch port number, from the command line arguments OR by user input
int	getPortNum (int argc, char*	argv[])
{
  //  Get listening socket
  int	portNum;

  if  (argc >= 2)
    portNum	= strtol(argv[1],NULL,0);
  else
  {
    char	buffer[BUFFER_LEN];

    printf("Port number to monopolize? ");
    fgets(buffer,BUFFER_LEN,stdin);
    portNum	= strtol(buffer,NULL,0);
  }

  return(portNum);
}

//  Attempt to create and return a file-descriptor for listening
int getServerFileDescriptor (int port)
{
  //  Attempt to get socket file descriptor and bind it to 'port'
  int socketDescriptor = socket(AF_INET, SOCK_STREAM, 0);

  if  (socketDescriptor < 0)
  {
    perror(THIS_PROGRAM_NAME);
    return(ERROR_FD);
  }

  // Socket structure
  struct sockaddr_in socketInfo;

  // Fill socketInfo with 0's
  memset(&socketInfo,'\0',sizeof(socketInfo));

  // Use TCP/IP
  socketInfo.sin_family = AF_INET;

  //  Tell port in network endian with htons()
  socketInfo.sin_port = htons(port);

  //  Allow machine to connect to this service
  socketInfo.sin_addr.s_addr = INADDR_ANY;

  //  Try to bind socket with port and other specifications
  int status = bind(socketDescriptor, (struct sockaddr*)&socketInfo, sizeof(socketInfo));

  if (status < 0)
  {
    perror(THIS_PROGRAM_NAME);
    return(ERROR_FD);
  }

  // Set OS queue length:
  listen(socketDescriptor, 5);

  return(socketDescriptor);
}

int main (int	argc, char*	argv[])
{
  int port	= getPortNum(argc,argv);
  int	listenFd	= getServerFileDescriptor(port);
  int	status	= EXIT_FAILURE;

  if (listenFd >= 0)
    
  {
    doServer(listenFd);
    close(listenFd);
    status	= EXIT_SUCCESS;
  }

  return(status);
}
