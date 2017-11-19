package com.victor.nesthabit.ui.presenter;

import com.victor.nesthabit.api.NestHabitApi;
import com.victor.nesthabit.bean.AlarmResponse;
import com.victor.nesthabit.bean.AlarmTime;
import com.victor.nesthabit.bean.NestInfo;
import com.victor.nesthabit.ui.base.RxPresenter;
import com.victor.nesthabit.ui.contract.ClockListContract;
import com.victor.nesthabit.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by victor on 7/25/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class ClockListPresenter extends RxPresenter implements ClockListContract.Presenter {
    public static final String TAG = "@victor ClockListPrese";
    private static onAlarmAdded sOnAlarmAdded;
    private ClockListContract.View mView;
    private List<AlarmTime> alarmTimes = new ArrayList<>();

    public ClockListPresenter(ClockListContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    public static void setOnAlarmAdded(onAlarmAdded alarmAdded) {
        sOnAlarmAdded = alarmAdded;
    }

    @Override
    public void start() {
        alarmTimes = new ArrayList<>();
//        alarmTimes = DataSupport.findAll(AlarmTime.class);
//        sOnAlarmAdded.AlarmAddAll(alarmTimes);
    }

    @Override
    public void unscribe() {
        unSubscribe();
    }


    private void cloneMy(AlarmResponse response) {
        boolean c = check(response);
        if (!c) {
            AlarmTime alarmTime = new AlarmTime();
            alarmTime.setSnap(response.isNap());
            alarmTime.setTitle(response.getTitle());
            alarmTime.setReceive_Voice(response.isWilling_music());
            alarmTime.setReceive_text(response.isWilling_text());
            String time = response.getTime();
            String[] str = time.split(",");
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str[0]);
            alarmTime.setHour(Integer.valueOf(m.replaceAll("").trim()));
            m = p.matcher(str[1]);
            alarmTime.setMinute(Integer.valueOf(m.replaceAll("").trim()));
            alarmTime.setWeeks(Utils.getSelectedWeeks(response.repeat));
            alarmTime.setMusic_id(response.getMusic_id());
            alarmTime.setMyid(response.get_id());
            alarmTime.setNestid(response.getBind_to_nest());
            Observable<NestInfo> observable = NestHabitApi.getInstance().getNestInfo(alarmTime
                            .getNestid()
                    , Utils.getHeader());
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NestInfo>() {
                        @Override
                        public void onCompleted() {
                            if (sOnAlarmAdded != null) {
                                sOnAlarmAdded.AlarmAdded(alarmTime);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(NestInfo nestInfo) {
                            alarmTime.setBind_to_nest(nestInfo.getName());
                            alarmTime.save();
                            alarmTimes.add(alarmTime);
                        }
                    });
        }
    }

    private boolean check(AlarmResponse alarmResponse) {
        for (AlarmTime alarmTime : alarmTimes) {
            if (alarmResponse.get_id().equals(alarmTime.getMyid())) {
                return true;
            }
        }
        return false;
    }

    public interface onAlarmAdded {
        void AlarmAdded(AlarmTime alarmTime);

        void AlarmAddAll(List<AlarmTime> alarmTimes);
    }
}
