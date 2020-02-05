package com.example.carloan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ccBox;
    private EditText dpBox;
    private EditText aprBox;
    private EditText payBox;
    private TextView loanLabel;
    private RadioButton LoanRadio;
    private RadioButton LeaseRadio;
    private SeekBar monthBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccBox = findViewById(R.id.ccBox);
        dpBox = findViewById(R.id.dpBox);
        aprBox = findViewById(R.id.aprBox);
        payBox = findViewById(R.id.payBox);
        loanLabel = findViewById(R.id.loanLabel);
        LoanRadio = findViewById(R.id.LoanRadio);
        LeaseRadio = findViewById(R.id.LeaseRadio);
        monthBar = findViewById(R.id.monthBar);

        monthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                loanLabel.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calculate(View v){
        if(LoanRadio.isChecked()) {
            String input = ccBox.getText().toString();
            String input2 = dpBox.getText().toString();
            String input3 = aprBox.getText().toString();
            String input4 = loanLabel.getText().toString();
            if(input.length() > 0 && input2.length() > 0 && input3.length() > 0 && input4.length() > 0) {
                double ccValue = Double.parseDouble(input);
                double dpValue = Double.parseDouble(input2);
                double aprValue = Double.parseDouble(input3);
                double mrValue = (aprValue*0.01) / 12;
                double oweValue = ccValue - dpValue;
                double monthValue = Double.parseDouble(input4);
                double payValue = (mrValue*oweValue) / (1-(Math.pow((1+mrValue),(-monthValue))));
                payBox.setText(String.format("$%.2f", payValue));
            }
            else if(input4.length() == 0){
                Toast.makeText(this, "Increase the Loan Month", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "One of the Boxes is not filled out", Toast.LENGTH_SHORT).show();
            }
        }
        else if(LeaseRadio.isChecked()){
            String input = ccBox.getText().toString();
            String input2 = dpBox.getText().toString();
            String input3 = aprBox.getText().toString();
            loanLabel.setText(String.format("%d", 36));
            if(input.length() > 0 && input2.length() > 0 && input3.length() > 0) {
                double ccValue = Double.parseDouble(input);
                double dpValue = Double.parseDouble(input2);
                double aprValue = Double.parseDouble(input3);
                double mrValue = (aprValue*0.01) / 12;
                double oweValue = (ccValue / 3)- dpValue;
                double payValue = (mrValue*oweValue) / (1-(Math.pow((1+mrValue),(-36))));
                payBox.setText(String.format("$%.2f", payValue));
            }
            else {
                Toast.makeText(this, "One of the Boxes is not filled out", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
