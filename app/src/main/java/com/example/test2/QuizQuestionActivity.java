package com.example.test2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class QuizQuestionActivity extends AppCompatActivity {

    TextView Question;
    TextView Question_number;
    Button ButtonA;
    Button ButtonB;
    Button ButtonC;
    Button ButtonD;
    Button ButtonNext;
    ArrayList<String> option = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        final int quiz_id = Integer.parseInt(this.getIntent().getStringExtra("quiz_ID"));
        Question = (TextView) findViewById(R.id.quiz_question);
        Question_number = (TextView) findViewById(R.id.question_number);
        ButtonA = (Button) findViewById(R.id.buttonA);//get id of button
        ButtonB = (Button) findViewById(R.id.buttonB);//get id of button
        ButtonC = (Button) findViewById(R.id.buttonC);//get id of button
        ButtonD = (Button) findViewById(R.id.buttonD);//get id of button
        String question = "";

        final DBHandler db=new DBHandler(getApplicationContext());
        question = db.getQuizQuestion(quiz_id);
        option = db.getQuestionOption(quiz_id);

        Question.setText(question);
        Question_number.setText("Q" + Integer.toString(quiz_id));
        ButtonA.setText(option.get(0));
        ButtonB.setText(option.get(1));
        ButtonC.setText(option.get(2));
        ButtonD.setText(option.get(3));

        ButtonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option.get(0).equalsIgnoreCase(db.getQuizAnswer(quiz_id))){
                    messageShow(true);
                }
                else{
                    messageShow(false);
                }

            }
        });
        ButtonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option.get(1).equalsIgnoreCase(db.getQuizAnswer(quiz_id))){
                    messageShow(true);
                }
                else{
                    messageShow(false);
                }
            }
        });
        ButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option.get(2).equalsIgnoreCase(db.getQuizAnswer(quiz_id))){
                    messageShow(true);
                }
                else{
                    messageShow(false);
                }

            }
        });
        ButtonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option.get(3).equalsIgnoreCase(db.getQuizAnswer(quiz_id))){
                    messageShow(true);
                }
                else{
                    messageShow(false);
                }

            }
        });
}
    public void messageShow(boolean trueOrFalse){
        DBHandler db = new DBHandler(getApplicationContext());
        String isAttempted = db.checkIsAttempted(incre_quiz_ID()-1,null,'Q');

        //if it is not attempted, award XP value
        if(isAttempted.equalsIgnoreCase("0")){
            if(trueOrFalse==true){
                db.setIsAttempted(incre_quiz_ID()-1,null,'Q');
                db.updateXPvalue(5);
            }

        }
        final Dialog dialogCorrect = new Dialog(QuizQuestionActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.activity_question_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //set message
        TextView message = (TextView) dialogCorrect.findViewById(R.id.notification);
        if(trueOrFalse==true){
            message.setText("You has answered this question correctly!");
        }
        else
            message.setText("You has answered this question wrongly!");
        ButtonNext = (Button) dialogCorrect.findViewById(R.id.buttonNext);//get id of button

        ButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCorrect.dismiss();
                String quiz_ID = Integer.toString(incre_quiz_ID());
                if(quiz_ID.equals(11)){

                    Intent quiz_function = new Intent(QuizQuestionActivity.this, QuizListActivity.class);
                    quiz_function.putExtra("quiz_ID",quiz_ID);
                    startActivity(quiz_function);
                }
                else{
                    Intent intent = getIntent();
                    intent.putExtra("quiz_ID",quiz_ID);
                    finish();
                    startActivity(intent);
                }

            }
        });

    }
    public int incre_quiz_ID(){
        int new_quiz_ID = Integer.parseInt(this.getIntent().getStringExtra("quiz_ID"));
        new_quiz_ID++;
        return new_quiz_ID;
    }
}
