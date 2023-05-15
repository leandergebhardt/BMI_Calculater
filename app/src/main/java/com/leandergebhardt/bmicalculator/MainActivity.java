package com.leandergebhardt.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private RadioButton radioFemal;
    private RadioButton radioMale;
    private RadioButton radioUndefined;
    private EditText textAge;
    private EditText textHeightM;
    private EditText textWeight;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        calculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // checking parameters
                if(textAge.getText() == null){
                    Toast.makeText(
                            MainActivity.this,
                            "Please provide your age in years!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                String ageText = textAge.getText().toString();
                int age = Integer.parseInt(ageText);
                if(age >= 18) {
                    try {
                        displayResult(calculateBmi());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        displayGuidance(calculateBmi());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(
                        MainActivity.this,
                        "Your BMI was calculated successfully",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private double calculateBmi() throws Exception {
        // checking parameters
        String warning = "";
        if(textHeightM.getText() == null){
            Toast.makeText(
                    MainActivity.this,
                    "Please provide your height in m!",
                    Toast.LENGTH_LONG).show();
            throw new Exception();
        } else if(textWeight.getText() == null){
            Toast.makeText(
                    MainActivity.this,
                    "Please provide your weight in kg!",
                    Toast.LENGTH_LONG).show();
            throw new Exception();
        }


        String heightMTest = textHeightM.getText().toString();
        String weightText = textWeight.getText().toString();

        double height = Double.parseDouble(heightMTest);
        int weight = Integer.parseInt(weightText);

        return weight / (height * height);
    }

    private void displayGuidance(double bmi) {
        String finalResult = "";

        if(radioFemal.isChecked()){
            finalResult = "As you are under 18, please consult your doctor for a healthy bmi range for girls";
        } else if(radioMale.isChecked()){
            finalResult = "As you are under 18, please consult your doctor for a healthy bmi range for boys.";
        } else if(radioUndefined.isChecked()) {
            finalResult = "As you are under 18, please consult your doctor for a healthy bmi range.";
        }

        resultText.setText(finalResult);
    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiResult = myDecimalFormatter.format(bmi);

        String finalResult;

        if(bmi < 18.5){
            finalResult = "You are underweight.";
        } else if(bmi > 25){
            finalResult = "You are overweight.";
        } else {
            finalResult = "You are healthy.";
        }

        resultText.setText(String.format("%s Your BMI is: %s",finalResult, bmiResult));
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);

        radioFemal = findViewById(R.id.radio_button_femal);
        radioMale = findViewById(R.id.radio_button_male);
        radioUndefined = findViewById(R.id.radio_button_undefined);

        textAge = findViewById(R.id.edit_text_age);
        textHeightM = findViewById(R.id.edit_text_height_m);
        textWeight = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);
    }

}

