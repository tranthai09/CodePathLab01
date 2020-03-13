package com.example.flashrev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.editQuestion)).getText().toString();
                String answer = ((EditText) findViewById(R.id.editAnswer)).getText().toString();
                String wrong_answer1 = ((EditText) findViewById(R.id.Template_wrongAns1)).getText().toString();
                String wrong_answer2 = ((EditText) findViewById(R.id.Template_wrongAns2)).getText().toString();

                /*if (question.isEmpty() || answer.isEmpty() || wrong_answer1.isEmpty() || wrong_answer2.isEmpty()) {
                    Snackbar.make(findViewById(R.id.flashcard_question),
                            "Must enter both question and answer!",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                else {
                    Snackbar.make(findViewById(R.id.flashcard_question),
                            "Not working!",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }*/
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("string1", question); // puts one string into the Intent, with the key as 'string1'
                data.putExtra("string2", answer); // puts another string into the Intent, with the key as 'string2
                data.putExtra("string3", wrong_answer1); // puts another wrong answer string into the Intent, with the key as 'string2
                data.putExtra("string4", wrong_answer2); // puts another wrong answer string into the Intent, with the key as 'string2
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity



            }
        });
        String q = getIntent().getStringExtra("stringKey1"); // this string will be 'harry potter`
        String a1 = getIntent().getStringExtra("stringKey2"); // this string will be 'voldemort'
        String a2 = getIntent().getStringExtra("stringKey3"); // this string will be 'voldemort'
        String a3 = getIntent().getStringExtra("stringKey4"); // this string will be 'voldemort'
        ((TextView) findViewById(R.id.editQuestion)).setText((q));
        ((TextView) findViewById(R.id.editAnswer)).setText((a1));
        ((TextView) findViewById(R.id.Template_wrongAns1)).setText((a2));
        ((TextView) findViewById(R.id.Template_wrongAns2)).setText((a3));


    }


}
