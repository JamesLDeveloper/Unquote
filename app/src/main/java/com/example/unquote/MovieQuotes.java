package com.example.unquote;

import com.example.unquote.Question;

public class MovieQuotes extends Question {


    Boolean isMovieQuote;

    public MovieQuotes(int imageIdentifier, String questionString, String answerZero, String answerOne, String answerTwo, String answerThree, int correctAnswerIndex, boolean isMovieQuote) {
        super(imageIdentifier, questionString, answerZero, answerOne, answerTwo, answerThree, correctAnswerIndex);

        this.isMovieQuote = isMovieQuote;


    }

    public Boolean getIsMovieQuote() {
        return isMovieQuote;
    }

    public void setIsMovieQuote(Boolean isMovieQuote) {
        this.isMovieQuote = isMovieQuote;
    }
}
