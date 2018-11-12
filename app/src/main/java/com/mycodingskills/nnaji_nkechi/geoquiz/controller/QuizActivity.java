package com.mycodingskills.nnaji_nkechi.geoquiz.controller;

/**
 * CONTROLLER / PRESENTER OBJECT
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.mycodingskills.nnaji_nkechi.geoquiz.R;
import com.mycodingskills.nnaji_nkechi.geoquiz.model.Question;


public class QuizActivity extends AppCompatActivity {

    private static final String TAG ="QUIZ ACTIVITY" ;
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private static  final String KEY_INDEX ="index";
    private int mCurrentIndex = 0;
    public static final int REQUEST_CODE_CHEAT = 0;

    private Button mCheatButton;
    private boolean mIsCheater;

    //Create an array of Question objects
    private Question[] mQuestionBank = new Question[]{
            new Question (R.string.question_ocean, true),
            new Question (R.string.question_mideast, false),
            new Question (R.string.question_africa, false),
            new Question (R.string.question_americas, true),
            new Question (R.string.question_asia, true)
    };

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        Log.d(TAG, "Updating question text", new Exception());
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuestionTextView=findViewById (R.id.question_text_view);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        if(savedInstanceState !=null)
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }


        mTrueButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });

        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

        mNextButton = findViewById(R.id.next_button);

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
                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });


        mPreviousButton=findViewById(R.id.previous_button);


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
        if(mIsCheater){
            messageResId = R.string.judgment_toast;
        }
        else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            if(data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);

        }
    }
}
