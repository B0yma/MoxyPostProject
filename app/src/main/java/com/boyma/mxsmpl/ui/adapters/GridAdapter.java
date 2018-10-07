package com.boyma.mxsmpl.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boyma.mxsmpl.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ItemViewHolder> {

    private List<String> data;
    private Context ctx;

    public GridAdapter(Context context) {
        data = new ArrayList<>();
        this.ctx = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gal, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        //Picasso.get().load(data.get(position)).placeholder(android.R.drawable.ic_menu_crop).into(holder.image);
        File f = new File(data.get(position));
        Picasso.get().load(f).fit().into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<String> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private ImageView image;
        private TextView body;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            body =  itemView.findViewById(R.id.body);
            image =  itemView.findViewById(R.id.image);
            progressBar =  itemView.findViewById(R.id.progressBar);

        }
    }
}
