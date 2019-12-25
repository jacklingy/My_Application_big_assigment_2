package com.example.myapplication_big_assigment_2.logAndReg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication_big_assigment_2.Adapter.SessionAdapter;
import com.example.myapplication_big_assigment_2.MainActivity;
import com.example.myapplication_big_assigment_2.R;
import com.example.myapplication_big_assigment_2.entity.SessionVO;
import com.example.myapplication_big_assigment_2.interfa.VolleyCallback;
import com.example.myapplication_big_assigment_2.util.DialogThridUtils;
import com.example.myapplication_big_assigment_2.util.WeiboDialogUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_Login extends AppCompatActivity {
    private Dialog mDialog;
    private Dialog mWeiboDialog;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DialogThridUtils.closeDialog(mDialog);
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    break;
            }
        }
    };
    private AlertDialog.Builder builder;

    private SharedPreferences sharedPreferences;
    private  static String r2;
    private SessionAdapter sessionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__login); final Button button = (Button) findViewById(R.id.btn);


        TextView textView = findViewById(R.id.register);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity_Login.this, MainActivity_SignUp.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setClickable(false);
                button.setText("登录中...");
                final Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        button.setClickable(true);
                        button.setText("登录");
                        // button.setBackgroundColor(Color.argb(0,200,200,200));
                    }
                };
                timer.schedule(timerTask, 2000);


                EditText et_account = findViewById(R.id.account);
                EditText et_password = findViewById(R.id.password);

                final String account = et_account.getText().toString();
                final String password = et_password.getText().toString();


                final TextView mTextView = (TextView) findViewById(R.id.text);
// ...


// Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                //below is the ip address of my server at the current LAN.
                //You'd better change it into your own ip address
                String url = getResources().getString(R.string.url) + "user/login/?account=" + account + "&password=" + password;

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("1")) {
                                    //登录成功！
                                    //传递用户名给MainActivity
//                                    MyApplication myApplication = (MyApplication) getApplication();
//                                    myApplication.setLogin_state(1);
//                                    myApplication.setAccount(account);
//                                    Toast.makeText(getApplicationContext(), "登录成功！loginState为: "
//                                          , Toast.LENGTH_SHORT).show();
                                    sharedPreferences=getSharedPreferences("userInformation",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString("account", account);
                                    editor.putString("password",password);
                                  //  editor.commit();
                                  //  finish();
                                    try {
                                        wait(200);
                                        finish();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    //跳转到下一个页面

                                    /**
                                     * 提前get信息
                                     */
                                    getSessionList(account, new VolleyCallback() {
                                        @Override
                                        public void onSuccess(String result) {
                                            r2=result;
                                            //r2="[{\"sessionContent\":\"xxxx\",\"sessionId\":6,\"talkerId\":6,\"time\":\"2019-12-26 15:20:01\",\"user\":{\"account\":\"postman\",\"id\":6,\"name\":\"test\",\"password\":\"1234\",\"picture\":\"4\"},\"userId\":18},{\"sessionContent\":\"sssss\",\"sessionId\":5,\"talkerId\":3,\"time\":\"2019-12-24 15:19:46\",\"user\":{\"account\":\"baba\",\"description\":\"babababa\",\"id\":3,\"password\":\"1234\",\"picture\":\"3\"},\"userId\":18}]";
                                        }
                                    });

                                    editor.putString("json",r2);
                                    editor.commit();
//                                    try {
//
//                                        List<SessionVO> mySessionList = JSON.parseArray("[{\"sessionContent\":\"xxxx\",\"sessionId\":6,\"talkerId\":6,\"time\":\"2019-12-26 15:20:01\",\"user\":{\"account\":\"postman\",\"id\":6,\"name\":\"test\",\"password\":\"1234\",\"picture\":\"4\"},\"userId\":18},{\"sessionContent\":\"sssss\",\"sessionId\":5,\"talkerId\":3,\"time\":\"2019-12-24 15:19:46\",\"user\":{\"account\":\"baba\",\"description\":\"babababa\",\"id\":3,\"password\":\"1234\",\"picture\":\"3\"},\"userId\":18}]", SessionVO.class);
//                                      //  sessionAdapter = new SessionAdapter(mySessionList);
//                                       // recyclerView.setAdapter(sessionAdapter);
//                                    }catch (Exception e){
//                                        e.printStackTrace();
//                                    }



                                   showDialog();
//                                    Intent intent=new Intent(MainActivity_Login.this, MainActivity.class);
//                                    intent.putExtra("myjson",r2);




//                                    mWeiboDialog = WeiboDialogUtils.createLoadingDialog(MainActivity_Login.this, "加载中...");
//                                    mHandler.sendEmptyMessageDelayed(1, 2000);
//                                    mDialog = DialogThridUtils.showWaitDialog(MainActivity_Login.this, "加载中...", false, true);
//                                    mHandler.sendEmptyMessageDelayed(1, 2000);

                                  //  startActivity(intent);



//                                    Intent intent=new Intent(MainActivity_Login.this, MainActivity.class);
//                                    intent.putExtra("myjson",r2);
//
//
//
//                                    startActivity(intent);
//                                    try{
//                                        Thread.sleep(2000);
//                                    }catch (Exception e ){
//
//                                    }finally {
//                                        startActivity(intent);
//
//                                    }





                                    /********更改登录状态********/
//                                    MyApplication myApplication = (MyApplication) getApplication();
//                                    myApplication.setLogin_state(1);

//                                    LoginState loginState;
//                                    loginState.setState(1);
                                    //此处不用跳转
                                    // Toast.makeText(getApplicationContext(), myApplication.getLogin_state(), Toast.LENGTH_SHORT).show();

                                    //finish();
                                    //Intent intent = new Intent(Activity_login.this, MainActivity.class);
                                    //startActivity(intent);

                                }
                                if (response.contains("0")) {
                                    //登录失败！
                                    Toast.makeText(getApplicationContext(), "登录失败，请重试！", Toast.LENGTH_SHORT).show();

                                }
                                // Display the first 500 characters of the response string.
                                //  mTextView.setText("Response is: "+ response.substring(0,8));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //网络连接失败
                        mTextView.setText("网络连接失败！");
                        error.printStackTrace();
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });

    }//onCreate


    public String getSessionList(String account,final VolleyCallback callback){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.url) + "session/select?account=" + account ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        r2=response;
                        callback.onSuccess(r2);
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
        return r2;


    }


    /******监听发送过来的消息，并立即显示**********/
    private void showDialog() {

        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("登录提示")
                .setMessage("登录成功！").setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        //  Toast.makeText(, "确定按钮", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(MainActivity_Login.this, MainActivity.class);
                        intent.putExtra("myjson",r2);
                        startActivity(intent);
                        dialogInterface.dismiss();
                       // Toast.makeText(getApplicationContext(),"感谢理解！",Toast.LENGTH_LONG).show();

                    }
                });
        builder.create().show();
    }

    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        return loadingDialog;
    }

    public static void closeDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }







}
