package com.example.myandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myandroidapplication.entity.Profile;

public class ProfileAddActivity extends AppCompatActivity {
    EditText edUsername, edPhone, edDivision, edDistrict, edArea, edAddress;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_add);

        edUsername = findViewById(R.id.editTextUsername);
        edPhone = findViewById(R.id.editTextPhone);
        edDivision = findViewById(R.id.editTextDivision);
        edDistrict = findViewById(R.id.editTextTextDistrict);
        edArea = findViewById(R.id.editTextArea);
        edAddress = findViewById(R.id.editTextAddress);
        addBtn = findViewById(R.id.profileAddButton);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String phone = edPhone.getText().toString();
                String division = edDivision.getText().toString();
                String district = edDistrict.getText().toString();
                String area = edArea.getText().toString();
                String address = edAddress.getText().toString();

                Profile profile = new Profile();
                profile.setUsername(username);
                profile.setPhone(phone);
                profile.setDivision(division);
                profile.setDistrict(district);
                profile.setArea(area);
                profile.setAddress(address);

                System.out.println(username + " " + phone + " " + division + " " + district+ " " + area+ " " + address);

                Database db = new Database(getApplicationContext(), "TO-LET", null, 1);
                if (username.length() == 0 || phone.length() == 0 || division.length() == 0 || district.length() == 0 || area.length() == 0 || address.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill the data field", Toast.LENGTH_SHORT).show();

                }else {
                    db.addProfile(profile);
                    Toast.makeText(getApplicationContext(), "Data inserted!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileAddActivity.this, ProfileActivity.class));
                }
            }
        });
    }
}