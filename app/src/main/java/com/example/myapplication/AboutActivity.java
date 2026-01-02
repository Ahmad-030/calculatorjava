package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView backBtn = findViewById(R.id.backBtn);
        Button privacyBtn = findViewById(R.id.privacyBtn);

        backBtn.setOnClickListener(v -> finish());

        privacyBtn.setOnClickListener(v ->
                startActivity(new Intent(AboutActivity.this, PrivacyPolicyActivity.class))
        );
    }
}