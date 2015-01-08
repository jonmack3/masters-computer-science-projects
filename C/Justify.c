/* Jonathan Mack
jmack3, 700130964
CS350
Left justifies text from a data file, and prints it to the screen.
Due 10/27/06 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct listNode
{            
   	char string[31];
   	struct listNode *nextPtr;
	int count;
}; /* end listNode */

typedef struct listNode ListNode;
typedef ListNode *ListNodePtr;

void insert(ListNodePtr *sPtr, char word[31], int count);
void printParagraph(ListNodePtr currentPtr, int maxColumns);
void rightJustify(char line[81]);

int main()
{
	/* initialize variables */
   	char word[31], fileName[20], *wordCopy;
	char chr = 0, prevChar = 0, prevprevChar = 0;
	FILE *fptr;
  	int i = 0, maxColumns = 0;
	ListNodePtr startPtr = NULL;
	
	/* open file */
  	printf("Enter the name of the file containing the text to justify: ");
	scanf("%s", &fileName);
	fptr = fopen(fileName,"r");

	printf("Enter the number of columns of each line (between 55 and 80): ");
	scanf("%d", &maxColumns);
	while(maxColumns < 55 || maxColumns > 80)
	{
	   printf("Enter the number of columns of each line (between 55 and 80): ");
	   scanf("%d", &maxColumns);
	} /* end while */
	/* read file */
	while(1)
	{
		/* read character */
		prevprevChar = prevChar;
		prevChar = chr;
		chr = fgetc(fptr);
		
		/* print paragraph if two \n's */
		if (chr == '\n' && prevChar == '\n' && prevprevChar != '\n')
		{
			printParagraph(startPtr, maxColumns);
			printf("\n");
			startPtr = NULL;
			free(startPtr);
			i=0;
		} /* end if */
		/* process blank: add word to list if first blank space */
		else if (isspace(chr) || feof(fptr))
		{
			if(isspace(prevChar))
			{
				i=0;
			} /* end if */
			else
			{
				word[i] = '\0';
				if (word[0] != '\0')
				{
					insert(&startPtr, word, i);
				} /* end if */
				i=0;
			} /* end else */
		} /* end else if */
		else if (chr == '\n' && prevChar == '\n' && prevprevChar == '\n') i = 0;
		/* add character to word in progress */
		else
		{
			word[i] = chr;
			i++;
		} /* end else */			
		/* print paragraph */
		if (feof(fptr))
		{
			if (startPtr != NULL) printParagraph(startPtr, maxColumns);
			startPtr = NULL;
			free(startPtr);
			break;
		} /* end if */
	} /* end while */
} /* end main */

void insert(ListNodePtr *sPtr, char *wordPtr, int wordCount)
{ 
   	ListNodePtr newPtr;
   	ListNodePtr previousPtr;
   	ListNodePtr currentPtr;
	
   	newPtr = malloc(sizeof(ListNode));

	if (newPtr != NULL)
	{
		/* transfer pointer to string */
		int k = 0;
		strcpy(newPtr->string, wordPtr);
		newPtr->count = wordCount;
      	newPtr->nextPtr = NULL;
		previousPtr = NULL;
      	currentPtr = *sPtr;
		while (currentPtr != NULL)
		{ 
        	previousPtr = currentPtr;
        	currentPtr = currentPtr->nextPtr;
    	} /* end while */
      	if (previousPtr == NULL) 
		{ 
         	newPtr->nextPtr = *sPtr;
         	*sPtr = newPtr;
      	} /* end while */
      	else 
		{
         	previousPtr->nextPtr = newPtr;
         	newPtr->nextPtr = currentPtr;
			} /* end while */
   	} /* end if */
   	else 
	{
      	printf("%c not inserted. No memory available.\n", *wordPtr);
   	} /* end else */
} /* end insert */

void printParagraph(ListNodePtr currentPtr, int maxColumns)
{
	char line[81], temp[81];
	int prevColumn = 0;
	int currentColumn = 0;
	int lastSpace = 0;
	if (currentPtr == NULL)
	{
      	printf("No text in file.\n");
   	} /* end if */
	else 
	{
		while (1) /* process paragraph */
		{
			line[0] = '\0';
			currentColumn = 0;
			while (1) /* process line */
			{
				if(currentColumn != 0 && !ispunct(currentPtr->string[0]))
				{
					prevColumn = currentColumn;
					currentColumn = currentColumn + currentPtr->count + 1;
					if (currentColumn > maxColumns) break;
					strcat(line, " ");
					strcat(line, currentPtr->string);
				} /* end if */
				else
				{
					prevColumn = currentColumn;
					currentColumn = currentColumn + currentPtr->count;
					if (currentColumn > maxColumns) break;
					strcat(line, currentPtr->string);					
				} /* end else */
				if (currentColumn > maxColumns) break;
				currentPtr = currentPtr->nextPtr;
				if (currentPtr == NULL) break;			
			} /* end while */
			
			/* full (right) justification */
			/* rightJustify(line); */
			/* while(prevColumn < maxColumns)
			{
				while(line[i + lastSpace] != '\0')
				{
					if (isspace(line[lastSpace + i]))
					{
						j = 0;
						while(line[j] != '\0');
						{
							temp[j] = line[j + i + lastSpace];
							j++;
						}
						temp[j] = '\0';
						line[lastSpace + i] = ' ';
						j = 0;
						while(temp[j] != '\0')
						{
							line[i + lastSpace + j + 1] = temp[j];
							j++;
						}
						line[j+1] = '\0';
						prevColumn++;
						lastSpace = i;
					}
					i++;
				}
				i=0;
				lastSpace = 0;
			} */
			currentColumn = 0;
			if (currentPtr == NULL) break;
			printf("%s\n", line);
    	} /* end while */
	} /* end else */
} /* end printParagraph */
