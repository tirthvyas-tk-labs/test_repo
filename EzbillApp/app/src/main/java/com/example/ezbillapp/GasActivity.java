package com.example.ezbillapp;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GasActivity extends AppCompatActivity {

    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public DocumentReference docRef = db.collection("Users").document(uid);

    TextView textViewData;
    String Name = "name";
    String elec = "Gas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        textViewData = findViewById(R.id.text_view_data);

        loadData();
        loadNote();
    }


    public void loadData(){
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();

                    //String title = documentSnapshot.getString(Name);
                    textViewData.setText(doc.getData().toString());
                }
            }
        });
    }


    public void loadNote() {
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String UName = documentSnapshot.getString(Name);
                            String elect = documentSnapshot.getString(elec);
                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            textViewData.setText("Name: " + UName + "\n"+ "Gas Usage: " + elect);
                        } else {
                            Toast.makeText(GasActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GasActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

}