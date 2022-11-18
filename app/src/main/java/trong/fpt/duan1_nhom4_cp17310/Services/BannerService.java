package trong.fpt.duan1_nhom4_cp17310.Services;

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.models.Banners;

public class BannerService extends IntentService {

    public static final String BANNER_SERVICE_EVENT = "BANNER_SERVICE_EVENT";
    public static final String BANNER_SERVICE_ACTION_GETDATA = "BANNER_SERVICE_ACTION_GETDATA";
    public static final String BANNER_SERVICE_ACTION_DELETE = "BANNER_SERVICE_ACTION_DELETE";
    public static final String BANNER_SERVICE_ACTION_INSERT = "BANNER_SERVICE_ACTION_INSERT";


    private FirebaseFirestore db;
    private ArrayList<Banners> list = new ArrayList<>();
    private Handler handler;
    private static AlertDialog alert;

    public BannerService() {
        super("BannerService");
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            switch (action) {
                case BANNER_SERVICE_ACTION_GETDATA: {
                    db.collection("banner")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {

                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Map<String, Object> map = document.getData();
                                            String linkAnh = map.get("linkAnh").toString();
                                            String moTa = map.get("tenphim").toString();
                                            Banners banners = new Banners(linkAnh, moTa);
                                            banners.setIdBanners(document.getId());
                                            list.add(banners);
                                        }

                                    }
                                    Intent outIntent = new Intent(BANNER_SERVICE_EVENT);
                                    outIntent.putExtra("result", list);
                                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(outIntent);
                                }
                            });
                    break;
                }
            }
        }
    }




}