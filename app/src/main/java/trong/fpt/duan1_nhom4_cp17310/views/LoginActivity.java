package trong.fpt.duan1_nhom4_cp17310.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import trong.fpt.duan1_nhom4_cp17310.R;

public class LoginActivity extends AppCompatActivity {

    private CheckBox chkShowPassword;
    private TextInputEditText tietPassword;
    private TextView tv_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        chkShowPassword = findViewById(R.id.chkShowPassword);
        tietPassword = findViewById(R.id.EditTextInputLayOutPass);
        tv_register = findViewById(R.id.tv_register);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });


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