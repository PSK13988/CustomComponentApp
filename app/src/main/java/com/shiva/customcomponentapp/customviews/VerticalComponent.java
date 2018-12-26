package com.shiva.customcomponentapp.customviews;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shiva.customcomponentapp.R;
import com.shiva.customcomponentapp.datasources.AlbumEntity;
import com.shiva.customcomponentapp.datasources.ArtistEntity;
import com.shiva.customcomponentapp.datasources.BaseEntity;
import com.shiva.customcomponentapp.datasources.PlaylistEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 14-01-2018.
 */

public class VerticalComponent extends RelativeLayout {

    private Context mContext;
    private View mRootView;
    private TextView tvHeaderTitle, tvShuffle, tvSortBy, tvFooterTitle;
    private ImageView ivCloseMenu;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View bottomSaparator;

    public static int SORTING_TYPE = 1;
    public static int ALPHA_SORT = 1;
    public static int ADVANCED_SORT = 2;
    public VerticalEventListener verticalEventListener;
    public Options options;
    private SectionType sectionType;
    private List<? extends BaseEntity> baseList;

    public enum Options {
        SHUFFLE,
        SHUFFLE_AND_SORT_BY,
        FOOTER_ONLY,
        SHUFFLE_WITH_FOOTER,
        SHUFFLE_AND_SORT_BY_WITH_FOOTER,
        OFF
    }

    public enum SectionType {
        ALBUM_LIST,
        ARTIST_LIST,
        PLAYLIST_LIST,
        MUSIC_VIDEO_LIST,
        AUDIOBOOK_LIST,
        SONG_LIST
    }

    public void setSortingType(int type) {
        SORTING_TYPE = type;
    }

    public interface VerticalEventListener {
//        void onSongShuffle();

        void onSongsMenuClick(SectionType sectionType, BaseEntity baseEntity, int position);

        void onVerticalFooterClicked(SectionType sectionType, List<? extends BaseEntity> baseList);
    }

    public VerticalComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initView();
    }

    public VerticalComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public VerticalComponent(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public List<? extends BaseEntity> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<? extends BaseEntity> baseList) {
        this.baseList = baseList;
    }

    public void setVerticalEventListener(VerticalEventListener verticalEventListener, Options options, SectionType sectionType) {
        this.verticalEventListener = verticalEventListener;
        this.options = options;
        this.sectionType = sectionType;
        setOptions();
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void initView() {

        mRootView = inflate(mContext, R.layout.layout_vertical_component, null);

        tvHeaderTitle = (TextView) mRootView.findViewById(R.id.tvHeaderTitle);
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        bottomSaparator = mRootView.findViewById(R.id.bottomSaparator);
        tvFooterTitle = (TextView) mRootView.findViewById(R.id.tvFooterTitle);

        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CustomAdapter();
        recyclerView.setAdapter(mAdapter);

        RelativeLayout.LayoutParams labelLayoutParams = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mRootView, labelLayoutParams);
    }

    public void setHeaderTitle(String title) {
        tvHeaderTitle.setText(title);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvContentTitle;
        public TextView tvContentDesc;
        public ImageView ivMenu;
        public ImageView ivContent;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            ivContent = (ImageView) v.findViewById(R.id.ivContent);
            ivMenu = (ImageView) v.findViewById(R.id.ivMenu);
            tvContentTitle = (TextView) v.findViewById(R.id.tvContentTitle);
            tvContentDesc = (TextView) v.findViewById(R.id.tvContentDesc);
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            // create a new view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.layout_vertical_list_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);

            if (sectionType == SectionType.ARTIST_LIST || sectionType == SectionType.PLAYLIST_LIST)
                vh.ivMenu.setVisibility(GONE);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            switch (sectionType) {
                case ALBUM_LIST:
                    if (baseList != null && !baseList.isEmpty() && baseList.size() > 0) {
                        holder.ivContent.setImageResource(R.drawable.ic_album);
                        holder.tvContentTitle.setText(((ArrayList<AlbumEntity>) baseList).get(position).getTitle());
                        holder.tvContentDesc.setText(((ArrayList<AlbumEntity>) baseList).get(position).getDescription());

                        holder.ivMenu.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (verticalEventListener != null) {
                                    verticalEventListener.onSongsMenuClick(sectionType, baseList.get(position), position);
                                }
                            }
                        });
                    }
                    break;
                case ARTIST_LIST:
                    if (baseList != null && !baseList.isEmpty() && baseList.size() > 0) {

                        holder.ivContent.setImageResource(R.drawable.ic_person_outline);
                        holder.tvContentTitle.setText(((ArrayList<ArtistEntity>) baseList).get(position).getTitle());
                        holder.tvContentDesc.setText(((ArrayList<ArtistEntity>) baseList).get(position).getDescription());
                    }


                    break;

                case PLAYLIST_LIST:
                    if (baseList != null && !baseList.isEmpty() && baseList.size() > 0) {
                        holder.ivContent.setImageResource(R.drawable.ic_playlist_play);
                        holder.tvContentTitle.setText(((ArrayList<PlaylistEntity>) baseList).get(position).getTitle());
                        holder.tvContentDesc.setText(((ArrayList<PlaylistEntity>) baseList).get(position).getDescription());

                        holder.ivMenu.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (verticalEventListener != null) {
                                    verticalEventListener.onSongsMenuClick(sectionType, baseList.get(position), position);
                                }
                            }
                        });
                    }
                    break;
            }


        }

        @Override
        public int getItemCount() {
            if (baseList != null && baseList.size() > 0)
                return baseList.size();
            return 0;
        }
    }

    public void setOptions() {
        switch (options) {
            case SHUFFLE:
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tvShuffle.getLayoutParams();
                tvShuffle.setVisibility(VISIBLE);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                tvShuffle.setLayoutParams(params);
                break;

            case SHUFFLE_AND_SORT_BY:
                tvSortBy.setVisibility(VISIBLE);
                tvShuffle.setVisibility(VISIBLE);
                break;

            case FOOTER_ONLY:
                tvFooterTitle.setVisibility(VISIBLE);
                //bottomSaparator.setVisibility(VISIBLE);
                tvShuffle.setVisibility(GONE);
                tvSortBy.setVisibility(GONE);
                break;

            case SHUFFLE_WITH_FOOTER:
                tvFooterTitle.setVisibility(VISIBLE);
                //bottomSaparator.setVisibility(VISIBLE);
                tvShuffle.setVisibility(VISIBLE);
                tvSortBy.setVisibility(GONE);
                params = (RelativeLayout.LayoutParams) tvShuffle.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                tvShuffle.setLayoutParams(params);
                break;

            case SHUFFLE_AND_SORT_BY_WITH_FOOTER:
                tvFooterTitle.setVisibility(VISIBLE);
                //bottomSaparator.setVisibility(VISIBLE);
                tvShuffle.setVisibility(VISIBLE);
                tvSortBy.setVisibility(VISIBLE);
                break;
        }
    }
}