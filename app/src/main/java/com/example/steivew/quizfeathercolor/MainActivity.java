package com.example.steivew.quizfeathercolor;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* Global variables */

    int score;
    boolean correctQ1, correctQ2, correctQ3, correctQ4;
    CheckBox yellowBox, redBox, blueBox, brownBox;
    ImageView img1, img2, img3, img4;
    RadioButton redQ1, yellowQ1, greenQ1, purpleQ1, yellowQ2, orangeQ2, brownQ2, blueQ2;
    String name = "";

    /* Initialize main screen, binding xml IDs with global variables */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yellowBox = (CheckBox) findViewById(R.id.checkbox_yellow_q3);
        redBox = (CheckBox) findViewById(R.id.checkbox_red_q3);
        blueBox = (CheckBox) findViewById(R.id.checkbox_blue_q3);
        brownBox = (CheckBox) findViewById(R.id.checkbox_brown_q3);
        img1 = (ImageView) findViewById(R.id.mark_up_q1);
        img2 = (ImageView) findViewById(R.id.mark_up_q2);
        img3 = (ImageView) findViewById(R.id.mark_up_q3);
        img4 = (ImageView) findViewById(R.id.mark_up_q4);
        redQ1 = (RadioButton) findViewById(R.id.radio_button_red_q1);
        yellowQ1 = (RadioButton) findViewById(R.id.radio_button_yellow_q1);
        greenQ1 = (RadioButton) findViewById(R.id.radio_button_green_q1);
        purpleQ1 = (RadioButton) findViewById(R.id.radio_button_purple_q1);
        orangeQ2 = (RadioButton) findViewById(R.id.radio_button_orange_q2);
        brownQ2 = (RadioButton) findViewById(R.id.radio_button_brown_q2);
        yellowQ2 = (RadioButton) findViewById(R.id.radio_button_yellow_q2);
        blueQ2 = (RadioButton) findViewById(R.id.radio_button_blue_q2);
    }

    /* Methods to find out user's input and assign values to variables */

    public void getUserName() {
        TextView user = (TextView) findViewById(R.id.name_field);
        name = " " + user.getText().toString() + "!";
    }

    public void onRadioButtonClickQ1() {
        RadioButton correctAnswer = (RadioButton) findViewById(R.id.radio_button_red_q1);
        if (correctAnswer.isChecked()) {
            correctQ1 = true;
            score++;
        } else {
            correctQ1 = false;
        }
    }

    public void onRadioButtonClickQ2() {
        RadioButton correctAnswer = (RadioButton) findViewById(R.id.radio_button_blue_q2);
        if (correctAnswer.isChecked()) {
            correctQ2 = true;
            score++;
        } else {
            correctQ2 = false;
        }
    }

    public void onCheckBoxesStatesQ3() {
        boolean yellowState = yellowBox.isChecked();
        boolean redState = redBox.isChecked();
        boolean blueState = blueBox.isChecked();
        boolean brownState = brownBox.isChecked();

        if (yellowState && !redState && blueState && brownState) {
            score++;
            correctQ3 = true;
        } else {
            correctQ3 = false;
        }

    }

    public void checkUserInputQ4() {
        TextView userInput = (TextView) findViewById(R.id.input_field_q4);
        String input = userInput.getText().toString().toLowerCase();
        String answer;
        answer = getString(R.string.blue).toLowerCase();
        if (input.equals(answer)) {
            score++;
            correctQ4 = true;
        } else {
            correctQ4 = false;
        }
    }

    /* Method to display a 'tick' for each correct answer, and a 'cross' for incorrect answer */

    public void markUp() {
        if (correctQ1) {
            img1.setImageResource(R.drawable.correct);
        } else {
            img1.setImageResource(R.drawable.incorrect);
        }
        if (correctQ2) {
            img2.setImageResource(R.drawable.correct);
        } else {
            img2.setImageResource(R.drawable.incorrect);
        }
        if (correctQ3) {
            img3.setImageResource(R.drawable.correct);
        } else {
            img3.setImageResource(R.drawable.incorrect);
        }
        if (correctQ4) {
            img4.setImageResource(R.drawable.correct);
        } else {
            img4.setImageResource(R.drawable.incorrect);
        }
    }

    /* Method to prepare a toast message base on different scores */

    public String burnToast() {
        String toastMessage = "";
        String greeting = getString(R.string.greeting);
        String youScore = "\n" + getString(R.string.you_score) + " ";

        if (score == 0)
            toastMessage = greeting + name + youScore + score + " " + getString(R.string.zero_out_of_4);
        if (score == 1)
            toastMessage = greeting + name + youScore + score + " " + getString(R.string.one_out_of_4);
        if (score == 2)
            toastMessage = greeting + name + youScore + score + " " + getString(R.string.two_out_of_4);
        if (score == 3)
            toastMessage = greeting + name + youScore + score + " " + getString(R.string.three_out_of_4);
        if (score == 4)
            toastMessage = greeting + name + youScore + score + " " + getString(R.string.four_out_of_4);

        return toastMessage;
    }

    /* Method triggered by the click event of VIEW SCORE button */

    public void viewScore(View view) {

        int duration = 5000; // make toast message duration variable

        /* Functions calling  other methods, and as a result global variables are updated */

        getUserName();
        onRadioButtonClickQ1();
        onRadioButtonClickQ2();
        onCheckBoxesStatesQ3();
        checkUserInputQ4();
        markUp();

        /* Toast message */

        String toastMessage = burnToast();

        final Toast myToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                myToast.show();
            }

            @Override
            public void onFinish() {
                myToast.cancel();
            }
        };

        myToast.show();
        toastCountDown.start();

        /* Reset score */

        score = 0;
    }
}
