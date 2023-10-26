package com.example.unquote;

public class Main {

//    Question question;

    public static void main(String[] args) {
        Question testQuestion = new Question(0, "Who invented the computer algorithm?", "Bill Gates", "Charles Babbage", "Ada Lovelace", "Leonardo DaVinci", 2);

        System.out.println("The player’s guess is: " + testQuestion.playerAnswer);
        if (testQuestion.playerAnswer == -1) {
            System.out.println("Default answer selected!");
        }
        testQuestion.playerAnswer = 2;
        System.out.println("The player’s guess is: " + testQuestion.playerAnswer);
        if (testQuestion.isCorrect()) {
            System.out.println("The player’s guess is correct!");
        } else {
            System.out.println("The player’s guess is incorrect!");
        }

        MainActivity mainActivity = new MainActivity();
        System.out.println("A random number between 0 and 50: " + mainActivity.generateRandomNumber(50));
        System.out.println("A random number between 0 and 1000: " + mainActivity.generateRandomNumber(1000));
        System.out.println("A random number between 0 and 1 million: " + mainActivity.generateRandomNumber(1000000));

        System.out.println("Game over test 1 (should be 2 out of 3): " + mainActivity.getGameOverMessage(2, 3));
        System.out.println("Game over test 2 (should be 4 out of 4): " + mainActivity.getGameOverMessage(4, 4));
    }
}
