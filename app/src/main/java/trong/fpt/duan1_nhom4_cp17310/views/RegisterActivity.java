package trong.fpt.duan1_nhom4_cp17310.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.models.Users;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText ip_user, ip_passWord, ip_enterPass;
    private int dem = 0;
    private Button btn_register;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Users> dsTaiKhoan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ip_user = findViewById(R.id.ip_user);
        ip_passWord = findViewById(R.id.ip_pass);
        ip_enterPass = findViewById(R.id.ip_enterpass);
        btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = ip_user.getText().toString();
                String passWord = ip_passWord.getText().toString();
                String enterPass = ip_enterPass.getText().toString();
                int loaiTaiKhoan = 0;

                if(user.length()==0 || passWord.length()==0 || enterPass.length() == 0){
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Thông báo")
                            .setMessage("Bạn phải nhập đầy đủ thông tin: \n- Tên tài khoản\n- Mật khẩu")
                            .setIcon(R.drawable.attention_warning_14525)
                            .setPositiveButton("OK", null)
                            .show();
                }else if(!passWord.equalsIgnoreCase(enterPass)){
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Thông báo")
                            .setMessage("Mật khẩu không trùng khớp")
                            .setIcon(R.drawable.attention_warning_14525)
                            .setPositiveButton("OK", null)
                            .show();
                }else{
                    insertUser(user, passWord, enterPass);
                }
            }
        });

    }

    private void insertUser(String user, String passWord, String enterPass) {
        ArrayList<Users> list = new ArrayList<>();
        db.collection("users")
                .whereEqualTo("tenTaiKhoan", user)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                String matKhau = map.get("matKhau").toString();
                                Integer loaiTaiKhoan = Integer.valueOf(map.get("loaiTaiKhoan").toString());
                                Users users = new Users(tenTaiKhoan, matKhau, loaiTaiKhoan);
                                list.add(users);
                            }
                            dsTaiKhoan = list;
                            if (dsTaiKhoan.size() == 0) {
                                Map<String, Object> users = new HashMap<>();
                                users.put("tenTaiKhoan", user);
                                users.put("matKhau", passWord);
                                users.put("loaiTaiKhoan", 0);

                                db.collection("users")
                                        .add(users)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(RegisterActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                for (int i = 0; i < dsTaiKhoan.size(); i++) {
                                    String tenTaiKhoans = dsTaiKhoan.get(i).getTenTaiKhoan();
                                    if (tenTaiKhoans.equalsIgnoreCase(user)) {
                                        dem = 1;
                                        break;
                                    }
                                }
                                if (dem == 0) {

                                    Map<String, Object> users = new HashMap<>();
                                    users.put("tenTaiKhoan", user);
                                    users.put("matKhau", passWord);
                                    users.put("loaiTaiKhoan", enterPass);

                                    db.collection("users")
                                            .add(user)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(RegisterActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }
                        }
                    }
                });
    }
}