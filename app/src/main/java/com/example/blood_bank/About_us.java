package com.example.blood_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About_us extends AppCompatActivity {
    private Button button_back14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        button_back14=(Button)findViewById(R.id.button_back14);
        button_back14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeatures();
            }
        });
    }
    public void openFeatures(){
        Intent intent =new Intent(this,Features.class);
        startActivity(intent);
    }
}