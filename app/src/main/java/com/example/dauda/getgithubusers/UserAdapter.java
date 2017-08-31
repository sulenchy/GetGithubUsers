package com.example.dauda.getgithubusers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Dauda on 8/30/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_URL = "url";

    private List<Users> users;
    private Context context;

    public UserAdapter(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_all_users, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Users usersList = users.get(position);
        holder.userName.setText(usersList.getUserName());

        Picasso.with(context)
                .load(usersList.getAvatar_url())
                .into(holder.avatar_url);

        holder.profileContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Users usersList = users.get(position);

                Intent skipIntent = new Intent(v.getContext(), UserDetailsActivity.class);
                skipIntent.putExtra(KEY_NAME, usersList.getUserName());
                skipIntent.putExtra(KEY_URL, usersList.getHtml_url());
                skipIntent.putExtra(KEY_IMAGE, usersList.getAvatar_url());
                v.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView userName;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout profileContainer;

        public ViewHolder(View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.username);
            avatar_url = (ImageView) itemView.findViewById(R.id.profilePic);
            html_url = (TextView) itemView.findViewById(R.id.url);
            profileContainer = (LinearLayout) itemView.findViewById(R.id.profilecontainer);
        }

    }
}
