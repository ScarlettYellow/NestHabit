package com.victor.nesthabit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.victor.nesthabit.R;
import com.victor.nesthabit.adapters.RecordListAdapter;
import com.victor.nesthabit.base.BaseActivity;
import com.victor.nesthabit.data.RecordItem;
import com.victor.nesthabit.fragments.RecordFragment;
import com.victor.nesthabit.utils.ActivityManager;

import org.litepal.crud.DataSupport;

import java.util.List;

public class RecordActivity extends BaseActivity {

    private View toolbarRecord;
    private RecyclerView record_list;
    private RecordListAdapter mRecordListAdapter;
    private List<RecordItem> mRecordItems;
    private FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected Activity getActivityToPush() {
        return RecordActivity.this;
    }

    @Override
    protected void initView() {
        toolbarRecord = (View) findViewById(R.id.toolbar_record);
        record_list = (RecyclerView) findViewById(R.id.record_list);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.add_new_record);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecordActivity.this);
        record_list.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        mRecordItems = DataSupport.findAll(RecordItem.class);
        mRecordListAdapter = new RecordListAdapter(RecordActivity.this, mRecordItems);
        record_list.setAdapter(mRecordListAdapter);
    }

    private void initEvent() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                RecordFragment recordFragment = new RecordFragment();
                recordFragment.show(transaction, "record");
            }
        });
    }

}
