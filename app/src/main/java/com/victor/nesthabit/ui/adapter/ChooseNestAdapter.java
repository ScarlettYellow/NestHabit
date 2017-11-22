package com.victor.nesthabit.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.nesthabit.R;
import com.victor.nesthabit.bean.NestInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 7/2/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science
 */

public class ChooseNestAdapter extends RecyclerView.Adapter<ChooseNestAdapter.ListViewHolder> {
    public static final String TAG = "@victor NestLisAdapter";
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<NestInfo> mBirdCageInfos;
    private ListViewHolder tickholer;
    private String tickedid = null;


    public ChooseNestAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRecyclerView = recyclerView;
        mBirdCageInfos = new ArrayList<>();
    }

    public void addData(NestInfo nestInfo) {
        mBirdCageInfos.add(nestInfo);
        notifyDataSetChanged();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        ListViewHolder holder;
        view = inflater.inflate(R.layout.nestlist_choose, parent, false);
        holder = new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        NestInfo info = mBirdCageInfos.get(position);
        holder.birdcageListText.setText(info.getName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.redborder.getVisibility() == View.INVISIBLE) {
                    holder.redborder.setVisibility(View.VISIBLE);
                    if (tickholer != null) {
                        if (tickholer != holder) {
                            tickholer.redborder.setVisibility(View.INVISIBLE);
                        }
                        tickholer = holder;
                    } else {
                        tickholer = holder;
                    }
                    tickedid = info.getObjectId();
                } else {
                    holder.redborder.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    public String getNestName() {
        if (tickholer != null) {
            return tickholer.birdcageListText.getText().toString();
        }
        return null;
    }

    public String getNestId() {
        if (tickedid != null) {
            return tickedid;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mBirdCageInfos.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView birdcageListImage;
        private TextView birdcageListText;
        private CardView mCardView;
        private CardView redborder;


        ListViewHolder(View itemView) {
            super(itemView);
            birdcageListImage = (ImageView) itemView.findViewById(R.id.birdcage_list_image);
            birdcageListText = (TextView) itemView.findViewById(R.id.birdcage_list_text);
            mCardView = (CardView) itemView.findViewById(R.id.card);
            redborder = (CardView) itemView.findViewById(R.id.card_background);
        }

    }


}