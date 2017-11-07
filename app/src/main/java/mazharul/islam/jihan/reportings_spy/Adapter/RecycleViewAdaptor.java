package mazharul.islam.jihan.reportings_spy.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import mazharul.islam.jihan.reportings_spy.R;

/**
 * Created by Jihan on 31-Oct-17.
 */

public class RecycleViewAdaptor extends RecyclerView.Adapter<RecycleViewAdaptor.ViewHolder> {

    ArrayList<Uri> alImage;
    Context context;

    public RecycleViewAdaptor(Context context, ArrayList<Uri> alImage) {
        super();
        this.context = context;
        this.alImage = alImage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.ricycl_view_image_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        System.out.println("i is "+i);
//        viewHolder.imgThumbnail.setImageURI(alImage.get(i));
        Glide
                .with(context)
                .load(alImage.get(i))
                .into(viewHolder.imgThumbnail);


        viewHolder.crossimgThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alImage.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return alImage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public ImageView crossimgThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            crossimgThumbnail = (ImageView) itemView.findViewById(R.id.CancelPreviewOneimageView);
        }
    }

}