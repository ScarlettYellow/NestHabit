package com.victor.nesthabit.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.victor.nesthabit.api.NestHabitApi;
import com.victor.nesthabit.bean.NestInfo;
import com.victor.nesthabit.db.NestDao;
import com.victor.nesthabit.db.NestHabitDataBase;
import com.victor.nesthabit.util.AppUtils;
import com.victor.nesthabit.util.NetWorkBoundUtils;

import retrofit2.Response;
import rx.Observable;

/**
 * @author victor
 * @date 11/18/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

public class NestRepository {

    private final NestHabitApi mNestHabitApi;
    private final NestDao mNestDao;


    private NestRepository(Context context) {
        mNestHabitApi = NestHabitApi.getInstance();
        mNestDao = NestHabitDataBase.getInstance(context).nestDao();
    }

    private static NestRepository instance;

    public static NestRepository getInstance() {
        if (instance == null) {
            instance = new NestRepository(AppUtils.getAppContext());
        }
        return instance;
    }

    public void loadNestInfo(String objectId, NetWorkBoundUtils.CallBack<NestInfo> callBack) {
        new NetWorkBoundUtils<NestInfo, NestInfo>(callBack) {
            @Override
            protected void saveCallResult(@NonNull NestInfo item) {
                mNestDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable NestInfo data) {
                return data == null;
            }

            @NonNull
            @Override
            protected Observable<NestInfo> loadFromDb() {
                return Observable.just(mNestDao.loadNestInfo(objectId));
            }

            @NonNull
            @Override
            protected Observable<Response<NestInfo>> createCall() {
                return mNestHabitApi.getNestInfo(objectId);
            }
        };
    }
}
