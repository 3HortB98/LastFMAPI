package com.example.lastfmapiv2.di;

import com.example.lastfmapiv2.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {NetworkModule.class, ApplicationModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
