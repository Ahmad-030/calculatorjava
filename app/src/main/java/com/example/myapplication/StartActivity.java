package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startBtn = findViewById(R.id.startBtn);
        Button historyBtn = findViewById(R.id.historyBtn);
        Button aboutBtn = findViewById(R.id.aboutBtn);

        startBtn.setOnClickListener(v ->
                startActivity(new Intent(StartActivity.this, CalculatorActivity.class))
        );

        historyBtn.setOnClickListener(v ->
                startActivity(new Intent(StartActivity.this, HistoryActivity.class))
        );

        aboutBtn.setOnClickListener(v ->
                startActivity(new Intent(StartActivity.this, AboutActivity.class))
        );
    }
}