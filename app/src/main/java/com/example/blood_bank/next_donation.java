package com.example.blood_bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class next_donation extends AppCompatActivity {

    private Button stop_id;
    private FirebaseFirestore db;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_donation);

        stop_id=(Button)findViewById(R.id.stop_id);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name").trim();

        }

        stop_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                update();


            }
        });


    }
    public void update(){

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference complaintsRef = rootRef.collection("Info");


        complaintsRef.whereEqualTo("name", name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        complaintsRef.document(document.getId()).update("yes_no","No");
                    }
                }
            }
        });

    }
}