package com.example.flashrev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isShowingAnswers = false;

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

        //toggle back and forth to show and hide answer choices
        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowingAnswers){
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.open_eye);
                    findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_correctAnswer).setVisibility(View.INVISIBLE);
                    isShowingAnswers = true;
                }
                else {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.closed_eye);
                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
                    findViewById(R.id.flashcard_correctAnswer).setVisibility(View.VISIBLE);
                    isShowingAnswers = false;
                }


            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                //MainActivity.this.startActivity(intent);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView q = findViewById(R.id.flashcard_question);
                String question = q.getText().toString();
                TextView a = findViewById(R.id.flashcard_answer);
                String answer = a.getText().toString();

                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                //MainActivity.this.startActivity(intent);
                intent.putExtra("stringKey1", question);
                intent.putExtra("stringKey2", answer);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String question = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("string2");

            ((TextView) findViewById(R.id.flashcard_question)).setText((question));
            ((TextView) findViewById(R.id.flashcard_answer)).setText((answer));
        }*/

        /*if (data != null) {
            String question = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("string2");

            ((TextView) findViewById(R.id.flashcard_question)).setText((question));
            ((TextView) findViewById(R.id.flashcard_answer)).setText((answer));
        }*/

        if (resultCode == RESULT_OK) {

            String question = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("string2");

            ((TextView) findViewById(R.id.flashcard_question)).setText((question));
            ((TextView) findViewById(R.id.flashcard_answer)).setText((answer));
        }



    }

}
