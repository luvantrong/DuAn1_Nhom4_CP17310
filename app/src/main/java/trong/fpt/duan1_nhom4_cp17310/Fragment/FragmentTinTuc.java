package trong.fpt.duan1_nhom4_cp17310.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;

import trong.fpt.duan1_nhom4_cp17310.R;

public class FragmentTinTuc extends Fragment {
    
    private Button btn_tintuc, btn_maps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tin_tuc, container, false);
        btn_tintuc = view.findViewById(R.id.btn_tintuc);
        btn_maps = view.findViewById(R.id.btn_maps);

        getFragmentManager().beginTransaction().replace(R.id.fr_tintuc, new FragmentKhac()).commit();
        
        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fr_tintuc, FragmentMaps.newInstance("FPT Polytechnic Ho Chi Minh")).commit();
            }
        });

        return view;
    }
}