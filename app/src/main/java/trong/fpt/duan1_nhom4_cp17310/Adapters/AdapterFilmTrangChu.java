package trong.fpt.duan1_nhom4_cp17310.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;

import trong.fpt.duan1_nhom4_cp17310.Interfaces.ItemClickListener;
import trong.fpt.duan1_nhom4_cp17310.Interfaces.OnItemClickFilmManager;
import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.ViewHolder.FilmManagerViewHolder;
import trong.fpt.duan1_nhom4_cp17310.ViewHolder.FilmTrangChuViewHolder;
import trong.fpt.duan1_nhom4_cp17310.models.Film;
import trong.fpt.duan1_nhom4_cp17310.views.TicketsActivity;

public class AdapterFilmTrangChu extends RecyclerView.Adapter<FilmTrangChuViewHolder> {
    Context context;
    ArrayList<Film> dsFilm;
    public AdapterFilmTrangChu(Context context, ArrayList<Film> dsFilm) {
        this.context = context;
        this.dsFilm = dsFilm;
    }

    @NonNull
    @Override
    public FilmTrangChuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_film_trangchu, parent, false);
        return new FilmTrangChuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmTrangChuViewHolder holder, int position) {
        Film film = dsFilm.get(position);
        String imageLink = film.getLinkAnh();
        new DownloadImageFromInternet(holder.imv_film_manager).execute(imageLink);
        holder.btn_datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TicketsActivity.class);
                intent.putExtra("thongTinPhim", film);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsFilm.size();
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView=imageView;
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
