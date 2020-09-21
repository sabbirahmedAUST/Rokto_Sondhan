package com.example.blood_bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Features extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //  private Button status_id,next_donation_id,facts_id;
    private ImageView search_id,status_id,next_donation_id,facts_id;

    private TextView drawer_name;
    String name;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");

        }


        count_blood();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TextView navUsername = (TextView) headerView.findViewById(R.id.drawer_name);
        navUsername.setText(name);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);
//        menu.findItem(R.id.nav_profile).setVisible(false);

        status_id=(ImageView)findViewById(R.id.status_id);
        search_id=(ImageView) findViewById(R.id.search_id);
        next_donation_id=(ImageView)findViewById(R.id.next_donation_id);
        facts_id=(ImageView)findViewById(R.id.facts_id);




        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Features.this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(Features.this);
//        navigationView.setCheckedItem(R.id.nav_home);

        status_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open_status();

            }
        });

        search_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open_search();

            }
        });

        next_donation_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name==null){

                    Toast.makeText(Features.this, "Login First", Toast.LENGTH_SHORT).show();

                }else {

                    Intent intent2 = new Intent(Features.this, next_donation.class);

                    intent2.putExtra("name", name);

                    startActivity(intent2);
                }

            }
        });
        facts_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open_Facts();

            }
        });





    }

    public void open_Facts(){

        Intent intent = new Intent(Features.this, Facts.class);

        startActivity(intent);


    }



    public void open_status(){

        Intent intent = new Intent(Features.this, Status_feed.class);

        startActivity(intent);


    }

    public void open_search(){

        Intent intent = new Intent(Features.this, Search.class);

        startActivity(intent);

    }
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
//            case R.id.nav_home:
//                break;
            case R.id.nav_bus:
                Intent intent = new Intent(Features.this,add_ambulance.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                Intent intent2 = new Intent(Features.this,Login.class);
                startActivity(intent2);
                break;
            case R.id.nav_ambulance:
                Intent intent3 = new Intent(Features.this,show_ambulance.class);
                startActivity(intent3);
                break;

            case R.id.nav_aboutus:
                Intent intent4 = new Intent(Features.this,About_us.class);
                startActivity(intent4);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }

    ArrayList<PieEntry> p=new ArrayList<>();

    PieChart pieChart;

    public void count_blood(){

        final int A_positive=0;


        pieChart=findViewById(R.id.chart_id);

        PieDataSet pieDataSet=new PieDataSet(p,"Blood");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        PieData pieData=new PieData((pieDataSet));


//        p.add(new PieEntry(22,"A+"));
//        p.add(new PieEntry(22,"O+"));
//        p.add(new PieEntry(33,"A-"));
//        p.add(new PieEntry(33,"B+"));
//        p.add(new PieEntry(22,"AB+"));








        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference complaintsRef = rootRef.collection("Info");


        complaintsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                QuerySnapshot count=task.getResult();

                p.clear();
                Map<String,Integer> s=new HashMap<>();

                for (QueryDocumentSnapshot document : task.getResult()) {

                    Log.d("DATA_rafat",document.get("blood").toString() );

                    if(s.get(document.get("blood").toString())==null){

                        s.put(document.get("blood").toString(),1);
                    }else{
                        s.put(document.get("blood").toString(),s.get(document.get("blood").toString())+1);
                    }



                }



                for (String document : s.keySet()) {

                    Log.d("RAFAT_DATA",document+"" );

                    Log.d("RAFAT_DATA",s.get(document)+"" );

                    p.add(new PieEntry(s.get(document),document));






                }
                PieDataSet pieDataSet=new PieDataSet(p,"Blood");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueLineColor(Color.BLACK);
                pieDataSet.setValueTextSize(16f);


                PieData pieData=new PieData((pieDataSet));
                pieChart.setData(pieData);
                pieChart.getDescription().setEnabled(false);
                pieChart.animate();


                pieChart.notifyDataSetChanged();
                pieChart.invalidate();



                Log.d("COUNT",count.size()+""  );

                if (task.isSuccessful()) {

                }



            }

        });

    }




}