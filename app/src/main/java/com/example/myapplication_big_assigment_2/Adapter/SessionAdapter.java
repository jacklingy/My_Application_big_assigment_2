package com.example.myapplication_big_assigment_2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication_big_assigment_2.MainActivity_chat;
import com.example.myapplication_big_assigment_2.R;
import com.example.myapplication_big_assigment_2.entity.SessionVO;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {
    private Context mContext;
    private List<SessionVO> sessionVOList;



    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout sessionLayout;
        ImageView picture;
        TextView name;
        TextView latestMessage;
        TextView time;


        public ViewHolder(View view) {
            super(view);
            picture = (ImageView) view.findViewById(R.id.picture);
            name = (TextView) view.findViewById(R.id.name);
            latestMessage = (TextView) view.findViewById(R.id.latestMessage);
            time = (TextView) view.findViewById(R.id.time);
            sessionLayout = (LinearLayout) view.findViewById(R.id.main_session);


        }
    }

    public SessionAdapter(Context context, List<SessionVO> mList) {
        this.mContext = context;
        sessionVOList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_item, parent,
                false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SessionVO sessionVO = sessionVOList.get(position);
        holder.name.setText(sessionVO.getUser().getName());
        holder.latestMessage.setText(sessionVO.getSessionContent());
        holder.time.setText(sessionVO.getTime());
        holder.picture.setImageResource(R.drawable.flower);
        holder.sessionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传递聊天双方的信息给ChatActivity

                Intent intent = new Intent(mContext,MainActivity_chat.class);
                intent.putExtra("me_id", sessionVO.getUserId());
                intent.putExtra("him_id", sessionVO.getTalkerId());
                    mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sessionVOList.size();
    }

    // 添加数据
    public void addSession(int position, SessionVO sessionVO) {
        // 在list中添加数据，并通知条目加入一条
        sessionVOList.add(position, sessionVO);
        // 添加动画
        notifyItemInserted(position);
    }

    // 删除数据
    public void removeSession(int position) {
        sessionVOList.remove(position);
        // 删除动画
        notifyItemRemoved(position);
    }

    //回到顶部
    public void moveToTop(RecyclerView recyclerView) {
        recyclerView.smoothScrollToPosition(0);
    }
}
