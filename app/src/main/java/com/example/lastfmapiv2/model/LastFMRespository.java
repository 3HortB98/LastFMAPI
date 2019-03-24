package com.example.lastfmapiv2.model;

import com.example.lastfmapiv2.Constants;
import com.example.lastfmapiv2.data.Artist;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;


public class LastFMRespository extends Observable implements Observer, DataSource {
    private final DataSource localDataSource;
    private final DataSource remoteDataSource;

    public LastFMRespository(DataSource localDataSource, DataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public void getArtist(String method, String artist, String api, String format) {
        remoteDataSource.setObserver(this);
        remoteDataSource.getArtist(method,artist,api,format);
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }

    @Override
    public void update(Observable o, Object results) {
        setChanged();
        notifyObservers(results);
    }
}
