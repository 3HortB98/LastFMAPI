package com.example.lastfmapiv2.model;

import com.example.lastfmapiv2.Constants;
import com.example.lastfmapiv2.data.Artist;
import com.example.lastfmapiv2.data.ArtistResponse;
import com.example.lastfmapiv2.model.DataSource;
import com.example.lastfmapiv2.net.LastFMService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource extends Observable implements DataSource {
    private LastFMService lastFMService;


    public RemoteDataSource(LastFMService lastFMService){
        this.lastFMService = lastFMService;
    }

    @Override
    public void getArtist(String method, String artist, String api, String format) {
        final List<Artist> artists = new ArrayList<>();
        lastFMService.getArtistSearch(method,artist,api,format).enqueue(new Callback<ArtistResponse>() {
            @Override
            public void onResponse(Call<ArtistResponse> call, Response<ArtistResponse> response) {
                artists.clear();
                artists.addAll(response.body().getResults().getArtistmatches().getArtist());
                setChanged();
                notifyObservers(artists);
            }

            @Override
            public void onFailure(Call<ArtistResponse> call, Throwable t) {
                t.fillInStackTrace();
            }
        });
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }
}
