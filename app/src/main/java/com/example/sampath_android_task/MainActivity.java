package com.example.sampath_android_task;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GridLayout gv_numbers;
    private Spinner sp_rules;
    private String[] rulesArray = {"Odd Numbers", "Even Numbers", "Prime Numbers", "Fibonacci"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateXML();
    }


    void updateXML(){
        try{
            gv_numbers = findViewById(R.id.gv_numbers);
            sp_rules = findViewById(R.id.sp_rules);

            for (int i = 1; i <= 100; i++) {
                TextView numberView = new TextView(this);
                numberView.setText(String.valueOf(i));
                numberView.setLayoutParams(new GridLayout.LayoutParams());
                numberView.setPadding(16, 16, 16, 16);
                numberView.setTextSize(24);
                numberView.setGravity(Gravity.CENTER);
                gv_numbers.addView(numberView);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, rulesArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_rules.setAdapter(adapter);
            sp_rules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedRule = parent.getItemAtPosition(position).toString();
                    highlightNumbers(selectedRule);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

        }catch (Exception e){
            Log.d("UpdateXML",e.getMessage());
        }

    }

    private void highlightNumbers(String rule) {
        try{
            for (int i = 0; i < gv_numbers.getChildCount(); i++) {
                TextView numberView = (TextView) gv_numbers.getChildAt(i);
                int number = Integer.parseInt(numberView.getText().toString());
                numberView.setBackgroundColor(Color.TRANSPARENT);
                switch (rule) {
                    case "Odd Numbers":
                        if (number % 2 != 0) {
                            numberView.setBackgroundColor(Color.YELLOW);
                        }
                        break;
                    case "Even Numbers":
                        if (number % 2 == 0) {
                            numberView.setBackgroundColor(Color.GREEN);
                        }
                        break;
                    case "Prime Numbers":
                        if (isPrime(number)) {
                            numberView.setBackgroundColor(Color.CYAN);
                        }
                        break;
                    case "Fibonacci":
                        if (isFibonacci(number)) {
                            numberView.setBackgroundColor(Color.MAGENTA);
                        }
                        break;
                }
            }
        }catch (Exception e){
            Log.d("highlightNumbers()", e.getMessage());
        }
    }

    private boolean isPrime(int num) {
        try{
            if (num <= 1) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
        }catch (Exception e){
            Log.d("isPrime()", e.getMessage());
            return false;
        }
        return true;
    }

    private boolean isFibonacci(int num) {
        int a = 0, b = 1;
        try{
            while (a < num) {
                int temp = a;
                a = b;
                b = temp + b;
            }
        }catch (Exception e){
            Log.d("isPrime()", e.getMessage());
            return false;
        }
        return a == num;
    }
}
