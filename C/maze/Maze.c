/* Jonathan Mack
jmack3, 700130964
CS350
Imports a maze in a file to a character array, determines its starting 
and ending points, and finds a path through it. 
Due 9/17/06 */

#include <stdio.h>

int Mstart, Nstart, Mend, Nend;

void getMaze(char maze[][30], int M, int N);
void printMaze(char[][30], int M, int N);
void findEntryExit(char[][30], int M, int N);
int findWayOut(char maze[][30], int M, int N, int Mcurr, int Ncurr);
int isValid(char maze[][30], int M, int N, int Mcurr, int Ncurr);

main()
{
	char maze[30][30];
	int i, M, N;

	/* Get maze dimensions */
    printf("Enter the height of the maze (# of rows): ");
    scanf("%d", &M);
	printf("Enter the width of the maze (# of columns): ");
    scanf("%d", &N);
	
	/* Import maze from file to maze array */
	getMaze(maze, M, N);
	printMaze(maze, M, N);

	/* Find entry and exit */
	findEntryExit(maze, M, N);    
	printf("Start: %d, %d   End: %d, %d\n", Mstart, Nstart, Mend, Nend);
	
	/* Determine if path exists, print result */
	if(findWayOut(maze, M, N, Mstart, Nstart) == 1)
    {
		printMaze(maze, M, N);
      	printf("Path found!\n");
    } /* end if */
    else
    {
		printMaze(maze, M, N);
      	printf("Path not found.\n");
    } /* end else */
	return 0;
} /* end main */

/* imports maze from file to character array */
void getMaze(char array[][30], int M, int N)
{
	FILE *fptr;
  	char c;
  	char file_name[20];
  	int i,j;
  	 	
  	printf("Type in the name of the file containing the Maze: ");
  	
	scanf("%s",file_name);
	fptr=fopen(file_name,"r");
  	for (i=0; i<M; i++)
	{
   		for (j=0; j<N; j++)
		{ 	
			c=fgetc(fptr); 
			while ( !((c == '1')||(c =='0')) ) c=fgetc(fptr);
			array[i][j]=c;
   		} /* end for */
	} /* end for */	
  	fclose(fptr);
} /* end getMaze */

/* prints maze */
void printMaze(char array[][30], int M, int N)
{
	int i, j;
  	for (i=0; i<M; i++)
	{
   		for (j=0; j<N; j++)  
		{
			if (j == 0) printf("\n");                
			printf("%c  ",array[i][j]);
   		} /* end for */
	} /* end for */
  	printf("\n");
} /* end printMaze */

/* determines the entry and exit point of the maze */
void findEntryExit(char maze[][30], int M, int N)
{
	int foundEntry = 0;
	int foundExit = 0;
	int i;

	/* search top of maze */	
	for (i=0; i<N; i++)
	{
		if (maze[0][i] == '0')
		{
			/* look for exit */
			if (foundEntry == 1 && foundExit == 0)
			{
				Mend = 0;
				Nend = i;
				foundExit = 1;
			} /* end if */
			/* look for entry */
			if (foundEntry == 0)
			{
				Mstart = 0;
				Nstart = i;
				foundEntry = 1;
			} /* end if */
		} /* end if */
	} /* end for */

	/* search right hand side of maze */
	for (i=1; i<M; i++)
	{
		if (maze[i][N-1] == '0')
		{
			/* look for exit */
			if (foundEntry == 1 && foundExit == 0)
			{
				Mend = i;
				Nend = N - 1;
				foundExit = 1;
			} /* end if */
			/* look for entry */
			if (foundEntry == 0)
			{
				Mstart = i;
				Nstart = N - 1;
				foundEntry = 1;
			} /* end if */		
		} /* end if */
	} /* end for */
	
	/* search bottom of maze */
	for (i=N-2; i>=0; i--)
	{
		if (maze[M-1][i] == '0')
		{		
			/* look for exit */
			if (foundEntry == 1 && foundExit == 0)
			{
				Mend = M - 1;
				Nend = i;
				foundExit = 1;
			} /* end if */
			/* look for entry */
			if (foundEntry == 0)
			{
				Mstart = M - 1;
				Nstart = i;
				foundEntry = 1;
			} /* end if */
		} /* end if */	
	} /* end for */
	
	/* search left hand side of maze */
	for (i=M-2; i>0; i--)
	{
		if (maze[i][0] == '0')
		{
			/* look for exit */
			if (foundEntry == 1 && foundExit == 0 )
			{
				Mend = i;
				Nend = 0;
				foundExit = 1;
			} /* end if */	
			/* look for entry */
			if (foundEntry == 0)
			{
				Mstart = i;
				Nstart = 0;
				foundEntry = 1;
			} /* end if */
		} /* end if */
	} /* end for */
} /* end findEntryExit */

/* traverses the maze */
int findWayOut(char maze[][30], int M, int N, int Mcurr, int Ncurr)
{
    int q = 0;
    
    /* Check for done */
    if (Mcurr == Mend && Ncurr == Nend)
    {
		maze[Mcurr][Ncurr] = 'X';
		return 1;
    } /* end if */
	
    /* Check for invalid placement */
    if (isValid(maze, M, N, Mcurr, Ncurr) == 0)
    {
     	return 0;
    } /* end if */
    
    maze[Mcurr][Ncurr] = 'X';

    /* up */
    if(isValid(maze, M, N, Mcurr-1, Ncurr) == 1)
    {
     	q = findWayOut(maze, M, N, Mcurr-1, Ncurr);
      	if (q == 1)
      	{
        	return q;
      	} /* end if */
    } /* end if */
	
    /* right */
    if(isValid(maze, M, N, Mcurr, Ncurr + 1) == 1)
    {
    	q = findWayOut(maze, M, N, Mcurr, Ncurr + 1);
      	if (q == 1)
      	{
	        return q;
    	} /* end if */
    } /* end if */
	
    /* down */
    if(isValid(maze, M, N, Mcurr + 1, Ncurr) == 1)
    {
      	q = findWayOut(maze, M, N, Mcurr + 1, Ncurr);
      	if (q == 1)
      	{
        	return q;
      	} /* end if */
    } /* end if */

    /* left */
    if(isValid(maze, M, N, Mcurr, Ncurr - 1) == 1)
    {
    	q = findWayOut(maze, M, N, Mcurr, Ncurr - 1);
      	if (q == 1)
      	{
	        return q;
    	} /* end if */
    } /* end if */
	
	/* if no spots available, backtrack */
    maze[Mcurr][Ncurr] = '0';
    return 0;
} /* end findWayOut */

/* determines if current spot is a valid one */
int isValid (char maze[][30], int M, int N, int Mcurr, int Ncurr)  
	{
    /* Check for outside of maze */
    if (Mcurr < 0 || Ncurr < 0 || Mcurr >= M || Ncurr >= N)
    {
    	return 0;
    } /* end if */
	
    /* Check for wall, already traveled */
    else if (maze[Mcurr][Ncurr] == '1' || maze[Mcurr][Ncurr] == 'X')
    {
      	return 0;
    } /* end else if */
    else
    {
      	return 1;
    } /* end else */
} /* end isValid */
