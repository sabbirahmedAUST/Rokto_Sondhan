package com.example.blood_bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements View.OnClickListener {

    private Button searchbutton;
    private Button button_back9;

    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private List<Profile> profileList;
    private ProgressBar progressBar;
    private String searchblood;
    EditText Editsearchblood;
    boolean a = false;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        button_back9=(Button)findViewById(R.id.button_back9);
        button_back9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeatures();
            }
        });


        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        findViewById(R.id.search_button).setOnClickListener(this);
        profileList = new ArrayList<>();
        adapter = new ProfileAdapter(this, profileList);

        recyclerView.setAdapter(adapter);


        db = FirebaseFirestore.getInstance();


    }

    public void openFeatures(){
        Intent intent =new Intent(this,Features.class);
        startActivity(intent);
    }


    void search(){



        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        profileList = new ArrayList<>();
        adapter = new ProfileAdapter(this, profileList);

        recyclerView.setAdapter(adapter);

        Editsearchblood=(EditText) findViewById(R.id.search_blood);
        searchblood=Editsearchblood.getText().toString().toUpperCase().trim();

        db.collection("Info")
                .whereEqualTo("blood",searchblood)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d : list){

                                Profile p = d.toObject(Profile.class);
                                profileList.add(p);

                            }

                            adapter.notifyDataSetChanged();
                            a=true;
                        }


                    }
                });

    }


    void search2(){



        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        profileList = new ArrayList<>();
        adapter = new ProfileAdapter(this, profileList);

        recyclerView.setAdapter(adapter);

        Editsearchblood=(EditText) findViewById(R.id.search_blood);
        searchblood=Editsearchblood.getText().toString().trim();

        db.collection("Info")
                .whereEqualTo("address",searchblood)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d : list){

                                Profile p = d.toObject(Profile.class);
                                profileList.add(p);


                            }

                            adapter.notifyDataSetChanged();



                        }


                    }
                });
    }




    public void onClick(View view){
        switch(view.getId()){
            case R.id.search_button:
                search();
                if(a==true){
                    break;
                }
                else {
                    search2();
                    break;
                }
        }
    }
}

