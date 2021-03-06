package com.victor.nesthabit.ui.contract;

import com.victor.nesthabit.bean.AlarmTime;
import com.victor.nesthabit.ui.base.BasePresenter;
import com.victor.nesthabit.ui.base.Baseview;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by victor on 7/22/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public interface AddAlarmContract {
    interface View extends Baseview<Presenter> {
        void setEditToobar();


        void clearText();

        String getIntentId();

        String getMusicUri();

        int getVolume();

        boolean isVibrate();

        String getNestid();

        String getSeletedHour();

        void setSeletedHour(String hour);


        String getSeletedMinute();

        void setSeletedMinute(String minute);

        List<Integer> getSeletedWeek();

        void setSelectedWeek(List<Integer> selectedWeek);

        String getEditTitle();

        void setEditTitle(String titletext);

        void setEditTitleError();

        String getNestName();

        void setNestname(String Nestname);

        void setNestError();

        String getMusic();

        void setMusic(String name);

        void setMusicFile(File file);

        boolean getSnap();

        void setSnap(boolean isSnap);

        boolean getVoice();

        void setVoice(boolean isVoice);

        boolean getRemindText();

        void setRemindText(boolean isRemindText);

        void finishActivity();


        void setMusicError();
    }

    interface Presenter extends BasePresenter {
        void finish();
    }
}

