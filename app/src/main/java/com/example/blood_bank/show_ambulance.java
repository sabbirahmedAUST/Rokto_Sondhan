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

public class show_ambulance extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private AmbulanceAdapter adapter;
    private List<Ambulance> profileList;
    private ProgressBar progressBar;
    private EditText search_text;
    private Button search_button;
    private Button button_back8;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ambulance);
        button_back8=(Button)findViewById(R.id.button_back8);
        button_back8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeatures();
            }
        });

        search_text = (EditText) findViewById(R.id.search_text);
        search_button = (Button) findViewById(R.id.search_button);

        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileList = new ArrayList<>();
        adapter = new AmbulanceAdapter(this, profileList);
        recyclerView.setAdapter(adapter);


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                search();

            }
        });

    }

    public void openFeatures(){
        Intent intent =new Intent(this,Features.class);
        startActivity(intent);
    }


    public  void search(){

            String district=search_text.getText().toString().trim();

            db = FirebaseFirestore.getInstance();

            db.collection("Ambulance")
                    .whereEqualTo("district",district)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                            if(!queryDocumentSnapshots.isEmpty()){

                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                                for(DocumentSnapshot d : list){

                                    Ambulance p = d.toObject(Ambulance.class);
                                    profileList.add(p);

                                }

                                adapter.notifyDataSetChanged();

                            }


                        }
                    });
        }


}