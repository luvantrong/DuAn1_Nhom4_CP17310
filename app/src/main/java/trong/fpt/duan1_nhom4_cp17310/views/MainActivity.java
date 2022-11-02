package trong.fpt.duan1_nhom4_cp17310.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import trong.fpt.duan1_nhom4_cp17310.Fragment.FragmentTrangChu;
import trong.fpt.duan1_nhom4_cp17310.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTrangChu fragmentTrangChu = new FragmentTrangChu();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.fr_main, fragmentTrangChu)
                .commit();
    }
}