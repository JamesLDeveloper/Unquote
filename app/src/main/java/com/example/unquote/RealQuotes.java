package com.example.unquote;

import com.example.unquote.Question;

public class RealQuotes extends Question {

    boolean isRealQuote;

    public RealQuotes(int imageIdentifier, String questionString, String answerZero, String answerOne, String answerTwo, String answerThree, int correctAnswerIndex, boolean isRealQuote) {
        super(imageIdentifier, questionString, answerZero, answerOne, answerTwo, answerThree, correctAnswerIndex);

        this.isRealQuote = isRealQuote;

    }

    public boolean getIsRealQuote() {
        return isRealQuote;
    }

    public void setIsRealQuote(boolean isRealQuote) {
        this.isRealQuote = isRealQuote;
    }
}
