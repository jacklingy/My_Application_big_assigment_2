package com.example.myapplication_big_assigment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity_test extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_test);

        Intent intent=getIntent();
        int id=intent.getIntExtra("me_id",0);
        textView=(TextView)findViewById(R.id.textView2);
        textView.setText(String.valueOf(id));

    }
}
