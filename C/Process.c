/* Jonathan Mack
700130964
jmack3
CS350 Programming Assignment 3
This program uses child processes to alternatively output the date/time,
calendar of the current month, or the files in the current directory. */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main()
{
	/* declare variables */
	pid_t childpid;
	int choice = 1;
	
	/* print starting process IDs */
	printf("Parent process ID: %ld, Parent's parent ID: %ld\n", (long)getpid(),
		(long)getppid());
	
	while (choice != 4)
	{
		/* print menu, read choice */	
		printf("Choose the function to be performed by the child:\n");
		printf("    (1) Display current date and time\n");
		printf("    (2) Display the calendar of the current month\n");
		printf("    (3) List the files in the current directory\n");
		printf("    (4) Exit from the program\n\n");
		printf("Enter your choice: ");
		scanf("%d", &choice);
		
		switch (choice)
		{
			case 1:	/* date */
				childpid = fork();
				if (childpid == 0)
				{
					printf("Child process ID %ld, Parent ID %ld\n", getpid(), 
					getppid());
					system("date");
					printf("\n");
					exit(0);
				} /* end if */
				break;
			case 2:	/* calendar of current month */
				childpid = fork();
				if (childpid == 0)
				{
					printf("Child process ID %ld, Parent ID %ld\n", getpid(), 
					getppid());
					system("cal");
					printf("\n");
					exit(0);
				} /* end if */
				break;
			case 3:	/* files in current directory */
				childpid = fork();
				if (childpid == 0)
				{
					printf("Child process ID %ld, Parent ID %ld\n", getpid(), 
					getppid());
					system("ls -l");
					printf("\n");
					exit(0);
				} /* end if */
				break;
			case 4:	/* exit */
				printf("Current process PID %ld\n", getpid());
				exit(0);
				break;
			default: /* invalid choice */
				printf("Invalid choice: please enter 1, 2, 3, or 4.\n\n");
				break;
		} /* end switch */

		/* wait for child */
		childpid = wait(NULL);
		printf("Current process PID %ld\n", getpid());
	} /* end while */
	return 0;
} /* end main */
