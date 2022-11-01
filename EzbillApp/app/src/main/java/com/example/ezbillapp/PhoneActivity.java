package com.example.ezbillapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class PhoneActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button logout, blue_button, group_btn;
    LinearLayout elec, water, gas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout);
       // button = findViewById(R.id.button);
        elec = findViewById(R.id.linearLayout);
        water = findViewById(R.id.linearLayout2);
        gas = findViewById(R.id.linearLayout3);
        blue_button = findViewById(R.id.blue_button);
        group_btn = findViewById(R.id.group_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PhoneActivity.this, LoginActivity.class));
                finish();
            }
        });

        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PhoneActivity.this, ElectricityActivity.class));

            }
        });

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PhoneActivity.this, WaterActivity.class));

            }
        });

        gas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PhoneActivity.this, GasActivity.class));

            }
        });


        blue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PhoneActivity.this, MainActivity.class));
            }
        });

        group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PhoneActivity.this, GroupActivity.class));
            }
        });


    }}