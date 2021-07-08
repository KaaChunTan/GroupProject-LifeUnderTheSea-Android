package com.example.test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //nav bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent main_function = new Intent(SearchActivity.this, MainActivity.class);
                        startActivity(main_function);
                        break;
                    case R.id.nav_profile:
                        Intent profile_function = new Intent(SearchActivity.this, ProfileActivity.class);
                        startActivity(profile_function);
                        break;
                }
                return true;
            }
        });

        //search snimal---textview
        String[] animal = {"jellyfish", "crab", "turtle", "seahorse", "shark"};
        ArrayAdapter<String > adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,animal);
        final AutoCompleteTextView search_animal = (AutoCompleteTextView) findViewById(R.id.search_animal);
        search_animal.setThreshold(1);
        search_animal.setAdapter(adapter);

        //search animal---button
        Button search_button = (Button) findViewById(R.id.search_animal_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search_animalName = search_animal.getText().toString();
                if(search_animalName.equals("")){
                    Toast.makeText(SearchActivity.this,"Please enter a sea animal name!",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent search_result =  new Intent(SearchActivity.this,SearchResultActivity.class);
                    search_result.putExtra("animalName",search_animalName);
                    startActivity(search_result);
                }

            }
        });
    }
}
