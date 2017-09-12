package com.example.dauda.getgithubusers;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_details);



        CircleImageView profileImage = (CircleImageView) findViewById(R.id.profileImage);
        TextView userNameTextView = (TextView) findViewById(R.id.profileName);
        Button shareProfile = (Button) findViewById(R.id.shareButton);
        TextView userUrl = (TextView) findViewById(R.id.profileUrl);

        //adds the horizontal lines in the recyclervview
        userUrl.setPaintFlags(userUrl.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //get intent
        Intent intent = getIntent();

        //get the username from the intent
        final String userName = intent.getStringExtra(UserAdapter.KEY_NAME);

        //gets the image fro intent
        String image = intent.getStringExtra(UserAdapter.KEY_IMAGE);

        //get the profileurl from the intent
        final String profileUrl = intent.getStringExtra(UserAdapter.KEY_URL);
        setTitle(userName);

        //loads the image and set it on the holder.avatar_url using picasso library
        Picasso.with(this)
                .load(image)
                .into(profileImage);


        userNameTextView.setText(userName);
        userUrl.setText(profileUrl);
        //test.setText(userName);


        /**
         * ClickListener onclick the developer profileurl
         */
        userUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = profileUrl;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        /**
         * clciklistener on click the share button
         */
        shareProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer " + userName +
                        " @ " + profileUrl);
                Intent chooser = Intent.createChooser(shareIntent, "Share via");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
    }


}
