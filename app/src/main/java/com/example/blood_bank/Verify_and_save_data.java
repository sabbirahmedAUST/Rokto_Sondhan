package com.example.blood_bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.concurrent.TimeUnit;

public class Verify_and_save_data extends AppCompatActivity {
    public String district,division,full_address;

    public String name,phone,yes_no,blood,password;

    private FirebaseAuth mAuth;

    public EditText code_id;
    String verificationId;

    boolean succes=false;

    private Button submit_id,send_otp_id,verify_id,home_id;


    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_and_save_data);

        db=FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();


        submit_id=(Button)findViewById(R.id.submit_id);
        home_id=(Button)findViewById(R.id.home_id);
        send_otp_id=(Button)findViewById(R.id.send_otp_id);
        verify_id=(Button)findViewById(R.id.verify_id);

        code_id=(EditText)findViewById(R.id.code_id);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            district = extras.getString("jela").trim();
            division = extras.getString("bivag").trim();
            full_address = extras.getString("thikana").trim();

            name=extras.getString("name").trim();
            phone=extras.getString("phone").trim();
            yes_no=extras.getString("yes_no").trim();
            blood=extras.getString("blood").trim();
            password=extras.getString("password").trim();

        }

        send_otp_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendVerificationCode(phone);

            }
        });

        submit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_data();
            }
        });

        home_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Verify_and_save_data.this, MainActivity.class);

                startActivity(intent);
            }
        });


        verify_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code=code_id.getText().toString().trim();
                if(code.isEmpty()){

                }else{
                    verifyCode(code);



                }

            }
        });





    }

    private void verifyCode(String code){

        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId,code);

        signInWithCredential(credential);


    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            succes=true;
                            Toast.makeText(Verify_and_save_data.this,"Verified",Toast.LENGTH_LONG).show();


                        }else {
                            Toast.makeText(Verify_and_save_data.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                1,
                TimeUnit.MINUTES,
                TaskExecutors.MAIN_THREAD,
                mCallBack

        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack=new
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId=s;

        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if(code !=null){

                verifyCode(code);

            }



        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
//            Toast.makeText(Verify_and_save_data.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };



    public void save_data(){

        if(succes==true){

//            Toast.makeText(Verify_and_save_data.this, "function", Toast.LENGTH_SHORT).show();


            CollectionReference db_info = db.collection("Info");

            Profile profile=new Profile(
                    name,
                    phone,
                    yes_no,
                    blood,
                    district,
                    division,
                    full_address,
                    password
            );

            db_info.add(profile)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(Verify_and_save_data.this, "Added To Firebase", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Verify_and_save_data.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });


        }



    }









}