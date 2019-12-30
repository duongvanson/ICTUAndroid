package dev.duongson.congtrunhanchiahaiso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPlus, btnMinus, btnMultiply, btnDivide;
    EditText txtNumberA, txtNumberB;
    TextView tvResult;
    RadioGroup radioGroup;
    RadioButton radPlus, radMinus, radMultiply, radDivide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControls();
        setEnvents();
    }

    private void setSateButton(boolean b) {
        btnPlus.setEnabled(b);
        btnMinus.setEnabled(b);
        btnMultiply.setEnabled(b);
        btnDivide.setEnabled(b);
        radPlus.setEnabled(b);
        radMinus.setEnabled(b);
        radMultiply.setEnabled(b);
        radDivide.setEnabled(b);
    }

    private void setEnvents() {
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        txtNumberA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    //Log.d("TEST", "onTextChanged: " + s.toString());
                    if (!txtNumberB.getText().toString().equals("")) {
                        if (Integer.parseInt(txtNumberB.getText().toString()) == 0) {
                            setSateButton(true);
                            btnDivide.setEnabled(false);
                            radDivide.setEnabled(false);
                        } else {
                            setSateButton(true);
                        }
                    }
                } else {
                    setSateButton(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Log.d("TEST", "onTextChanged: "+s.toString());
            }
        });
        txtNumberB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    //Log.d("TEST", "onTextChanged: " + s.toString());
                    if (!txtNumberA.getText().toString().equals("")) {
                        if (Integer.parseInt(txtNumberB.getText().toString()) == 0) {
                            setSateButton(true);
                            btnDivide.setEnabled(false);
                            radDivide.setEnabled(false);
                        } else {
                            setSateButton(true);
                        }
                    }
                } else {
                    //Log.d("TEST", "EMPTY: " + s.toString());
                    setSateButton(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int numberA = Integer.parseInt(txtNumberA.getText().toString());
                int numberB = Integer.parseInt(txtNumberB.getText().toString());
                switch (checkedId) {
                    case R.id.radPlus:
                        tvResult.setText(numberA + numberB + "");
                        break;
                    case R.id.radMinus:
                        tvResult.setText(numberA - numberB + "");
                        break;
                    case R.id.radMultiply:
                        tvResult.setText(numberA * numberB + "");
                        break;
                    case R.id.radDivde:
                        tvResult.setText(((float) numberA / numberB) + "");
                        break;
                }
            }
        });
    }

    private void setControls() {
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        txtNumberA = findViewById(R.id.txtNumberA);
        txtNumberB = findViewById(R.id.txtNumberB);
        tvResult = findViewById(R.id.tvResult);
        radioGroup = findViewById(R.id.radGroup);
        radPlus = findViewById(R.id.radPlus);
        radMinus = findViewById(R.id.radMinus);
        radMultiply = findViewById(R.id.radMultiply);
        radDivide = findViewById(R.id.radDivde);
    }

    @Override
    public void onClick(View v) {
        int numberA = Integer.parseInt(txtNumberA.getText().toString());
        int numberB = Integer.parseInt(txtNumberB.getText().toString());
        switch (v.getId()) {
            case R.id.btnPlus:
                tvResult.setText(numberA + numberB + "");
                break;
            case R.id.btnMinus:
                tvResult.setText(numberA - numberB + "");
                break;
            case R.id.btnMultiply:
                tvResult.setText(numberA * numberB + "");
                break;
            case R.id.btnDivide:
                tvResult.setText(((float) numberA / numberB) + "");
                break;
        }
    }
}
