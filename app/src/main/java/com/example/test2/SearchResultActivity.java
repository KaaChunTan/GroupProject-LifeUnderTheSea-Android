package com.example.test2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class SearchResultActivity extends AppCompatActivity {

    ImageView click_image_id;
    TextView funfact_title;
    String description = "";
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        DBHandler db = new DBHandler(getApplicationContext());
        funfact_title = findViewById(R.id.funfact_title);
        TextView descriptiontext=(TextView)findViewById(R.id.textView_description2);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        descriptiontext.setMovementMethod(new ScrollingMovementMethod());

        String searchResultName =  getIntent().getStringExtra("animalName").toLowerCase();

        if((!searchResultName.equals("jellyfish"))&&
                (!searchResultName.equals("turtle"))&&
                (!searchResultName.equals("crab"))&&
                (!searchResultName.equals("shark"))&&
                (!searchResultName.equals("seahorse"))){
            funfact_title.setText("Not found");
            click_image_id.setImageResource(R.drawable.not_found);
            descriptiontext.setText("OOPS! Not found in the database!");
        }
        else{
            description = db.getFunfact(searchResultName);
            if(searchResultName.equalsIgnoreCase("jellyfish")){
                funfact_title.setText("Jellyfish");
                Integer [] images = {R.drawable.jellyfish,R.drawable.jellyfish2,R.drawable.jellyfish3};
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,images);
                viewPager.setAdapter(viewPagerAdapter);
                descriptiontext.setText(description);
            }
            else if(searchResultName.equalsIgnoreCase("turtle")){
                funfact_title.setText("Turtle");
                Integer [] images = {R.drawable.turtle,R.drawable.turtle2,R.drawable.turtle3};
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,images);
                viewPager.setAdapter(viewPagerAdapter);
                descriptiontext.setText(description);
            }
            else if(searchResultName.equalsIgnoreCase("seahorse")){
                funfact_title.setText("Seahorse");
                Integer [] images = {R.drawable.seahorse,R.drawable.seahorse2,R.drawable.seahorse3};
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,images);
                viewPager.setAdapter(viewPagerAdapter);
                descriptiontext.setText(description);
            }
            else if(searchResultName.equalsIgnoreCase("shark")){
                funfact_title.setText("Shark");
                Integer [] images = {R.drawable.shark,R.drawable.shark2,R.drawable.shark3};
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,images);
                viewPager.setAdapter(viewPagerAdapter);
                descriptiontext.setText(description);
            }
            else if(searchResultName.equalsIgnoreCase("crab")){
                funfact_title.setText("Crab");
                Integer [] images = {R.drawable.crab,R.drawable.crab2,R.drawable.crab3};
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,images);
                viewPager.setAdapter(viewPagerAdapter);
                descriptiontext.setText(description);
            }
        }


        //nav bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent main_function = new Intent(SearchResultActivity.this, MainActivity.class);
                        startActivity(main_function);
                        break;
                    case R.id.nav_profile:
                        Intent profile_function = new Intent(SearchResultActivity.this, ProfileActivity.class);
                        startActivity(profile_function);
                        break;
                }
                return true;
            }
        });
    }


}

