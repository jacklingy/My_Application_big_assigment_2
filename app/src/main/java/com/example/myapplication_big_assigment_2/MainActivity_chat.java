package com.example.myapplication_big_assigment_2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication_big_assigment_2.Adapter.MessageAdapter;
import com.example.myapplication_big_assigment_2.entity.Message;
import com.example.myapplication_big_assigment_2.entity.MessageRec;
import com.example.myapplication_big_assigment_2.entity.MessageReceivedList;
import com.google.android.material.navigation.NavigationView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 需要本人信息和对方信息，建立一个会话
 */

public class MainActivity_chat extends AppCompatActivity {

    private Timer timer;


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private RecyclerView recyclerView;
    private List<Message> messageList = new ArrayList<>();
    private MessageAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Button btn_send;
    private EditText editText;
    private ImageView avtar2;
    private Message messageR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        initMessage();
        Intent intent = getIntent();

//        final String me_id="18";
//        final String him_id="1";

        new Thread()  {


            @Override
            public void run() {
                //这里写入子线程需要做的工作


                timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // (1) 使用handler发送消息
                        receiveMessage();

                    }
                },0,1000);//每隔一秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行


            }
        }.start();


        final int me_id = intent.getIntExtra("me_id",0);
        final int him_id = intent.getIntExtra("him_id",0);

//        Toast.makeText(MainActivity_chat.this, String.valueOf(me_id), Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity_chat.this, String.valueOf(him_id), Toast.LENGTH_LONG).show();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //将消息队列加载到屏幕上
        adapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(adapter);

        btn_send = (Button) findViewById(R.id.btn_send);
        editText = (EditText) findViewById(R.id.edit);

        //非空判断

        btn_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String present_message = editText.getText().toString();
                // Log.d("1:",present_message);
                if (present_message.length() != 0) {
                    Message presentMessage = new Message(present_message,me_id,
                           him_id, 0);
                    //  Toast.makeText(getApplicationContext(), presentMessage.getContent(), Toast.LENGTH_SHORT).show();
                    sendMessage(presentMessage);

                    //获取回复，参数为发送的会话
                    //getReply(present_message);
                    //sendMessage(presentMessage);
                    //清空内容
                    editText.setText("");
                } else {
                    //  Toast.makeText(getApplicationContext(), "空！", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    //初始化消息，读取两者间的消息记录；
    public void initMessage() {

        Intent intent = getIntent();
        final int me_id=intent.getIntExtra("me_id",0);
        final int him_id=intent.getIntExtra("him_id",0);




        String strRec=getResources().getString(R.string.url)+"session/record?me_id="
                +me_id+"&him_id="
                +him_id;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strRec,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //将获取到的回复发送到列表中
                        //

                        List<MessageRec> messageRec=JSON.parseArray(response,MessageRec.class);

                        for (int i=messageRec.size()-1;i>=0;i--){
                            Message message=new Message(messageRec.get(i).getContent(),me_id,him_id,
                                    messageRec.get(i).getState());
                            //messageList.add(message);
                            adapter.addData(messageList.size(), message);
                        }


                        adapter.moveToPresent(recyclerView);






//                        MessageReceivedList messageReceivedList=JSON
//                                .parseObject(response,MessageReceivedList.class);
//                        if (messageReceivedList.getCode()==0)//0-目前没有发过来的消息；
//                        {
//                            Toast.makeText(MainActivity_chat.this,"消息已经读取了",Toast.LENGTH_LONG).show();
//
//
//                        }
//                        else  if (messageReceivedList.getCode()==1)    //1-收到新的消息
//                                Toast.makeText(MainActivity_chat.this,messageReceivedList
//                                        .getMessages().get(0).getContent(),Toast.LENGTH_LONG).show();

//                            for (int i=0;i<messageReceivedList.getMessages().size();i++){
//                                messageR=new Message(messageReceivedList.getMessages()
//                                        .get(i).getContent(),me_id,him_id,Message.TYPE_RECEIVED);
//                                adapter.addData(messageList.size(), messageR);
//                                adapter.moveToPresent(recyclerView);
//
//                            }




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //网络连接失败
                Toast.makeText(getApplicationContext(), "网络连接失败！"+
                        error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
// Add the request to the RequestQueue.






//        Message m1 = new Message("hi好", 18,
//                1, 1);
//        m1.setType(Message.TYPE_RECEIVED);//接收
//        messageList.add(m1);
//        Message m2 = new Message("hi", 18,
//                1, 0);
//        m2.setType(Message.TYPE_SENT);//发送
//        messageList.add(m2);
//        sendMessage(m1);
//        sendMessage(m2);

    }
    //接受消息
    //新开一个线程，一直侦听看是否有发送给自己的消息，有就加入消息列表；
    public void receiveMessage(){
        Intent intent = getIntent();
        final int me_id=intent.getIntExtra("me_id",0);
        final int him_id=intent.getIntExtra("him_id",0);




        String strRec=getResources().getString(R.string.url)+"session/receiveOne?me_id="
                    +me_id+"&him_id="
                    +him_id;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, strRec,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //将获取到的回复发送到列表中
                            //
                            MessageReceivedList messageReceivedList=JSON
                                    .parseObject(response,MessageReceivedList.class);
                            if (messageReceivedList.getCode()==0)//0-目前没有发过来的消息；
                            {
                          //      Toast.makeText(MainActivity_chat.this,"消息已经读取了",Toast.LENGTH_LONG).show();


                            }
                            else  if (messageReceivedList.getCode()==1)    //1-收到新的消息
//                                Toast.makeText(MainActivity_chat.this,messageReceivedList
//                                        .getMessages().get(0).getContent(),Toast.LENGTH_LONG).show();

                                for (int i=0;i<messageReceivedList.getMessages().size();i++){
                                    messageR=new Message(messageReceivedList.getMessages()
                                            .get(i).getContent(),me_id,him_id,Message.TYPE_RECEIVED);
                                    adapter.addData(messageList.size(), messageR);
                                    adapter.moveToPresent(recyclerView);

                                }




                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //网络连接失败
                    Toast.makeText(getApplicationContext(), "网络连接失败！"+
                            error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
         queue.add(stringRequest);
// Add the request to the RequestQueue.

    }




    //发送方
    public void sendMessage(Message message) {

        //发送方头像是确定的
        //参数为 要发送的消息的位置 即当前位置，  要发的消息本身
        adapter.addData(messageList.size(), message);
        //跳转到最最后一条消息
        adapter.moveToPresent(recyclerView);
        int senderId = message.getMe_id();
        int receiverId = message.getHim_id();
        String content = message.getContent();
        String urlSent = getResources().getString(R.string.url) + "session/send?senderId=" + senderId +
                "&receiverId=" + receiverId + "&content=" + content;

        //发送请求并获取传回的结果
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSent,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("OK"))
                        {}
                         //   Toast.makeText(MainActivity_chat.this, "消息发送成功！", Toast.LENGTH_LONG).show();
                        //将获取到的回复发送到列表中
                        //并改变头像位置

//
//                        MessageSend messageSend = jsonToBean(response);
//                        Message message_recv = new Message(messageSend.getResult().getText(), R.drawable.bing);
//                        message_recv.setType(Message.TYPE_RECEIVED);
//                        sendMessage(message_recv);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //网络连接失败
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


    //接收方
    public void sendMessage2(Message message) {

        //发送方头像是确定的
        //参数为 要发送的消息的位置 即当前位置，  要发的消息本身
        //  adapter.getItemCount();
        adapter.addData(messageList.size(), message);
        //跳转到最最后一条消息
        adapter.moveToPresent(recyclerView);

    }

    public void receiveMessage(Message message) {

        //收消息，改变元素的属性


        adapter.addData(messageList.size(), message);
        adapter.moveToPresent(recyclerView);


    }

    //参数为json字符串，返回MessageSend对象
    public MessageSend jsonToBean(String jsonStr) {

        MessageSend messageSend = JSON.parseObject(jsonStr, new TypeReference<MessageSend>() {
        });
        return messageSend;
    }

}
