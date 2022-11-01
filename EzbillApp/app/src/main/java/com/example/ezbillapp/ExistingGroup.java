package com.example.ezbillapp;


import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ExistingGroup extends AppCompatActivity {


    String Name = "name";
    String Electricity = "Electricity";
    String Water = "Water";
    String Gas = "Gas";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public DocumentReference docRef1 = db.collection("Users").document("a59TKRxCUfV4NeXiIb1tW0DlNZB3");
    public DocumentReference docRef2 = db.collection("Users").document("Cp9cd1jaL2hdfHM9wDDBhWavdnx1");
    public DocumentReference docRef3 = db.collection("Users").document("rKC59hB66MfZv0AAUv5tiJgWnuQ2");

    TextView textViewData1, textViewData2, textViewData3;
    Button create_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_group);

        textViewData1 = findViewById(R.id.text_view_data1);
        textViewData2 = findViewById(R.id.text_view_data2);
        textViewData3 = findViewById(R.id.text_view_data3);


        loadNote();


    }

    public void loadNote() {
        docRef1.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String UName = documentSnapshot.getString(Name);
                            String Elec = documentSnapshot.getString(Electricity);
                            String Wate = documentSnapshot.getString(Water);
                            String Ga = documentSnapshot.getString(Gas);
                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            textViewData1.setText("Name: " + UName + "\n"+ "Electricity Usage: " + Elec + "\n" + "Water Usage: " + Wate + "\n" + "Gas: " + Ga);
                        } else {
                            Toast.makeText(ExistingGroup.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        docRef2.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            String UName = documentSnapshot.getString(Name);
                            String Elec = documentSnapshot.getString(Electricity);
                            String Wate = documentSnapshot.getString(Water);
                            String Ga = documentSnapshot.getString(Gas);

                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            textViewData2.setText("Name: " + UName + "\n"+ "Electricity Usage: " + Elec + "\n" + "Water Usage: " + Wate + "\n" + "Gas: " + Ga);
                        } else {
                            Toast.makeText(ExistingGroup.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        docRef3.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String UName = documentSnapshot.getString(Name);
                            String Elec = documentSnapshot.getString(Electricity);
                            String Wate = documentSnapshot.getString(Water);
                            String Ga = documentSnapshot.getString(Gas);
                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            textViewData3.setText("Name: " + UName + "\n"+ "Electricity Usage: " + Elec + "\n" + "Water Usage: " + Wate + "\n" + "Gas: " + Ga);

                        } else {
                            Toast.makeText(ExistingGroup.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ExistingGroup.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

}