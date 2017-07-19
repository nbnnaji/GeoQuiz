package com.mycodingskills.nnaji_nkechi.geoquiz.model;

/**
 * Created by Nnaji-MacPro1 on 7/14/17.
 */
/**
 * MODEL OBJECT
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId, boolean answerTrue){

    }
}
