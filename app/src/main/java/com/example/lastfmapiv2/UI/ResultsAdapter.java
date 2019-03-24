package com.example.lastfmapiv2.UI;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastfmapiv2.R;
import com.example.lastfmapiv2.data.Artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.itemViewHolder> {
    private final List<Artist> artists = new ArrayList<>();


    public void setData(List<Artist> newData){
        artists.clear();
        artists.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_results,viewGroup,false);
        return new itemViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder itemViewHolder, int position) {
        itemViewHolder.bind(artists.get(position));
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    static class itemViewHolder extends RecyclerView.ViewHolder {
            TextView tvName;
            ImageView imageView;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            imageView = itemView.findViewById(R.id.imageView);
        }
        public void bind( Artist artist){
            String image = artist.getImage().get(1).getText();
            Picasso.get().load(image).into(imageView);
            tvName.setText(artist.getName());
        }

    }
}
