package com.example.lastfmapiv2.model;

import com.example.lastfmapiv2.data.Artist;

import java.util.Observer;


public interface DataSource {
    void getArtist(String method, String artist, String api, String format);

    void setObserver(Observer observer);
}
