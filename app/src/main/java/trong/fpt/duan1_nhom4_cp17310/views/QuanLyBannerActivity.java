package trong.fpt.duan1_nhom4_cp17310.views;

import static trong.fpt.duan1_nhom4_cp17310.Services.BannerService.BANNER_SERVICE_ACTION_DELETE;
import static trong.fpt.duan1_nhom4_cp17310.Services.BannerService.BANNER_SERVICE_ACTION_GETDATA;
import static trong.fpt.duan1_nhom4_cp17310.Services.BannerService.BANNER_SERVICE_EVENT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import trong.fpt.duan1_nhom4_cp17310.Adapters.AdapterBannerManager;
import trong.fpt.duan1_nhom4_cp17310.Interfaces.OnItemClickBannerManager;
import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.Services.BannerService;
import trong.fpt.duan1_nhom4_cp17310.models.Banners;

public class QuanLyBannerActivity extends AppCompatActivity implements OnItemClickBannerManager {
    private ImageView iv_trangchu_banner;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageButton flt_insert_banner;
    private RecyclerView rv_banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_banner);
        flt_insert_banner = findViewById(R.id.btn_insert_banner);
        rv_banner = findViewById(R.id.rv_banner);
        iv_trangchu_banner = findViewById(R.id.iv_trangchu_banner);

        iv_trangchu_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuanLyBannerActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        flt_insert_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLyBannerActivity.this, InsertBannerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        IntentFilter intentFilter = new IntentFilter(BANNER_SERVICE_EVENT);
        LocalBroadcastManager.getInstance(this).registerReceiver(getDataReceiver, intentFilter);


    }

    private void getData(){
        Intent intent = new Intent(QuanLyBannerActivity.this, BannerService.class);
        intent.setAction(BANNER_SERVICE_ACTION_GETDATA);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(getDataReceiver);

    }



    private BroadcastReceiver getDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Banners> list = (ArrayList<Banners>) intent.getSerializableExtra("result");
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(QuanLyBannerActivity.this);
            rv_banner.setLayoutManager(layoutManager);
            AdapterBannerManager adapterBannerManager = new AdapterBannerManager(QuanLyBannerActivity.this, list);
            rv_banner.setAdapter(adapterBannerManager);
        }
    };



    @Override
    public void onItemClickDelete(Banners banners) {
        new AlertDialog.Builder(this)
                .setTitle("Notification")
                .setMessage("Deletion will not restore")
                .setIcon(R.drawable.attention_warning_14525)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(QuanLyBannerActivity.this, BannerService.class);
                        intent.setAction(BANNER_SERVICE_ACTION_DELETE);
                        intent.putExtra("banners", banners);
                        startService(intent);

                        db.collection("banner")
                                .document(banners.getIdBanners())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        new AlertDialog.Builder(QuanLyBannerActivity.this)
                                                .setTitle("Notification")
                                                .setMessage("Delete successfully")
                                                .setIcon(R.drawable.attention_warning_14525)
                                                .setPositiveButton("OK", null)
                                                .show();
                                        getData();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        new AlertDialog.Builder(QuanLyBannerActivity.this)
                                                .setTitle("Notification")
                                                .setMessage("Delete failed")
                                                .setIcon(R.drawable.attention_warning_14525)
                                                .setPositiveButton("OK", null)
                                                .show();
                                    }
                                });
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


}