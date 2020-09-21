package com.example.blood_bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class add_ambulance extends AppCompatActivity {


    private EditText driver_name_id,phone_id,district_id;
    private Button save_id;
    private Button button_back6;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ambulance);
        button_back6=(Button)findViewById(R.id.button_back6);
        button_back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeatures();
            }
        });

        db= FirebaseFirestore.getInstance();
        driver_name_id=(EditText) findViewById(R.id.driver_name_id);
        phone_id=(EditText)findViewById(R.id.phone_id);
        district_id=(EditText)findViewById(R.id.district_id);

        save_id=(Button) findViewById(R.id.save_id);

        save_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });




    }
    public void openFeatures(){
        Intent intent =new Intent(this,Features.class);
        startActivity(intent);
    }


    public void save(){

        String name=driver_name_id.getText().toString().trim();
        String phone=phone_id.getText().toString().trim();
        String district=district_id.getText().toString().trim();

        CollectionReference db_info = db.collection("Ambulance");

        Ambulance ambulance=new Ambulance(
                name,
                phone,
                district
        );

        db_info.add(ambulance)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(add_ambulance.this, "Added To Firebase", Toast.LENGTH_SHORT).show();


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(add_ambulance.this, e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }


}
