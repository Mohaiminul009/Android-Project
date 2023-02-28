package com.example.myandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edName, edEmail, edUsername, edPassword;
    Button registerBtn;
    TextView accountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edName = findViewById(R.id.editTextRegisterName);
        edEmail = findViewById(R.id.editTextRegisterEmail);
        edUsername =  findViewById(R.id.editTextRegisterUsername);
        edPassword = findViewById(R.id.editTextRegisterPassword);
        registerBtn = findViewById(R.id.registerButton);
        accountText = findViewById(R.id.alreadyAccountText);

//        edUsername.setKeyListener(null);
//        edPassword.setKeyListener(null);
//
//        Intent it = getIntent();
//        String userName = it.getStringExtra("username");
//        String passWord = it.getStringExtra("password");
//
//        edUsername.setText(userName);
//        edPassword.setText(passWord);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String userEmail = edEmail.getText().toString();
                String userName = edUsername.getText().toString();
                String passWord = edPassword.getText().toString();

                System.err.println(name + " " + userEmail + " " + userName + " " + passWord);

                Database db = new Database(getApplicationContext(), "TO-LET", null, 1);
                if (name.length() == 0 || userEmail.length() == 0 || userName.length() == 0 || passWord.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill the data field", Toast.LENGTH_SHORT).show();

                }else {
                    if (passWord.length() >= 6){
                        db.addNewUser(name, userEmail, userName, passWord);
                        Toast.makeText(getApplicationContext(), "Data inserted!", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getApplicationContext(), "Password must be minimum 6 characters", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        accountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}