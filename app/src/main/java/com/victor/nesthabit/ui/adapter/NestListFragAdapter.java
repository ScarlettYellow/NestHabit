package com.victor.nesthabit.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.nesthabit.R;
import com.victor.nesthabit.bean.NestInfo;
import com.victor.nesthabit.ui.activity.NestSpecificActivity;
import com.victor.nesthabit.ui.presenter.AddNestPresenter;
import com.victor.nesthabit.ui.presenter.NestListPresenter;
import com.victor.nesthabit.util.ActivityManager;
import com.victor.nesthabit.util.Utils;

import java.util.List;

import static com.victor.nesthabit.R.id.progress_text;

/**
 * Created by victor on 7/2/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science
 */

public class NestListFragAdapter extends RecyclerView.Adapter<NestListFragAdapter.ListViewHolder>
        implements AddNestPresenter.OnCageDataChanged, NestListPresenter.onNestInfoAdded {
    public static final String TAG = "@victor NestLisAdapter";
    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<NestInfo> mBirdCageInfos;

    public NestListFragAdapter(Context context, RecyclerView recyclerView, List<NestInfo>
            birdCageInfos) {
        mContext = context;
        mRecyclerView = recyclerView;
        mBirdCageInfos = birdCageInfos;
        AddNestPresenter.setOnCageDataChanged(this);
        NestListPresenter.setOnNestInfoAdded(this);
    }

    @Override
    public void OnDataAdded(NestInfo cageInfo) {
        mBirdCageInfos.add(cageInfo);
        notifyDataSetChanged();
    }

    @Override
    public void addNestInfos(List<NestInfo> nestInfos) {
        mBirdCageInfos.clear();
        mBirdCageInfos.addAll(nestInfos);
        notifyDataSetChanged();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        ListViewHolder holder;
        view = inflater.inflate(R.layout.birdcagelist, parent, false);
        holder = new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        NestInfo info = mBirdCageInfos.get(position);
//        holder.progresstext.setText(info.getDay_insist() + "/" + info
//                .getChallenge_days());
        boolean isOwner = info.owner.equals(Utils.getUsername());
        holder.birdcageListText.setText(info.getName());
        holder.peoplea.setText("+" + info.getMembers_amount() + "人");
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NestSpecificActivity.class);
                intent.putExtra("id", info.get_id());
                intent.putExtra("isOwner", isOwner);
                ActivityManager.startActivity((Activity) mContext, intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBirdCageInfos.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView birdcageListImage;
        private TextView birdcageListText;
        private TextView progresstext;
        private TextView peoplea;
        private CardView mCardView;


        ListViewHolder(View itemView) {
            super(itemView);
            birdcageListImage = (ImageView) itemView.findViewById(R.id.birdcage_list_image);
            birdcageListText = (TextView) itemView.findViewById(R.id.birdcage_list_text);
            progresstext = (TextView) itemView.findViewById(progress_text);
            peoplea = (TextView) itemView.findViewById(R.id.people);
            mCardView = (CardView) itemView.findViewById(R.id.card);
        }

    }


}
