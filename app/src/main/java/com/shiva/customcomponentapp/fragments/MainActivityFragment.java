package com.shiva.customcomponentapp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiva.customcomponentapp.R;
import com.shiva.customcomponentapp.customviews.HorizontalComponent;
import com.shiva.customcomponentapp.customviews.VerticalComponent;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {
    private HorizontalComponent mHorizontalComponent;
    private VerticalComponent mVerticalComponent;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mHorizontalComponent = (HorizontalComponent) view.findViewById(R.id.horizontalComponent);
        mVerticalComponent = (VerticalComponent) view.findViewById(R.id.verticalComponent);

        initView();
        return view;
    }

    private void initView() {
        mHorizontalComponent.setHeaderTitle("Horizontal Component Title");
        mHorizontalComponent.setFooterTitle("View All");
        mHorizontalComponent.setBaseList(setAlbumData());
        mHorizontalComponent.setOnHorizontalEventListener(this, HorizontalComponent.SectionType.ALBUM_SECTION, HorizontalComponent.SectionIndex.DEFAULT);

        mVerticalComponent.setHeaderTitle("Vertical Component Title");
        mVerticalComponent.setVerticalEventListener(this, VerticalComponent.Options.OFF, VerticalComponent.SectionType.SONG_LIST);
        mVerticalComponent.setBaseList(setPlaylistData());

    }
}
