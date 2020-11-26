package com.example.loin_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView loin_im;
    EditText ed_ema,ed_pass;
    Button forget_bt,login_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loin_im=findViewById(R.id.login_im);
        ed_ema=findViewById(R.id.ed_email);
        ed_pass=findViewById(R.id.ed_pass);
        forget_bt=findViewById(R.id.forget_btn);
        login_bt=findViewById(R.id.login_btn);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(MainActivity.this,Register_page.class);
                startActivity(in);
            }
        });

    }
}