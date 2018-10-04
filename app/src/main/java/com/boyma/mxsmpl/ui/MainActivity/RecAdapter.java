package com.boyma.mxsmpl.ui.MainActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boyma.mxsmpl.R;
import com.boyma.mxsmpl.data.jsonplaceholder.allnews.models.ResponseJsonObj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.NewsViewHolder> {

    private List<ResponseJsonObj> data;

    public RecAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.body.setText(data.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<ResponseJsonObj> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView title,body;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
