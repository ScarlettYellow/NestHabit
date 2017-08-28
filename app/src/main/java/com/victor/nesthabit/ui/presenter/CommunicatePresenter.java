package com.victor.nesthabit.ui.presenter;

import android.util.Log;

import com.victor.nesthabit.api.UserApi;
import com.victor.nesthabit.bean.MessageList;
import com.victor.nesthabit.bean.SendMessageResponse;
import com.victor.nesthabit.ui.adapter.CommunicateAdapter;
import com.victor.nesthabit.ui.base.RxPresenter;
import com.victor.nesthabit.ui.contract.CommunicateContract;
import com.victor.nesthabit.util.RxUtil;
import com.victor.nesthabit.util.Utils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by victor on 8/22/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class CommunicatePresenter extends RxPresenter implements CommunicateContract.Presenter {

    public static final String TAG = "@victor CommPresenter";
    private CommunicateContract.View mView;

    public CommunicatePresenter(CommunicateContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        String key = Utils.createAcacheKey("get-message-list", mView.getNestId());
        Observable<MessageList> observable = UserApi.getInstance().getMessageList(mView.getNestId
                (), Utils.getHeader()).compose(RxUtil.<MessageList>rxCacheListHelper(key));
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MessageList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MessageList messageList) {
                        List<SendMessageResponse> responses = messageList.chat_log;
                        Observable.from(responses).map(new Func1<SendMessageResponse,
                                SendMessageResponse>() {
                            @Override
                            public SendMessageResponse call(SendMessageResponse
                                                                    sendMessageResponse) {
                                if (sendMessageResponse.username.equals(Utils.getUsername())) {
                                    sendMessageResponse.type = CommunicateAdapter.RIGHT_TYPR;
                                } else
                                    sendMessageResponse.type = CommunicateAdapter.LEFT_TYPE;
                                Log.d(TAG, "time: " + sendMessageResponse.creat_time + " ");
//                                sendMessageResponse.time *= 1000;
                                return sendMessageResponse;
                            }
                        }).toSortedList(new Func2<SendMessageResponse, SendMessageResponse,
                                Integer>() {

                            @Override
                            public Integer call(SendMessageResponse sendMessageResponse,
                                                SendMessageResponse sendMessageResponse2) {
                                if (sendMessageResponse.creat_time >=
                                        sendMessageResponse2.creat_time) {
                                    return 1;
                                }
                                return -1;
                            }
                        }).subscribe(new Observer<List<SendMessageResponse>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(List<SendMessageResponse>
                                                       sendMessageResponses) {
                                mView.addAll(sendMessageResponses);
                            }
                        });
                    }
                });
        addSubscribe(subscription);
    }

    @Override
    public void unscribe() {
        unSubscribe();
    }

    @Override
    public void sendMessage() {

        Observable<SendMessageResponse> observable = UserApi.getInstance().sendMessage(mView
                        .getMessage(), System.currentTimeMillis(),
                mView.getNestId(), Utils.getHeader());
        Subscription subscription = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SendMessageResponse>() {
                    @Override
                    public void onCompleted() {
                        mView.setEditText("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast("发送失败");

                    }

                    @Override
                    public void onNext(SendMessageResponse sendMessageResponse) {
                        sendMessageResponse.type = CommunicateAdapter.RIGHT_TYPR;
                        mView.addItem(sendMessageResponse);
                    }
                });
        addSubscribe(subscription);
    }
}
