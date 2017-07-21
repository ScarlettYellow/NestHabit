package com.victor.nesthabit.ui.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.victor.nesthabit.R;
import com.victor.nesthabit.ui.adapters.MyFragPageAdapter;
import com.victor.nesthabit.ui.base.BaseActivity;
import com.victor.nesthabit.ui.fragments.CommunicateFragment;
import com.victor.nesthabit.ui.fragments.DaKaWallFragment;
import com.victor.nesthabit.ui.fragments.RankTotalFragment;
import com.victor.nesthabit.utils.AppUtils;

import java.lang.reflect.Field;

public class RankActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.support.design.widget.TabLayout tab;
    private android.support.v4.view.ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Activity getActivityToPush() {
        return RankActivity.this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    protected void initView() {
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tab = (TabLayout) findViewById(R.id.tab);
        this.back = (ImageView) findViewById(R.id.back);
        setUpViewPager();
    }

    @Override
    protected void initEvent() {

    }
    private void setUpViewPager() {
        MyFragPageAdapter adapter = new MyFragPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new RankTotalFragment(), "总打卡");
        adapter.addFragment(new CommunicateFragment(), "连续打卡");
        viewpager.setAdapter(adapter);
        tab.setupWithViewPager(viewpager);
        setUpIndicatorWidth(tab, 30, 30);
    }
    private void setUpIndicatorWidth(TabLayout tabLayout, int marginLeft, int marginRight) {
        Class<?> tabLayoutClass = tabLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        LinearLayout layout = null;
        try {
            if (tabStrip != null) {
                layout = (LinearLayout) tabStrip.get(tabLayout);
            }
            for (int i = 0; i < layout.getChildCount(); i++) {
                View child = layout.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    params.setMarginStart( dpToPx(marginLeft));
                    params.setMarginEnd(dpToPx(marginRight));
                }
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * 将dp转换成px
     *
     * @param dp
     * @return
     */
    public  int dpToPx(int dp) {
        return (int) (dp * AppUtils.getAppContext().getResources().getDisplayMetrics().density);
    }
}
