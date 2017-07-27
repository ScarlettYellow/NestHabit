package com.victor.nesthabit.data;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by victor on 7/26/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class MyNestInfo extends DataSupport {
    /**
     * _id : 597431a9f6ded6062862d268
     * name : aaaaaaa
     * desc : aaaa
     * members_limit : 0
     * start_time : 1331856000
     * challenge_days : 1000
     * open : true
     * cover_image :
     * created_time : 1500758313
     * creator : far
     * owner : far
     * members_amount : 1
     * members : [{"username":"far","joined_nests":[{"_id":"59737af7f6ded61e7af0e6b8",
     * "kept_days":0},{"_id":"597431a9f6ded6062862d268","kept_days":0}],
     * "uploaded_musics":["59742ce5f6ded67422b9ffa3"],"alarm_clocks":["597385a6f6ded65832a3c175",
     * "597385aaf6ded65832a3c176"],"avatar":"http://nesthabit.pek3a.qingstor.com/image
     * /581583075164355.png","nickname":"a"}]
     */

    private long id;
    private String myid;
    private String name;
    private String desc;
    private int members_limit;
    private long start_time;
    private int challenge_days;
    private boolean open;
    private String cover_image;
    private long created_time;
    private String creator;
    private String owner;
    private int members_amount;
    private List<UserInfo> members;
    private int day_insist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDay_insist() {
        return day_insist;
    }

    public void setDay_insist(int day_insist) {
        this.day_insist = day_insist;
    }

    public String getMyid() {
        return myid;
    }

    public void setMyid(String myid) {
        this.myid = myid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMembers_limit() {
        return members_limit;
    }

    public void setMembers_limit(int members_limit) {
        this.members_limit = members_limit;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public int getChallenge_days() {
        return challenge_days;
    }

    public void setChallenge_days(int challenge_days) {
        this.challenge_days = challenge_days;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getMembers_amount() {
        return members_amount;
    }

    public void setMembers_amount(int members_amount) {
        this.members_amount = members_amount;
    }

    public List<UserInfo> getMembers() {
        return members;
    }

    public void setMembers(List<UserInfo> members) {
        this.members = members;
    }
}
