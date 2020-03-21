package com.example.flashrev;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.plattysoft.leonids.ParticleSystem;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isShowingAnswers = false;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    Flashcard cardToEdit;

    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());

        }

        countDownTimer = new CountDownTimer(16000, 1000) {
            public long time;
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished / 1000;
                ((TextView) findViewById(R.id.timer)).setText("" + time);


            }

            public void onFinish() {
                this.start();
                if (this.time == 0){
                    this.cancel();
                }

            }

        };
        countDownTimer.start();

        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            // returns a random number between minNumber and maxNumber, inclusive.
            // for example, if i called getRandomNumber(1, 3), there's an equal chance of it returning either 1, 2, or 3.
            public int getRandomNumber(int minNumber, int maxNumber) {
                Random rand = new Random();
                return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
            }

            @Override
            public void onClick(View v) {
                currentCardDisplayedIndex = getRandomNumber(0,currentCardDisplayedIndex);
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }



                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);
                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // set the question and answer TextViews with data from the database
                        ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        //((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(getRandomNumber(1,3)).getQuestion());
                        //((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(getRandomNumber(1,3)).getAnswer());
                        findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                        findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                        // this method is called when the animation is finished playing
                        findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });
                countDownTimer.start();
            }

        });

        findViewById(R.id.trashBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    //((TextView) findViewById(R.id.flashcard_question)).setText("Make a card!");
                    //((TextView) findViewById(R.id.flashcard_answer)).setText("Make an Answer!");
                    currentCardDisplayedIndex = 0;
                }

                if (currentCardDisplayedIndex == 0){
                    ((TextView) findViewById(R.id.flashcard_question)).setText("Make a Card");
                    ((TextView) findViewById(R.id.flashcard_answer)).setText("Make an Answer!");
                }
                else {
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                }

            }
        });

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                final View questionSideView = findViewById(R.id.flashcard_question);
                View answerSideView = findViewById(R.id.flashcard_answer);

                /*// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                // hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);



                anim.setDuration(500);
                anim.start();*/

                questionSideView.animate()
                        .rotationY(90)
                        .setDuration(200)
                        .start();
                questionSideView.animate()
                        .rotationY(90)
                        .setDuration(200)
                        .withEndAction(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        questionSideView.setVisibility(View.INVISIBLE);
                                        findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                                        // second quarter turn
                                        findViewById(R.id.flashcard_answer).setRotationY(-90);
                                        findViewById(R.id.flashcard_answer).animate()
                                                .rotationY(0)
                                                .setDuration(200)
                                                .start();
                                    }
                                }
                        ).start();
                findViewById(R.id.flashcard_question).setCameraDistance(25000);
                findViewById(R.id.flashcard_answer).setCameraDistance(25000);
            }
        });

        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View questionSideView = findViewById(R.id.flashcard_question);
                final View answerSideView = findViewById(R.id.flashcard_answer);

                /*// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                // hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);



                anim.setDuration(500);
                anim.start();*/

                answerSideView.animate()
                        .rotationY(90)
                        .setDuration(200)
                        .start();
                answerSideView.animate()
                        .rotationY(90)
                        .setDuration(200)
                        .withEndAction(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        answerSideView.setVisibility(View.INVISIBLE);
                                        findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                                        // second quarter turn
                                        findViewById(R.id.flashcard_question).setRotationY(-90);
                                        findViewById(R.id.flashcard_question).animate()
                                                .rotationY(0)
                                                .setDuration(200)
                                                .start();
                                    }
                                }
                        ).start();
                findViewById(R.id.flashcard_question).setCameraDistance(25000);
                findViewById(R.id.flashcard_answer).setCameraDistance(25000);

                //findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                //findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.red));
            }
        });

        findViewById(R.id.flashcard_correctAnswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_correctAnswer).setBackgroundColor(getResources().getColor(R.color.lightGreen));
                new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                        .setSpeedRange(0.2f, 0.5f)
                        .oneShot(findViewById(R.id.flashcard_correctAnswer), 100);
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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.editBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //allFlashcards = flashcardDatabase.getAllCards();
                TextView q = findViewById(R.id.flashcard_question);
                String question = q.getText().toString();
                TextView a = findViewById(R.id.flashcard_answer);
                String answer = a.getText().toString();
                TextView a2 = findViewById(R.id.flashcard_answer1);
                String answer2 = a2.getText().toString();
                TextView a3 = findViewById(R.id.flashcard_answer2);
                String answer3 = a3.getText().toString();


                //Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                Intent myIntent = new Intent(MainActivity.this, AddCardActivity.class);
                //MainActivity.this.startActivity(intent);
                myIntent.putExtra("stringKey1", question);
                myIntent.putExtra("stringKey2", answer);
                myIntent.putExtra("stringKey3", answer2);
                myIntent.putExtra("stringKey4", answer3);
                //MainActivity.this.startActivityForResult(intent, 100);

                MainActivity.this.startActivityForResult(myIntent, 200);

                for (int i = 0; allFlashcards.get(i).equals(q); i++){
                    cardToEdit.setQuestion(question);
                }
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




        if (resultCode == RESULT_OK || resultCode == 100) {
            String question = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("string2");
            String w_answer1 = data.getExtras().getString("string3");
            String w_answer2 = data.getExtras().getString("string4");

            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Card successfully created!",
                    Snackbar.LENGTH_SHORT)
                    .show();

            ((TextView) findViewById(R.id.flashcard_question)).setText((question));
            ((TextView) findViewById(R.id.flashcard_answer)).setText((answer));
            ((TextView) findViewById(R.id.flashcard_answer1)).setText((w_answer1));
            ((TextView) findViewById(R.id.flashcard_answer2)).setText((w_answer2));
            ((TextView) findViewById(R.id.flashcard_correctAnswer)).setText((answer));

            flashcardDatabase.insertCard(new Flashcard(question, answer));

        }

        else if (resultCode == RESULT_OK || resultCode == 200) {
            String question = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String answer = data.getExtras().getString("string2");
            String w_answer1 = data.getExtras().getString("string3");
            String w_answer2 = data.getExtras().getString("string4");

            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Card successfully created!",
                    Snackbar.LENGTH_SHORT)
                    .show();

            ((TextView) findViewById(R.id.flashcard_question)).setText((question));
            ((TextView) findViewById(R.id.flashcard_answer)).setText((answer));
            ((TextView) findViewById(R.id.flashcard_answer1)).setText((w_answer1));
            ((TextView) findViewById(R.id.flashcard_answer2)).setText((w_answer2));
            ((TextView) findViewById(R.id.flashcard_correctAnswer)).setText((answer));

            cardToEdit.setQuestion(question);
            cardToEdit.setAnswer(answer);

            flashcardDatabase.updateCard(cardToEdit);

        }


        }

}
