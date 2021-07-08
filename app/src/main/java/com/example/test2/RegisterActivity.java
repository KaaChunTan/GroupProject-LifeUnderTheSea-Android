package com.example.test2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    Intent next_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        next_activity=new Intent(this,MainActivity.class);
        final EditText name_field=(EditText)findViewById(R.id.Name);
        final EditText email_field=(EditText)findViewById(R.id.Email);
        final EditText password_field=(EditText)findViewById(R.id.Password);
        final EditText confirm_password_field=(EditText)findViewById(R.id.confirm_password);
        final SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
        final boolean isReg = sharedPreferences.getBoolean("ISLOGGEDIN",false);
        final Button registerbutton=(Button)findViewById(R.id.register);
        final DBHandler db = new DBHandler(getApplicationContext());

        if(isReg){
            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        }
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name_field.getText().toString();
                String email_id=email_field.getText().toString();
                String password_1=password_field.getText().toString();
                String password_2=confirm_password_field.getText().toString();

                if(validation(name,email_id,password_1,password_2)==true){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("ISLOGGEDIN",true).apply();
                    editor.commit();
                    db.insertUserInfo(name);
                    startActivity(next_activity);
                    Snackbar.make(registerbutton,"Register successfully!", Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

    public Boolean validation(String name,String email,String password,String confirmpass){
        Boolean valid=false;

        //notify length of userID and password must at least 6 characters
        if(name.length()<6&&password.length()<6){
            valid=false;
            Toast.makeText(RegisterActivity.this,"Username and password must contain at least 6 characters",Toast.LENGTH_LONG).show();
        }

        //notify either one field is empty
        else if(name.isEmpty()||email.isEmpty()||password.isEmpty()||confirmpass.isEmpty()){
            valid=false;
            Toast.makeText(RegisterActivity.this,"Please Enter username,email and password!",Toast.LENGTH_LONG).show();
        }

        //notify password not match with confirmation password
        else if (!password.equals(confirmpass)){
            valid=false;
            Toast.makeText(RegisterActivity.this,"Password are not same!",Toast.LENGTH_LONG).show();
        }

        //notify format of email is invalid
        else if(Patterns.EMAIL_ADDRESS.matcher(email).matches()==false){
            valid=false;
            Toast.makeText(RegisterActivity.this,"Please enter valid email address!",Toast.LENGTH_LONG).show();
        }
        else{
            valid=true;
        }

        return valid;
    }
}
