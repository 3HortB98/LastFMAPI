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
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lastfmapiv2.Constants.BASE_URL;

public class RemoteDataSource extends Observable implements DataSource {
    private final LastFMService lastFMService;


    public RemoteDataSource(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();

        lastFMService = retrofit.create(LastFMService.class);
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
