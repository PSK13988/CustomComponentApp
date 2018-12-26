package com.shiva.customcomponentapp.customviews;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shiva.customcomponentapp.R;
import com.shiva.customcomponentapp.datasources.AlbumEntity;
import com.shiva.customcomponentapp.datasources.ArtistEntity;
import com.shiva.customcomponentapp.datasources.AudiobookEntity;
import com.shiva.customcomponentapp.datasources.BaseEntity;
import com.shiva.customcomponentapp.datasources.PlaylistEntity;
import com.shiva.customcomponentapp.datasources.VideoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 13-01-2018.
 */

public class HorizontalComponent extends RelativeLayout {
    private Context mContext;
    private View mRootView;
    private TextView tvHeaderTitle, tvFooterTitle;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SectionType sectionType;
    private SectionIndex sectionIndex;
    private OnHorizontalEventListener onHorizontalEventListener;
    private List<? extends BaseEntity> baseList;

    public enum SectionType {
        ALBUM_SECTION,
        ARTIST_SECTION,
        PLAYLIST_SECTION,
        MUSIC_VIDEO_SECTION,
        AUDIOBOOK_SECTION
    }

    public enum SectionIndex {
        DEFAULT, ONE, TWO, THREE, FOUR, FIVE
    }

    public interface OnHorizontalEventListener {
        void onHorizontalFooterClicked(SectionType sectionType, SectionIndex sectionIndex);

        void onHorizontalItemClicked(SectionType sectionType, BaseEntity baseEntity, int position, SectionIndex sectionIndex);
    }

    public HorizontalComponent(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public HorizontalComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public HorizontalComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void setHeaderTitle(String title) {
        tvHeaderTitle.setText(title);
    }

    public void setFooterTitle(String title) {
        tvFooterTitle.setText(title);
    }

    public void setOnHorizontalEventListener(OnHorizontalEventListener onHorizontalEventListener, SectionType sectionType, SectionIndex sectionIndex) {
        this.onHorizontalEventListener = onHorizontalEventListener;
        this.sectionType = sectionType;
        this.sectionIndex = sectionIndex;
    }

    public List<? extends BaseEntity> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<? extends BaseEntity> baseList) {
        this.baseList = baseList;
    }

    private void initView() {

        mRootView = inflate(mContext, R.layout.layout_horizontal_component, null);

        recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        tvHeaderTitle = (TextView) mRootView.findViewById(R.id.tvHeaderTitle);
        tvFooterTitle = (TextView) mRootView.findViewById(R.id.tvFooterTitle);

        tvFooterTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onHorizontalEventListener != null) {
                    onHorizontalEventListener.onHorizontalFooterClicked(sectionType, sectionIndex);
                }
            }
        });

        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mContext, LinearLayoutManager.HORIZONTAL);
        //recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CustomAdapter();
        recyclerView.setAdapter(mAdapter);

        //recyclerView.setOnScrollListener(new CustomScrollListener());

        RelativeLayout.LayoutParams labelLayoutParams = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mRootView, labelLayoutParams);

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvContentTitle;
        public TextView tvContentDesc;
        public ImageView ivContent;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            ivContent = (ImageView) v.findViewById(R.id.ivContent);
            tvContentTitle = (TextView) v.findViewById(R.id.tvContentTitle);
            tvContentDesc = (TextView) v.findViewById(R.id.tvContentDesc);
        }
    }


    public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.layout_horizontal_list_item, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            switch (sectionType) {
                case ALBUM_SECTION:
                    if (baseList != null && !baseList.isEmpty() && baseList.size() > 0) {
                        holder.ivContent.setImageResource(R.drawable.ic_album);
                        holder.tvContentTitle.setText(((ArrayList<AlbumEntity>) baseList).get(position).getTitle());
                        holder.tvContentDesc.setText(((ArrayList<AlbumEntity>) baseList).get(position).getDescription());

                        holder.layout.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (onHorizontalEventListener != null) {
                                    onHorizontalEventListener.onHorizontalItemClicked(sectionType, baseList.get(position), position, sectionIndex);
                                }
                            }
                        });
                    }
                    break;
                case PLAYLIST_SECTION:
                    if (baseList != null && !baseList.isEmpty() && baseList.size() > 0) {
                        holder.ivContent.setImageResource(R.drawable.ic_playlist_play);
                        holder.tvContentTitle.setText(((ArrayList<PlaylistEntity>) baseList).get(position).getTitle());
                        holder.tvContentDesc.setText(((ArrayList<PlaylistEntity>) baseList).get(position).getDescription());

                        holder.layout.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (onHorizontalEventListener != null) {
                                    onHorizontalEventListener.onHorizontalItemClicked(sectionType, baseList.get(position), position, sectionIndex);
                                }
                            }
                        });
                    }
                    break;
                case MUSIC_VIDEO_SECTION:
                    if (baseList != null && !baseList.isEmpty() && baseList.size() > 0) {
                        holder.ivContent.setImageResource(R.drawable.ic_music_video);
                        holder.tvContentTitle.setText(((ArrayList<VideoEntity>) baseList).get(position).getTitle());
                        holder.tvContentDesc.setText(((ArrayList<VideoEntity>) baseList).get(position).getDescription());

                        holder.layout.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (onHorizontalEventListener != null) {
                                    onHorizontalEventListener.onHorizontalItemClicked(sectionType, baseList.get(position), position, sectionIndex);
                                }
                            }
                        });
                    }
                    break;
                case ARTIST_SECTION:
                    if (baseList != null && !baseList.isEmpty() && baseList.size() > 0) {
                        holder.ivContent.setImageResource(R.drawable.ic_person_outline);
                        holder.tvContentTitle.setText(((ArrayList<ArtistEntity>) baseList).get(position).getTitle());
                        holder.tvContentDesc.setText(((ArrayList<ArtistEntity>) baseList).get(position).getDescription());

                        holder.layout.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (onHorizontalEventListener != null) {
                                    onHorizontalEventListener.onHorizontalItemClicked(sectionType, baseList.get(position), position, sectionIndex);
                                }
                            }
                        });
                    }
                    break;
                case AUDIOBOOK_SECTION:
                    if (baseList != null && !baseList.isEmpty() && baseList.size() > 0) {
                        holder.ivContent.setImageResource(R.drawable.ic_album);
                        holder.tvContentTitle.setText(((ArrayList<AudiobookEntity>) baseList).get(position).getTitle());
                        holder.tvContentDesc.setText(((ArrayList<AudiobookEntity>) baseList).get(position).getDescription());

                        holder.layout.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (onHorizontalEventListener != null) {
                                    onHorizontalEventListener.onHorizontalItemClicked(sectionType, baseList.get(position), position, sectionIndex);
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
}
