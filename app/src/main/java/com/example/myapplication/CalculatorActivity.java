package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        EditText num1 = findViewById(R.id.num1);
        EditText num2 = findViewById(R.id.num2);
        TextView result = findViewById(R.id.result);
        Button addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(v -> {
            String s1 = num1.getText().toString();
            String s2 = num2.getText().toString();

            if (s1.isEmpty() || s2.isEmpty()) {
                Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            int a = Integer.parseInt(s1);
            int b = Integer.parseInt(s2);
            result.setText("Result: " + (a + b));
        });
    }
}
