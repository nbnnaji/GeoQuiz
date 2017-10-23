package com.mycodingskills.nnaji_nkechi.geoquiz.controller;

/**
 * CONTROLLER / PRESENTER OBJECT
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mycodingskills.nnaji_nkechi.geoquiz.R;
import com.mycodingskills.nnaji_nkechi.geoquiz.model.Question;

import static android.widget.Toast.LENGTH_SHORT;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;


    //Create an array of Question objects
    private Question[] mQuestionBank = new Question[]{
            new Question (R.string.question_ocean, true),
            new Question (R.string.question_mideast, false),
            new Question (R.string.question_africa, false),
            new Question (R.string.question_americas, true),
            new Question (R.string.question_asia, true)
    };
    private int mCurrentIndex = 0;

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuestionTextView=(TextView)findViewById (R.id.question_text_view);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);


        mTrueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
//                Toast toast=Toast.makeText(QuizActivity.this,R.string.incorrect_toast, LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP,0,0);
//                toast.show();

                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
//                Toast toast=Toast.makeText(QuizActivity.this,R.string.incorrect_toast, LENGTH_SHORT);
//                toast.setGravity(Gravity.TOP,0,0);
//                toast.show();

                checkAnswer(false);
            }
        });

        mNextButton=(ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * mCurrentIndex = 0, mQuestionBank.length = 5
                 * 0 + 1 = 1
                 * So, 1%5 = 1 which is "The Suez Canal connects the Red Sea and the Indian Ocean."
                 *
                 */
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });


        mPreviousButton=(ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /**
                 * mCurrentIndex = 0, mQuestionBank.length = 5
                 * 0 + 1 = 1
                 * So, 1%5 = 1 which is "The Suez Canal connects the Red Sea and the Indian Ocean."
                 *
                 */
                if (mCurrentIndex >0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }
                else {
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                updateQuestion();
            }
        });


        updateQuestion();
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId= R.string.correct_toast;
        }
        else{
            messageResId= R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
