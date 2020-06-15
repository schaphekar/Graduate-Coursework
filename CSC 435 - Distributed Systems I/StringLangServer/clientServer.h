// Header files
#include	<stdlib.h>
#include	<stdio.h>
#include	<string.h>
#include	<unistd.h>		// For unlink()
#include	<sys/types.h>	// For waitpid(), opendir()
#include	<sys/wait.h>	// For waitpid()
#include	<dirent.h>		// For opendir(), readdir(), closedir()
#include	<sys/socket.h>	// For socket()
#include	<netinet/in.h>	// For sockaddr_in and htons()
#include	<netdb.h>		// For getaddrinfo()
#include	<errno.h>		// For errno var
#include	<sys/stat.h>	// For open(), read(),write(), stat()
#include	<fcntl.h>		// and close()

// Constant definitions
#define		BUFFER_LEN				512
#define		STRING_LANG_CMD_CHAR	's'
#define		QUIT_CMD_CHAR			'q'
#define		STRING_LANG_PROGNAME	"./stringLanguage"
