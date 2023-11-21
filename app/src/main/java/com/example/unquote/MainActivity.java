package com.example.unquote;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.core.widget.TextViewCompat;

//import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;

//import com.example.unquote.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MyTimer.TimerCallback {

    private MyTimer countDownTimer;

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

    TextView timeRemainingTextView;

    TextView countDownTimerRemaining;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    Boolean validAnswer;

    Boolean correctAnswer = false;

    String correctAnswerDisplay;

    final String[] options = {"Real life", "Movie quotes", "Both"};

    String selectedOption;


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


/*        Button showDialogButton = findViewById(R.id.showDialogButton);
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showOptionsDialog();
            }
        }); */

        countDownTimer = new MyTimer(20000, 1000, this);
        countDownTimer.setInterval(1000);

        // TODO 3-B: assign View member variables

        int autoSizeMinTextSize = 6;
        int autoSizeMaxTextSize = 30;
        int autoSizeStepGranularity = 1;
        int unit = TypedValue.COMPLEX_UNIT_SP;


        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);



        questionsRemainingCountTextView = findViewById(R.id.tv_main_questions_remaining_count);

        countDownTimerRemaining = findViewById(R.id.tv_time_remaining_countdown);



        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                questionsRemainingTextView, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        timeRemainingTextView = findViewById(R.id.tv_time_remaining_text);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                timeRemainingTextView, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        answer0Button = findViewById(R.id.btn_main_answer_0);
                TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                answer0Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        answer1Button = findViewById(R.id.btn_main_answer_1);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                answer1Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        answer2Button = findViewById(R.id.btn_main_answer_2);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                answer2Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        answer3Button = findViewById(R.id.btn_main_answer_3);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                answer3Button, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);

        submitButton = findViewById(R.id.btn_main_submit_answer);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
                submitButton, autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);




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

if (validAnswer) {
    countDownTimer.stopTimer();
    onAnswerSubmission();
} else {

}
            }
        });


 //       startNewGame();
        chooseGameMode();

    }

    @Override
    public void onTimerTick(long millisUntilFinished) {
        //     long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
        //     countDownTimerRemaining.setText(String.valueOf(seconds));
        //     updateUITimer(millisUntilFinished);
        countDownTimerRemaining.setText(String.valueOf(millisUntilFinished / 1000));
    }

    @Override
    public void onTimerFinish() {

        if (questions.size() > 0) {
            validAnswer = true;
            onAnswerSubmission();
        }
    }

//    @Override
//    public void onTimerFinishWithValidAnswer() {
//        validAnswer = true;
//        if (questions.size() > 0) {
//            onAnswerSubmission();
//        }
//    }


//    private void updateUITimer(long millisUntilFinished) {
//      //  long timeLeftInMillis = countDownTimer.getInterval();
////        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
//        countDownTimerRemaining.setText(String.valueOf(millisUntilFinished / 1000));
//
//    }

    // TODO 3-F: displayQuestion(Question question) {...}

    public void displayQuestion(Question question) {
        countDownTimer.startTimer();
        questionImageView.setImageResource(question.imageId);
        questionTextView.setText(question.questionText);
        answer0Button.setText(question.answer0);
        answer1Button.setText(question.answer1);
        answer2Button.setText(question.answer2);
        answer3Button.setText(question.answer3);
    }

    // TODO 3-C: displayQuestionsRemaining(int questionRemaining) {...}

    public void displayQuestionsRemaining(int questionsRemaining) {
//questionsRemainingTextView.setText(questionsRemaining);

        questionsRemainingCountTextView.setText(String.valueOf(questionsRemaining));

    }

    // TODO 4-A: onAnswerSelected(int answerSelected) {...}

    public void onAnswerSelected(int answerSelection) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelection;
        if (answerSelection == 0) {
            answer0Button.setText("✔ " + currentQuestion.answer0);
            answer1Button.setText(currentQuestion.answer1);
            answer2Button.setText(currentQuestion.answer2);
            answer3Button.setText(currentQuestion.answer3);
            validAnswer = true;
        } else if (answerSelection == 1) {
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


    public void chooseQuestionSet() {



                while (questions.size() > 6) {
                    questions.remove(generateRandomNumber(questions.size()));
                }



 /*       AlertDialog.Builder chooseGameModeDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
        chooseGameModeDialogueBuilder.setCancelable(false);
        chooseGameModeDialogueBuilder.setTitle("Welcome to Unquote: Choose your game mode.");
        chooseGameModeDialogueBuilder.setMessage("Would you like to play with real life quotes, movie quotes or both?");

        final String[] options = {"Real life", "Movie quotes", "Both"};

        chooseGameModeDialogueBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String selectedOption = options[which];

                Log.d("Options", Arrays.toString(options));

  /*              if (selectedOption.equals("Real life")) {
                    for (Question q : questions) {
                        if (q instanceof RealQuotes) {
                            questions.remove(q);

                        }
                    }
                }
                        else if (selectedOption.equals("Movie quotes")) {
                            for (Question q : questions) {
                                if (q instanceof MovieQuotes) {
                                    questions.remove(q);
                        }
                    }
                }
*/
/*
                if (selectedOption.equals("Real life")) {
                    removeQuestionsOfType(RealQuotes.class);
                } else if (selectedOption.equals("Movie quotes")) {
                    removeQuestionsOfType(MovieQuotes.class);
                }
            }

        });

//            countDownTimer.stopTimer();
        chooseGameModeDialogueBuilder.create().show();
*/



    }




    private void removeQuestionsOfType(Class<? extends Question> questionType) {
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()) {
            Question q = iterator.next();
            if (questionType.isInstance(q)) {
                iterator.remove();
            }
        }
    }




    public void chooseGameMode(){

        AlertDialog.Builder chooseGameModeDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        chooseGameModeDialogBuilder.setCancelable(false);
        chooseGameModeDialogBuilder.setTitle("Choose your game mode.");

//        final String[] options = {"Real life", "Movie quotes", "Both"};

        chooseGameModeDialogBuilder.setItems(new String[]{"Real Life", "Movie Quotes", "Both"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("TestDialog", "Selected Option: " + which);

                selectedOption = options[which];

                //     Log.d("Options", Arrays.toString(options));



startNewGame();

            }
        });
        chooseGameModeDialogBuilder.create().show();


 /*       AlertDialog.Builder chooseGameModeDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
        chooseGameModeDialogueBuilder.setCancelable(false);
        chooseGameModeDialogueBuilder.setTitle("Welcome to Unquote: Choose your game mode.");
        chooseGameModeDialogueBuilder.setMessage("Would you like to play with real life quotes, movie quotes or both?");

        final String[] options = {"Real life", "Movie quotes", "Both"};

        chooseGameModeDialogueBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String selectedOption = options[which];

                Log.d("Options", Arrays.toString(options));

  /*              if (selectedOption.equals("Real life")) {
                    for (Question q : questions) {
                        if (q instanceof RealQuotes) {
                            questions.remove(q);

                        }
                    }
                }
                        else if (selectedOption.equals("Movie quotes")) {
                            for (Question q : questions) {
                                if (q instanceof MovieQuotes) {
                                    questions.remove(q);
                        }
                    }
                }
*/
/*
                if (selectedOption.equals("Real life")) {
                    removeQuestionsOfType(RealQuotes.class);
                } else if (selectedOption.equals("Movie quotes")) {
                    removeQuestionsOfType(MovieQuotes.class);
                }
            }

        });

//            countDownTimer.stopTimer();
        chooseGameModeDialogueBuilder.create().show();
*/



    }





    // TODO #3 add startNewGame() here

    public void startNewGame() {

        // TODO 2-H: Provide actual drawables for each of these questions!
        questions = new ArrayList<>();

        Question question0 = new RealQuotes(R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it… Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2, true);
        Question question1 = new RealQuotes(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who authored this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0, true);
        Question question2 = new RealQuotes(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow — who actually said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1, true);
        Question question3 = new RealQuotes(R.drawable.img_quote_3, "Insanely inspiring, insanely incorrect (maybe). Who is the true source of this inspiration?", "Nelson Mandela", "Harriet Tubman", "Mahatma Gandhi", "Nicholas Klein", 3, true);
        Question question4 = new RealQuotes(R.drawable.img_quote_4, "A peace worth striving for — who actually reminded us of this?", "Malata Yousafzai", "Martin Luther King Jn.", "Liu Xiaobo", "Dalai Lama", 1, true);
        Question question5 = new RealQuotes(R.drawable.img_quote_5, "Unfortunately, true — but did Marilyn Monroe convey it or did someone else?", "Laurel Thatcher Ulrich", "Eleanor Roosevelt", "Marilyn Monroe", "Queen Victoria", 0, true);
        Question question6 = new MovieQuotes(R.drawable.img_quote_6, "Here’s the truth, Will Smith did say this, but in which movie?", "Independence Day", "Bad Boys", "Men in Black", "The pursuit of Happyness", 2, true);
        Question question7 = new MovieQuotes(R.drawable.img_quote_7, "Which TV funny gal actually quipped this 1-liner?", "Ellen Degeneres", "Amy Phoeler", "Betty White", "Tina Fey", 3, true);
        Question question8 = new MovieQuotes(R.drawable.img_quote_8, "This mayor won’t get my vote — but did he actually give this piece of advice? And if not, who did?", "Forrest Gump, Forrest Gump", "Dorry, Finding Nemo", "Esther Williams", "The Mayor, Jaws", 1, true);
        Question question9 = new MovieQuotes(R.drawable.img_quote_9, "Her heart will go on, but whose heart is it?", "Whitney Houston", "Diana Ross", "Celine Dion", "Mariah Carey", 2, true);
        Question question10 = new MovieQuotes(R.drawable.img_quote_10, "He’s the king of something alright — to whom does this self-titling line belong to?", "Tony Montana, Scarface", "Joker, The Dark Knight", "Lex Luthor, Batman Vs Superman", "Jack, Titanic", 3, true);
        Question question11 = new MovieQuotes(R.drawable.img_quote_11, "Is “Grey” synonymous for “wise”? If so, maybe Gandalf did preach this advice. If not, who did?", "Yoda, Star Wars", "Gandalf the Grey, Lord of the Rings", "Dumbledore, Harry Potter", "Uncle Ben, Spider-Man", 0, true);
        Question question12 = new MovieQuotes(R.drawable.img_quote_12, "Houston, we have a problem with this quote — which space-traveler does this catch-phrase actually belong to?", "Han Solo, Star Wars", "Captain Kirk, Star Trek", "Buzz Lightyear, Toy Story", "Jim Lovell, Apollo 13", 2, true);
        Question question13 = new MovieQuotes(R.drawable.img_quote_0, "Who said To Be or Not To Be, that is the question.", "Jimminy Cricket", "Hamlet", "George Harrison", "Micheal Schummacher", 1, true);
        Question question14 = new RealQuotes(R.drawable.img_quote_1, "Who said \"My favourite is Hanky, Panky\"", "Michael McIntyre", "King Charles III", "Gary Chapman", "Bill Clinton", 0, true);


/*        AlertDialog.Builder incorrectAnswerDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
        incorrectAnswerDialogueBuilder.setCancelable(false);
        incorrectAnswerDialogueBuilder.setTitle("Drum Roll Please...");
        incorrectAnswerDialogueBuilder.setMessage("Sorry the correct answer was: " + correctAnswerDisplay);
        incorrectAnswerDialogueBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                questions.remove(getCurrentQuestion());

                if (questions.size() == 1) {
                    questionsRemainingTextView.setText("Question Remaining");
                }

                if (questions.size() > 0){

                    chooseNewQuestion();
                    displayQuestionsRemaining(questions.size());
                    // TODO: uncomment after implementing displayQuestion()
                    displayQuestion(getCurrentQuestion());
                } else {
                    gameOver();
                }


            }
        });
//            countDownTimer.stopTimer();
        incorrectAnswerDialogueBuilder.create().show();
*/


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
        questions.add(question13);
        questions.add(question14);
        ques

        totalCorrect = 0;


        if (selectedOption.equals("Real life")) {

            ArrayList<Question> realLifeQuotes = new ArrayList<>();


            for (Question q : questions) {
                if (q instanceof RealQuotes) {
                    realLifeQuotes.add(q);
                }

            }
            questions = realLifeQuotes;
        }


        else if (selectedOption.equals("Movie quotes")) {

            ArrayList<Question> movieQuotes = new ArrayList<>();

            for (Question q : questions) {
                if (q instanceof MovieQuotes) {
                    movieQuotes.add(q);
                }
            }
            questions = movieQuotes;

        } else {

        }






        chooseQuestionSet();
        totalQuestions = questions.size();
        questionsRemainingTextView.setText("Questions Remaining");



        Question firstQuestion = chooseNewQuestion();
// displayQuestion(firstQuestion);
// displayQuestionsRemaining(questions.size());

        // TODO 3-D.ii: Uncomment the line below after implementing displayQuestionsRemaining(int)
        displayQuestionsRemaining(questions.size());

        // TODO 3-H.ii: Uncomment after implementing displayQuestion(Question)
        displayQuestion(firstQuestion);


    }

    // TODO #4 add chooseNewQuestion() here

    public Question chooseNewQuestion() {
        int randomNumber = generateRandomNumber(questions.size());

        currentQuestionIndex = randomNumber;
        validAnswer = false;
        return questions.get(currentQuestionIndex);

    }

    // TODO #5 add getCurrentQuestion() here

    public Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }


    // TODO #6 add onAnswerSubmission() here
    public void onAnswerSubmission() {
        countDownTimer.stopTimer();
        if (getCurrentQuestion().isCorrect()) {
            totalCorrect++;
            System.out.println("That's correct");
            correctAnswer = true;


            AlertDialog.Builder correctAnswerDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
            correctAnswerDialogueBuilder.setCancelable(true);
            correctAnswerDialogueBuilder.setTitle("Drum Roll Please...");
            correctAnswerDialogueBuilder.setMessage("That's right!");
            correctAnswerDialogueBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    questions.remove(getCurrentQuestion());

                    if (questions.size() == 1) {
                        questionsRemainingTextView.setText("Question Remaining");
                    }

                    if (questions.size() > 0){

                    chooseNewQuestion();
                    displayQuestionsRemaining(questions.size());
                    // TODO: uncomment after implementing displayQuestion()
                    displayQuestion(getCurrentQuestion());
                    } else {
                        gameOver();
                    }
                }
            });
            correctAnswerDialogueBuilder.create().show();


        } else {
 //           System.out.println("Sorry the correct answer was: " + getCurrentQuestion().correctAnswer);

            switch (getCurrentQuestion().correctAnswer) {
                case 0:
                    correctAnswerDisplay = getCurrentQuestion().answer0;
                    break;
                case 1:
                    correctAnswerDisplay = getCurrentQuestion().answer1;
                    break;
                case 2:
                    correctAnswerDisplay = getCurrentQuestion().answer2;
                    break;
                case 3:
                    correctAnswerDisplay = getCurrentQuestion().answer3;
                    break;
                default:
                    correctAnswerDisplay = "One of the four you could have chosen, except the one you did ;)";
            }

            correctAnswer = false;

//            countDownTimer.stopTimer();
            AlertDialog.Builder incorrectAnswerDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
            incorrectAnswerDialogueBuilder.setCancelable(false);
            incorrectAnswerDialogueBuilder.setTitle("Drum Roll Please...");
            incorrectAnswerDialogueBuilder.setMessage("Sorry the correct answer was: " + correctAnswerDisplay);
            incorrectAnswerDialogueBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    questions.remove(getCurrentQuestion());

                    if (questions.size() == 1) {
                        questionsRemainingTextView.setText("Question Remaining");
                    }

                    if (questions.size() > 0){

                        chooseNewQuestion();
                        displayQuestionsRemaining(questions.size());
                        // TODO: uncomment after implementing displayQuestion()
                        displayQuestion(getCurrentQuestion());
                    } else {
                        gameOver();
                    }


                }
            });
//            countDownTimer.stopTimer();
            incorrectAnswerDialogueBuilder.create().show();


        }

        countDownTimer.stopTimer();

        }




    public void gameOver() {
        if (questions.size() == 0) {
            //    System.out.println(getGameOverMessage(totalCorrect, totalQuestions));
            questionsRemainingTextView.setText("Questions Remaining");
            countDownTimer.stopTimer();
            // TODO 5-D: Show a popup instead
            AlertDialog.Builder gameOverDialogueBuilder = new AlertDialog.Builder(MainActivity.this);
            gameOverDialogueBuilder.setCancelable(false);
            gameOverDialogueBuilder.setTitle("Game Over");
            gameOverDialogueBuilder.setMessage(getGameOverMessage(totalCorrect, totalQuestions));
            gameOverDialogueBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  // startNewGame();
                    chooseGameMode();
                }
            });
            displayQuestionsRemaining(questions.size());
            gameOverDialogueBuilder.create().show();



        }

    }


        public int generateRandomNumber ( int max){
            double randomNumber = Math.random();
            int randomIntNumber = (int) (randomNumber * max);
            //System.out.println(randomIntNumber);
            return randomIntNumber;
        }

        public String getGameOverMessage ( int totalCorrect, int totalQuestions){
            if (totalCorrect == totalQuestions) {
                return "You got all " + (totalQuestions) + " right! You won!";
            } else {
                return "You got " + totalCorrect + " correct out of " + totalQuestions + ". Better luck next time!";
            }
        }

    }


//The code above should pause the timer when onAnswerSubmission() is called but it doesnt. Any idea why?