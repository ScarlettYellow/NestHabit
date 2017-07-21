package com.victor.nesthabit.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.victor.nesthabit.R;
import com.victor.nesthabit.ui.base.BaseActivity;
import com.victor.nesthabit.ui.contracts.BirdCageContract;
import com.victor.nesthabit.utils.ActivityManager;
import com.victor.nesthabit.view.CircleImageView;
import com.victor.nesthabit.view.SwitchButton;

public class NestGroupDetailActivity extends BaseActivity {

    private View toolbar;
    private android.widget.EditText name;
    private android.widget.RelativeLayout memberlayout;
    private android.widget.EditText day;
    private android.widget.TextView starttime;
    private android.widget.RelativeLayout starttimelayout;
    private com.victor.nesthabit.view.SwitchButton limitamounttoogle;
    private android.widget.EditText amount;
    private com.victor.nesthabit.view.SwitchButton opentoogle;
    private android.widget.TextView quit;
    private android.widget.TextView loginway;
    private android.widget.RelativeLayout invitationtoplayout;
    private CardView remind;
    private com.victor.nesthabit.view.CircleImageView qq;
    private com.victor.nesthabit.view.CircleImageView qzone;
    private com.victor.nesthabit.view.CircleImageView wtchat;
    private com.victor.nesthabit.view.CircleImageView moment;
    private ImageView back;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Activity getActivityToPush() {
        return NestGroupDetailActivity.this;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nest_group_detail;
    }

    @Override
    protected void initView() {
        this.moment = (CircleImageView) findViewById(R.id.moment);
        this.wtchat = (CircleImageView) findViewById(R.id.wtchat);
        this.qzone = (CircleImageView) findViewById(R.id.qzone);
        this.qq = (CircleImageView) findViewById(R.id.qq);
        this.invitationtoplayout = (RelativeLayout) findViewById(R.id.invitation_top_layout);
        this.loginway = (TextView) findViewById(R.id.login_way);
        this.quit = (TextView) findViewById(R.id.quit);
        this.opentoogle = (SwitchButton) findViewById(R.id.open_toogle);
        this.amount = (EditText) findViewById(R.id.amount);
        this.limitamounttoogle = (SwitchButton) findViewById(R.id.limit_amount_toogle);
        this.starttimelayout = (RelativeLayout) findViewById(R.id.start_time_layout);
        this.starttime = (TextView) findViewById(R.id.start_time);
        this.day = (EditText) findViewById(R.id.day);
        this.memberlayout = (RelativeLayout) findViewById(R.id.member_layout);
        this.name = (EditText) findViewById(R.id.name);
        this.toolbar = findViewById(R.id.toolbar);
        remind = (CardView) findViewById(R.id.layout_four);
        back = (ImageView) (toolbar.findViewById(R.id.back));
        title = (TextView) (toolbar.findViewById(R.id.title_text));
    }

    @Override
    protected void initEvent() {
        memberlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.startActivity(NestGroupDetailActivity.this, MemberListActivity
                        .class);
            }
        });
        starttimelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.startActivity(NestGroupDetailActivity.this, ChooseActivity.class);
            }
        });
        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.startActivity(NestGroupDetailActivity.this, RemindFriendActivity
                        .class);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.finishActivity(NestGroupDetailActivity.this);
            }
        });

    }
}
