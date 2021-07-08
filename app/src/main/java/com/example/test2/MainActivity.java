package com.example.test2;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    // Define the button type variable
    Button camera_open_id;
    Button search_button;
    Button quiz_button;
    ProfileActivity profile;

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final String name=sharedPreferences.getString("NAME","DEFAULT_NAME");
        //TextView welcometext = (TextView) findViewById(R.id.textView_name);
        //welcometext.setText("Welcome" + name);

        // nav bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        //Intent main_function = new Intent(MainActivity.this, MainActivity.class);
                        //startActivity(main_function);
                        break;
                    case R.id.nav_profile:
                        Intent profile_function = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(profile_function);
                        break;
                }
                return true;
            }
        });

        camera_open_id = (Button)findViewById(R.id.button_camera); ;//get id of button 1
        // Camera_open button is for open the camera
        // and add the setOnClickListener in this button
        camera_open_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent image_recog = new Intent(MainActivity.this, ResultActivity.class);
                    startActivity(image_recog);
            }
        });

        search_button = (Button) findViewById(R.id.button_search);//get id of button 2


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search_function = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(search_function);
            }
        });

        quiz_button = (Button) findViewById(R.id.button_quiz);//get id of button 3

        quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quiz_function = new Intent(MainActivity.this, QuizListActivity.class);
                startActivity(quiz_function);
            }
        });
    }
}