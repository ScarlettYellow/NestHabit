package com.victor.nesthabit.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.victor.nesthabit.util.ActivityManager;


/**
 * Created by victor on 7/12/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityManager.getInstance().pushActivity(getActivity());
        initView();
        initEvent();
        if (getPresnter() != null) {
            getPresnter().start();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresnter() != null) {
            getPresnter().unscribe();
        }
    }

    protected abstract BasePresenter getPresnter();

    protected abstract Activity getActivity();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initEvent();

    protected void showToast(String description) {
        Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
    }
}
