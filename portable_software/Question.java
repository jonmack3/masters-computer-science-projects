package com.Quiz;

import java.util.ArrayList;

public class Question
{
	private String question;
	private ArrayList<String> answers;
	private int correctAnswer;

	public Question()
	{
		question = "";
		answers = new ArrayList<String>();
		correctAnswer = 0;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public ArrayList<String> getAnswers()
	{
		return answers;
	}

	public void setAnswers(ArrayList<String> answers)
	{
		this.answers = answers;
	}

	public int getCorrectAnswer()
	{
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}

	
}