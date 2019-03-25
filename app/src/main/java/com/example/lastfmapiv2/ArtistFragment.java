package com.example.lastfmapiv2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistFragment extends Fragment {
    public static final String ARTIST_NAME = "artist_name";
    public static final String ARTIST_LISTENER ="artist_listener";
    public static final String ARTIST_MBID="artist_mbid";
    public static final String ARTIST_URL="artist_url";
    public static final String ARTIST_STREAMABLE="artist_streamable";

    private String name = "";
    private String listeners ="";
    private String mbid= "";
    private String url= "";
    private String streamable= "";

    public ArtistFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARTIST_NAME)) {
            name = getArguments().getString(ARTIST_NAME);
            listeners= getArguments().getString(ARTIST_LISTENER);
            mbid= getArguments().getString(ARTIST_MBID);
            url= getArguments().getString(ARTIST_URL);
            streamable=getArguments().getString(ARTIST_STREAMABLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.result_detail, container, false);

        TextView tvName= rootView.findViewById(R.id.tvName);
        TextView tvListeners= rootView.findViewById(R.id.tvListeners);
        TextView tvMBID= rootView.findViewById(R.id.tvMBID);
        TextView tvURL= rootView.findViewById(R.id.tvURL);
        TextView tvStreamable= rootView.findViewById(R.id.tvStreamable);

        tvName.setText(name);
        tvListeners.setText(listeners);
        tvMBID.setText(mbid);
        tvURL.setText(url);
        tvStreamable.setText(streamable);


        return rootView;
    }
}
