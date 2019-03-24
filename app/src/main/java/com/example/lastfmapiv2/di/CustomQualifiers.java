package com.example.lastfmapiv2.di;



import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class CustomQualifiers {
    @Qualifier
    @Retention(RUNTIME)
    public @interface Remote{

    }

    @Qualifier
    @Retention(RUNTIME)
    public @interface Local{

    }

    @Qualifier
    @Retention(RUNTIME)
    public @interface Repo{

    }
}
