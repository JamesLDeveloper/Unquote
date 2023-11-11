package com.example.unquote;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

//import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

        // TODO 4-E: set onClickListener for each answer Button

        // TODO 5-A: set onClickListener for the submit answer Button

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



    // TODO #1: add integer member variables here




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

        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);

        totalCorrect = 0;
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
int randomNumber = generateRandomNumber(totalQuestions-1);
        currentQuestionIndex = randomNumber;
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
        } else {
            System.out.println("Sorry the correct answer was" + getCurrentQuestion().correctAnswer);
        }
        questions.remove(getCurrentQuestion());
        // TODO 3-D.i: Uncomment the line below after implementing displayQuestionsRemaining(int)
         displayQuestionsRemaining(questions.size());
        if (questions.size() == 0) {
            System.out.println(getGameOverMessage(totalCorrect, totalQuestions));
            // TODO 5-D: Show a popup instead
            startNewGame();
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
        return "You got " + totalCorrect + "correct out of " + totalQuestions + ". Better luck next time!";
    }
}

}