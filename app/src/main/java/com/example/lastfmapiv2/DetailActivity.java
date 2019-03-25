package com.example.lastfmapiv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lastfmapiv2.data.Artist;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_detail);

        String name = getIntent().getStringExtra(ArtistFragment.ARTIST_NAME);
        String listeners= getIntent().getStringExtra(ArtistFragment.ARTIST_LISTENER);
        String mbid= getIntent().getStringExtra(ArtistFragment.ARTIST_MBID);
        String url= getIntent().getStringExtra(ArtistFragment.ARTIST_URL);
        String streamable= getIntent().getStringExtra(ArtistFragment.ARTIST_STREAMABLE);


        ArtistFragment artistFragment = new ArtistFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ArtistFragment.ARTIST_NAME, name);
        bundle.putString(ArtistFragment.ARTIST_NAME, name);
        bundle.putString(ArtistFragment.ARTIST_LISTENER,listeners);
        bundle.putString(ArtistFragment.ARTIST_MBID,mbid);
        bundle.putString(ArtistFragment.ARTIST_URL,url);
        bundle.putString(ArtistFragment.ARTIST_STREAMABLE,streamable);


        artistFragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.resultsContainer, artistFragment)
                .commit();
    }
}
