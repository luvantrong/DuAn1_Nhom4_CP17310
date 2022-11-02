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
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import trong.fpt.duan1_nhom4_cp17310.Adapters.PhotoAdapter;
import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.models.Photo;

public class FragmentTrangChu extends Fragment {

    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private RecyclerView rvBook;
    private List<Photo> mList;
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

        mList = getListPhoto();
        PhotoAdapter adapter = new PhotoAdapter(mList);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);

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

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.cn1));
        list.add(new Photo(R.drawable.cn2));
        list.add(new Photo(R.drawable.cn3));
        list.add(new Photo(R.drawable.cn4));
        list.add(new Photo(R.drawable.cn5));
        list.add(new Photo(R.drawable.cn6));
        list.add(new Photo(R.drawable.cn7));
        return list;
    }
}