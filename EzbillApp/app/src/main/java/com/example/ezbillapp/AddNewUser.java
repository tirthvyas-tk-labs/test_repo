package com.example.ezbillapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddNewUser extends AppCompatActivity {


    String Name = "name";





    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public DocumentReference docRef1 = db.collection("Users").document("a59TKRxCUfV4NeXiIb1tW0DlNZB3");
    public DocumentReference docRef2 = db.collection("Users").document("Cp9cd1jaL2hdfHM9wDDBhWavdnx1");
    public DocumentReference docRef3 = db.collection("Users").document("rKC59hB66MfZv0AAUv5tiJgWnuQ2");

    TextView textViewData;
    CheckBox checkBox1 , checkBox2, checkBox3;
    Button create_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        textViewData = findViewById(R.id.text_view_data);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);

        create_btn = findViewById(R.id.create_btn);
        loadNote();


        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AddNewUser.this, GroupActivity.class));
                Toast.makeText(AddNewUser.this, "Users Added To Group Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadNote() {
        docRef1.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String UName = documentSnapshot.getString(Name);
                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            checkBox1.setText(UName);
                        } else {
                            Toast.makeText(AddNewUser.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        docRef2.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String UName = documentSnapshot.getString(Name);
                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            checkBox2.setText(UName);
                        } else {
                            Toast.makeText(AddNewUser.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        docRef3.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String UName = documentSnapshot.getString(Name);
                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            checkBox3.setText(UName);

                        } else {
                            Toast.makeText(AddNewUser.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNewUser.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

}