package com.Quiz;

import java.io.IOException;

public class Executor
{
	public static void main(String[] args) throws IOException
	{
		Quiz quiz = new Quiz();
		quiz.startTime();
		quiz.getFileName();
		quiz.setQuestions();
		quiz.getNumQuestions();
		quiz.setQuestionNumbers();
		quiz.printQuestionNums();
		quiz.conductQuiz();
		quiz.computeStats();
		quiz.displayStats();
	}
}