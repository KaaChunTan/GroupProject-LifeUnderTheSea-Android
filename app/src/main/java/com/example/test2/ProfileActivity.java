package com.example.test2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.view.View.INVISIBLE;

public class ProfileActivity extends AppCompatActivity {
    TextView viewTextName;
    EditText editTextName;
    ConstraintLayout layout;
    TextView animalSummary;
    TextView quizSummary;
    TextView XPfield;
    Button medalButton;
    String image_path;
    ImageView profileView;

    private static final int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button uploadProfile = (Button) findViewById(R.id.profile_button);
        layout = (ConstraintLayout)findViewById(R.id.relativeLayout);
        viewTextName = (TextView)findViewById(R.id.username);
        editTextName = (EditText)findViewById(R.id.edit_name);
        animalSummary = (TextView)findViewById(R.id.animal_summary2);
        quizSummary = (TextView) findViewById(R.id.quiz_summary);
        medalButton = (Button) findViewById(R.id.medal_button);
        XPfield = (TextView) findViewById(R.id.xpfield);
        profileView = (ImageView) findViewById(R.id.imageView_profile);


        final DBHandler db = new DBHandler(getApplicationContext());
        ArrayList<String> userInfo = new ArrayList<>();
        ArrayList<String> summary = new ArrayList<>();

        //get user info from database
        userInfo = db.getUserInfo();
        final String name = userInfo.get(0);
        String xpValue = userInfo.get(1);

        //get summary from database
        summary = db.getSummary();
        String animal_summary_number = summary.get(0);
        String quiz_summary_number = summary.get(1);

        //set information that retrieved from database
        viewTextName.setText(name);
        XPfield.setText(xpValue + " XP");
        animalSummary.setText(animal_summary_number + " animal pictures has been captured.");
        quizSummary.setText(quiz_summary_number + " quiz questions has been attempted.");

        //level up
        if (Integer.parseInt(xpValue)>=50){
            medalButton.setText(Integer.toString(2));
        }

        //check whether changed picture or not
        if((userInfo.get(3)).equals("1")){
            File imgFile = new File(userInfo.get(2));
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Bitmap resized = Bitmap. createScaledBitmap ( myBitmap , 1000 , 1000 , true ) ;
            profileView.setImageBitmap(resized);
        }

        //bottom nav bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent main_function = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(main_function);
                        break;
                    case R.id.nav_profile:
                        //Intent profile_function = new Intent(ProfileActivity.this, ProfileActivity.class);
                        //startActivity(profile_function);
                        break;
                }
                return true;
            }
        });

        uploadProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_IMAGE_REQUEST);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        viewTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewTextName.setVisibility(INVISIBLE);
                editTextName.setVisibility(View.VISIBLE);
                ArrayList<String>userInfo = db.getUserInfo();
                editTextName.setText(userInfo.get(0));
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewTextName.getVisibility()==INVISIBLE) {
                    String edit_name = editTextName.getText().toString();
                    //viewTextName.setText(edit_name);
                    viewTextName.setVisibility(View.VISIBLE);
                    editTextName.setVisibility(INVISIBLE);
                    db.updateUsername(edit_name);
                    viewTextName.setText(edit_name);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                final DBHandler db = new DBHandler(getApplicationContext());

                //get image path form uri
                image_path = getRealPathFromURI(uri);
                //update picture
                db.updateUserPicture(image_path,true);
                File imgFile = new File(image_path);
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                Bitmap resized = Bitmap. createScaledBitmap ( myBitmap , 1000 , 1000 , true ) ;
                profileView.setImageBitmap(resized);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
                } else {
                    //inform if no give permission to access gallery
                    Toast.makeText(getApplicationContext(),"Unable to change profile image without permission!!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
