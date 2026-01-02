package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout historyContainer;
    private TextView emptyHistoryText;
    private Button clearHistoryBtn;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyContainer = findViewById(R.id.historyContainer);
        emptyHistoryText = findViewById(R.id.emptyHistoryText);
        clearHistoryBtn = findViewById(R.id.clearHistoryBtn);
        ImageView backBtn = findViewById(R.id.backBtn);

        prefs = getSharedPreferences("CalculatorHistory", MODE_PRIVATE);

        backBtn.setOnClickListener(v -> finish());
        clearHistoryBtn.setOnClickListener(v -> clearHistory());

        loadHistory();
    }

    private void loadHistory() {
        Set<String> historySet = prefs.getStringSet("history", new HashSet<>());
        ArrayList<String> historyList = new ArrayList<>(historySet);

        if (historyList.isEmpty()) {
            emptyHistoryText.setVisibility(TextView.VISIBLE);
            clearHistoryBtn.setEnabled(false);
            clearHistoryBtn.setAlpha(0.5f);
        } else {
            emptyHistoryText.setVisibility(TextView.GONE);
            clearHistoryBtn.setEnabled(true);
            clearHistoryBtn.setAlpha(1f);

            for (int i = historyList.size() - 1; i >= 0; i--) {
                addHistoryCard(historyList.get(i));
            }
        }
    }

    private void addHistoryCard(String calculation) {
        CardView card = new CardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 24);
        card.setLayoutParams(cardParams);
        card.setCardElevation(8);
        card.setRadius(16);
        card.setCardBackgroundColor(getResources().getColor(android.R.color.white));

        TextView textView = new TextView(this);
        textView.setText(calculation);
        textView.setTextSize(18);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setPadding(32, 32, 32, 32);

        card.addView(textView);
        historyContainer.addView(card);
    }

    private void clearHistory() {
        prefs.edit().remove("history").apply();
        historyContainer.removeAllViews();
        emptyHistoryText.setVisibility(TextView.VISIBLE);
        clearHistoryBtn.setEnabled(false);
        clearHistoryBtn.setAlpha(0.5f);
    }
}