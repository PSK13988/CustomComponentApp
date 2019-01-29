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
        list.add(new AlbumEntity("Album One", "1"));
        list.add(new AlbumEntity("Album Two", "2"));
        list.add(new AlbumEntity("Album Three", "3"));
        list.add(new AlbumEntity("Album Four", "4"));
        list.add(new AlbumEntity("Album Five", "5"));
        list.add(new AlbumEntity("Album Six", "6"));
        list.add(new AlbumEntity("Album Seven", "7"));
        list.add(new AlbumEntity("Album Eight", "8"));
        list.add(new AlbumEntity("Album Nine", "9"));
        list.add(new AlbumEntity("Album Ten", "10"));
        return list;
    }

    public List<BaseEntity> setArtistData() {
        List<BaseEntity> list = new ArrayList<>();
        list.add(new ArtistEntity("Artist One", "1"));
        list.add(new ArtistEntity("Artist Two", "2"));
        list.add(new ArtistEntity("Artist Three", "3"));
        list.add(new ArtistEntity("Artist Four", "4"));
        list.add(new ArtistEntity("Artist Five", "5"));
        return list;
    }

    public List<BaseEntity> setAudiobookData() {
        List<BaseEntity> list = new ArrayList<>();
        list.add(new AudiobookEntity("Audiobook One", "1"));
        list.add(new AudiobookEntity("Audiobook Two", "2"));
        list.add(new AudiobookEntity("Audiobook Three", "3"));
        list.add(new AudiobookEntity("Audiobook Four", "4"));
        list.add(new AudiobookEntity("Audiobook Five", "5"));
        return list;
    }

    public List<BaseEntity> setVideoData() {
        List<BaseEntity> list = new ArrayList<>();
        list.add(new VideoEntity("Video One", "1"));
        list.add(new VideoEntity("Video Two", "2"));
        list.add(new VideoEntity("Video Three", "3"));
        list.add(new VideoEntity("Video Four", "4"));
        list.add(new VideoEntity("Video Five", "5"));
        return list;
    }

    public List<BaseEntity> setPlaylistData() {
        List<BaseEntity> list = new ArrayList<>();
        list.add(new PlaylistEntity("Song 1", "1"));
        list.add(new PlaylistEntity("Song 2", "2"));
        list.add(new PlaylistEntity("Song 3", "3"));
        list.add(new PlaylistEntity("Song 4", "4"));
        list.add(new PlaylistEntity("Song 5", "5"));
        return list;
    }

    public List<BaseEntity> setSongData() {
        List<BaseEntity> list = new ArrayList<>();
        list.add(new SongEntity("Song 1", "1"));
        list.add(new SongEntity("Song 2", "2"));
        list.add(new SongEntity("Song 3", "3"));
        list.add(new SongEntity("Song 4", "4"));
        list.add(new SongEntity("Song 5", "5"));
        return list;
    }
}
