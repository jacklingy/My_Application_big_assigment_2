package com.example.myapplication_big_assigment_2.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication_big_assigment_2.Adapter.SessionAdapter;
import com.example.myapplication_big_assigment_2.MainActivity_chat;
import com.example.myapplication_big_assigment_2.R;
import com.example.myapplication_big_assigment_2.entity.SessionVO;
import com.example.myapplication_big_assigment_2.entity.User;
import com.example.myapplication_big_assigment_2.interfa.OnRecyclerViewClickListener;
import com.example.myapplication_big_assigment_2.interfa.VolleyCallback;
import com.example.myapplication_big_assigment_2.util.DialogThridUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private boolean isCreated=false;
    private boolean isGetData = false;

    private View root;
    static String r1;



    private HomeViewModel homeViewModel;
    private  String myJson;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SessionAdapter sessionAdapter;
    private List<SessionVO> sessionVOS = new ArrayList<>();

    private List<SessionVO> sessionVOS2 = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated=true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Toast.makeText(getContext(),"该fragment不可见",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getContext(),"该fragment可见",Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isCreated){

            Toast.makeText(getContext(),"该fragment可见",Toast.LENGTH_SHORT).show();
            //相当于Fragment的onResume，为true时，Fragment已经可见


            SharedPreferences sharedPreferences=getContext().getSharedPreferences("userInformation",
                    Context.MODE_PRIVATE);
            String account=sharedPreferences.getString("account","");
            myJson=sharedPreferences.getString("json","666");


            recyclerView = root.findViewById(R.id.recyclerView);
//        homeViewModel.getSessionVO().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable SessionVO s) {
//                recyclerView.setAdapter(sessionAdapter);
//            }
//        });


//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);

            initSession();

            //  Toast.makeText(getActivity(),"获取到的账号是"+account,Toast.LENGTH_LONG).show();

            //通过获取到的账号，查询到该用户的所有会话列表
            getSessionList(account, new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    r1=result;
                    r1="[{\"sessionContent\":\"xxxx\",\"sessionId\":6,\"talkerId\":6,\"time\":\"2019-12-26 15:20:01\",\"user\":{\"account\":\"postman\",\"id\":6,\"name\":\"test\",\"password\":\"1234\",\"picture\":\"4\"},\"userId\":18},{\"sessionContent\":\"sssss\",\"sessionId\":5,\"talkerId\":3,\"time\":\"2019-12-24 15:19:46\",\"user\":{\"account\":\"baba\",\"description\":\"babababa\",\"id\":3,\"password\":\"1234\",\"picture\":\"3\"},\"userId\":18}]";


                }
            });

            String r3=(String)getActivity().getIntent().getStringExtra("myjson");
            //json信息   Toast.makeText(getActivity(),r3,Toast.LENGTH_LONG).show();

            //   List<SessionVO> mySessionList = JSON.parseArray("[{\"sessionContent\":\"xxxx\",\"sessionId\":6,\"talkerId\":6,\"time\":\"2019-12-26 15:20:01\",\"user\":{\"account\":\"postman\",\"id\":6,\"name\":\"test\",\"password\":\"1234\",\"picture\":\"4\"},\"userId\":18},{\"sessionContent\":\"sssss\",\"sessionId\":5,\"talkerId\":3,\"time\":\"2019-12-24 15:19:46\",\"user\":{\"account\":\"baba\",\"description\":\"babababa\",\"id\":3,\"password\":\"1234\",\"picture\":\"3\"},\"userId\":18}]", SessionVO.class);
            List<SessionVO> mySessionList = JSON.parseArray(r3,SessionVO.class);
            sessionAdapter = new SessionAdapter(getContext(),mySessionList);
            recyclerView.setAdapter(sessionAdapter);


        }else
        {
         //   Toast.makeText(getContext(),"该fragment不可见",Toast.LENGTH_SHORT).show();



        }




    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (enter&& !isGetData){
            //进行刷新操作
         //   Toast.makeText(getContext(),"该fragment可见",Toast.LENGTH_SHORT).show();




            SharedPreferences sharedPreferences=getContext().getSharedPreferences("userInformation",
                    Context.MODE_PRIVATE);
            String account=sharedPreferences.getString("account","");
            myJson=sharedPreferences.getString("json","666");


            recyclerView = root.findViewById(R.id.recyclerView);
//        homeViewModel.getSessionVO().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable SessionVO s) {
//                recyclerView.setAdapter(sessionAdapter);
//            }
//        });


//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);

            initSession();

            //  Toast.makeText(getActivity(),"获取到的账号是"+account,Toast.LENGTH_LONG).show();

            //通过获取到的账号，查询到该用户的所有会话列表
            getSessionList(account, new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    r1=result;
                    r1="[{\"sessionContent\":\"xxxx\",\"sessionId\":6,\"talkerId\":6,\"time\":\"2019-12-26 15:20:01\",\"user\":{\"account\":\"postman\",\"id\":6,\"name\":\"test\",\"password\":\"1234\",\"picture\":\"4\"},\"userId\":18},{\"sessionContent\":\"sssss\",\"sessionId\":5,\"talkerId\":3,\"time\":\"2019-12-24 15:19:46\",\"user\":{\"account\":\"baba\",\"description\":\"babababa\",\"id\":3,\"password\":\"1234\",\"picture\":\"3\"},\"userId\":18}]";


                }
            });

            String r3=(String)getActivity().getIntent().getStringExtra("myjson");
            //json信息   Toast.makeText(getActivity(),r3,Toast.LENGTH_LONG).show();

            //   List<SessionVO> mySessionList = JSON.parseArray("[{\"sessionContent\":\"xxxx\",\"sessionId\":6,\"talkerId\":6,\"time\":\"2019-12-26 15:20:01\",\"user\":{\"account\":\"postman\",\"id\":6,\"name\":\"test\",\"password\":\"1234\",\"picture\":\"4\"},\"userId\":18},{\"sessionContent\":\"sssss\",\"sessionId\":5,\"talkerId\":3,\"time\":\"2019-12-24 15:19:46\",\"user\":{\"account\":\"baba\",\"description\":\"babababa\",\"id\":3,\"password\":\"1234\",\"picture\":\"3\"},\"userId\":18}]", SessionVO.class);
            List<SessionVO> mySessionList = JSON.parseArray(r3,SessionVO.class);
            sessionAdapter = new SessionAdapter(getContext(),mySessionList);
            recyclerView.setAdapter(sessionAdapter);

         //   Toast.makeText(getContext(),"该fragment可见22222222",Toast.LENGTH_SHORT).show();










        }else {
         //   Toast.makeText(getContext(),"该fragment不可见",Toast.LENGTH_SHORT).show();
        }

        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public void initSession() {
        User user = new User(1, "testaccount", "12345678", "男", "微信昵称", "https://www.baidu.com/s?wd=%E4%BB%8A%E6%97%A5%E6%96%B0%E9%B2%9C%E4%BA%8B&tn=SE_Pclogo_6ysd4c7a&sa=ire_dl_gh_logo&rsv_dl=igh_logo_pc", "测试描述");
        for (int i = 0; i < 20; i++) {
            SessionVO s = new SessionVO(R.drawable.ic_launcher_background, 2, user,"最近消息最近消息最近消息", "18:18", 1);
            sessionVOS.add(s);
        }

    }

    //将获取到的json字符串转换为对象；

    public String getSessionList(String account,final VolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = getResources().getString(R.string.url) + "session/select?account=" + account ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        r1=response;
                        callback.onSuccess(r1);
//                        Toast.makeText(getActivity(),getSessionList(r1),Toast.LENGTH_LONG).show();





                    }}, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //网络连接失败
                            Log.d("******************", error.toString());
                            System.out.println(error.toString());
                      //      mTextView.setText("网路连接失败！");
                        }
                    });
// Add the request to the RequestQueue.
                    queue.add(stringRequest);
                    return r1;


        }









        }