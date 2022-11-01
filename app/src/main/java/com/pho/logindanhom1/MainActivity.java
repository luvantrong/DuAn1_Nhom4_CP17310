package com.pho.logindanhom1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private CheckBox chkShowPassword;
    private TextInputEditText tietPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chkShowPassword = findViewById(R.id.chkShowPassword);
        tietPassword = findViewById(R.id.EditTextInputLayOutPass);

        chkShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    tietPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    tietPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
}