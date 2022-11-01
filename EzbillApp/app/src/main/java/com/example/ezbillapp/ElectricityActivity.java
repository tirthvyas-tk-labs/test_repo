package com.example.ezbillapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ElectricityActivity extends AppCompatActivity {


    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public DocumentReference docRef = db.collection("Users").document(uid);

    TextView tt_time;

    TextView textViewData;
    String Name = "name";
    String elec = "Electricity";
    Button save_button;
    EditText text_1;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);

        textViewData = findViewById(R.id.text_view_data);
        save_button = findViewById(R.id.save_button);
        text_1 = findViewById(R.id.editTextNumber);

        tt_time = (TextView) findViewById(R.id.elec_text);

        loadData();
        loadNote();



        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        data = intent.getStringExtra("message");
        // display the string into textView
        tt_time.setText(data);

       // tt_time.setText(getIntent().getStringExtra("message"));


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = text_1.getText().toString();
                CharSequence text = "Your limit has been set to: " + value;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });
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

                            textViewData.setText("Name: " + UName + "\n"+ "Electricity Usage: " + elect);
                        } else {
                            Toast.makeText(ElectricityActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ElectricityActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }


}