package com.example.user.androidtest;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.androidtest.model.User;

/**
 * Created by User on 20/12/2018.
 */

public class ListUserAdapter extends PagedListAdapter<User, ListUserAdapter.UserViewHolder> {

    OnItemClickListener listener;
    ListUserAdapter(){
        super(Diff_Callback);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);
        return new UserViewHolder(item);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = getItem(position);

        if(user != null){
            holder.textName.setText(user.getName());
            holder.textAddress.setText(user.getAddress());
        }
    }

    public static DiffUtil.ItemCallback<User> Diff_Callback = new  DiffUtil.ItemCallback<User>(){
        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView textName;
        private TextView textAddress;

        public UserViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textAddress = (TextView) itemView.findViewById(R.id.textAddress);
        }

    }

    public interface OnItemClickListener{
        void onClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
