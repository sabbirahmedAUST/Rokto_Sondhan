package com.example.blood_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login<valid_name> extends AppCompatActivity {


    private EditText name_id;
    private EditText pass_id;
    private Button login_id;
    private Button button_back1;
    String valid_name;
    String password;
    String name;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_back1=(Button)findViewById(R.id.button_back1);
        button_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });


        name_id=(EditText)findViewById(R.id.name_id);
        pass_id=(EditText)findViewById(R.id.pass_id);
        login_id=(Button)findViewById(R.id.login_id);


        login_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                open_features();
                valid_name = name_id.getText().toString();
                match();

            }
        });

    }


    public void openMainActivity(){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }





    public void match(){
        db = FirebaseFirestore.getInstance();

        name = name_id.getText().toString();
        password = pass_id.getText().toString();

        db.collection("Info")
                .whereEqualTo("name",name)
                .whereEqualTo("password", password)

                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Toast.makeText(Login.this, "Working", Toast.LENGTH_SHORT).show();




                        if(queryDocumentSnapshots.isEmpty()){

                            Toast.makeText(Login.this, "Wrong Name & password", Toast.LENGTH_SHORT).show();



                        }
                        else
                        {


                            Toast.makeText(Login.this, "Logged", Toast.LENGTH_SHORT).show();
                            open_features();

                        }


                    }
                });
                        }



    public void open_features(){
        Intent intent = new Intent(Login.this, Features.class);
        intent.putExtra("name",valid_name);

        startActivity(intent);

    }


    }