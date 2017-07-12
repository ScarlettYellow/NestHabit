package com.victor.nesthabit.presenter;

import com.victor.nesthabit.data.BirdCageInfo;
import com.victor.nesthabit.contacts.BirdCageContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 7/12/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class BirdCagePresenter implements BirdCageContact.Presenter {
    private final BirdCageContact.View mView;
    public BirdCagePresenter(BirdCageContact.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        List<BirdCageInfo> mBirdCageInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BirdCageInfo info = new BirdCageInfo();
            info.setDay_insist(7);
            info.setDay_total(100);
            info.setInfo("早起的鸟儿有虫吃");
            info.setPeople(15);
            mBirdCageInfos.add(info);
        }
        mView.showRecyclerview(mBirdCageInfos);
    }
}
