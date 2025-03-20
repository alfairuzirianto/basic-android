package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult, tvInput;
    private String operator = "";
    private String prevInput = "";
    private String currentInput = "";
    private Double result = null;
    private Double firstNumber = null;
    private Double secondNumber = null;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);
        tvInput = findViewById(R.id.tv_input);

        setNumber(R.id.btn_0, "0");
        setNumber(R.id.btn_00, "00");
        setNumber(R.id.btn_1, "1");
        setNumber(R.id.btn_2, "2");
        setNumber(R.id.btn_3, "3");
        setNumber(R.id.btn_4, "4");
        setNumber(R.id.btn_5, "5");
        setNumber(R.id.btn_6, "6");
        setNumber(R.id.btn_7, "7");
        setNumber(R.id.btn_8, "8");
        setNumber(R.id.btn_9, "9");
        setNumber(R.id.btn_point, ".");

        findViewById(R.id.btn_clear).setOnClickListener(v -> clearDisplay());
        findViewById(R.id.btn_delete).setOnClickListener(v -> deleteChar());

        findViewById(R.id.btn_add).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btn_subtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.btn_power).setOnClickListener(v -> setOperator("^"));

        findViewById(R.id.btn_calc).setOnClickListener(v -> calcResult());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setNumber(int btnId, String value) {
        findViewById(btnId).setOnClickListener(v -> {
            if (value.equals(".")) {
                if (currentInput.contains(".")) return;
            }
            appendToDisplay(value);
        });
    }

    private void appendToDisplay(String value) {
        currentInput += value;
        prevInput += value;
        tvInput.setText(prevInput);
    }

    private void clearDisplay() {
        currentInput = prevInput = operator = "";
        firstNumber = secondNumber = null;
        isOperatorPressed = false;
        tvResult.setText("");
        tvInput.setText("0");
    }

    private void deleteChar() {
        if (!prevInput.isEmpty()) {
            prevInput = prevInput.substring(0, prevInput.length() - 1);
            tvInput.setText(prevInput);
        }
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            if (firstNumber == null) {
                firstNumber = Double.parseDouble(currentInput);
            } else if (isOperatorPressed) {
                secondNumber = Double.parseDouble(currentInput);
                calcResult();
                firstNumber = result;
            }
            currentInput = "";
            operator = op;
            prevInput += " " + operator + " ";
            tvInput.setText(prevInput);
            isOperatorPressed = true;
        }
    }

    private void calcResult() {
        if (!isOperatorPressed || currentInput.isEmpty()) return;

        secondNumber = Double.parseDouble(currentInput);

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "^":
                result = Math.pow(firstNumber, secondNumber);
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    tvInput.setText("Error");
                    return;
                }
                break;
        }

        isOperatorPressed = false;
        operator = "";

        if (result % 1 == 0) {
            currentInput = String.valueOf(result.intValue());
        } else {
            currentInput = String.valueOf(result);
        }

        tvResult.setText(currentInput);
        firstNumber = result;
    }
}
