package com.example.ezbillapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GroupActivity extends AppCompatActivity {

    Button new_button, exis_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);


        new_button = findViewById(R.id.new_button);

        exis_btn = findViewById(R.id.exis_button);

        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroupActivity.this, CreateGroupActivity.class));
            }
        });

        exis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroupActivity.this, AllGroups.class));
            }
        });
    }
}