package com.mycodingskills.nnaji_nkechi.geoquiz.controller;

/**
 * CONTROLLER / PRESENTER OBJECT
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mycodingskills.nnaji_nkechi.geoquiz.R;

import static android.widget.Toast.LENGTH_SHORT;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast toast=Toast.makeText(QuizActivity.this,R.string.incorrect_toast, LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
            }
        });

    }
}
