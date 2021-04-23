package com.shiva.customcomponentapp.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.shiva.customcomponentapp.customviews.HorizontalComponent;
import com.shiva.customcomponentapp.customviews.VerticalComponent;
import com.shiva.customcomponentapp.datasources.AlbumEntity;
import com.shiva.customcomponentapp.datasources.ArtistEntity;
import com.shiva.customcomponentapp.datasources.AudiobookEntity;
import com.shiva.customcomponentapp.datasources.BaseEntity;
import com.shiva.customcomponentapp.datasources.PlaylistEntity;
import com.shiva.customcomponentapp.datasources.SongEntity;
import com.shiva.customcomponentapp.datasources.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 14-01-2018.
 */

public class BaseFragment extends Fragment implements HorizontalComponent.OnHorizontalEventListener, VerticalComponent.VerticalEventListener {
    private static final String TAG = "BaseFragment";

    @Override
    public void onHorizontalFooterClicked(HorizontalComponent.SectionType sectionType, HorizontalComponent.SectionIndex sectionIndex) {
        if (sectionType == HorizontalComponent.SectionType.ALBUM_SECTION) {
            Log.d("Horizontal", "AlbumModifiedComponent:- " + sectionType);

        }
        if (sectionType == HorizontalComponent.SectionType.PLAYLIST_SECTION) {
            Log.d("Horizontal", "PlaylistModifiedComponent:- " + sectionType);

        }
    }

    @Override
    public void onHorizontalItemClicked(HorizontalComponent.SectionType sectionType, BaseEntity baseEntity, int position, HorizontalComponent.SectionIndex sectionIndex) {

        if (sectionType == HorizontalComponent.SectionType.ALBUM_SECTION) {
            Log.d("Horizontal", "AlbumModifiedComponent:- " + sectionType + " position:- " + position);
        }
        if (sectionType == HorizontalComponent.SectionType.PLAYLIST_SECTION) {
            Log.d("Horizontal", "PlaylistModifiedComponent:- " + sectionType + " position:- " + position);
        }
    }

    @Override
    public void onSongsMenuClick(VerticalComponent.SectionType sectionType, BaseEntity baseEntity, int position) {
        Log.d(TAG, "onSongsMenuClick: called : sectionType " + sectionType  + " position:- " + position);
    }

    @Override
    public void onVerticalFooterClicked(VerticalComponent.SectionType sectionType, List<? extends BaseEntity> baseList) {
        Log.d(TAG, "onVerticalFooterClicked: called : "+sectionType);
    }


    public List<BaseEntity> setAlbumData() {
        List<BaseEntity> list = new ArrayList<>();
        list.add(new AlbumEntity("Item One", "1"));
        list.add(new AlbumEntity("Item Two", "2"));
        list.add(new AlbumEntity("Item Three", "3"));
        list.add(new AlbumEntity("Item Four", "4"));
        list.add(new AlbumEntity("Item Five", "5"));
        list.add(new AlbumEntity("Item Six", "6"));
        list.add(new AlbumEntity("Item Seven", "7"));
        list.add(new AlbumEntity("Item Eight", "8"));
        list.add(new AlbumEntity("Item Nine", "9"));
        list.add(new AlbumEntity("Item Ten", "10"));
        return list;
    }

    public List<BaseEntity> setSongData() {
        List<BaseEntity> list = new ArrayList<>();
        list.add(new SongEntity("Item One", "1"));
        list.add(new SongEntity("Item Two", "2"));
        list.add(new SongEntity("Item Three", "3"));
        list.add(new SongEntity("Item Four", "4"));
        list.add(new SongEntity("Item Five", "5"));
        list.add(new SongEntity("Item Six", "6"));
        list.add(new SongEntity("Item Seven", "7"));
        list.add(new SongEntity("Item Eight", "8"));
        list.add(new SongEntity("Item Nine", "9"));
        list.add(new SongEntity("Item Ten", "10"));
        return list;
    }
}
