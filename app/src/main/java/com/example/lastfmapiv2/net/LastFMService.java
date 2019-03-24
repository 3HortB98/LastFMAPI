package com.example.lastfmapiv2.net;

import com.example.lastfmapiv2.data.ArtistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.lastfmapiv2.Constants.ENDPOINT;

public interface LastFMService {
    @GET(ENDPOINT)
    Call<ArtistResponse> getArtistSearch(@Query("method") String method, @Query("artist") String artist,
                                         @Query("api_key") String apiKey, @Query("format") String format);

}
