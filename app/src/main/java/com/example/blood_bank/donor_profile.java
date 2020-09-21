package com.example.blood_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class donor_profile extends AppCompatActivity {

    private TextView phone_id,blood_id,district_id,division_id,name_id,address_id;

    String name,blood,district,division,address,phone;
    private Button button_back12;
    private Button call_id,msg_id,notification_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);

        button_back12=(Button)findViewById(R.id.button_back12);
        button_back12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeatures();
            }
        });


        phone_id=(TextView)findViewById(R.id.phone_id);
        blood_id=(TextView)findViewById(R.id.blood_id);
        district_id=(TextView)findViewById(R.id.district_id);
        division_id=(TextView)findViewById(R.id.division_id);
        name_id=(TextView)findViewById(R.id.name_id);
        address_id=(TextView)findViewById(R.id.address_id);
        call_id=(Button) findViewById(R.id.call_id);





        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            blood = extras.getString("blood");
            phone = extras.getString("phone");
            district = extras.getString("district");
            division = extras.getString("division");
            address = extras.getString("address");


        }

        name_id.setText(name);
        blood_id.setText(blood);
        district_id.setText(district);
        division_id.setText(division);
        address_id.setText(address);
        phone_id.setText(phone);

        call_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });







    }
    public void openFeatures(){
        Intent intent =new Intent(this,Features.class);
        startActivity(intent);
    }
}