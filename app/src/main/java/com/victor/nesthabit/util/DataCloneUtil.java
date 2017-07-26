package com.victor.nesthabit.util;

import com.victor.nesthabit.data.MyNestInfo;
import com.victor.nesthabit.data.NestInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 7/26/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class DataCloneUtil {
    public static NestInfo cloneMynestToNest(MyNestInfo info) {
        NestInfo nestInfo = new NestInfo();
        nestInfo.setDay_insist(info.getDay_insist());
        nestInfo.setMembers_limit(info.getMembers_limit());
        nestInfo.setStart_time(info.getStart_time());
        nestInfo.setCreated_time(info.getCreated_time());
        nestInfo.setMembers(info.getMembers());
        nestInfo.setChallenge_days(info.getChallenge_days());
        nestInfo.setName(info.getName());
        nestInfo.setDesc(info.getDesc());
        nestInfo.setCover_image(info.getCover_image());
        return nestInfo;
    }

    public static MyNestInfo cloneNestToMyNest(NestInfo info) {
        MyNestInfo nestInfo = new MyNestInfo();
        nestInfo.setDay_insist(info.getDay_insist());
        nestInfo.setMembers_limit(info.getMembers_limit());
        nestInfo.setStart_time(info.getStart_time());
        nestInfo.setCreated_time(info.getCreated_time());
        nestInfo.setMembers(info.getMembers());
        nestInfo.setChallenge_days(info.getChallenge_days());
        nestInfo.setName(info.getName());
        nestInfo.setDesc(info.getDesc());
        nestInfo.setCover_image(info.getCover_image());
        return nestInfo;
    }

    public static List<MyNestInfo> cloneNestToMyNestList(List<NestInfo> info) {
        List<MyNestInfo> reslut = new ArrayList<>();
        if (!info.isEmpty()) {
            for (NestInfo nnestInfo : info) {
                MyNestInfo nestInfo = new MyNestInfo();
                nestInfo.setDay_insist(nnestInfo.getDay_insist());
                nestInfo.setMembers_limit(nnestInfo.getMembers_limit());
                nestInfo.setStart_time(nnestInfo.getStart_time());
                nestInfo.setCreated_time(nnestInfo.getCreated_time());
                nestInfo.setMembers(nnestInfo.getMembers());
                nestInfo.setChallenge_days(nnestInfo.getChallenge_days());
                nestInfo.setName(nnestInfo.getName());
                nestInfo.setDesc(nnestInfo.getDesc());
                nestInfo.setCover_image(nnestInfo.getCover_image());
                reslut.add(nestInfo);
            }
        }
        return reslut;
    }

    public static List<NestInfo> cloneMyNestToNestList(List<MyNestInfo> info) {
        List<NestInfo> reslut = new ArrayList<>();
        if (!info.isEmpty()) {
            for (MyNestInfo nnestInfo : info) {
                NestInfo nestInfo = new NestInfo();
                nestInfo.setDay_insist(nnestInfo.getDay_insist());
                nestInfo.setMembers_limit(nnestInfo.getMembers_limit());
                nestInfo.setStart_time(nnestInfo.getStart_time());
                nestInfo.setCreated_time(nnestInfo.getCreated_time());
                nestInfo.setMembers(nnestInfo.getMembers());
                nestInfo.setChallenge_days(nnestInfo.getChallenge_days());
                nestInfo.setName(nnestInfo.getName());
                nestInfo.setDesc(nnestInfo.getDesc());
                nestInfo.setCover_image(nnestInfo.getCover_image());
                reslut.add(nestInfo);
            }
        }
        return reslut;
    }
}
