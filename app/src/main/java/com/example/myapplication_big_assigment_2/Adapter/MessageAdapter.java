package com.example.myapplication_big_assigment_2.Adapter;

/**
 *
 * 消息适配器，用于显示所有消息
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication_big_assigment_2.R;
import com.example.myapplication_big_assigment_2.entity.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> messageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar_right;
        TextView content_right;
        TextView content_left;
        ImageView avatar_left;
        LinearLayout linearLayout_left;
        LinearLayout linearLayout_right;


        public ViewHolder(View view) {
            super(view);
            avatar_right = (ImageView) view.findViewById(R.id.avatar_right);
            content_right = (TextView) view.findViewById(R.id.content_right);
            content_left = (TextView) view.findViewById(R.id.content_left);
            avatar_left = (ImageView) view.findViewById(R.id.avatar_left);
            linearLayout_left = (LinearLayout) view.findViewById(R.id.message_left);
            linearLayout_right = (LinearLayout) view.findViewById(R.id.message_right);

        }


    }

    public MessageAdapter(List<Message> mList) {
        messageList = mList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //设置消息格式
        Message message = messageList.get(position);
        if (message.getType() == Message.TYPE_RECEIVED) {
            //类型是接收的，放在左边
            holder.linearLayout_left.setVisibility(View.VISIBLE);
            holder.linearLayout_right.setVisibility(View.GONE);
            holder.content_left.setText(message.getContent());

            holder.avatar_left.setImageResource(R.drawable.flower);

        } else if (message.getType() == Message.TYPE_SENT) {

            //类型是发送的，应该放在右边
            holder.linearLayout_right.setVisibility(View.VISIBLE);
            holder.linearLayout_left.setVisibility(View.GONE);
            holder.content_right.setText(message.getContent());
            holder.avatar_right.setImageResource(R.drawable.flower);

        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    // 添加数据
    public void addData(int position, Message message) {
        // 在list中添加数据，并通知条目加入一条
        messageList.add(position, message);
        // 添加动画
        notifyItemInserted(position);
    }

    // 删除数据，暂时未实现
    public void removeData(int position) {
        messageList.remove(position);
        // 删除动画
        notifyItemRemoved(position);
    }

    public void moveToPresent(RecyclerView recyclerView) {
        recyclerView.smoothScrollToPosition(messageList.size());
    }
}
