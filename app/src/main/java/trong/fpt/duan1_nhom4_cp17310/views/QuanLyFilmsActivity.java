package trong.fpt.duan1_nhom4_cp17310.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import trong.fpt.duan1_nhom4_cp17310.R;

public class QuanLyFilmsActivity extends AppCompatActivity {

    private FloatingActionButton btn_insert_film;
    private RecyclerView rv_manager_film;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_films);

        btn_insert_film = findViewById(R.id.btn_insert_film);
        rv_manager_film = findViewById(R.id.rv_film);

        rv_manager_film.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0   && btn_insert_film.isShown()){
                    btn_insert_film.hide();
                }else{
                    btn_insert_film.show();
                }
            }
        });

        btn_insert_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLyFilmsActivity.this, InsertFilmActivity.class);
                startActivity(intent);
            }
        });
    }
}