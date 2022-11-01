package com.example.ezbillapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AllGroups extends AppCompatActivity {

    String groupname = "Group Name";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public DocumentReference docRef1 = db.collection("Group").document("Group");

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_groups);


        textView = findViewById(R.id.text_view_data4);

        loadNote();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllGroups.this, ExistingGroup.class));
            }
        });


    }


    public void loadNote() {
        docRef1.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String UName = documentSnapshot.getString(groupname);
                            //String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            textView.setText("Group Name: " + UName + "\n");
                        } else {
                            Toast.makeText(AllGroups.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

}
