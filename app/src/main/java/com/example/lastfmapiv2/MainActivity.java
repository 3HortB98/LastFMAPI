package com.example.lastfmapiv2;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.lastfmapiv2.UI.HomeViewModel;
import com.example.lastfmapiv2.UI.ResultsAdapter;
import com.example.lastfmapiv2.data.Artist;

import java.util.List;



public class MainActivity extends AppCompatActivity {

ProgressBar progressBar;
    Button btnSearch;
    EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch= findViewById(R.id.btnSearch);
        etInput = findViewById(R.id.etInput);
        progressBar = findViewById(R.id.pbProgress);

        final ResultsAdapter resultsAdapter = new  ResultsAdapter();

        RecyclerView recyclerView = findViewById(R.id.rvResults);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(resultsAdapter);

        //appComponent.inject(this);

        final HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.getArtistObservable().observe(this, new Observer<List<Artist>>() {
            @Override
            public void onChanged(@Nullable List<Artist> artists) {
                resultsAdapter.setData(artists);
                progressBar.setVisibility(View.GONE);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                homeViewModel.getArtists(etInput.getText().toString());
                onSearchRequested();

            }
        });



    }

}
