package trong.fpt.duan1_nhom4_cp17310.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.views.ProfileActivity;
import trong.fpt.duan1_nhom4_cp17310.views.QuanLyBannerActivity;
import trong.fpt.duan1_nhom4_cp17310.views.QuanLyFilmsActivity;

public class FragmentKhac extends Fragment {
    LinearLayout ln_quanlyphim, ln_quanlybanner,ln_thongtinuser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_khac, container, false);

        ln_quanlyphim = view.findViewById(R.id.ln_quanlyphim);
        ln_quanlybanner = view.findViewById(R.id.ln_quanlybanner);
        ln_thongtinuser = view.findViewById(R.id.ln_thongtinuser);

        ln_quanlyphim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), QuanLyFilmsActivity.class);
                startActivity(i);
            }
        });

       ln_thongtinuser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(v.getContext(),ProfileActivity.class);
               startActivity(i);
           }
       });

        ln_quanlybanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), QuanLyBannerActivity.class);
                startActivity(i);
            }
        });
        return view;

    }
}
