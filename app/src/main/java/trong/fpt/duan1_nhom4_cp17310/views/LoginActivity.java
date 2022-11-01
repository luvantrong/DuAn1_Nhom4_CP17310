package trong.fpt.duan1_nhom4_cp17310.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.textfield.TextInputEditText;

import trong.fpt.duan1_nhom4_cp17310.R;

public class LoginActivity extends AppCompatActivity {

    private CheckBox chkShowPassword;
    private TextInputEditText tietPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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