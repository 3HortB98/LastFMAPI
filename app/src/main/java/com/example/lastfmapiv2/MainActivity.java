package com.example.lastfmapiv2;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lastfmapiv2.UI.HomeViewModel;
import com.example.lastfmapiv2.UI.HomeViewModelFactory;
import com.example.lastfmapiv2.UI.ResultsAdapter;
import com.example.lastfmapiv2.di.AppComponent;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;

public class MainActivity extends AppCompatActivity {

    @Inject
    public HomeViewModelFactory homeViewModelFactory;

    private AppComponent appComponent;

    Button btnSearch;
    EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnSearch= findViewById(R.id.btnSearch);
        etInput = findViewById(R.id.etInput);

        ViewDataBinding dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        final ResultsAdapter resultsAdapter = new  ResultsAdapter();

        RecyclerView recyclerView = findViewById(R.id.rvResults);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(resultsAdapter);

        appComponent.inject(this);
        final HomeViewModel homeViewModel = ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel.class);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.getArtists(etInput.getText().toString());
            }
        });



    }

}
