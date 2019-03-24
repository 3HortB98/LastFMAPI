package com.example.lastfmapiv2.di;

import com.example.lastfmapiv2.model.DataSource;
import com.example.lastfmapiv2.model.RemoteDataSource;
import com.example.lastfmapiv2.net.LastFMService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lastfmapiv2.Constants.BASE_URL;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public  HttpLoggingInterceptor provideLogger(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitClient(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public LastFMService provideService(Retrofit retrofit){
        return retrofit.create(LastFMService.class);
    }

    @Provides
    @Singleton
    public DataSource provideRemoteDataSource(LastFMService service){
        return new RemoteDataSource(service);
    }



}
