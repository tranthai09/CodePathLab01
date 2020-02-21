package com.example.flashrev;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean isShowingAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.red));
                findViewById(R.id.flashcard_correctAnswer).setBackgroundColor(getResources().getColor(R.color.lightGreen));
            }
        });

        findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.red));
                findViewById(R.id.flashcard_correctAnswer).setBackgroundColor(getResources().getColor(R.color.lightGreen));
            }
        });

        findViewById(R.id.flashcard_correctAnswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_correctAnswer).setBackgroundColor(getResources().getColor(R.color.lightGreen));
            }
        });

        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowingAnswers){
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.open_eye);
                    findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_correctAnswer).setVisibility(View.INVISIBLE);
                }
                //isShowingAnswers = true;

            }
        });

        findViewById(R.id.toggle_choices_invisibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowingAnswers){
                    findViewById(R.id.toggle_choices_visibility).setVisibility(View.INVISIBLE);
                    ((ImageView) findViewById(R.id.toggle_choices_invisibility)).setImageResource(R.drawable.closed_eye);

                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_correctAnswer).setVisibility(View.VISIBLE);
                }


            }
        });





    }

}
