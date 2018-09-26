package com.tarakhan.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button;
    RelativeLayout relativeLayout;
    TextView SumtextView,resultTextview,pointsTextView,timerTextView;
    ArrayList<Integer> answers = new ArrayList();
    int locationofCorrectAnswer;
    int numOfQuestions = 0;
    Button b1,b2,b3,b4,playAgainButton;
    int score=0;

    public void generateQuestion()
    {
        Random random = new Random();
        b1 = findViewById(R.id.button0);
        b2 = findViewById(R.id.button1);
        b3 = findViewById(R.id.button2);
        b4 = findViewById(R.id.button3);
        int num1 = random.nextInt(21);
        int num2 = random.nextInt(21);
        SumtextView.setText(num1+" + "+num2);
        locationofCorrectAnswer = random.nextInt(4);
        answers.clear();
        int incorectAnswer;
        for (int i=0;i<4;i++)
        {
            if (i==locationofCorrectAnswer)
            {
                answers.add(num1+num2);
            }
            else {
                incorectAnswer=random.nextInt(41);
                while (incorectAnswer==num1+num2)
                {
                    incorectAnswer=random.nextInt(41);
                }
                answers.add(incorectAnswer);

            }
        }
        b1.setText(Integer.toString(answers.get(0)));
        b2.setText(Integer.toString(answers.get(1)));
        b3.setText(Integer.toString(answers.get(2)));
        b4.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View view)
    {
        score=0;
        numOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextview.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
        }

        @Override
        public void onFinish() {
            playAgainButton.setVisibility(View.VISIBLE);
            timerTextView.setText("0s");
            resultTextview.setText("Your scores are"+Integer.toString(score)+" / "+Integer.toString(numOfQuestions));

        }
    }.start();
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationofCorrectAnswer)))
        {
            score++;
            resultTextview.setText("Correct");

        }
        else {
            resultTextview.setText("Wrong");
        }
        numOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+" / "+Integer.toString(numOfQuestions));
        generateQuestion();

    }

    public  void goButton(View view)
    {
        button.setVisibility(view.INVISIBLE);
        relativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.GoButton);
        SumtextView = findViewById(R.id.SumtextView);
        resultTextview = findViewById(R.id.AnswertextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timer);
        playAgainButton = findViewById(R.id.playAgain);
        relativeLayout = findViewById(R.id.gamelayout);




    }
}
