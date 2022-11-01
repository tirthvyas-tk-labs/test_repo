package com.example.ezbillapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateGroupActivity extends AppCompatActivity {



    EditText group_name;
    Button save_button;

    String groupname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        group_name = findViewById(R.id.group_name);

        save_button = findViewById(R.id.save_name_button);


        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                groupname = group_name.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> Groups = new HashMap<>();
                Groups.put("Group Name", groupname);
                //Groups.put("Electricity", "");
               // Groups.put("Water", "");
               // Groups.put("Gas", "");


                db.collection("Group").document("Group").set(Groups).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateGroupActivity.this, "Group Created Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateGroupActivity.this, AddNewUser.class));
                        }
                    }
                });

            }
        });






    }


}