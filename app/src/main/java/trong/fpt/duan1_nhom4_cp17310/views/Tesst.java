package trong.fpt.duan1_nhom4_cp17310.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import trong.fpt.duan1_nhom4_cp17310.Fragment.FragmentTrangChu;
import trong.fpt.duan1_nhom4_cp17310.R;

public class Tesst extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tesst);
        frameLayout = findViewById(R.id.frTesst);

        FragmentTrangChu fragmentTrangChu = new FragmentTrangChu();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.frTesst, fragmentTrangChu)
                .commit();
    }
}