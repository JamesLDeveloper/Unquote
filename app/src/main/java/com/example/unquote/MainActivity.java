package com.example.unquote;

import android.os.Bundle;

//import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;

//import com.example.unquote.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

    // TODO #1: add integer member variables here

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;

    // TODO #2: add ArrayList member variable here

    ArrayList<Question> questions;

    // TODO #3 add startNewGame() here

    public void startNewGame(){
    }

    // TODO #4 add chooseNewQuestion() here

    // TODO #5 add getCurrentQuestion() here

    // TODO #6 add onAnswerSubmission() here

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