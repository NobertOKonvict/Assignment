package com.example.lulu.assignment;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class Login extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = (Button) findViewById(R.id.loginButton);

        username=findViewById(R.id.usernameEditText);
        password=findViewById(R.id.passwordEditText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {
                boolean whoLoggedIn = login(username.getText().toString(), password.getText().toString());
                if(!whoLoggedIn){
                    password.setError("Username and Password do not match!");
                }
                //Intent intent = new Intent(Login.this, MainActivity.class);
                //startActivity(intent);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean login(String username, String password){
        boolean loggedIn= false;
        if (Objects.equals("toyota",username)&&Objects.equals("toyota",password)){

            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.putExtra("user", "Toyota");
            startActivity(intent);

            loggedIn=true;
        } else if (Objects.equals("mercedes",username)&&Objects.equals("mercedes",password)){

            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            intent.putExtra("user","Mercedes");
            startActivity(intent);

            loggedIn=true;
        } else if (Objects.equals("subaru",username)&&Objects.equals("subaru",password)){

            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            intent.putExtra("user", "Subaru");
            startActivity(intent);

            loggedIn=true;
        }else{
            loggedIn = false;
        }return loggedIn;
    }
}
