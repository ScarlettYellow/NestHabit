package com.victor.nesthabit.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;

public class AlarmReceiver extends BroadcastReceiver {
    private final int SECONDLY = 1, MINUTELY = 2, HOURLY = 3, DAILY = 4;
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    @Override
    public void onReceive(Context context, Intent intent) {


    }

}
