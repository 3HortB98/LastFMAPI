package com.example.lastfmapiv2.UI;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.lastfmapiv2.Constants;
import com.example.lastfmapiv2.data.Artist;
import com.example.lastfmapiv2.model.DataSource;

import java.util.List;
import java.util.Observable;
import java.util.Observer;



public class HomeViewModel extends ViewModel implements Observer {
    private final MutableLiveData artistObservable;
    private final DataSource repository;
    private ResultsAdapter resultsAdapter;

    public HomeViewModel(DataSource repository) {
        this.repository = repository;
        this.artistObservable = new MutableLiveData();
    }

    public final LiveData getArtistObservable(){
        return artistObservable;
    }

    public final void getArtists(String artist){
        resultsAdapter = new ResultsAdapter();
        repository.setObserver(this);
        repository.getArtist(Constants.METHOD,artist,Constants.API_KEY,Constants.FORMAT);

    }

    @Override
    public void update(Observable o, Object results) {
        List<Artist> artists = (List<Artist>) results;
        artistObservable.setValue(results);


    }
}
