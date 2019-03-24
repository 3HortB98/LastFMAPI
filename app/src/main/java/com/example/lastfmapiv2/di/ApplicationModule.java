package com.example.lastfmapiv2.di;

import android.app.Application;

import com.example.lastfmapiv2.model.DataSource;
import com.example.lastfmapiv2.UI.HomeViewModelFactory;
import com.example.lastfmapiv2.model.LastFMRespository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application application;

    @Provides
    @Singleton
public Application provideApplicationContext(){
        return this.application;
    }

    @Provides
    @CustomQualifiers.Repo
    @Singleton
    public DataSource providesRepository(@CustomQualifiers.Local DataSource localDataSource, @CustomQualifiers.Remote DataSource remoteDataSource){

        return new LastFMRespository(localDataSource, remoteDataSource);

    }

    @Provides
    @Singleton
    public HomeViewModelFactory provideHomeViewModelFactory(@CustomQualifiers.Repo DataSource repository){
        return new HomeViewModelFactory(repository);
    }

    public  ApplicationModule(Application application){
        this.application = application;
    }
}
