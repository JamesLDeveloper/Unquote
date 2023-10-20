package com.example.unquote;

public class AndroidOS {
    public AndroidOS() {
        System.out.println("AndroidOS Booting Up...");
    }

    public void runQuizApp() {
        // Create three questions here
Question questionOne = new Question(921238, "How tall is the Eiffel tower?", "1024 ft", "1063 ft", "1124 ft", "1163 ft", 1);
Question questionTwo = new Question(107343, "Who invented the computer algorithm?", "Charles Babbage", "John Carmack", "Alan Turing", "Ada Lovelace",3);
Question questionThree = new Question(748294, "What is the name for the patch of skin found on your elbow?", "Elbow Skin", "Fascia Elbora", "Wenis", "Todd", 2);
        // System.out.println("Question 1: "" + ...)
        System.out.println(toString(questionOne));
        System.out.println(toString(questionTwo));
        System.out.println(toString(questionThree));
    }

    public String toString(Question question){
return "Image ID: "+(question.imageId)+". Question: "+(question.questionText)+". Option One: "+(question.answer0)+". Option Two: "+(question.answer1)+". Option Three: "+(question.answer2)+". Option Four: "+(question.answer3)+". Correct Answer: "+(question.correctAnswer)+". Player Answer: "+(question.playerAnswer);
    }

    public static void main(String[] args) {
        System.out.println("Starting: AndroidOS");
        AndroidOS androidOS = new AndroidOS();
        androidOS.runQuizApp();
    }
}
