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

    /**
     * the class constructor
     * @param users
     * @param context
     */
    public UserAdapter(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    /**
     * create a ViewHolder to hold R.layout.activity_all_users
     * @param parent
     * @param viewType
     * @return View v
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_all_users, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        ///gets the position of the a record from the arraylis users
        final Users usersList = users.get(position);

        //get the Username from the UserList and set it into the userName textview
        holder.userName.setText(usersList.getUserName());

        //loads the image and set it on the holder.avatar_url using picasso library
        Picasso.with(context)
                .load(usersList.getAvatar_url())
                .into(holder.avatar_url);


        holder.profileContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the position of the item clicked on the recycleview
                Users usersList = users.get(position);

                //creates an intent and passed three data via putextra
                Intent skipIntent = new Intent(v.getContext(), UserDetailsActivity.class);
                skipIntent.putExtra(KEY_NAME, usersList.getUserName());
                skipIntent.putExtra(KEY_URL, usersList.getHtml_url());
                skipIntent.putExtra(KEY_IMAGE, usersList.getAvatar_url());
                v.getContext().startActivity(skipIntent);
            }
        });

    }

    /**
     * count the number of records in the arraylist users
     * @return users.size
     */
    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * ViewHolder Class hold view on the recyclerView
     */
    public class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView userName;
        public ImageView avatar_url;
        public TextView html_url;
        public LinearLayout profileContainer;

        public ViewHolder(View itemView) {
            super(itemView);

            userName = (TextView) itemView.findViewById(R.id.username);
            avatar_url = (ImageView) itemView.findViewById(R.id.profilePic);
            profileContainer = (LinearLayout) itemView.findViewById(R.id.profilecontainer);
        }

    }
}
