package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvResult, tvExpression;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tvResult = findViewById(R.id.tvResult);
        tvExpression = findViewById(R.id.tvExpression);

        setupNumberButtons();
        setupOperatorButtons();
        setupFunctionButtons();
    }

    private void setupNumberButtons() {
        int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        View.OnClickListener numberClickListener = v -> {
            Button button = (Button) v;
            if (isNewOperation) {
                currentNumber = "";
                isNewOperation = false;
            }
            currentNumber += button.getText().toString();
            tvResult.setText(currentNumber);
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        // Dot button
        findViewById(R.id.btnDot).setOnClickListener(v -> {
            if (isNewOperation) {
                currentNumber = "0";
                isNewOperation = false;
            }
            if (!currentNumber.contains(".")) {
                currentNumber += ".";
                tvResult.setText(currentNumber);
            }
        });
    }

    private void setupOperatorButtons() {
        View.OnClickListener operatorClickListener = v -> {
            Button button = (Button) v;
            if (!currentNumber.isEmpty()) {
                if (!operator.isEmpty()) {
                    calculateResult();
                }
                firstNumber = Double.parseDouble(currentNumber);
                operator = button.getText().toString();
                tvExpression.setText(currentNumber + " " + operator);
                currentNumber = "";
            }
        };

        findViewById(R.id.btnAdd).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnSubtract).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnMultiply).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnDivide).setOnClickListener(operatorClickListener);
        findViewById(R.id.btnPercent).setOnClickListener(operatorClickListener);
    }

    private void setupFunctionButtons() {
        // Clear button
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            currentNumber = "";
            operator = "";
            firstNumber = 0;
            isNewOperation = true;
            tvResult.setText("0");
            tvExpression.setText("");
        });

        // Delete button
        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                tvResult.setText(currentNumber.isEmpty() ? "0" : currentNumber);
            }
        });

        // Plus/Minus button
        findViewById(R.id.btnPlusMinus).setOnClickListener(v -> {
            if (!currentNumber.isEmpty() && !currentNumber.equals("0")) {
                if (currentNumber.startsWith("-")) {
                    currentNumber = currentNumber.substring(1);
                } else {
                    currentNumber = "-" + currentNumber;
                }
                tvResult.setText(currentNumber);
            }
        });

        // Equals button
        findViewById(R.id.btnEquals).setOnClickListener(v -> {
            if (!operator.isEmpty() && !currentNumber.isEmpty()) {
                calculateResult();
                operator = "";
                isNewOperation = true;
            }
        });
    }

    private void calculateResult() {
        double secondNumber = Double.parseDouble(currentNumber);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    tvResult.setText("Error");
                    tvExpression.setText("");
                    currentNumber = "";
                    return;
                }
                break;
            case "%":
                result = firstNumber % secondNumber;
                break;
        }

        // Format result to remove unnecessary decimals
        String resultString;
        if (result == (long) result) {
            resultString = String.valueOf((long) result);
        } else {
            resultString = String.valueOf(result);
        }

        tvExpression.setText(firstNumber + " " + operator + " " + secondNumber);
        tvResult.setText(resultString);
        currentNumber = resultString;
        firstNumber = result;
    }
}