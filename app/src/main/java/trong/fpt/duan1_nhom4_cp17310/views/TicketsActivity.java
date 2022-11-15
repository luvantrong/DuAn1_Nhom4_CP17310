package trong.fpt.duan1_nhom4_cp17310.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import trong.fpt.duan1_nhom4_cp17310.Adapters.AdapterBannerTrangChu;
import trong.fpt.duan1_nhom4_cp17310.Adapters.AdapterFilmTrangChu;
import trong.fpt.duan1_nhom4_cp17310.Adapters.AdapterSuatXem;
import trong.fpt.duan1_nhom4_cp17310.Fragment.FragmentDatVe;
import trong.fpt.duan1_nhom4_cp17310.Interfaces.ItemClickListenerSuatXem;
import trong.fpt.duan1_nhom4_cp17310.Interfaces.SetSelectedItemMenuBottom;
import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.ViewHolder.SuatXemViewHolder;
import trong.fpt.duan1_nhom4_cp17310.models.Banners;
import trong.fpt.duan1_nhom4_cp17310.models.Film;
import trong.fpt.duan1_nhom4_cp17310.models.SuatXem;
import trong.fpt.duan1_nhom4_cp17310.models.Tickets;

public class TicketsActivity extends AppCompatActivity {

    private ImageView imv_ticket;
    private TextView tv_tenphim_ticket, tv_chonngay_xem, tv_chon_suat_xem, tv_soluong, edt_soluong;
    private Button btn_chonngay_xem, btn_ticket, btn_cancel_ticket;
    private LinearLayout ln_chon_soluong;
    private RecyclerView rv_chon_suat_xem;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<SuatXem> dsSuatXem;
    private AdapterSuatXem adapterSuatXem;

    private ArrayList<Tickets> dsTicketsSuat8h;
    private ArrayList<Tickets> dsTicketsSuat9h;
    private ArrayList<Tickets> dsTicketsSuat10h;
    private ArrayList<Tickets> dsTicketsSuat11h;
    private ArrayList<Tickets> dsTicketsSuat13h;
    private ArrayList<Tickets> dsTicketsSuat14h;
    private ArrayList<Tickets> dsTicketsSuat15h;
    private ArrayList<Tickets> dsTicketsSuat16h;
    private ArrayList<Tickets> dsTicketsSuat17h;
    private ArrayList<Tickets> dsTicketsSuat18h;
    private ArrayList<Tickets> dsTicketsSuat19h;
    private ArrayList<Tickets> dsTicketsSuat20h;


    int soLuongSuat8h, soLuongSuat9h, soLuongSuat10h, soLuongSuat11h, soLuongSuat13h, soLuongSuat14h,
            soLuongSuat15h, soLuongSuat16h, soLuongSuat17h, soLuongSuat18h, soLuongSuat19h, soLuongSuat20h;

    private ItemClickListenerSuatXem itemClickListenerSuatXem;

    private Film film;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        imv_ticket = findViewById(R.id.imv_ticket);
        tv_tenphim_ticket = findViewById(R.id.tv_tenphim_ticket);
        tv_chonngay_xem = findViewById(R.id.tv_chonngay_xem);
        btn_chonngay_xem = findViewById(R.id.btn_chonngay_xem);
        btn_ticket = findViewById(R.id.btn_ticket);
        btn_cancel_ticket = findViewById(R.id.btn_cancel_ticket);
        rv_chon_suat_xem = findViewById(R.id.rv_chon_suat_xem);
        tv_chon_suat_xem = findViewById(R.id.tv_chon_suat_xem);
        ln_chon_soluong = findViewById(R.id.ln_chon_soluong);
        edt_soluong = findViewById(R.id.edt_soluong);
        tv_soluong = findViewById(R.id.tv_soluong);

        btn_cancel_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicketsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dsSuatXem = dsSuatXem();

        dsTicketsSuat8h = new ArrayList<>();
        dsTicketsSuat9h = new ArrayList<>();
        dsTicketsSuat10h = new ArrayList<>();
        dsTicketsSuat11h = new ArrayList<>();
        dsTicketsSuat13h = new ArrayList<>();
        dsTicketsSuat14h = new ArrayList<>();
        dsTicketsSuat15h = new ArrayList<>();
        dsTicketsSuat16h = new ArrayList<>();
        dsTicketsSuat17h = new ArrayList<>();
        dsTicketsSuat18h = new ArrayList<>();
        dsTicketsSuat19h = new ArrayList<>();
        dsTicketsSuat20h = new ArrayList<>();

        Intent intent = getIntent();
        film = (Film) intent.getSerializableExtra("thongTinPhim");
        String imageLink = film.getLinkAnh();
        new DownloadImageFromInternet(imv_ticket).execute(imageLink);
        tv_tenphim_ticket.setText(film.getTenFilm());
        dsSuatXem = dsSuatXem();

        itemClickListenerSuatXem = new ItemClickListenerSuatXem() {
            @Override
            public void onClick(String s) {
                Log.d("TAG", "Selected: " + s);
                if(s.equals("08:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat8h));
                }

                if(s.equals("09:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat9h));
                }

                if(s.equals("10:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat10h));
                }

                if(s.equals("11:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat11h));
                }

                if(s.equals("13:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat13h));
                }

                if(s.equals("14:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat14h));
                }

                if(s.equals("15:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat15h));
                }

                if(s.equals("16:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat16h));
                }

                if(s.equals("17:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat17h));
                }

                if(s.equals("18:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat18h));
                }

                if(s.equals("19:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat19h));
                }

                if(s.equals("20:00")){
                    tv_soluong.setText("Số lượng ghế còn lại: " + (50 -soLuongSuat20h));
                }

                ln_chon_soluong.setVisibility(View.VISIBLE);
            }
        };


        int numberOfColumns = 4;
        rv_chon_suat_xem.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        btn_chonngay_xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(TicketsActivity.this,
                        0, ngayXem, mYear, mMonth, mDay);
                d.show();
            }
        });
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

    private ArrayList<SuatXem> dsSuatXem() {
        ArrayList<SuatXem> dsSuatXem = new ArrayList<>();
        dsSuatXem.add(new SuatXem("08:00"));
        dsSuatXem.add(new SuatXem("09:00"));
        dsSuatXem.add(new SuatXem("10:00"));
        dsSuatXem.add(new SuatXem("11:00"));
        dsSuatXem.add(new SuatXem("13:00"));
        dsSuatXem.add(new SuatXem("14:00"));
        dsSuatXem.add(new SuatXem("15:00"));
        dsSuatXem.add(new SuatXem("16:00"));
        dsSuatXem.add(new SuatXem("17:00"));
        dsSuatXem.add(new SuatXem("18:00"));
        dsSuatXem.add(new SuatXem("19:00"));
        dsSuatXem.add(new SuatXem("20:00"));
        return dsSuatXem;
    }





    DatePickerDialog.OnDateSetListener ngayXem = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            tv_chonngay_xem.setText(sdf.format(c.getTime()));
            tv_chon_suat_xem.setVisibility(View.VISIBLE);
            rv_chon_suat_xem.setVisibility(View.VISIBLE);

            getDataPhimSuat8h(film.getTenFilm(),sdf.format(c.getTime()), "08:00");
            getDataPhimSuat9h(film.getTenFilm(),sdf.format(c.getTime()), "09:00");
            getDataPhimSuat10h(film.getTenFilm(),sdf.format(c.getTime()), "10:00");
            getDataPhimSuat11h(film.getTenFilm(),sdf.format(c.getTime()), "11:00");
            getDataPhimSuat13h(film.getTenFilm(),sdf.format(c.getTime()), "13:00");
            getDataPhimSuat14h(film.getTenFilm(),sdf.format(c.getTime()), "14:00");
            getDataPhimSuat15h(film.getTenFilm(),sdf.format(c.getTime()), "15:00");
            getDataPhimSuat16h(film.getTenFilm(),sdf.format(c.getTime()), "16:00");
            getDataPhimSuat17h(film.getTenFilm(),sdf.format(c.getTime()), "17:00");
            getDataPhimSuat18h(film.getTenFilm(),sdf.format(c.getTime()), "18:00");
            getDataPhimSuat19h(film.getTenFilm(),sdf.format(c.getTime()), "19:00");
            getDataPhimSuat20h(film.getTenFilm(),sdf.format(c.getTime()), "20:00");

            btn_chonngay_xem.setEnabled(false);
        }
    };

    private void getDataPhimSuat8h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat8h.add(tickets);
                            }
                            if(dsTicketsSuat8h.size()==0){
                                soLuongSuat8h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat8h.size();i++){
                                    Tickets tickets = dsTicketsSuat8h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat8h+=soLuong;
                                }
                            }

                            if (soLuongSuat8h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("08:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat9h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat9h.add(tickets);
                            }
                            if(dsTicketsSuat9h.size()==0){
                                soLuongSuat9h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat9h.size();i++){
                                    Tickets tickets = dsTicketsSuat9h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat9h+=soLuong;
                                }
                            }

                            if (soLuongSuat9h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("09:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat10h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat10h.add(tickets);
                            }
                            if(dsTicketsSuat10h.size()==0){
                                soLuongSuat10h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat10h.size();i++){
                                    Tickets tickets = dsTicketsSuat10h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat10h+=soLuong;
                                }
                            }

                            if (soLuongSuat10h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("10:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat11h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat11h.add(tickets);
                            }
                            if(dsTicketsSuat11h.size()==0){
                                soLuongSuat11h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat11h.size();i++){
                                    Tickets tickets = dsTicketsSuat11h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat11h+=soLuong;
                                }
                            }

                            if (soLuongSuat11h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("11:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat13h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat13h.add(tickets);
                            }

                            if(dsTicketsSuat13h.size()==0){
                                soLuongSuat13h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat13h.size();i++){
                                    Tickets tickets = dsTicketsSuat13h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat13h+=soLuong;
                                }
                            }

                            Log.d("SL", soLuongSuat13h+"");

                            if (soLuongSuat13h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("13:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);

                        }
                    }
                });
    }

    private void getDataPhimSuat14h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat14h.add(tickets);
                            }
                            if(dsTicketsSuat14h.size()==0){
                                soLuongSuat14h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat14h.size();i++){
                                    Tickets tickets = dsTicketsSuat14h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat14h+=soLuong;
                                }
                            }

                            if (soLuongSuat14h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("14:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat15h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat15h.add(tickets);
                            }
                            if(dsTicketsSuat15h.size()==0){
                                soLuongSuat15h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat15h.size();i++){
                                    Tickets tickets = dsTicketsSuat15h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat15h+=soLuong;
                                }
                            }

                            if (soLuongSuat15h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("15:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat16h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat16h.add(tickets);
                            }
                            if(dsTicketsSuat16h.size()==0){
                                soLuongSuat16h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat16h.size();i++){
                                    Tickets tickets = dsTicketsSuat16h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat16h+=soLuong;
                                }
                            }

                            if (soLuongSuat16h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("16:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat17h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat17h.add(tickets);
                            }
                            if(dsTicketsSuat17h.size()==0){
                                soLuongSuat17h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat17h.size();i++){
                                    Tickets tickets = dsTicketsSuat17h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat17h+=soLuong;
                                }
                            }

                            if (soLuongSuat17h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("17:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat18h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat18h.add(tickets);
                            }
                            if(dsTicketsSuat18h.size()==0){
                                soLuongSuat18h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat18h.size();i++){
                                    Tickets tickets = dsTicketsSuat18h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat18h+=soLuong;
                                }
                            }

                            if (soLuongSuat18h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("18:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat19h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat19h.add(tickets);
                            }
                            if(dsTicketsSuat19h.size()==0){
                                soLuongSuat19h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat19h.size();i++){
                                    Tickets tickets = dsTicketsSuat19h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat19h+=soLuong;
                                }
                            }

                            if (soLuongSuat19h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("19:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

    private void getDataPhimSuat20h(String tenPhim, String ngayXem, String suatXem){
        db.collection("tickets")
                .whereEqualTo("tenPhim", tenPhim)
                .whereEqualTo("ngayXemPhim", ngayXem)
                .whereEqualTo("suatXem", suatXem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                String tenPhim = map.get("tenPhim").toString();
                                String giaVe = map.get("giaVe").toString();
                                String ngayXemPhim = map.get("ngayXemPhim").toString();
                                String soLuong = map.get("soLuong").toString();
                                String suatXem = map.get("suatXem").toString();
                                String tenTaiKhoan = map.get("tenTaiKhoan").toString();
                                Tickets tickets = new Tickets(tenPhim, suatXem, soLuong, tenTaiKhoan, ngayXemPhim, giaVe);
                                tickets.setIdTickets(document.getId());
                                dsTicketsSuat20h.add(tickets);
                            }
                            if(dsTicketsSuat20h.size()==0){
                                soLuongSuat20h = 0;
                            }else {
                                for (int i=0; i<dsTicketsSuat20h.size();i++){
                                    Tickets tickets = dsTicketsSuat20h.get(i);
                                    int soLuong = Integer.parseInt(tickets.getSoLuong());
                                    soLuongSuat20h+=soLuong;
                                }
                            }

                            if (soLuongSuat20h == 50) {
                                for (int i = 0; i < dsSuatXem.size(); i++) {
                                    if (dsSuatXem.get(i).getSuatXem().equals("20:00")) {
                                        dsSuatXem.remove(i);
                                    }
                                }
                            }

                            adapterSuatXem = new AdapterSuatXem(dsSuatXem, itemClickListenerSuatXem);
                            rv_chon_suat_xem.setAdapter(adapterSuatXem);
                        }
                    }
                });
    }

}