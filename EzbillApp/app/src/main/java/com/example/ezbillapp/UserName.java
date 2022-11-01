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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserName extends AppCompatActivity {

    EditText user_name, address;
    Button save_button;

    String name_user, address1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);

        user_name = findViewById(R.id.group_name);

        save_button = findViewById(R.id.save_name_button);

        //skipping = findViewById(R.id.Skiping);

 //       skipping.setOnClickListener(new View.OnClickListener() {
//            @Override
  //          public void onClick(View view) {
 //               startActivity(new Intent(UserName.this, PhoneActivity.class));
 //           }
 //       });

        address = findViewById(R.id.address_name);

        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                name_user = user_name.getText().toString();

                address1 = address.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map<String, Object> Users = new HashMap<>();
                Users.put("name", name_user);
                Users.put("Address", address1);
                Users.put("Electricity", "");
                Users.put("Water", "");
                Users.put("Gas", "");

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                db.collection("Users").document(uid).set(Users).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserName.this, "Name Added Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UserName.this, PhoneActivity.class));
                        }
                    }
                });

            }
        });






    }


}