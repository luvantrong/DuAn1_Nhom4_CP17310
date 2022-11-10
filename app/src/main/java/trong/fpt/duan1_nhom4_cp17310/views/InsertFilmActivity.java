package trong.fpt.duan1_nhom4_cp17310.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import trong.fpt.duan1_nhom4_cp17310.R;

public class InsertFilmActivity extends AppCompatActivity {

    private ImageView imv_choose_image_film;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_film);

        imv_choose_image_film = findViewById(R.id.imv_choose_image_film);
    }
}