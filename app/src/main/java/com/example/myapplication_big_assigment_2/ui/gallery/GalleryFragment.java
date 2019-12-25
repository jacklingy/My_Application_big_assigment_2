package com.example.myapplication_big_assigment_2.ui.gallery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication_big_assigment_2.Main2Activity_test;
import com.example.myapplication_big_assigment_2.MainActivity;
import com.example.myapplication_big_assigment_2.MainActivity_chat;
import com.example.myapplication_big_assigment_2.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private AlertDialog.Builder builder;


    private Button button;
    private EditText editText;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        SharedPreferences sharedPreferences=getContext().getSharedPreferences("userInformation", Context.MODE_PRIVATE);
        final String account=sharedPreferences.getString("account","");

        button=root.findViewById(R.id.btn_addFriend);
        editText=root.findViewById(R.id.friend_account);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String friendAccount=editText.getText().toString();
                String myAccount=account;
                String urlAddFriend=getResources().getString(R.string.url)+"session/add?me_account="+
                        myAccount+"&him_account="+friendAccount;


                RequestQueue queue = Volley.newRequestQueue(getActivity());
                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAddFriend,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.contains("0"))
                                {
                                     Toast.makeText(getContext(), "添加好友失败！", Toast.LENGTH_LONG).show();

                                }
                                else if(response.contains("1"))
                                {

                                    builder = new AlertDialog.Builder(getContext()).setIcon(R.mipmap.ic_launcher).setTitle("添加好友")
                                            .setMessage("好友添加成功，请返回重新登陆！").setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    //ToDo: 你想做的事情
                                                    //  Toast.makeText(, "确定按钮", Toast.LENGTH_LONG).show();
                                                    dialogInterface.dismiss();
                                                    Intent intent=new Intent(getContext(), MainActivity.class);
                                                    startActivity(intent);


                                                }
                                            });
                                    builder.create().show();
                                    Toast.makeText(getContext(), "添加好友成功！\n" +
                                            "请返回消息列表查看！", Toast.LENGTH_SHORT).show();

                                }    else if(response.contains("2"))
                                {
                                    Toast.makeText(getContext(), "请不要重复添加！" ,
                                            Toast.LENGTH_SHORT).show();

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //网络连接失败
                      Toast.makeText(getContext(), "网络连接失败！"+error.toString(),
                              Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);

            }
        });
                return root;
    }
}