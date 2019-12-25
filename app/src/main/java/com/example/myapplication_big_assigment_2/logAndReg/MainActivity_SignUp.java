package com.example.myapplication_big_assigment_2.logAndReg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication_big_assigment_2.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__sign_up);
        final Button button = (Button) findViewById(R.id.btn_reg);
        // ImmersionBar.with(this).statusBarDarkFont(true).init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button.setClickable(false);
                button.setText("注册中...");

                try {
                    wait(1000);

                } catch (Exception e) {

                }
                button.setClickable(true);
                button.setText("注册");
                EditText et_account = findViewById(R.id.account_reg);
                EditText et_password = findViewById(R.id.password_reg);

                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                if (account.length() < 6) {
                    Toast.makeText(MainActivity_SignUp.this, "请输入6位以上账号！", Toast.LENGTH_LONG).show();

                } else {


                    final TextView mTextView = (TextView) findViewById(R.id.text);
// ...


// Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String url = getResources().getString(R.string.url) + "user/register?account=" + account + "&password=" + password;

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.contains("1")) {
                                        //注册成功！
                                        Toast.makeText(getApplicationContext(), "注册成功！马上带您去登录！", Toast.LENGTH_SHORT).show();
                                        //跳转到下一个页面


                                        Timer timer = new Timer();
                                        TimerTask timerTask = new TimerTask() {
                                            @Override
                                            public void run() {
                                                button.setClickable(true);
                                                button.setText("注册");
                                                finish();
                                            }
                                        };
                                        timer.schedule(timerTask, 1500);
                                    }
                                    if (response.contains("0")) {
                                        //登录失败！
                                        Toast.makeText(getApplicationContext(), "注册失败，请重试！", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //网络连接失败
                            Log.d("******************", error.toString());
                            System.out.println(error.toString());
                            mTextView.setText("网路连接失败！");
                        }
                    });
                    queue.add(stringRequest);
                }
            }//click
        });
    }
}