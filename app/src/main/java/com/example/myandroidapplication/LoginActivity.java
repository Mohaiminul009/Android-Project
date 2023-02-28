package com.example.myandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btn;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

        edUsername =  findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.signInButton);
        tvRegister = findViewById(R.id.registerHereText);
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

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                it.putExtra("username", edUsername.getText().toString());
                it.putExtra("password", edPassword.getText().toString());
                startActivity(it);
            }
        });
    }
}