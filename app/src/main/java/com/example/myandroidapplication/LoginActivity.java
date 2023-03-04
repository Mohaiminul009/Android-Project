package com.example.myandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button loginBtn;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

        edUsername =  findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        loginBtn = findViewById(R.id.signInButton);
        tvRegister = findViewById(R.id.registerHereText);

        Database db = new Database(getApplicationContext(), "TO-LET", null, 1);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edUsername.getText().toString();
                String passWord = edPassword.getText().toString();

                if (userName.length() == 0 || passWord.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill all the data field", Toast.LENGTH_SHORT).show();
                }else {
                    if (db.login(userName, passWord)==1){
                        Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username", userName);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

//                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
//                it.putExtra("username", edUsername.getText().toString());
//                it.putExtra("password", edPassword.getText().toString());
//                startActivity(it);
            }
        });
    }
}