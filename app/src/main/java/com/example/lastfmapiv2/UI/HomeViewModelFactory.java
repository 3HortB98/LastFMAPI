package com.example.lastfmapiv2.UI;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.lastfmapiv2.model.DataSource;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private final DataSource repository;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(HomeViewModel.class)){
            return (T) new HomeViewModel(this.repository);
        }else{
            throw new IllegalArgumentException("Unexpected Class");
        }

    }

    public HomeViewModelFactory(DataSource repository){
        this.repository = repository;
    }
}
