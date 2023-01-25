package com.example.myandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

        edUsername =  findViewById(R.id.editTextUsername);
        edPassword = findViewById(R.id.editTextPassword);
        btn = findViewById(R.id.signInButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edUsername.getText().toString();
                String passWord = edPassword.getText().toString();

                if (userName.length() == 0 || passWord.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill all the data field", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}