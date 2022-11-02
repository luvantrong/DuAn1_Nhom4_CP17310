package trong.fpt.duan1_nhom4_cp17310.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import trong.fpt.duan1_nhom4_cp17310.R;
import trong.fpt.duan1_nhom4_cp17310.models.Photo;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private final List<Photo> mList;
    public PhotoAdapter(List<Photo> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = mList.get(holder.getAdapterPosition());
        if(photo == null){
            return;
        }
        holder.imgPhoto.setImageResource(photo.getImage());


//        holder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                Toast.makeText(view.getContext(), holder.getAdapterPosition()+"", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
//        implements View.OnLongClickListener

        private final ImageView imgPhoto;
//        private ItemClickListener itemClickListener;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnLongClickListener(this);
            imgPhoto = itemView.findViewById(R.id.im_im);
        }

//        public void setItemClickListener(ItemClickListener itemClickListener)
//        {
//            this.itemClickListener = itemClickListener;
//        }
//
//        @Override
//        public boolean onLongClick(View view) {
//            itemClickListener.onClick(view,getAdapterPosition(),true);
//            return true;
//        }
    }
}
