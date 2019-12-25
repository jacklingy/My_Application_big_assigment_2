package com.example.myapplication_big_assigment_2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication_big_assigment_2.entity.SessionVO;

public class HomeViewModel extends ViewModel {
    private SessionVO sessionVO;

    private MutableLiveData<String> mText;
    private MutableLiveData<SessionVO> sessionMutableLiveData=new MutableLiveData<SessionVO>();


//    public void initSession() {
//        User user = new User(1, "testaccount", "12345678", "男", "微信昵称", R.drawable.ic_launcher_background, "测试描述");
//
//        sessionVO = new SessionVO(R.drawable.ic_launcher_background, 2, "最近消息最近消息最近消息", "18:18",1);
//
//
//    }
    public HomeViewModel() {
      //  initSession();



        sessionMutableLiveData=new MutableLiveData<>();
        sessionMutableLiveData.setValue(sessionVO);

//
//        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
    }

    public LiveData<SessionVO> getSessionVO() {
        return sessionMutableLiveData;
    }
}