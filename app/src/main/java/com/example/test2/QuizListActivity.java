package com.example.test2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QuizListActivity extends AppCompatActivity {

    Button Quiz1;
    Button Quiz2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        final DBHandler db=new DBHandler(getApplicationContext());

        Quiz1 = (Button) findViewById(R.id.quiz_list1);
        Quiz2 = (Button) findViewById(R.id.quiz_list2);
        Quiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quiz_ID = "1" ;
                Intent question_function = new Intent(QuizListActivity.this, QuizQuestionActivity.class);
                question_function.putExtra("quiz_ID",quiz_ID);
                startActivity(question_function);
            }
        });

        if(db.checkIsAllAttempted()==true){
            Quiz2.setBackgroundResource(R.drawable.round_corner_quiz_list);
            Quiz2.setTextColor(Color.parseColor("#000000"));
        }

        //nav bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent main_function = new Intent(QuizListActivity.this, MainActivity.class);
                        startActivity(main_function);
                        break;
                    case R.id.nav_profile:
                        Intent profile_function = new Intent(QuizListActivity.this, ProfileActivity.class);
                        startActivity(profile_function);
                        break;
                }
                return true;
            }
        });
    }
}
