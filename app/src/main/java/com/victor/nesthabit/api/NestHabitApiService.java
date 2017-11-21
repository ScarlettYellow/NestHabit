package com.victor.nesthabit.api;

import com.victor.nesthabit.bean.AddResponse;
import com.victor.nesthabit.bean.AlarmInfo;
import com.victor.nesthabit.bean.ChatInfo;
import com.victor.nesthabit.bean.Constants;
import com.victor.nesthabit.bean.DateOfNest;
import com.victor.nesthabit.bean.JoinedNests;
import com.victor.nesthabit.bean.MessageList;
import com.victor.nesthabit.bean.MsgResponse;
import com.victor.nesthabit.bean.MusicInfo;
import com.victor.nesthabit.bean.NestInfo;
import com.victor.nesthabit.bean.PostFileResponse;
import com.victor.nesthabit.bean.PunchInfo;
import com.victor.nesthabit.bean.RegisterResponse;
import com.victor.nesthabit.bean.SendMessageResponse;
import com.victor.nesthabit.bean.UpdateInfo;
import com.victor.nesthabit.bean.UserInfo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by victor on 7/23/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public interface NestHabitApiService {

    //user api
    @POST("1/users")
    Observable<Response<RegisterResponse>> register(@Body RequestBody registerBody);

    @GET("1/login")
    Observable<Response<UserInfo>> login(@Query("username") String username, @Query("password")
            String
            password);

    @GET("1/users/{objectId}")
    Observable<Response<UserInfo>> getUserInfo(@Path("objectId") String objectId);

    @PUT("1/users/{objectId}")
    Observable<Response<UpdateInfo>> editUserInfo(@Path("objectId") String objectId, @Header
            ("X-Bmob-Session-Token") String header, @Body RequestBody body);


    @DELETE("1/user/{username}/session")
    Observable<MsgResponse> logout(@Path("username") String username, @Header
            (Constants.HEADER_AU) String header);


    //nest api
    @POST("1/classes/nest")
    Observable<Response<AddResponse>> addNest(@Body RequestBody body);

    @DELETE("1/classes/nest/{objectId}")
    Observable<MsgResponse> deleteNest(@Path("objectId") String objectId);

    @POST("1/classes/nest/{objectId}")
    Observable<NestInfo> changeNest(@Path("objectId") String objectId, @Body RequestBody body);

    @DELETE("nest/{id}/members/{member_username}")
    Observable<NestInfo> deleteMember(@Path("id") String id, @Path("member_username")
            String membername, @Header(Constants.HEADER_AU) String header);

    @POST("user/{username}/joined_nests")
    Observable<JoinedNests> enterNest(@Path("username") String username, @Body
            RequestBody body, @Header(Constants.HEADER_AU) String header);

    @DELETE("user/{username}/joined_nests")
    Observable<JoinedNests> quitNest(@Path("username") String username, @Body
            RequestBody body, @Header(Constants.HEADER_AU) String header);


    @POST("1/classes/alarm_clock")
    Observable<AlarmInfo> addAlarm(@Body RequestBody body);

    @GET("1/classes/alarm_clock/{objectId}")
    Observable<Response<AlarmInfo>> getAlarmInfo(@Path("Id") String objectId);

    @DELETE("alarm_clock/{id}")
    Observable<MsgResponse> deleteAlarm(@Path("id") String id, @Header(Constants
            .HEADER_AU) String header);

    @PUT("1/classes/alarm_clock/{objectId}")
    Observable<AlarmInfo> changeAlarm(@Path("objectId") String id, @Body RequestBody body);


    @GET("1/classes/nest/{objectId}")
    Observable<Response<NestInfo>> getNestInfo(@Path("objectId") String objectId);

    @GET("user/{username}/nest/{nest_id}/punches")
    Observable<DateOfNest> getDateOfNest(@Path("username") String username, @Path("nest_id")
            String nestid, @Header(Constants.HEADER_AU) String header);

    //punch api
    @POST("1/classes/punch")
    Observable<Response<AddResponse>> punch(@Body RequestBody body);

    @GET("1/classes/punch/{objectId}")
    Observable<Response<PunchInfo>> getPunchInfo(@Path("objectId") String objectId);

    //chat api
    @POST("1/classes/chat")
    Observable<Response<AddResponse>> chat(@Body RequestBody body);

    @GET("1/classes/chat/{objectId}")
    Observable<Response<ChatInfo>> getChatInfo(@Path("objectId") String objectId);

    @GET("music/{id}")
    Observable<MusicInfo> getMusicName(@Path("id") String id, @Header(Constants.HEADER_AU)
            String header);

    @POST("user/{username}/avatar/{name}")
    Observable<UserInfo> postImage(@Path("username") String username, @Path("name")
            String name, @Part MultipartBody.Part file, @Header(Constants.HEADER_AU) String
                                           header);

    @POST("2/file/{filename}")
    Observable<PostFileResponse> postMusic(@Path("filename") String fileName, @Body RequestBody
            file);

    @POST("chat_log")
    Observable<SendMessageResponse> sendMessage(@Body RequestBody body, @Header(Constants
            .HEADER_AU) String
            header);

    @GET("nest/{id}/chat_log")
    Observable<MessageList> getMessageList(@Path("id") String id, @Header(Constants.HEADER_AU)
            String header);
}
