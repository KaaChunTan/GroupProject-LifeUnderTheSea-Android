package com.example.test2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    //import tensorflow trained model
    private static final String MODEL = "YHMN_cnn.tflite";
    private static final boolean QUANT = false;
    private static final String LABEL= "marine_labels.txt";
    private static final int INPUT_SIZE = 128;
    private Classifier classifier;
    private Executor executor = Executors.newSingleThreadExecutor();
    private TextView textViewResult;
    private ImageView imageView;
    private Button search;
    private Button info;
    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //nav bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent main_function = new Intent(ResultActivity.this, MainActivity.class);
                        startActivity(main_function);
                        break;
                    case R.id.nav_profile:
                        Intent profile_function = new Intent(ResultActivity.this, ProfileActivity.class);
                        startActivity(profile_function);
                        break;
                }
                return true;
            }
        });

        //check for camera permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(PERMISSION_CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{PERMISSION_CAMERA},0);
            }
            else
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0);
        }

        imageView =findViewById(R.id.imageView);
        textViewResult=findViewById(R.id.textView_description2);
        search=findViewById(R.id.img_btn);
        info=findViewById(R.id.info_btn);

        //search for another image
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0);
            }
        });





        loadmodel();

    }

    //check result of camera permission
    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //start camera activity after getting permission
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 0);
            } else {
                //return to main page after permission is denied
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(image);
            image= Bitmap.createScaledBitmap(image,INPUT_SIZE, INPUT_SIZE, false);


            final List<Classifier.Recognition> results = classifier.recognizeImage(image);
            textViewResult.setText(results.toString());

            //set isCaptured into true
            DBHandler db = new DBHandler(getApplicationContext());
            String isCaptured=db.checkIsAttempted(0,results.get(0).getTitle(),'I');

            if(isCaptured.equals("0")) {
                db.setIsAttempted(0, results.get(0).getTitle(), 'I');
                db.updateXPvalue(5);
            }

            //view fun fact of marine life
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent funFact = new Intent(ResultActivity.this, SearchResultActivity.class);
                    funFact.putExtra("animalName",results.get(0).getTitle());
                    startActivity(funFact);
                }
            });

        }

    }

    //load CNN model by calling create in image classification class
    private void loadmodel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = ImageClassification.create(getAssets(), MODEL, LABEL, INPUT_SIZE, QUANT);
                    //makeButtonVisible();
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }
}