package trong.fpt.duan1_nhom4_cp17310.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator3;
import trong.fpt.duan1_nhom4_cp17310.Adapters.AdapterBannerManager;
import trong.fpt.duan1_nhom4_cp17310.Adapters.AdapterBannerTrangChu;
import trong.fpt.duan1_nhom4_cp17310.Adapters.AdapterFilmManager;
import trong.fpt.duan1_nhom4_cp17310.Adapters.AdapterFilmTrangChu;
import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.models.Banners;
import trong.fpt.duan1_nhom4_cp17310.models.Film;
import trong.fpt.duan1_nhom4_cp17310.models.Photo;
import trong.fpt.duan1_nhom4_cp17310.views.QuanLyBannerActivity;
import trong.fpt.duan1_nhom4_cp17310.views.QuanLyFilmsActivity;

public class FragmentTrangChu extends Fragment {

    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private RecyclerView rv_all_film;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private List<Banners> mList;
    private Handler mHanler = new Handler(Looper.getMainLooper());
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int current = viewPager2.getCurrentItem();
            if(current == mList.size() - 1 ){
                viewPager2.setCurrentItem(0);
            }else {
                viewPager2.setCurrentItem(current + 1);
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_trangchu, container, false);
        viewPager2 = view.findViewById(R.id.vp_sl_Top);
        circleIndicator3 = view.findViewById(R.id.circleIndicator);
        rv_all_film = view.findViewById(R.id.rv_all_film);
        //setting viewpager
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);




        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHanler.removeCallbacks(mRunnable);
                mHanler.postDelayed(mRunnable, 4000);
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                int current = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(current);
            }
        });

        return view;
    }

    private void getDataBanner(){

        db.collection("banner")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            mList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String linkAnh = map.get("linkAnh").toString();
                                String moTa = map.get("tenphim").toString();
                                Banners banners = new Banners(linkAnh, moTa);
//                                course.setCourseId(document.getId());
                                mList.add(banners);
                            }
                            AdapterBannerTrangChu adapter = new AdapterBannerTrangChu(mList);
                            viewPager2.setAdapter(adapter);
                            circleIndicator3.setViewPager(viewPager2);
                        }
                    }
                });
    }

    private void getDataFilm() {
        db.collection("films")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Film> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String linkAnh = map.get("linkAnh").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayKhoiChieu = map.get("ngayKhoiChieu").toString();
                                Film film = new Film(tenPhim, ngayKhoiChieu, giaVe, linkAnh);
                                film.setIdFilm(document.getId());
                                list.add(film);
                            }
                            int numberOfColumns = 2;
                            rv_all_film.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
                            AdapterFilmTrangChu adapterFilmTrangChu = new AdapterFilmTrangChu(getContext(), list);
                            rv_all_film.setAdapter(adapterFilmTrangChu);
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataBanner();
        getDataFilm();
    }
}
