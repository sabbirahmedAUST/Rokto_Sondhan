package com.example.blood_bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Create_account extends AppCompatActivity {

    private Button next_id;
    public EditText name_id,phone_id,pass_id;
    public Button blood_id;
    private Button button_back4;
    public RadioGroup radioGroup;

    public RadioButton select_radio_id;

    public String name,phone,yes_no,blood,password;

    StringBuilder string1B = new StringBuilder();
    StringBuilder string2B = new StringBuilder();



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        next_id=(Button)findViewById(R.id.next_id);
        name_id=(EditText)findViewById(R.id.name_id);
        phone_id=(EditText)findViewById(R.id.phone_id);
        blood_id=(Button)findViewById(R.id.blood_id);
        pass_id=(EditText) findViewById(R.id.pass_id);
        button_back4=(Button)findViewById(R.id.button_back4) ;

        radioGroup=(RadioGroup)findViewById(R.id.group_id);

        blood_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(Create_account.this, blood_id);

                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        StringBuilder string = new StringBuilder();
                        string1B.setLength(0);
                        string1B.append(item.getTitle());

                        blood_id.setText(string1B);

                        return true;
                    }
                });
                popupMenu.show();

            }
        });



        next_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=name_id.getText().toString();
                phone=phone_id.getText().toString();
                blood=string1B.toString().trim();
                password=pass_id.getText().toString();

                int select_id=radioGroup.getCheckedRadioButtonId();

                select_radio_id=(RadioButton)findViewById(select_id);
                yes_no=select_radio_id.getText().toString();



                if(!name.isEmpty()&& !yes_no.isEmpty()&& !phone.isEmpty() && !password.isEmpty()
                        && !blood.isEmpty() ){

                    open_create_account2();


                }else{


                    Toast.makeText(Create_account.this, "Fill Everything", Toast.LENGTH_SHORT).show();


                }


            }
        });

        button_back4=(Button)findViewById(R.id.button_back4);
        button_back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

    }

    public void openMainActivity(){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void open_create_account2(){





            Intent intent = new Intent(Create_account.this, Create_account2.class);

            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("blood", blood);
            intent.putExtra("yes_no", yes_no);
            intent.putExtra("password", password);

            startActivity(intent);
        }




}