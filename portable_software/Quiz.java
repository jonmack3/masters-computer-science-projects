package com.Quiz;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Quiz
{
	enum LineType
	{
		QUESTION, ANSWERS, CORRECT_ANSWER, NONE
	};

	ArrayList<Question> questions = new ArrayList<Question>();
	ArrayList<Integer> questionNums = new ArrayList<Integer>();
	long startTime;
	long endTime;
	int quizTime;
	int numQuestions;
	int numCorrect = 0;
	int percentCorrect;
	String quizFileName;

	public void startTime()
	{
		// get start time
		startTime = System.currentTimeMillis();
	}

	public void getFileName()
	{
		// get name and path of file
		System.out
			.println("Enter the full path and filename of the quiz file: ");
		Scanner in = new Scanner(System.in);
		quizFileName = in.nextLine();
	}

	public void setQuestions() throws IOException
	{
		FileReader reader = new FileReader(quizFileName);
		Scanner inFile = new Scanner(reader);

		// get questions/answers
		StringBuffer questionBuffer = new StringBuffer();
		Question question = new Question();
		LineType type = LineType.NONE;
		while (inFile.hasNextLine())
		{
			String line = inFile.nextLine();

			// blank line or comment; do nothing
			if (line.length() == 0 || line.substring(0, 1).equals("*"))
			;

			// start of question
			else if (line.length() >= 2 && line.substring(0, 2).equals("@Q"))
			{
				question = new Question();
				type = LineType.QUESTION;
			}

			// end of question: save completed question, delete question
			// buffer
			else if (line.length() >= 2 && line.substring(0, 2).equals("@A"))
			{
				question.setQuestion(questionBuffer.toString());
				questionBuffer.delete(0, questionBuffer.length());
				type = LineType.CORRECT_ANSWER;
			}

			/*
			 * end of answers: add completed question object to ArrayList of
			 * questions, clear answers
			 */
			else if (line.length() >= 2 && line.substring(0, 2).equals("@E"))
			{
				type = LineType.NONE;
				questions.add(question);
			}

			// question line: add to question buffer
			else if (type == LineType.QUESTION)
			{
				questionBuffer.append(line + " ");
			}

			// right answer line: set as right answer
			else if (type == LineType.CORRECT_ANSWER)
			{
				question.setCorrectAnswer(Integer.parseInt(line));
				type = LineType.ANSWERS;
			}

			// answer line: add to answers
			else if (type == LineType.ANSWERS)
			{
				question.getAnswers().add(line);
			}
		}

		// close file
		inFile.close();
	}

	public void getNumQuestions()
	{
		// get number of questions to ask
		System.out.println("There are " + questions.size()
			+ " questions in this quiz file.");
		System.out.println("How many questions should the current quiz have? ");
		Scanner in = new Scanner(System.in);
		numQuestions = in.nextInt();

		// if number of questions to ask less than number in file, abort
		if (numQuestions > questions.size())
		{
			throw new IllegalArgumentException(
				"The question pool is only of size " + questions.size()
					+ ". Quiz aborted.");
		}
		else if (numQuestions <= 0)
		{
			throw new IllegalArgumentException(
				"The quiz must contain at least one question. Quiz aborted.");
		}
	}

	public void setQuestionNumbers()
	{
		// generate numbers of questions to ask
		Random generator = new Random();
		for (int i = 0; i < numQuestions; i++)
		{
			while (questionNums.size() != numQuestions)
			{
				int questionNumber = generator.nextInt(questions.size()) + 1;
				if (!questionNums.contains(questionNumber))
				{
					questionNums.add(questionNumber);
				}
			}
		}
	}

	// print question numbers
	public void printQuestionNums()
	{
		System.out.print("Question number(s) ");
		for (int i : questionNums)
		{
			System.out.print(i + " ");
		}
		System.out.println("will be asked.");
	}

	public void conductQuiz()
	{
		// conduct quiz
		System.out.println("\nThe quiz will now begin.");
		System.out.println("For each question, please type in the number " +
				"of your answer after the prompt.");
		System.out.println("(1 for the first answer, 2 for the second, etc.)\n");
		for (int questionNum : questionNums)
		{
			// print question
			System.out.println(questions.get(questionNum - 1).getQuestion());

			// print answers
			for (String answer : questions.get(questionNum - 1).getAnswers())
			{
				System.out.println(answer);
			}

			// get answer
			System.out.println("Your answer: ");
			Scanner in = new Scanner(System.in);
			int userAnswer = in.nextInt();

			// compare answer, provide feedback
			if (userAnswer == questions.get(questionNum - 1).getCorrectAnswer())
			{
				System.out.println("Your answer was correct!\n");
				numCorrect++;
			}
			else
			{
				System.out.println("Alas, your answer was incorrect.\n");
			}
		}
	}

	public void computeStats()
	{
		long endTime = System.currentTimeMillis();
		quizTime = (int) ((endTime - startTime) / 1000.0);
		percentCorrect = (int) Math.round((double) numCorrect
			/ (double) numQuestions * 100.0);
	}

	public void displayStats()
	{
		System.out.println("Quiz completed! Statistics:");
		System.out.println(numQuestions + " question(s) were asked.");
		System.out.println("You had " + numCorrect + " correct answer(s).");
		System.out.println(percentCorrect + "% of your answer(s) were correct.");
		System.out.println("Total time for this quiz was " + quizTime 
			+ " seconds.");
	}

	public void printQuestions()
	{
		// print questions/correct answers/answers
		for (Question question : questions)
		{
			System.out.println(question.getQuestion());
			System.out.println(question.getCorrectAnswer());
			for (String answer : question.getAnswers())
			{
				System.out.println(answer);
			}
		}
	}
}
