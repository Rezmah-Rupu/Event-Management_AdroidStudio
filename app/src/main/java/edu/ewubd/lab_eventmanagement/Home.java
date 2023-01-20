package edu.ewubd.lab_eventmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    TextView eventinfo, createlogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        eventinfo = findViewById(R.id.t1);
        eventinfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i1 = new Intent(Home.this,eventinfo.class);
                startActivity(i1);
                return;
            }
        });

        createlogin = findViewById(R.id.t2);
        createlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Home.this, signup.class);
                startActivity(i1);
                return;
            }
        });

    }
}
