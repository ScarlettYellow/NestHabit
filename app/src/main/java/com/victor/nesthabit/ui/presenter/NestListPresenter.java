package com.victor.nesthabit.ui.presenter;

import com.victor.nesthabit.api.NestHabitApi;
import com.victor.nesthabit.bean.JoinedNests;
import com.victor.nesthabit.bean.NestInfo;
import com.victor.nesthabit.ui.base.RxPresenter;
import com.victor.nesthabit.ui.contract.NestListContract;
import com.victor.nesthabit.util.RxUtil;
import com.victor.nesthabit.util.Utils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by victor on 7/12/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class NestListPresenter extends RxPresenter implements NestListContract.Presenter {
    public static final String TAG = "@victor NestListPresen";
    private static onNestInfoAdded sOnNestInfoAdded;
    private final NestListContract.View mView;


    public NestListPresenter(NestListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    public static void setOnNestInfoAdded(onNestInfoAdded onnestInfoAdded) {
        sOnNestInfoAdded = onnestInfoAdded;
    }

    @Override
    public void start() {

    }

    @Override
    public void unscribe() {
        unSubscribe();
    }



    public interface onNestInfoAdded {
        void addNestInfos(List<NestInfo> nestInfos);
    }
}
