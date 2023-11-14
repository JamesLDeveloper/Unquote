package com.example.unquote;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

//import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.util.ArrayList;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;

//import com.example.unquote.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;

    // TODO #2: add ArrayList member variable here

    ArrayList<Question> questions;

    // TODO 3-A: Declare View member variables

ImageView questionImageView;
TextView questionTextView;
TextView questionsRemainingTextView;
TextView questionsRemainingCountTextView;
Button answer0Button;
Button answer1Button;
Button answer2Button;
Button answer3Button;
Button submitButton;

Boolean validAnswer;


//    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO 2-G: Show app icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);


        setContentView(R.layout.activity_main); //This has been added to make a blank screen rather than one with standard navigation buttons at the bottom

        /*        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        */


        // TODO 3-B: assign View member variables

        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining);
        questionsRemainingCountTextView = findViewById(R.id.tv_main_questions_remaining_count);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);
        validAnswer = false;

        // TODO 4-E: set onClickListener for each answer Button

        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(0);
            }
        });

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(1);
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(2);
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(3);
            }
        });


        // TODO 5-A: set onClickListener for the submit answer Button

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validAnswer == false)       {
                }
                else {
                onAnswerSubmission();}
            }
        });


        startNewGame();

    }

    // TODO 3-F: displayQuestion(Question question) {...}

    public void displayQuestion(Question question) {
questionImageView.setImageResource(question.imageId);
questionTextView.setText(question.questionText);
answer0Button.setText(question.answer0);
answer1Button.setText(question.answer1);
answer2Button.setText(question.answer2);
answer3Button.setText(question.answer3);
    }

    // TODO 3-C: displayQuestionsRemaining(int questionRemaining) {...}

    public void displayQuestionsRemaining(int questionsRemaining){
//questionsRemainingTextView.setText(questionsRemaining);

questionsRemainingCountTextView.setText(String.valueOf(questionsRemaining));

    }

    // TODO 4-A: onAnswerSelected(int answerSelected) {...}

    public void onAnswerSelected(int answerSelection){
       Question currentQuestion = getCurrentQuestion();
       currentQuestion.playerAnswer = answerSelection;
       if (answerSelection == 0) {
           answer0Button.setText("✔ " + currentQuestion.answer0);
           answer1Button.setText(currentQuestion.answer1);
           answer2Button.setText(currentQuestion.answer2);
           answer3Button.setText(currentQuestion.answer3);
           validAnswer = true;
       }    else if (answerSelection == 1) {
               answer1Button.setText("✔ " + currentQuestion.answer1);
               answer0Button.setText(currentQuestion.answer0);
           answer2Button.setText(currentQuestion.answer2);
           answer3Button.setText(currentQuestion.answer3);
           validAnswer = true;
            } else if (answerSelection == 2) {
                answer2Button.setText("✔ " + currentQuestion.answer2);
           answer0Button.setText(currentQuestion.answer0);
           answer1Button.setText(currentQuestion.answer1);
           answer3Button.setText(currentQuestion.answer3);
           validAnswer = true;
            } else if (answerSelection == 3) {
               answer3Button.setText("✔ " + currentQuestion.answer3);
           answer0Button.setText(currentQuestion.answer0);
           answer1Button.setText(currentQuestion.answer1);
           answer2Button.setText(currentQuestion.answer2);
           validAnswer = true;
            } else {
           validAnswer = false;
        }
        }



    // TODO #1: add integer member variables here


    public void chooseQuestionSet(){
        while (questions.size() > 6){
            questions.remove(generateRandomNumber(questions.size()));
        }
    }


    // TODO #3 add startNewGame() here

    public void startNewGame(){

        // TODO 2-H: Provide actual drawables for each of these questions!
        questions = new ArrayList<>();

        Question question0 = new Question(R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it… Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);
        Question question1 = new Question(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who authored this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);
        Question question2 = new Question(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow — who actually said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);
        Question question3 = new Question(R.drawable.img_quote_3, "Insanely inspiring, insanely incorrect (maybe). Who is the true source of this inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3);
        Question question4 = new Question(R.drawable.img_quote_4, "A peace worth striving for — who actually reminded us of this?", "Malata Yousafzai", "Martin Luther King Jn.", "Liu Xiaobo", "Dalai Lama", 1);
        Question question5 = new Question(R.drawable.img_quote_5, "Unfortunately, true — but did Marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0);
        Question question6 = new Question(R.drawable.img_quote_6, "Here’s the truth, Will Smith did say this, but in which movie?", "Independence Day", "Bad Boys", "Men in Black", "The pursuit of Happyness", 2);
        Question question7 = new Question(R.drawable.img_quote_7, "Which TV funny gal actually quipped this 1-liner?", "Ellen Degeneres", "Amy Phoeler", "Betty White", "Tina Fey", 3);
        Question question8 = new Question(R.drawable.img_quote_8, "This mayor won’t get my vote — but did he actually give this piece of advice? And if not, who did?", "Forrest Gump, Forrest Gump", "Dorry, Finding Nemo", "Esther Williams", "The Mayor, Jaws", 1);
        Question question9 = new Question(R.drawable.img_quote_9, "Her heart will go on, but whose heart is it?", "Whitney Houston", "Diana Ross", "Celine Dion", "Mariah Carey", 2);
        Question question10 = new Question(R.drawable.img_quote_10, "He’s the king of something alright — to whom does this self-titling line belong to?", "Tony Montana, Scarface", "Joker, The Dark Knight", "Lex Luthor, Batman Vs Superman", "Jack, Titanic", 3);
        Question question11 = new Question(R.drawable.img_quote_11, "Is “Grey” synonymous for “wise”? If so, maybe Gandalf did preach this advice. If not, who did?", "Yoda, Star Wars", "Gandalf the Grey, Lord of the Rings", "Dumbledore, Harry Potter", "Uncle Ben, Spider-Man", 0);
        Question question12 = new Question(R.drawable.img_quote_12, "Houston, we have a problem with this quote — which space-traveler does this catch-phrase actually belong to?", "Han Solo, Star Wars", "Captain Kirk, Star Trek", "Buzz Lightyear, Toy Story", "Jim Lovell, Apollo 13", 2);

        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);

        totalCorrect = 0;

        chooseQuestionSet();
        totalQuestions = questions.size();




        Question firstQuestion = chooseNewQuestion();
// displayQuestion(firstQuestion);
// displayQuestionsRemaining(questions.size());

        // TODO 3-D.ii: Uncomment the line below after implementing displayQuestionsRemaining(int)
         displayQuestionsRemaining(questions.size());

        // TODO 3-H.ii: Uncomment after implementing displayQuestion(Question)
         displayQuestion(firstQuestion);





    }

    // TODO #4 add chooseNewQuestion() here

    public Question chooseNewQuestion(){
int randomNumber = generateRandomNumber(questions.size());
        currentQuestionIndex = randomNumber;
        validAnswer = false;
return questions.get(currentQuestionIndex);

    }

    // TODO #5 add getCurrentQuestion() here

public Question getCurrentQuestion(){
       Question currentQuestion = questions.get(currentQuestionIndex);
       return currentQuestion;
}

    // TODO #6 add onAnswerSubmission() here
    public void onAnswerSubmission(){
        if (getCurrentQuestion().isCorrect()){
            totalCorrect ++;
            System.out.println("That's correct");

            AlertDialog.Builder correctAnswerDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
            correctAnswerDialogueBuilder.setCancelable(true);
            correctAnswerDialogueBuilder.setTitle("Correct?");
            correctAnswerDialogueBuilder.setMessage("That's right!");
            correctAnswerDialogueBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            correctAnswerDialogueBuilder.create().show();



        } else {
            System.out.println("Sorry the correct answer was " + getCurrentQuestion().correctAnswer);

            String correctAnswerDisplay;

            switch (getCurrentQuestion().correctAnswer){
                case 0: correctAnswerDisplay = getCurrentQuestion().answer0;
                break;
                case 1: correctAnswerDisplay = getCurrentQuestion().answer1;
                break;
                case 2: correctAnswerDisplay = getCurrentQuestion().answer2;
                break;
                case 3: correctAnswerDisplay = getCurrentQuestion().answer3;
                break;
                default: correctAnswerDisplay = "One of the four you could have chosen, except the one you did ;)";
            }



            AlertDialog.Builder incorrectAnswerDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
            incorrectAnswerDialogueBuilder.setCancelable(true);
            incorrectAnswerDialogueBuilder.setTitle("Correct?");
            incorrectAnswerDialogueBuilder.setMessage("Sorry the correct answer was " + correctAnswerDisplay);
            incorrectAnswerDialogueBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            incorrectAnswerDialogueBuilder.create().show();



        }
        questions.remove(getCurrentQuestion());
        // TODO 3-D.i: Uncomment the line below after implementing displayQuestionsRemaining(int)
         displayQuestionsRemaining(questions.size());
        if (questions.size() == 0) {
        //    System.out.println(getGameOverMessage(totalCorrect, totalQuestions));

            // TODO 5-D: Show a popup instead
            AlertDialog.Builder gameOverDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
            gameOverDialogueBuilder.setCancelable(false);
            gameOverDialogueBuilder.setTitle("Game Over");
            gameOverDialogueBuilder.setMessage(getGameOverMessage(totalCorrect, totalQuestions));
            gameOverDialogueBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startNewGame();
                }
            });
            gameOverDialogueBuilder.create().show();

           // startNewGame();
        } else {
            chooseNewQuestion();
            // TODO: uncomment after implementing displayQuestion()
 displayQuestion(getCurrentQuestion());
        }

    }

    public int generateRandomNumber(int max){
        double randomNumber = Math.random();
        int randomIntNumber = (int)(randomNumber * max);
        //System.out.println(randomIntNumber);
        return randomIntNumber;
    }

public String getGameOverMessage(int totalCorrect, int totalQuestions) {
    if (totalCorrect == totalQuestions) {
        return "You got all " + (totalQuestions) + " right! You won!";
    } else {
        return "You got " + totalCorrect + " correct out of " + totalQuestions + ". Better luck next time!";
    }
}

}