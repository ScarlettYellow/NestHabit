package com.victor.nesthabit.api;

import android.util.Log;

import com.victor.nesthabit.bean.AlarmInfo;
import com.victor.nesthabit.bean.ChatInfo;
import com.victor.nesthabit.bean.Constants;
import com.victor.nesthabit.bean.NestInfo;
import com.victor.nesthabit.bean.PunchInfo;
import com.victor.nesthabit.bean.UserInfo;
import com.victor.nesthabit.util.AppUtils;
import com.victor.nesthabit.util.GetFileMimeUtil;
import com.victor.nesthabit.util.PrefsUtils;
import com.victor.nesthabit.util.Utils;
import com.victor.nesthabit.util.safe.Base64Cipher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by victor on 7/23/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class JsonRequestBody {

    private static final MediaType sMediaType = MediaType.parse("application/json");

    public static RequestBody getJsonRegister(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            password = Base64Cipher.encrypt(password);
            jsonObject.put("avatar", "");
            jsonObject.put("joined_nests", new JSONArray());
            jsonObject.put("uploaded_musics", new JSONArray());
            jsonObject.put("alarm_clocks", new JSONArray());
            jsonObject.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("@victor", jsonObject.toString());
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getUserInfo(UserInfo userInfo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", userInfo.getUsername());
            jsonObject.put("avatar", userInfo.getAvatar());
            jsonObject.put("joined_nests", userInfo.getJoined_nests() == null ? new JSONArray()
                    : new JSONArray(userInfo.getJoined_nests()));
            jsonObject.put("uploaded_musics", userInfo.getUploaded_musics() == null ? new
                    JSONArray()
                    : new JSONArray(userInfo.getUploaded_musics()));
            jsonObject.put("alarm_clocks", userInfo.getAlarm_clocks() == null ? new JSONArray()
                    : new JSONArray(userInfo.getAlarm_clocks()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("@victor", jsonObject.toString());
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getJsonLogin(String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            password = Base64Cipher.encrypt(password);
            jsonObject.put("password", password);
            jsonObject.put("client_id", "android_client_87542701");
            jsonObject.put("client_secret", "385trd4m");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getAddNest(NestInfo nestInfo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", nestInfo.getName());
            jsonObject.put("desc", nestInfo.getDesc());
            jsonObject.put("members_limit", nestInfo.getMembers_limit());
            jsonObject.put("start_time", nestInfo.getStart_time());
            jsonObject.put("challenge_days", nestInfo.getChallenge_days());
            jsonObject.put("open", nestInfo.isOpen());
            jsonObject.put("cover_image", "");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("userId", PrefsUtils.getValue(AppUtils.getAppContext(), Constants
                    .USER_OBJEDCTID, null));
            jsonObject1.put("kept_days", 0);
            jsonObject1.put("constant_days", 0);
            jsonObject1.toJSONArray(new JSONArray());
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject1);
            jsonObject.put("members", jsonArray);
            jsonObject.put("members_amount", 1);
            jsonObject.put("owner", Utils.getUsername());
            jsonObject.put("chatlogs", new JSONArray());
            jsonObject.put("punchlogs", new JSONArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("@victor", "add: " + jsonObject.toString());
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getNestInfo(NestInfo nestInfo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", nestInfo.getName());
            jsonObject.put("desc", nestInfo.getDesc());
            jsonObject.put("members_limit", nestInfo.getMembers_limit());
            jsonObject.put("start_time", nestInfo.getStart_time());
            jsonObject.put("challenge_days", nestInfo.getChallenge_days());
            jsonObject.put("open", nestInfo.isOpen());
            jsonObject.put("cover_image", nestInfo.getCover_image());
            JSONArray jsonArray = new JSONArray();
            for (NestInfo.MembersBean bean : nestInfo.getMembers()) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("userId", bean.getUserId());
                jsonObject1.put("kept_days", bean.getKept_days());
                jsonObject1.put("constant_days", bean.getConstant_days());
                jsonObject1.toJSONArray(new JSONArray());
                jsonArray.put(jsonObject1);
            }
            jsonObject.put("members", jsonArray);
            jsonObject.put("members_amount", 1);
            jsonObject.put("owner", Utils.getUsername());
            jsonObject.put("chatlogs", nestInfo.getChatlogs() == null ? new JSONArray() : new
                    JSONArray(nestInfo.getChatlogs()));
            jsonObject.put("punchlogs", nestInfo.getPunchlogs() == null ? new JSONArray() : new
                    JSONArray(nestInfo.getPunchlogs()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(sMediaType, jsonObject.toString());
    }


    public static RequestBody getNest(String[] id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nests", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getAlarm(AlarmInfo alarmInfo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", alarmInfo.getTitle());
            jsonObject.put("hour", alarmInfo.getHour());
            jsonObject.put("minute", alarmInfo.getMinute());
            jsonObject.put("repeat", new JSONArray(alarmInfo.getRepeat()));
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("filename", alarmInfo.getMusic().getFilename());
            jsonObject1.put("cdn", alarmInfo.getMusic().getCdn());
            jsonObject1.put("url", alarmInfo.getMusic().getUrl());
            jsonObject.put("music", jsonObject1);
            jsonObject.put("nap", alarmInfo.isNap());
            jsonObject.put("volume", alarmInfo.getVolume());
            jsonObject.put("vibrate", alarmInfo.isVibrate());
            jsonObject.put("bind_to_nest", alarmInfo.getBind_to_nest());
            jsonObject.put("willing_music", alarmInfo.isWilling_music());
            jsonObject.put("willing_text", alarmInfo.isWilling_text());
            jsonObject.put("owner", Utils.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getPunchInfo(PunchInfo punchInfo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", punchInfo.getUserId());
            jsonObject.put("contents", punchInfo.getContents());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getChatInfo(ChatInfo chatInfo) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", chatInfo.getUserId());
            jsonObject.put("contents", chatInfo.getContents());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getCommunicationItem(String value, long time, String targetnest) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("value", value);
            jsonObject.put("creat_time", time);
            jsonObject.put("target_nest", targetnest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestBody.create(sMediaType, jsonObject.toString());
    }

    public static RequestBody getFile(File file) {
        return RequestBody.create(MediaType.parse(GetFileMimeUtil.getFileMime(file)), file);
    }
}
