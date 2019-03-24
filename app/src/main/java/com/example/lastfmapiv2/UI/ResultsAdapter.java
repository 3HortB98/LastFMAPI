package com.example.lastfmapiv2.UI;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lastfmapiv2.data.Artist;
import com.example.lastfmapiv2.databinding.ItemResultsBinding;

import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.itemViewHolder> {
    private final List<Artist> artists = new ArrayList();

    public void setData(List<Artist> newData){
        artists.clear();
        artists.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater rootView = LayoutInflater.from(viewGroup.getContext());
                //.inflate(R.layout.item_results,viewGroup,false);

        return new itemViewHolder(ItemResultsBinding.inflate(rootView,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder itemViewHolder, int position) {
itemViewHolder.tvName.setText(artists.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    static class itemViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ItemResultsBinding itemResultsBinding;
        public itemViewHolder(@NonNull ItemResultsBinding itemResultsBinding) {
            super(itemResultsBinding.getRoot());
        }
        public final void bind(Artist artist){
            itemResultsBinding.setArtists(artist);
            itemResultsBinding.executePendingBindings();
        }
    }
}
