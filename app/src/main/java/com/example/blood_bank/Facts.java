package com.example.blood_bank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.blood_bank.R.id.facts;

public class Facts extends AppCompatActivity {
    private Button button_back13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        button_back13=(Button)findViewById(R.id.button_back13);
        button_back13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeatures();
            }
        });
        findViewById(facts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicked_btn("https://www.redcrossblood.org/donate-blood/how-to-donate/eligibility-requirements/eligibility-criteria-alphabetical.html?fbclid=IwAR2ch_-qIja4oh97FKRoyBDjBXjkz9lom5PKj6bUn5epbtZqjMnc95XIXVw#:~:text=You%20must%20be%20at%20least,or%20limitations%20to%20your%20activities");
            }
        });

    }

    public void openFeatures(){
        Intent intent =new Intent(this,Features.class);
        startActivity(intent);
    }

    public void clicked_btn(String url)
    {
        Intent intent=new Intent (Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}